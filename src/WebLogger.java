import java.util.LinkedList;

/**
 * 本参考程序来自九章算法，由 @九章算法 提供。版权所有，转发请注明出处。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，算法强化班，Java入门与基础算法班，Android 项目实战班，
 * - Big Data 项目实战班，算法面试高频题班, 动态规划专题班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/?source=code
 */

public class WebLogger {

    private LinkedList<Integer> timestamps;
    public WebLogger() {
        timestamps = new LinkedList<Integer>();
    }

    /**
     * @param timestamp an integer
     * @return void
     */
    public void hit(int timestamp) {
        timestamps.add(timestamp);
    }

    /**
     * @param timestamp an integer
     * @return an integer
     */
    public int get_hit_count_in_last_5_minutes(int timestamp) {
        while (!timestamps.isEmpty() && timestamps.getFirst()  + 300 <= timestamp)
            timestamps.removeFirst();
        return timestamps.size();
    }

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
    }
}