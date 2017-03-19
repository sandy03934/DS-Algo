package practize.command.restaurant;

/**
 * @author Sandip Singh.
 */
public class Customer implements Runnable {

    Order order;

    Restaurant restaurant;

    public Customer(Order order, Restaurant restaurant) {
        this.order = order;
        this.restaurant = restaurant;
    }

    public void run() {
        Waiter waiter = this.restaurant.getWaiter();
        waiter.takeOrder(order);


    }
}
