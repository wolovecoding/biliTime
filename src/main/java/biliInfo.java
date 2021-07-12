//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;

public class biliInfo {
    private String uid = "BV15E411w7GB";
    private long allTime;
    private String jsonBody;
    private long restTime;
    private JSONArray data;

    public biliInfo(String uid) {
        this.uid = uid;
        this.connectUrl();
        this.getJsonData();
    }

    public void getAllTime() {
        this.allTime=0;
        for(int i = 0; i < this.data.size(); ++i) {
            Map<String, Object> map = (Map)this.data.get(i);
            long timeS = Integer.toUnsignedLong((Integer)map.get("duration"));
            this.allTime += timeS;
        }

        System.out.println("视频总时长  " + this.sT0String(this.allTime));
    }

    public void getLastTime(int chapter) {
        this.restTime=0;
        for(int i = chapter; i < this.data.size(); ++i) {
            Map<String, Object> map = (Map)this.data.get(i);
            long timeS = Integer.toUnsignedLong((Integer)map.get("duration"));
            this.restTime += timeS;
        }

        System.out.println("未看时常  " + this.sT0String(this.restTime));
    }

    public void getWatchedTime(int chapter) {
        int watchedTime = 0;

        for(int i = 0; i < chapter; ++i) {
            Map<String, Object> map = (Map)this.data.get(i);
            long timeS = Integer.toUnsignedLong((Integer)map.get("duration"));
            watchedTime = (int)((long)watchedTime + timeS);
        }

        PrintStream var10000 = System.out;
        String var10001 = this.sT0String((long)watchedTime);
        var10000.println("已看时常  " + var10001);
    }

    public String sT0String(long timeLong) {
        ArrayList<String> timeFen = new ArrayList();
        long rmd = timeLong;

        for(int i = 0; rmd % 60L > 0L && i < 3; ++i) {
            timeFen.add(Long.toString(rmd % 60L));
            rmd /= 60L;
        }

        String time = "";
        time = time.concat(timeFen.size() == 3 ? (String)timeFen.get(2) : "0").concat("小时").concat(timeFen.size() >= 2 ? (String)timeFen.get(1) : "0").concat("分").concat((String)timeFen.get(0)).concat("秒");
        return time;
    }

    private void connectUrl() {
        Connection connect = Jsoup.connect("https://api.bilibili.com/x/player/pagelist").data("bvid", this.uid).data("jsonp", "jsonp").ignoreContentType(true).header("Content-Type", "application/json;charset=utf-8");

        try {
            Response response = connect.execute();
            this.jsonBody = response.body();
        } catch (IOException var3) {
            System.out.println("连接错误！！");
            var3.printStackTrace();
        }

    }

    private void getJsonData() {
        JSONObject json = (JSONObject)JSONObject.parse(this.jsonBody);
        this.data = json.getJSONArray("data");
    }
}
