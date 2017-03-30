package practize.observable.news;

/**
 * @author Sandip Singh
 */
public class SportsNews implements News {

    String news;

    public void setNews(String news) {
        this.news = news;
    }

    @Override
    public String getNews() {
        return this.news;
    }
}
