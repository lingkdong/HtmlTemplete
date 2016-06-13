package Files;

import com.google.common.collect.ImmutableSet;
import com.htmltemp.webapp.dto.DirectoyDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import testmothed.Img;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User:lk
 * Date: 16-5-26
 * Time: 上午10:18
 * To change this template use File | Settings | File Templates.
 */
public class FileDirectory {
//    private  final static  String directory="F:\\WEB\\HtmlTemplete\\src\\main\\webapp\\Templetes\\";
    private  final static  String directory="F:\\Html_css_web_temp\\des\\";
    public static void main(String[] args) {
    }

    public static Collection<File> listFiles(
            File directory, String[] fileNames, boolean recursive) {
        IOFileFilter filter;
        if (fileNames == null) {
            filter = TrueFileFilter.INSTANCE;
        } else {
            filter = new SuffixFileFilter(fileNames);
        }
        return FileUtils.listFiles(directory, filter,
                recursive ? TrueFileFilter.INSTANCE : FalseFileFilter.INSTANCE);
    }

    @Test
    public void fileTypeFilterTest(){
        Iterator<File> fileIterator= FileUtils.iterateFiles(new File(directory),new String[]{"html"},true);
        while (fileIterator.hasNext()){
            File file=fileIterator.next();
            System.out.println(file.getAbsolutePath());
        }
    }

    @Test
    public  void endWithFileNameFilter(){
        File fileDirectoy=new File(directory);
        Collection<File> files=listFiles(fileDirectoy,new String[]{"index.html"},true);
        for(File file:files){
            System.out.println(file.getAbsolutePath());
            String test=file.getAbsolutePath().replace(fileDirectoy.getAbsolutePath(), "");
            System.out.println(test);
        }

    }

    @Test
    public void reName(){
        File fileDirectory=new File(directory);
        File[]files=fileDirectory.listFiles();
        for(File file:files){
               if(file.isDirectory()){
                   String fileName=file.getName();
                   String newName=fileName.replaceAll(" Free Website Template - Free-CSS.com","");
                   newName= upperCaseFirstChar(newName);
                   if(!fileName.equals(newName)){
                       File newfile=new File(file.getParent()+"/"+newName);
//                       if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
//                           System.out.println(newName+"已经存在！");
//                       else{
                           file.renameTo(newfile);
//                       }
                   }


               }
        }
    }

    public static String upperCaseFirstChar(String src) {
        if (StringUtils.isBlank(src)) return src;
        char[] array = src.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        return new String(array);
    }


    @Test
    public void buildCsv(){
        Collection<File> files=listFiles(new File(directory),new String[]{"index.html"},true);
        File fileDirectoy=new File(directory);
        try {
            StringBuilder builder=new StringBuilder();
            builder.append("name,url,icon").append("\r\n");
            for(File file:files){
                String relativePath =file.getAbsolutePath().replace(directory, "");
                String arr[]=relativePath.split("\\\\");
                if(arr==null||arr.length==0||arr.length<2){
                    System.out.println(" error :"+file.getAbsoluteFile());
                }else {
                    String name=arr[0];
                    String url=relativePath;
                    String icon="img"+File.separator+name+"_s.png";
                    builder.append(name).append(",");
                    builder.append(url).append(",");
                    builder.append(icon).append("\r\n");
                }

            }

            //生成并写入文件
           File CsvFile=new File(directory+File.separator+"directory.csv");
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(CsvFile));
            bufferedWriter.write(builder.toString());
            bufferedWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Test
    public void  SetTest(){
        DirectoyDto directoryDto=new DirectoyDto("test","4","9");
        DirectoyDto directoryDto2=new DirectoyDto("test2","4","9");
        DirectoyDto directoryDto3=new DirectoyDto("test","4","9");

        List<DirectoyDto> directoyDtos=new ArrayList<DirectoyDto>();
        directoyDtos.add(directoryDto);
        directoyDtos.add(directoryDto2);
        directoyDtos.add(directoryDto3);

        System.out.println(directoyDtos.size());
        System.out.println(ImmutableSet.copyOf(directoyDtos).asList().size());



        List<String> test=new ArrayList<String>();
        test.add("hello");
        test.add("hello");
        test.add("hello");

        Set<String> sets2=new HashSet<String>(test);

        System.out.println(sets2.size());

    }

    @Test
    public void snapShortTest(){
        try {
             File file =new File(directory+"directory.csv");
            Reader reader=new FileReader(file);
             Iterable<CSVRecord> csvRecords= CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            for(CSVRecord csvRecord:csvRecords){
                String name=csvRecord.get("name");
                String url=csvRecord.get("url");
                String icon=csvRecord.get("icon");
                String htmlPathName="file:///"+directory+url;
                String imgPathName="F:\\Html_css_web_temp\\img\\"+name+".png";
                File f=new File(imgPathName);
                if(!f.exists()) {
                   if(!snapShort(htmlPathName,imgPathName,30*1000)){
                       continue;
                   }
                }
//                Img imgCom = new Img(f);
//                imgCom.resizeFix(312, 220);
                ImageCompression.doCompress(imgPathName,312,220,0.8f,"_s",false);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Boolean  snapShort(String url,String filePathName,int time){
        try {
            // 此方法仅适用于JdK1.6及以上版本
            url=url.replaceAll(" ", "%20");
            Desktop.getDesktop().browse(
                    new URL(url).toURI());
            Robot robot = new Robot();
            robot.delay(time);
            Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
            int width = (int) d.getWidth();
            int height = (int) d.getHeight();
            // 最大化浏览器
            robot.keyRelease(KeyEvent.VK_F11);
            robot.delay(3*1000);
            width=width-287*2;
            height=height-90-42;
            Image image = robot.createScreenCapture(new Rectangle(287, 90, width,
                    height));
            BufferedImage bi = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
            // 保存图片
            File file=new File(filePathName);
            ImageIO.write(bi, "png", file);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }



    @Test
    public  void sp(){
        String fileName="index.csv";
        String[] arr=fileName.split("\\.");
        System.out.println(arr.length);
    }

    @Test
    public void isAllFileInCsv(){
        //获取csv中文件记录
        try {
            Reader in=new FileReader(new File("F:\\WEB\\HtmlTemplete\\src\\main\\webapp\\document\\index.csv"));
            Iterable<CSVRecord> csvRecords=CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            Set<String> set=new HashSet<String>();
            for(CSVRecord csvRecord:csvRecords){
                String name=csvRecord.get("name");
                 set.add(name.toLowerCase());
            }
            File fileDirectory=new File(directory);
            File [] files=fileDirectory.listFiles();
            for(File file:files){
                if(file.isDirectory()){
                    if(!set.contains(file.getName().toLowerCase())){
                        System.out.println("need add .... "+file.getAbsolutePath());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("finish.....");

    }
}
