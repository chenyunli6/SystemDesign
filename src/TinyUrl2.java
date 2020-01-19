import java.util.HashMap;

public class TinyUrl2 {
    private HashMap<String, String> shortUrlMap = new HashMap<String, String>();
    private HashMap<String, String> longUrlMap = new HashMap<String, String>();
    private int cnt = 0;
    private final StringBuffer tinyUrl = new StringBuffer("http://tiny.url/");
    private final String charset = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM";

    private String newShortUrl() {
        StringBuffer res = new StringBuffer();
        for (int i = 0, j = cnt; i < 6; i++, j /=62) {
            res.append(charset.charAt(j % 62));
        }
        cnt++;
        return tinyUrl + res.toString();
    }

    public String createCustom(String longUrl, String key) {
        String shortUrl = tinyUrl + key;
        if (longUrlMap.containsKey(longUrl)) {
            if (longUrlMap.get(longUrl).equals(shortUrl)) {
                return shortUrl;
            } else {
                return "error";
            }
        }

        if (shortUrlMap.containsKey(shortUrl)) {
            return "error";
        }

        longUrlMap.put(longUrl, shortUrl);
        shortUrlMap.put(shortUrl, longUrl);
        return shortUrl;
    }

    public String longToShort(String longUrl) {
        if (longUrlMap.containsKey(longUrl)) {
            return longUrlMap.get(longUrl);
        }
        String shortUrl = newShortUrl();
        longUrlMap.put(longUrl, shortUrl);
        shortUrlMap.put(shortUrl, longUrl);
        return shortUrl;
    }

    public String shortToLong(String shortUrl) {
        if (shortUrlMap.containsKey(shortUrl)) {
            return shortUrlMap.get(shortUrl);
        }
        return "error";
    }

    public static void main(String[] args) {
        TinyUrl2 tinyUrl2 = new TinyUrl2();
        System.out.println(tinyUrl2.createCustom("http://www.lintcode.com/", "lccode"));
        System.out.println(tinyUrl2.longToShort("http://www.lintcode.com/problem/"));
        System.out.println(tinyUrl2.shortToLong("http://tiny.url/lccode"));
        System.out.println(tinyUrl2.createCustom("http://www.lintcode.com/", "lc"));
        System.out.println(tinyUrl2.createCustom("http://www.lintcode.com/en/ladder/", "lccode"));
    }
}
