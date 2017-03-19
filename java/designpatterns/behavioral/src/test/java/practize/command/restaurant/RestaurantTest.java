package practize.command.restaurant;

import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Sandip Singh.
 */
public class RestaurantTest {

    @Test
    public void testRestaurant() {
        Restaurant restaurant = new Restaurant();

        Customer customer1 = new Customer(new MainCourseOrder(), restaurant);
        Customer customer2 = new Customer(new SoupOrder(), restaurant);
        Customer customer3 = new Customer(new SoupOrder(), restaurant);
        Customer customer4 = new Customer(new MainCourseOrder(), restaurant);

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(customer1);
        service.execute(customer2);
        service.execute(customer3);
        service.execute(customer4);

        restaurant.processOrder();
    }
}