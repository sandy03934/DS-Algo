package practize.command.buysellstock;

/**
 * @author Sandip Singh
 */
public class BuyStockOrder implements Order {

    private StockTrade trade;

    public BuyStockOrder(StockTrade s) {
        this.trade = s;
    }
    public void execute() {
        this.trade.buy();
    }
}
