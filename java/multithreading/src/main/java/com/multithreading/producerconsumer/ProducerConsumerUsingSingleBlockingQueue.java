package com.multithreading.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerUsingSingleBlockingQueue {


    private static class Producer implements Runnable {

        BlockingQueue<Integer> shared;

        public Producer(BlockingQueue shared) {
            this.shared = shared;
        }
        public void run() {
            for (int i = 1; i <= 20; i++) {
                try {
                    this.shared.put(i);
                    System.out.println("Producer Thread" + Thread.currentThread().getName() + " : " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class EvenConsumer implements Runnable {

        BlockingQueue<Integer> shared;

        CountDownLatch latch;

        public EvenConsumer(BlockingQueue shared, CountDownLatch latch) {
            this.shared = shared;
            this.latch = latch;
        }
        public void run() {
            while(true) {
                if (this.shared.element() % 2 == 0) {
                    try {
                        System.out.println("Even Consumer" + Thread.currentThread().getName() + " : " + this.shared.poll(5, TimeUnit.SECONDS));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.latch.countDown();
                }

//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    private static class OddConsumer implements Runnable {

        BlockingQueue<Integer> shared;

        CountDownLatch latch;

        public OddConsumer(BlockingQueue shared, CountDownLatch latch) {
            this.shared = shared;
            this.latch = latch;
        }
        public void run() {
            while(true) {
                if (this.shared.element() % 2 != 0) {
                    try {
                        System.out.println("Odd Thread" + Thread.currentThread().getName() + " : " + this.shared.poll(5, TimeUnit.SECONDS));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.latch.countDown();
                }

//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    public static void main(String ... args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1);

        CountDownLatch latch = new CountDownLatch(20);
        Producer producer = new Producer(queue);
        OddConsumer oddConsumer = new OddConsumer(queue, latch);
        EvenConsumer evenConsumer = new EvenConsumer(queue, latch);

//        new Thread(producer).start();
//
//        new Thread(oddConsumer).start();
//
//        new Thread(evenConsumer).start();
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(producer);
        service.submit(oddConsumer);
        service.submit(evenConsumer);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Processing Finished");
//        service.shutdownNow();
    }
}
