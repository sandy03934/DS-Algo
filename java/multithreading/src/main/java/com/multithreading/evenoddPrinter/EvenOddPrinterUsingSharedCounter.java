package com.multithreading.evenoddPrinter;

import java.util.concurrent.atomic.AtomicInteger;

public class EvenOddPrinterUsingSharedCounter {

    private static class Counter {
        AtomicInteger counter = new AtomicInteger(0);
    }

    private static class EvenTask implements Runnable {
        AtomicInteger counter = null;
        boolean shutdown = false;
        EvenTask(AtomicInteger counter) {
            this.counter = counter;
        }

        public void shutdown() {
            this.shutdown = true;
        }

        public void run() {
            while (!shutdown) {
                synchronized (counter) {
                    if (counter.get() % 2 != 0) {
                        counter.notify();
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(Thread.currentThread().getName() + " : " + counter.getAndIncrement());

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class OddTask implements Runnable {
        AtomicInteger counter = null;
        boolean shutdown = false;
        OddTask(AtomicInteger counter) {
            this.counter = counter;
        }

        public void shutdown() {
            this.shutdown = true;
        }
        public void run() {
            while (!shutdown) {
                synchronized (counter) {
                    if (counter.get() % 2 == 0) {
                        counter.notify();
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(Thread.currentThread().getName() + " : " + counter.getAndIncrement());

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String [] args) {
        Counter counter = new Counter();
        EvenTask eventTask = new EvenTask(counter.counter);
        OddTask oddTask = new OddTask(counter.counter);
        Thread evenThread = new Thread(eventTask,"evenThread");
        Thread oddThread = new Thread(oddTask,"oddThread");

        evenThread.start();
        oddThread.start();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        eventTask.shutdown();
        oddTask.shutdown();

        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
