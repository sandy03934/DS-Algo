package practize.cor;

/**
 * @author Sandip Singh
 */
public abstract class Handler {

    private Handler successor;

    public Handler getSuccessor() {
        return successor;
    }

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    protected void handleRequest(Request request) {
        if (!request.isHandled() && this.getSuccessor() != null) {
            this.getSuccessor().handleRequestImpl(request);
        }
    }

    public abstract void handleRequestImpl(Request request);
}
