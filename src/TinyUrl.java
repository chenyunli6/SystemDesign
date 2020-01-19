import java.util.HashMap;
import java.util.Random;

public class TinyUrl {
    private HashMap<String, String> longToShort;
    private HashMap<String, String> shortToLong;

    public TinyUrl() {
        longToShort = new HashMap<String, String>();
        shortToLong = new HashMap<String, String>();
    }

    public String longToShort(String longUrl) {
        if (longToShort.containsKey(longUrl)) {
            return longToShort.get(longUrl);
        }
        while (true) {
            String shortUrl = generateShortUrl();
            if (!shortToLong.containsKey(shortUrl)) {
                shortToLong.put(shortUrl, longUrl);
                longToShort.put(shortUrl, longUrl);
                return shortUrl;
            }
        }
    }

    public String shortToLong(String shortUrl) {
        if (!shortToLong.containsKey(shortUrl)) {
            return null;
        }

        return shortToLong.get(shortUrl);
    }

    private String generateShortUrl() {
        String allowedChars = "0123456789" + "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        String shortUrl = "http://tiny.url/";
        for (int i = 0; i < 6; i++) {
            int index = rand.nextInt(62);
            shortUrl += allowedChars.charAt(index);
        }

        return shortUrl;
    }

    public static void main(String[] args) {
        TinyUrl tinyUrl = new TinyUrl();
        String shortUrl = tinyUrl.longToShort("http://www.lintcode.com/faq/?id=10");
        String longUrl = tinyUrl.shortToLong(shortUrl);
        System.out.println(shortUrl);
        System.out.println(longUrl);
    }
}
