package practize.helloworld;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

/**
 * Created by sinsandi on 9/28/2016.
 */
@Configuration
@ComponentScan
public class Application {

    @Bean
    @Scope(scopeName = "prototype")
    MessageService mockMessageService () {

        return new MessageService() {
            public String getMessage() {
                return "Hello World!!!";
            }
        };
    }

    @Bean(autowire = Autowire.BY_NAME, name = "messageService2")
    MessageService mockMessageService2 () {

        return new MessageService() {
            public String getMessage() {
                return "Hello Bizzare!!!";
            }
        };
    }

    public static void main(String [] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        MessagePrinter messagePrinter = applicationContext.getBean(MessagePrinter.class);
        messagePrinter.printMessage();
        messagePrinter.printMessage();
        MessagePrinter messagePrinter2 = applicationContext.getBean(MessagePrinter.class);
        messagePrinter2.printMessage();
        MessageService messageService = applicationContext.getBean("mockMessageService", MessageService.class);
        System.out.println(messageService.toString());
    }
}
