package testmothed;

import java.io.*;
import java.util.Date;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class Img {
    private Image img;
    private int width;
    private int height;
    private File file;
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        System.out.println("开始：" + new Date().toLocaleString());
        Img imgCom = new Img("C:\\temp\\pic123.jpg");
        imgCom.resizeFix(400, 400);
        System.out.println("结束：" + new Date().toLocaleString());
    }
    /**
     * 构造函数
     */
    public Img(String fileName) throws IOException {
        File file = new File(fileName);// 读入文件
        this.file=file;
        img = ImageIO.read(file);      // 构造Image对象
        width = img.getWidth(null);    // 得到源图宽
        height = img.getHeight(null);  // 得到源图长
    } /**
     * 构造函数
     */
    public Img(File file) throws IOException {
        this.file=file;
        img = ImageIO.read(file);      // 构造Image对象
        width = img.getWidth(null);    // 得到源图宽
        height = img.getHeight(null);  // 得到源图长
    }
    /**
     * 按照宽度还是高度进行压缩
     * @param w int 最大宽度
     * @param h int 最大高度
     */
    public void resizeFix(int w, int h) throws IOException {
        if (width / height > w / h) {
            resizeByWidth(w);
        } else {
            resizeByHeight(h);
        }
    }
    /**
     * 以宽度为基准，等比例放缩图片
     * @param w int 新宽度
     */
    public void resizeByWidth(int w) throws IOException {
        int h = (int) (height * w / width);
        resize(w, h);
    }
    /**
     * 以高度为基准，等比例缩放图片
     * @param h int 新高度
     */
    public void resizeByHeight(int h) throws IOException {
        int w = (int) (width * h / height);
        resize(w, h);
    }
    /**
     * 强制压缩/放大图片到固定的大小
     * @param w int 新宽度
     * @param h int 新高度
     */
    public void resize(int w, int h) throws IOException {
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
        BufferedImage image = new BufferedImage(w, h,BufferedImage.SCALE_SMOOTH );
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
        String fileName=this.file.getName();

        int index=fileName.lastIndexOf(".");
        String newFileName=fileName.substring(0,index)+"_m"+fileName.substring(index);
        File destFile = new File(file.getParent()+File.separator+newFileName);
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
        // 可以正常实现bmp、png、gif转jpg
       ImageIO.write(image,fileName.substring(index+1),out);
    }
}