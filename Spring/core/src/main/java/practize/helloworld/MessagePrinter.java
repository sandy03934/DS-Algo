package practize.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by sinsandi on 9/28/2016.
 */
@Component
public class MessagePrinter {

    @Autowired
    @Qualifier(value = "mockMessageService")
    private MessageService messageService;

    public void printMessage() {
        System.out.println(this.messageService.toString());
        System.out.println(this.messageService.getMessage());
    }
}
