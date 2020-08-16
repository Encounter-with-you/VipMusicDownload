package top.tremp.Main;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * 发送请求等一些工具函数
 */
public class Util {

    public static Connection.Response request(HashMap<String, String> headers, String url, HashMap<String, String> data, Connection.Method method){
        Connection connect = Jsoup.connect(url)
                .headers(headers)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")
                .ignoreContentType(true)
                .method(method)
                .data(data);
        Connection.Response response = null;
        try {
            response = connect.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return response;
    }

    public static void downloadFile(String urlSong, String destFile,String name){
        String dest = destFile + name + ".mp3";
        try {
            URL url = new URL(urlSong);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            OutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(dest));
            byte[] bytes = new byte[1024 * 5];
            int len = -1;
            while ((len = inputStream.read(bytes)) != -1){
                fileOutputStream.write(bytes,0,len);
            }
            fileOutputStream.close();
            inputStream.close();
            System.out.println("下载完成 ---> " + name);
        } catch (IOException e) {
            System.out.println("下载失败 ---> " + name);
        }
    }
}
