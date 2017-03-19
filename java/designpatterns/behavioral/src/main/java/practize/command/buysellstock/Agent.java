package practize.command.buysellstock;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Sandip Singh.
 */
public class Agent extends ArrayBlockingQueue<Order> {

    private boolean start = false;

    public Agent(int capacity) {
        super(capacity);
    }

    public void placeOrder(Order order) {
        offer(order);
        try {
            take().execute();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }

}
