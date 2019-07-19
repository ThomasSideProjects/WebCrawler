
public class Main {
    public static void main(String[] args) {
        Crawler crawler = new Crawler("https://www.stackoverflow.com",2);
        crawler.scrapeSite("https://www.stackoverflow.com");
    }
}
