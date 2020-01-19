import java.util.HashMap;

public class TinyUrl1 {
    public static int GLOBAL_ID = 0;
    private static String baseTinyUrl = "http://tiny.url/";
    private HashMap<Integer, String> idToUrl = new HashMap<Integer, String>();
    private HashMap<String, Integer> urlToId = new HashMap<String, Integer>();

    private String getShortKey(String url) {
        return url.substring(baseTinyUrl.length());
    }

    // 很懵逼 这是在做什么 ==!
    private int toBase62(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }
        return c - 'A' + 36;
    }

    private int shortKeyToId(String shortKey) {
        int id = 0;
        for (int i = 0; i < shortKey.length(); ++i) {
            id = id * 62 + toBase62(shortKey.charAt(i));
        }
        return id;
    }

    private String idToShortKey(int id) {
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shortUrl = "";
        while (id > 0) {
            shortUrl = chars.charAt(id % 62) + shortUrl;
            id /= 62;
        }
        while (shortUrl.length() < 6) {
            shortUrl = "0" + shortUrl;
        }
        return shortUrl;
    }

    public String longToShort(String longUrl) {
        if (urlToId.containsKey(longUrl)) {
            return baseTinyUrl + idToShortKey(urlToId.get(longUrl));
        }
        GLOBAL_ID++;
        urlToId.put(longUrl, GLOBAL_ID);
        idToUrl.put(GLOBAL_ID, longUrl);
        return baseTinyUrl + idToShortKey(GLOBAL_ID);
    }

    public String shortToLong(String shortUrl) {
        String shortKey = getShortKey(shortUrl);
        int id = shortKeyToId(shortKey);
        return idToUrl.get(id);
    }

    public static void main(String[] args) {
        TinyUrl tinyUrl = new TinyUrl();
        String shortUrl = tinyUrl.longToShort("http://www.lintcode.com/faq/?id=10");
        String longUrl = tinyUrl.shortToLong(shortUrl);
        System.out.println(shortUrl);
        System.out.println(longUrl);
    }
}
