package practize.command.buysellstock;

import org.testng.annotations.Test;

/**
 * @author Sandip Singh.
 */
public class BuySellStockTest {

    @Test
    public void testBuySellStock() throws Exception {
        StockTrade trade = new StockTrade();
        BuyStockOrder buyStockOrder = new BuyStockOrder(trade);
        SellStockOrder sellStockOrder = new SellStockOrder(trade);

        Agent agent = new Agent(10);
//        agent.placeOrder();

        //agent.start()
        agent.placeOrder(buyStockOrder);
        agent.placeOrder(sellStockOrder);

//        agent.stop();
    }
}