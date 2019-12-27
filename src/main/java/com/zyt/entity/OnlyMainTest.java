package com.zyt.entity;

import java.util.concurrent.*;

/**
 * @Author: zhouyt
 * @Date: 2019/12/26
 * @Description:
 */

public class OnlyMainTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> future = new FutureTask<>(new RealData("1"));

        /**
         * 必要时创建新线程；空闲线程会被保留60秒
         */
        final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        /**
         * 该池包含固定数量的线程，空闲线程会一直被保留
         */
        ExecutorService executor = Executors.newFixedThreadPool(1);

        /**
         * 只有一个线程的“池”，该线程顺序执行每一个提交的任务（类似于Swing 事件分配线程）
         */
        final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        /**
         * 用于在给定的延迟后运行或定期执行的固定线程池。替代java.util.Timer
         */
        final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        /**
         * 用于在给定的延迟后运行或定期执行的单线程“池”
         */
        final ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        executor.submit(future);
        System.out.println(future.get());
    }
}
