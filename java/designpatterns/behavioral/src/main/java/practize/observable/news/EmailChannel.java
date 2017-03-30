package practize.observable.news;

import java.util.List;

/**
 * @author Sandip Singh.
 */
public class EmailChannel implements Channel {

    @Override
    public void updateNews(News news) {
        this.sendEmail(news);
    }

    public void sendEmail(News news) {
        List<Reader> readerList = newsToUser.get(news.getClass().getName());
        READERS.forEach(reader -> {if (readerList.contains(reader)) {
            reader.receiveNews(this, news.getNews());}});
    }
}
