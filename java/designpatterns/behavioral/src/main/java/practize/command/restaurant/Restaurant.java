package practize.command.restaurant;

import java.util.concurrent.Executors;

/**
 * @author Sandip Singh.
 */
public class Restaurant {

    Restaurant() {
        start();
    }

    private void start() {
        Executors.newFixedThreadPool(2).execute(new Cook(this));
    }

    Waiter waiter = new Waiter(1, this);

    OrderQueue orderQueue = new OrderQueue(2);

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public OrderQueue getOrderQueue() {
        return orderQueue;
    }

    public void setOrderQueue(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    public void processOrder() {
        while (!getOrderQueue().isEmpty()) {
            System.out.println("Processing the Order");
        }
    }
}
