package practize.cor;

/**
 * Created by sinsandi on 3/19/2017.
 */
public class RequestHandler {

    private Handler startHandler = null;

    public void attachHandler(Handler handler) {
        if (startHandler == null) {
            startHandler = handler;
        } else {
            Handler lastHandler = startHandler;
            while (lastHandler.getSuccessor() != null) {
                lastHandler = lastHandler.getSuccessor();
            }
            lastHandler.setSuccessor(handler);
        }
    }

    public void handle(Request request) {
        this.startHandler.handleRequestImpl(request);
    }
}
