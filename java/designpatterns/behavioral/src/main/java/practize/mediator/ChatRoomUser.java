package practize.mediator;

/**
 * Created by sinsandi on 3/19/2017.
 */
public interface ChatRoomUser {

    public String getName();

    public void send(String message);

    public void receive(String message);
}
