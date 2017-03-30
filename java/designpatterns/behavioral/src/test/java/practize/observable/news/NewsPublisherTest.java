package practize.observable.news;

import org.testng.annotations.Test;

/**
 * @author Sandip Singh.
 */
public class NewsPublisherTest {


    @Test
    public void testNewsSubscription () throws Exception {

        Reader ram = new Reader("Ram");
        Reader shyam = new Reader("Shyam");

        SportsNews sports = new SportsNews();

        SMSNewsChannel smsNewsChannel = new SMSNewsChannel();
        EmailChannel emailChannel = new EmailChannel();


        ram.addSubscription(smsNewsChannel, sports);
        ram.addSubscription(emailChannel, sports);

        BusinessNews businessNews = new BusinessNews();
        shyam.addSubscription(emailChannel, businessNews);
        shyam.addSubscription(smsNewsChannel, businessNews);



        businessNews.setNews("Update 1 : BusinessNews");
        BusinessNewsPublisher businessNewsPublisher = new BusinessNewsPublisher(businessNews);
        businessNewsPublisher.register(emailChannel);
        businessNewsPublisher.publishNews();

        sports.setNews("Update 1 : Sports News");
        SportsNewsPublisher sportsNewsPublisher = new SportsNewsPublisher(sports);
        sportsNewsPublisher.register(smsNewsChannel);
        sportsNewsPublisher.register(emailChannel);
        sportsNewsPublisher.publishNews();

    }

}