package practize.cor;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Sandip Singh
 */
public class HandlerTest {

    RequestHandler startHandler = null;

    @BeforeTest
    private void setUp() {
        startHandler = new RequestHandler();
        startHandler.attachHandler(new NegativeHandler());
        startHandler.attachHandler(new ZeroHandler());
        startHandler.attachHandler(new PositiveHandler());
    }

    @Test
    public void testNegativeHandler() {
        Request request = new Request();
        request.setValue(-3);

        startHandler.handle(request);

        assertTrue(request.isHandled());
        assertEquals(request.getOutput(), "Handled by Negative Handler");
    }

    @Test
    public void testZeroHandler() {
        Request request = new Request();
        request.setValue(0);

        startHandler.handle(request);

        assertTrue(request.isHandled());
        assertEquals(request.getOutput(), "Handled By Zero Handler");
    }

    @Test
    public void testPositiveHandler() {
        Request request = new Request();
        request.setValue(16);

        startHandler.handle(request);

        assertTrue(request.isHandled());
        assertEquals(request.getOutput(), "Handled By Positive Handler");
    }
}