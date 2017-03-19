package practize.cor;

/**
 * @author Sandip Singh.
 */
public class NegativeHandler extends Handler {
    public void handleRequestImpl(Request request) {
        if (request != null && request.getValue() < 0) {
            request.setOutput("Handled by Negative Handler");
            request.setHandled(true);
        } else {
            super.handleRequest(request);
        }
    }
}
