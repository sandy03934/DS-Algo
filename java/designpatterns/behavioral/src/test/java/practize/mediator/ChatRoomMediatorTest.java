package practize.mediator;

import org.testng.annotations.Test;

/**
 * @author Sandip Singh.
 */
public class ChatRoomMediatorTest {

    @Test
    public void testMediator() {
        ChatRoomMediator mediator = new ChatRoomMediator();
        User ram = new User(mediator, "Ram");
        User addy = new User(mediator, "Addy");
        User john = new User(mediator, "John");
        User shyam = new User(mediator, "Shyam");

        mediator.registerUser(ram);
        mediator.registerUser(addy);
        mediator.registerUser(john);
        mediator.registerUser(shyam);

        ram.send("Hello Friends !! Good Morning");
        shyam.send("Good Morning Ram :)");
    }
}