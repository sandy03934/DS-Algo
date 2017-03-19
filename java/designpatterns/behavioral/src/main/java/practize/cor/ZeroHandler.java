package practize.cor;

/**
 * @author Sandip Singh.
 */
public class ZeroHandler extends Handler {
    public void handleRequestImpl(Request request) {
        if (request != null && request.getValue() == 0) {
            request.setHandled(true);
            request.setOutput("Handled By Zero Handler");
        } else {
            super.handleRequest(request);
        }
    }
}
