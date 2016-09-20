package Files;


import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User:lk
 * Date: 16-5-29
 * Time: 下午12:09
 * To change this template use File | Settings | File Templates.
 */
public class ImageCompression {
    /**
     * 压缩图片方法
     *
     * @param oldFile 将要压缩的图片
     * @param width 压缩宽
     * @param height 压缩高
     * @param quality 压缩清晰度 <b>建议为1.0</b>
     * @param percentage 是否等比压缩 若true宽高比率将将自动调整
     */
    public static Boolean doCompress(String oldFile, int width, int height, float quality, String smallFlag, boolean percentage) {
        if (oldFile != null && width > 0 && height > 0) {
            Image srcFile=null;
            String newImage = "";
            try {
                File file = new File(oldFile);
                // 文件不存在
                if (!file.exists()) {
                    return false;
                }
				/*读取图片信息*/
                srcFile = ImageIO.read(file);
                int new_w = width;
                int new_h = height;
                if (percentage) {
//					// 为等比缩放计算输出的图片宽度及高度
                    double rate1 = ((double) srcFile.getWidth(null)) / (double) width + 0.1;
                    double rate2 = ((double) srcFile.getHeight(null)) / (double) height + 0.1;
                    double rate = rate1 > rate2 ? rate1 : rate2;
                    new_w = (int) (((double) srcFile.getWidth(null)) / rate);
                    new_h = (int) (((double) srcFile.getHeight(null)) / rate);
                }
				/* 宽高设定*/
                BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
                tag.getGraphics().drawImage(srcFile.getScaledInstance(new_w, new_h,  Image.SCALE_SMOOTH), 0, 0,  null);
				/*压缩后的文件名 */
                int index=oldFile.lastIndexOf(".");
                newImage=oldFile.substring(0,index)+smallFlag+oldFile.substring(index);
				/*压缩之后临时存放位置*/
                ImageIO.write(tag,oldFile.substring(index+1),new FileOutputStream(newImage));
            } catch (Exception e) {
                e.printStackTrace();
               return false;
            }finally{
                srcFile.flush();
            }
        } else {
            return false;
        }

        return true;
    }
}
