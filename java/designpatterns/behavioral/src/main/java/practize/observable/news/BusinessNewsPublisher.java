package practize.observable.news;

/**
 * @author Sandip Singh.
 */
public class BusinessNewsPublisher extends NewsPublisher {

    private News news;

    public BusinessNewsPublisher(BusinessNews news) {
        this.news = news;
    }

    @Override
    public News getNews() {
        return this.news;
    }
}
