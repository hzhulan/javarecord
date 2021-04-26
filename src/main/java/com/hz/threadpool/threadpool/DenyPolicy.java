package com.hz.threadpool.threadpool;

/**
 * @file: DenyPolicy
 * @version: 1.0
 * @Description: 拒绝策略
 * @Author: pp_lan
 * @Date: 2021/4/26
 */
@FunctionalInterface
public interface DenyPolicy {

    void reject(Runnable runnable, ThreadPool threadPool);

    /**
     * 策略1: 直接丢弃任务
     */
    class DiscardDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            // do nothing
        }
    }

    /**
     * 策略2: 抛异常
     */
    class AbortDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new ThreadPoolException(String.format("线程%s被放弃.", runnable));
        }
    }

    /**
     * 策略3: 直接强行执行，不使用线程池管理
     */
    class RunnerDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {

            if (!threadPool.isShutdown()) {
                runnable.run();
            }
        }
    }
}
