import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashSet;


public class Crawler {

    private int crawlLimit;
    private HashSet<String> discoveredURLs;


    public Crawler(String seedURL, int crawlLimit) {
        this.crawlLimit = crawlLimit;
        this.discoveredURLs = new HashSet<>();
        this.discoveredURLs.add(seedURL);
    }


    public void scrapeSite(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String title = document.title();
            String description = document.select("meta[name=description]").get(0).attr("content");
            System.out.println(title + " " + description);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashSet<String> getDiscoveredURLs() {
        return this.discoveredURLs;
    }


}
