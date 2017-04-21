package practize.csvparser.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sinsandi on 4/20/2017.
 */
public class URLResolver {

    URL url;

    public URLResolver(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public String resolve() {
        StringBuilder outputStream = new StringBuilder();
        try {
            URLConnection connection = this.url.openConnection();
            try (BufferedReader is = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = is.readLine()) != null) {
                    outputStream.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toString();
    }
}
