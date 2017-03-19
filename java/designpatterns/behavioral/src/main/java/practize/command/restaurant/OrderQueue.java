package practize.command.restaurant;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by sinsandi on 3/19/2017.
 */
public class OrderQueue extends ArrayBlockingQueue<Order> {

    public OrderQueue(int capacity) {
        super(capacity);
    }
}
