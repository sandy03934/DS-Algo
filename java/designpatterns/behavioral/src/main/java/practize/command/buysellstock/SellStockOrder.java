package practize.command.buysellstock;

/**
 * @author Sandip Singh.
 */
public class SellStockOrder implements Order {

    StockTrade trade;

    public SellStockOrder(StockTrade t) {
        this.trade = t;
    }
    public void execute() {
        this.trade.sell();
    }
}
