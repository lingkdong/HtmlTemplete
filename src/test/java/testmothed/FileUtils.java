package testmothed;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import javax.servlet.http.HttpServletRequest;
import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
 
public class FileUtils {
	/**
	 * 压缩图片方法
	 * 
	 * @param oldFile 将要压缩的图片
	 * @param width 压缩宽
	 * @param height 压缩高
	 * @param quality 压缩清晰度 <b>建议为1.0</b>
	 * @param temp 绝对路径
	 * @param smallIcon 压缩图片后,添加的扩展名（在图片后缀名前添加）
	 * @param percentage 是否等比压缩 若true宽高比率将将自动调整
	 * @return 如果处理正确返回"success"; null则参数可能有误
	 */
	public static String doCompress(String oldFile, int width, int height, float quality,String[] temp, String smallIcon, boolean percentage) {
		if (oldFile != null && width > 0 && height > 0) {
			Image srcFile=null;
			String newImage = "";
			try {
				File file = new File(oldFile);
				// 文件不存在
				if (!file.exists()) {
					return "";
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
				tag.getGraphics().drawImage(srcFile, 0, 0, new_w, new_h, null);
				/*压缩后的文件名 */
				newImage = temp[0]+smallIcon+"."+temp[1];
				/*压缩之后临时存放位置*/
				FileOutputStream out = new FileOutputStream(newImage);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
				/* 压缩质量 */
				jep.setQuality(quality, true);
				encoder.encode(tag, jep);
				out.close();
				return "success";
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				try {
					FileUtils.logFile(e.toString());//写入错误日志
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch (IOException e) {
				try {
					FileUtils.logFile(e.toString());//写入错误日志
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally{
				srcFile.flush();
			}
			return "";
		} else {
			return "";
		}
	}
	/**
	 * @Description:
	 * @param oldFilePath :图片原路径
	 * @param max 最大边
	 * @param quality 压缩质量
	 * @return
	 * @throws java.io.IOException
	 * @author xdong
	 * @date 2015年1月28日 上午10:42:01
	 */
	public static String getImgAfterDeal(String oldFilePath,int max,float quality) throws IOException {
		 
		try{
 	       String path = oldFilePath;
	       String[] temp = path.split("\\.");
	     //防止路径中出现点
		for(int i=1;i<temp.length-1;i++){
			temp[0]+="."+temp[i];
		}
		temp[1]=temp[temp.length-1];
		File oldFile = new File(path);
		Image srcFile=null;
		if(!oldFile.exists())
		{
		  return "nofile";
		}
		 srcFile = ImageIO.read(oldFile);
		int width = srcFile.getWidth(null);
		int height = srcFile.getHeight(null); 
		srcFile.flush();
		String fileUrl_m ="m";
		if(max<=0){
			//原高宽压缩
			fileUrl_m =
				(FileUtils.doCompress(path,
				width,
				height,
						quality,temp, "_m", true));
		}else{
			//限制最大边压缩
			fileUrl_m =
				(FileUtils.doCompress(path,
				width>max&&width>height?max:width,
				height>max&&height>=width?max:height,
						quality,temp, "_m", true));
		}
		if(fileUrl_m.equals("")||fileUrl_m.equals(null)){
		   return "error";
		}

		}catch(Exception e){
			 return "exception";
		}
		
		return "success";
	}
	
	/*public static String getImgAfterDeal_k(String oldFilePath,int max,int k) throws IOException {
		try{ 
		 	String path =oldFilePath;
			String[] temp = path.split("\\.");//temp 硬盘绝对路径，格式
			//防止路径中出现点
				for(int i=1;i<temp.length-1;i++){
					temp[0]+="."+temp[i];
				}
				temp[1]=temp[temp.length-1];
		File oldFile = new File(path);
		Image srcFile=null;
		if(!oldFile.exists()){return "nofile";}
		String fileUrl="k";
		double d=0.0;
		 srcFile = ImageIO.read(oldFile);
	     int width = srcFile.getWidth(null);
		 int height = srcFile.getHeight(null); 
		 srcFile.flush();
		 if(max<=0){
			 fileUrl =
						(FileUtils.doCompress(path,
						width,
						height,
						(float)(1),temp, "_k", true));
		 }else{
			 fileUrl =
					(FileUtils.doCompress(path,
					width>max&&width>height?max:width,
					height>max&&height>=width?max:height,
					(float)(1),temp, "_k", true));
			 oldFile=new File(temp[0]+"_k."+temp[1]);
			 srcFile = ImageIO.read(oldFile);
		     width = srcFile.getWidth(null);
			 height = srcFile.getHeight(null); 
			 srcFile.flush();
		 }
		 //长宽不变，将图片压缩至 k KB以下
		 while(getFileSize(temp[0]+"_k."+temp[1])>k){
			 fileUrl =
					(FileUtils.doCompress(path,
					 width,
					height,
					(float)(1-d),temp, "_k", true));
			        d=d+0.1;
			}
		if(fileUrl.equals("")||fileUrl.equals(null)){
			return "error";
		}
		}catch(Exception e){
			return "exception";
		}
		return "success";
	}
	*/
	/*
	 * java 获取文件大小(KB)
	 * author:董新
	 */
	
	private static int getFileSize(String path){  
		  double sizes=0.0;
		  File file = new File(path.toString().trim());
		  if(file.exists()){
		    sizes = file.length();
		}
		int r_Int=(int)(sizes/1024);
		return r_Int;
	}
	
	/*
	 * 错误日志
	 * author:董新
	 *  
	 */
	public static void logFile(String log) throws IOException
	{
		String path = "D:/imagedeal_log.txt";
		File file=new File(path);
		if(!file.exists())
		{
			file.createNewFile();
		}
		
			 FileWriter writer = new FileWriter(file, true);
			 Date date=new Date();
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String strdate= format.format(date);//获得当前时间
			 writer.write(log+"\r\n"+strdate);
	         writer.close();
		
	}
	//文件删除
    /**
     *   
     *@param sPath  要删除文件
    */
    public static void deleteFile(String sPath) {
    	  File file = new File(sPath);
    	 if (file.isFile() && file.exists()) {  
    	        file.delete();  
    	   }  
         
    }


    public static void reduceImg(String imgsrc, String imgdist, int widthdist,
                                 int heightdist) {
        try {
            File srcfile = new File(imgsrc);
            if (!srcfile.exists()) {
                return;
            }
            Image src = javax.imageio.ImageIO.read(srcfile);

            BufferedImage tag= new BufferedImage((int) widthdist, (int) heightdist,
                    BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,  Image.SCALE_SMOOTH), 0, 0,  null);

            FileOutputStream out = new FileOutputStream(imgdist);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
            out.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
