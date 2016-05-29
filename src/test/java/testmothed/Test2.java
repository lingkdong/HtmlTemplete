package testmothed;

import gui.ava.html.image.generator.HtmlImageGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User:lk
 * Date: 16-5-25
 * Time: 下午3:37
 * To change this template use File | Settings | File Templates.
 */
public class Test2 {

    public static void main(String[] args) {
//        //1.....
//        URL url;
//        InputStream is = null;
//        BufferedReader br;
//        String line;
//
//        try {
//            url = new URL("http://stackoverflow.com/");
//            is = url.openStream();  // throws an IOException
//            br = new BufferedReader(new InputStreamReader(is));
//
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (MalformedURLException mue) {
//            mue.printStackTrace();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        } finally {
//            try {
//                if (is != null) is.close();
//            } catch (IOException ioe) {
//                // nothing to see here
//            }
//        }
//        // 2.......
//        String content = null;
//        URLConnection connection = null;
//        try {
//            connection =  new URL("http://baidu.com").openConnection();
//            Scanner scanner = new Scanner(connection.getInputStream(),"UTF-8");
//            scanner.useDelimiter("\\Z");
//            content = scanner.next();
//        }catch ( Exception ex ) {
//            ex.printStackTrace();
//        }
//        System.out.println(content);
        //3......
        try {
            HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
            imageGenerator.loadUrl("https://www.baidu.com/");
            imageGenerator.saveAsImage("F:\\WEB\\HtmlTemplete\\src\\main\\webapp\\img\\CreativeZone.jpg");
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    private static String getUrlSource(String url) throws IOException {
        URL yahoo = new URL(url);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();

        return a.toString();
    }
}
