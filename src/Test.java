public class Test {
    public static void main(String[] args) {
        WebLogger weblogger = new WebLogger();
        int now = (int) System.currentTimeMillis();
        weblogger.hit(1578994047);
        weblogger.hit(1578994047);
        weblogger.hit(1578994047);
        int nowHitCount = weblogger.get_hit_count_in_last_5_minutes(1578994047);
        int futureHitCount = weblogger.get_hit_count_in_last_5_minutes(1579080952);
        int passedHitCount = weblogger.get_hit_count_in_last_5_minutes(1578908911);

        System.out.println(nowHitCount);
        System.out.println(futureHitCount);
        System.out.println(passedHitCount);

        TimeUtil timeUtil = new TimeUtil();
        timeUtil.printTestValue();
    }
}
