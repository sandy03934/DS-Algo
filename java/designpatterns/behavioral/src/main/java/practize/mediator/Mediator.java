package practize.mediator;

/**
 * @author Sandip Singh.
 */
public interface Mediator {

    void registerUser(ChatRoomUser user);

    void sendMessage(String message, ChatRoomUser user);
}
