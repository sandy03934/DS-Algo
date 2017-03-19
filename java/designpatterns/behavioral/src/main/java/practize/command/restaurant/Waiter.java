package practize.command.restaurant;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by sinsandi on 3/19/2017.
 */
public class Waiter  extends ArrayBlockingQueue<Order> {

    Restaurant restaurant;

    public Waiter(int capacity, Restaurant restaurant) {
        super(capacity);
        this.restaurant = restaurant;
    }

    public void takeOrder(Order order) {
        try {
            put(order);
            System.out.println("Taking Order from " +  Thread.currentThread().getName());
            this.restaurant.getOrderQueue().put(take());
        } catch (InterruptedException e) {
            System.out.println("Waiter Interrupted");
        }
    }
}
