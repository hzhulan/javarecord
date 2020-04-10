package com.hz.cb;

import redis.clients.jedis.Jedis;

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
     *
     * @param lockName
     * @return
     */
    public boolean getLock(String lockName) {
        Jedis jedis = getClient();
        boolean locked = false;
        Long status = jedis.setnx(lockName, "true");

        //设置2个小时的失效时间，避免宕机导致锁无法释放
        jedis.expire(lockName, 2 * 3600);
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
    public void releaseLock(String lockName) {
        Jedis jedis = getClient();
        jedis.del(lockName);
    }

}
