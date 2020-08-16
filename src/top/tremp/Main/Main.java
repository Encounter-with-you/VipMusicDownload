package top.tremp.Main;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static String url = "http://music.onlychen.cn/";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        //请求数据
        HashMap<String, String> data = new HashMap<>();
        data.put("input",input);
        data.put("filter","name");
        data.put("type","qq");
        data.put("page","1");
        //请求头
        HashMap<String, String> headers = new HashMap<>();
        headers.put("accept","application/json, text/javascript, */*; q=0.01");
        headers.put("X-Requested-With","XMLHttpRequest");
        Connection.Response response = Util.request(headers,url, data, Connection.Method.POST);
        String body = response.body();
        JSONObject object = JSONObject.parseObject(body);
        //注意：results中的内容带有中括号[]，所以要转化为JSONArray类型的对象
        JSONArray result = object.getJSONArray("data");
        String songUrl = null;
        String dest = "E:/VipMusicDownload/downTest/";
        String songName  = null;
        for(int i = 0;i < result.size();i++){
            songUrl = (String) result.getJSONObject(i).get("url");
            songName =  (String) result.getJSONObject(i).get("title") ;
            Util.downloadFile(songUrl,dest,songName);
        }
    }
}
