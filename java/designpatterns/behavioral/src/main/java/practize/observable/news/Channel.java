package practize.observable.news;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Sandip Singh.
 */
public interface Channel {

    Set<Reader> READERS = new HashSet<>();

    Map<String, List<Reader>> newsToUser = new HashMap<>();

    void updateNews(News news);

    default void addUser(Reader reader, News news) {
        READERS.add(reader);
        List<Reader> readers = newsToUser.get(news.getClass().getName());
        if (readers == null) {
            readers = new ArrayList<>();
        }
        readers.add(reader);
        newsToUser.put(news.getClass().getName(), readers);
    }
}
