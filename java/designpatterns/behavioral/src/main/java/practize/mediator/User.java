package practize.mediator;

/**
 * @author Sandip Singh.
 */
public class User implements ChatRoomUser {

    private String name;

    Mediator mediator;

    public User(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void send(String message) {
        this.mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println(message);
    }
}
