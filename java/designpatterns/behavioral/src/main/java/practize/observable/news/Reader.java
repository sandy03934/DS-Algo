package practize.observable.news;

/**
 * News Reader
 * @author Sandip Singh
 */
public class Reader {

    private String name;

    public Reader(String name) {
        this.name = name;
    }

    public void addSubscription(Channel channel, News newsType) {
        channel.addUser(this, newsType);
    }

    public void receiveNews(Channel channel, String news) {
        System.out.println(this.name + " Received news from " + channel.getClass().getName() + " : " + news);
    }
}
