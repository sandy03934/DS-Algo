package practize.command.restaurant;

/**
 * @author Sandip Singh.
 */
public class Cook implements Runnable {

    private Restaurant restaurant;

    public Cook(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public void run() {
        while(true) {
            try {
                Order order = this.restaurant.getOrderQueue().take();
                order.execute();
            } catch (InterruptedException e) {
                System.out.println("Cook takes the order");
            }
        }
    }
}
