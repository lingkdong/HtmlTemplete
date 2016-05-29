package testmothed;

import testmothed.Img;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User:lk
 * Date: 16-5-25
 * Time: 下午3:30
 * To change this template use File | Settings | File Templates.
 */
public class Snapshort {
    public static void main(String[] args) throws MalformedURLException,
            IOException, URISyntaxException, AWTException {
        // 此方法仅适用于JdK1.6及以上版本
        Desktop.getDesktop().browse(
                new URL("https://www.baidu.com").toURI());
        Robot robot = new Robot();
        robot.delay(10*1000);
        Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        int width = (int) d.getWidth();
        int height = (int) d.getHeight();
        // 最大化浏览器
        robot.keyRelease(KeyEvent.VK_F11);
        robot.delay(1000);
        width=width-287*2;
        height=height-90-42;
        Image image = robot.createScreenCapture(new Rectangle(287, 90, width,
                height));
        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        // 保存图片
        String filePath="F:\\WEB\\HtmlTemplete\\src\\main\\webapp\\img\\CreativeZone.png";
        File file=new File(filePath);
        ImageIO.write(bi, "png",file );


        Img imgCom = new Img(file);
        imgCom.resizeFix(156, 110);


    }
}
