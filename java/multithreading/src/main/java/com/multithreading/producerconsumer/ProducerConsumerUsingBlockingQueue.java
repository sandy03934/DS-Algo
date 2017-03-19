package com.multithreading.producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class to demonstrate the  implementation of ProducerConsumer example using BlockingQueue implementation.
 * @author Sandip Singh.
 */
public class ProducerConsumerUsingBlockingQueue {

    public static void main(String [] args) {
        BlockingQueue<Integer> evenQueue = new LinkedBlockingQueue<Integer>();
        BlockingQueue<Integer> oddqueue = new LinkedBlockingQueue<Integer>();
        CountDownLatch latch = new CountDownLatch(20);
        Producer producer = new Producer(evenQueue, oddqueue);
        OddConsumer oddConsumer = new OddConsumer(latch, oddqueue);
        EvenConsumer evenConsumer = new EvenConsumer(latch, evenQueue);

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
        service.shutdownNow();
    }

    private static class Producer implements Runnable {
        BlockingQueue<Integer> evenQueue = null;
        BlockingQueue<Integer> oddQueue = null;

        public Producer(BlockingQueue<Integer> evenQueue, BlockingQueue<Integer> oddQueue) {
            this.evenQueue = evenQueue;
            this.oddQueue = oddQueue;
        }

        public void run() {
            int i = 0;
            while (i < 20) {
                System.out.println("Putting number " + i);
                try {
                    if (i % 2 == 0) {
                        evenQueue.put(i);
                    } else {
                        oddQueue.put(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
//            latch.countDown();
        }
    }

    private static class OddConsumer implements Runnable {

        CountDownLatch latch = null;
        BlockingQueue<Integer> queue = null;
        public OddConsumer(CountDownLatch latch, BlockingQueue<Integer> queue) {
            this.latch = latch;
            this.queue = queue;
        }

        public void run() {
            while(true) {
//                if (this.queue.peek() % 2 != 0) {
                    try {
                        Integer num = this.queue.take();
                        System.out.println("Inside the Odd Consumer : " + num);
                        this.latch.countDown();
                    } catch (InterruptedException e) {
                        System.out.println("Odd Consumer ended up in exception");
                    }

//                }
            }
        }
    }


    private static class EvenConsumer implements Runnable {

        CountDownLatch latch = null;

        BlockingQueue<Integer> queue;

        public EvenConsumer(CountDownLatch latch, BlockingQueue<Integer> queue) {
            this.latch = latch;
            this.queue = queue;
        }

        public void run() {
            while(true) {
//                if (this.queue.peek() % 2 == 0) {
                    try {
                        Integer num = this.queue.take();
                        System.out.println("Inside the Even Consumer : " + num);
                        this.latch.countDown();
                    } catch (InterruptedException e) {
                        System.out.println("Even Consumer ended up in exception");
                    }
//                }
            }
        }
    }
}
