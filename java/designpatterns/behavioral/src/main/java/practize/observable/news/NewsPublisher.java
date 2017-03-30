package practize.observable.news;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sandip Singh.
 */
public abstract class NewsPublisher {

    List<Channel> channels = new ArrayList<>();

    private void notifySubscribers(News news) {
        channels.forEach(channel -> {
            channel.updateNews(news);});
    }

    public void register(Channel channel) {
        this.channels.add(channel);
    }

    public void unregister(Channel channel) {
        this.channels.remove(channel);
    }

    public void publishNews() {
        News news = this.getNews();
        System.out.println(this.getClass().getName() + " published news " + this.getNews());
        notifySubscribers(news);
    }

    public abstract News getNews();
}
