package com.threadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest1 {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        CountDownLatch countDownLatch = new CountDownLatch(5);
//        for (int i = 0; i < 5; i++) {
//            int finalI = i;
//            executor.execute(()->{
        for (int j = 0; j < 500000; j++) {
            System.err.println(j);
            System.err.println(Thread.currentThread().getName());
//                }
//                countDownLatch.countDown();
//            });
//        }
        }
            System.err.println("线程睡了=============");
//        countDownLatch.await();
            long endTime = System.currentTimeMillis();    //获取结束时间
            System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间

    }
}
