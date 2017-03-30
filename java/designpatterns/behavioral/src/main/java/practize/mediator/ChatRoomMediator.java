package practize.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sandip Singh.
 */
public class ChatRoomMediator implements Mediator {

    private List<ChatRoomUser> users = new ArrayList<ChatRoomUser>();

    public void registerUser(ChatRoomUser user) {
        this.users.add(user);
    }

    public void sendMessage(String message, ChatRoomUser user) {
        users.forEach(userA -> {
            if (user != userA) {
                userA.receive(user.getName() + " : " + message);
            }
        });
    }
}
