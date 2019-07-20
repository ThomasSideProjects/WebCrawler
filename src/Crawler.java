import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;

public class Crawler {

    private final int CRAWL_LIMIT;
    private int pagesCrawled;
    private HashSet<String> discoveredURLs;

    public Crawler(String seedURL, int crawlLimit) {
        CRAWL_LIMIT = crawlLimit;
        discoveredURLs = new HashSet<>();
        pagesCrawled = 0;
        scrapeSite(seedURL);
    }

    private void scrapeSite(String url) {
        if (pagesCrawled < CRAWL_LIMIT && !discoveredURLs.contains(url)) {
            try {

                Document document = Jsoup.connect(url).get();
                String title = document.title();

                String description;
                if (document.select("meta[name=description]").size() == 0)  {
                    description = "No description for this site";
                } else {
                    description = document.select("meta[name=description]").get(0).attr("content");
                }
                System.out.println(title + " " + description);
                System.out.println(url + "\n");

                discoveredURLs.add(url);
                pagesCrawled++;
                Elements scrapedLinks = document.select("a[href]");

                for (int i=0; i < scrapedLinks.size(); i++) {
                    scrapeSite(scrapedLinks.get(i).attr("abs:href"));
                }

            //For handling malformed urls scraped from hyperlinks
            } catch (MalformedURLException e) {
                System.err.println("Unparsable URL found: " + url + ", it has been skipped" + "\n");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }


}
