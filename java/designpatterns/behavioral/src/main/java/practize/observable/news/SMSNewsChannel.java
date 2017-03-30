package practize.observable.news;

import java.util.List;

/**
 * @author Sandip Singh
 */
public class SMSNewsChannel implements Channel {

    @Override
    public void updateNews(News news) {
        this.sendSMS(news);
    }

    private void sendSMS(News news) {
        List<Reader> readerList = newsToUser.get(news.getClass().getName());
        READERS.forEach(reader -> { if (readerList.contains(reader)) {
            reader.receiveNews(this, news.getNews()); }});
    }
}
