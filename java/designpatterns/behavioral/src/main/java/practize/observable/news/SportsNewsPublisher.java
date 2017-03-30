package practize.observable.news;

/**
 * @author Sandip Singh.
 */
public class SportsNewsPublisher extends NewsPublisher {

    private News news;

    public SportsNewsPublisher(SportsNews news) {
        this.news = news;
    }

    @Override
    public News getNews() {
        return this.news;
    }
}
