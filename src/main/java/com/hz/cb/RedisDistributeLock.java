package com.hz.cb;

import org.junit.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.text.MessageFormat;

/**
 * @Description redis分布式锁
 * @Date 2019/6/22 15:03
 * @Created by CZB
 */
public class RedisDistributeLock {

    String redisAddr;

    int port;

    private Jedis jedis;

    public String getRedisAddr() {
        return redisAddr;
    }

    public void setRedisAddr(String redisAddr) {
        this.redisAddr = redisAddr;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public RedisDistributeLock(String redisAddr, int port) {
        this.redisAddr = redisAddr;
        this.port = port;
    }

    public Jedis getClient() {

        if (jedis == null) {
            synchronized (RedisDistributeLock.class) {
                if (jedis == null) {
                    jedis = new Jedis(redisAddr, port);
                }
            }
        }
        return jedis;
    }

    /**
     * 获取锁
     * EX second ：设置键的过期时间为 second 秒。 SET key value EX second 效果等同于 SETEX key second value 。
     * PX millisecond ：设置键的过期时间为 millisecond 毫秒。 SET key value PX millisecond 效果等同于 PSETEX key millisecond value 。
     * NX ：只在键不存在时，才对键进行设置操作。 SET key value NX 效果等同于 SETNX key value 。
     * XX ：只在键已经存在时，才对键进行设置操作。
     * @param lockName
     * @param expire 失效时间 单位秒
     * @return
     */
    public boolean getLock(String lockName, int expire) {
        Jedis jedis = getClient();
        SetParams params = new SetParams();
        params.nx();
        params.ex(expire);
        String aTrue = jedis.set(lockName, "true", params);
        return "OK".equalsIgnoreCase(aTrue);
    }

    /**
     * 获取锁
     * @param lockName
     * @return
     */
    public boolean getLock(String lockName) {
        return getLock(lockName, 2 * 3600);
    }

    @Deprecated
    public boolean getLockPrimary(String lockName) {
        Jedis jedis = getClient();
        boolean locked = false;
        Long status;
        try {
            status = jedis.setnx(lockName, "true");
        } finally {
            //设置2个小时的失效时间，避免宕机导致锁无法释放
            try {
                jedis.expire(lockName, 2 * 3600);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (status == 0) {
            locked = true;
        }
        return locked;
    }

    /**
     * 释放锁
     *
     * @param lockName
     */
    public boolean releaseLock(String lockName) {
        Jedis jedis = getClient();
        Long del = jedis.del(lockName);
        return del > 0;
    }

    /**
     * 检查对应锁是否处理锁住状态
     * @param lockName
     * @return
     */
    public boolean isLocked(String lockName) {
        Jedis jedis = getClient();
        return jedis.get(lockName) != null;
    }

    public static void main(String[] args) {
        RedisDistributeLock lock = new RedisDistributeLock("localhost", 6379);
        String lockName = "afanda";
        boolean status = lock.getLock(lockName);
        if(status) {
            try {
                System.out.println("获取到锁，开始进行操作");
            } finally {
                boolean releaseStatus = lock.releaseLock(lockName);
                System.out.println(MessageFormat.format("释放状态:{0}", releaseStatus));
            }
        }

        Assert.assertFalse(lock.isLocked(lockName));
    }

}
