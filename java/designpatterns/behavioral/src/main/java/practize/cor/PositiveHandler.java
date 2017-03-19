package practize.cor;

/**
 * @author Sandip Singh.
 */
public class PositiveHandler extends Handler {
    public void handleRequestImpl(Request request) {
        if (request != null && request.getValue() > 0) {
            request.setOutput("Handled By Positive Handler");
            request.setHandled(true);
        } else {
            super.handleRequest(request);
        }
    }
}
