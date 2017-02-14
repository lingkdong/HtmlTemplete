package testmothed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.htmltemp.webapp.dto.Directory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DT254 on 2016/9/18.
 */
public class MyTest {
    //final的数据禁止克隆  test
    @Test
    public  void testFinal() throws NoSuchMethodException {
        Constructor constructor=Directory.class.getConstructor();
        System.out.println(111);
    }
   @Test
    public void testIf(){

   }

    @Test
    public  void testReference(){
        //指向同一个对象的引用
        StringBuffer stringBuffer=new StringBuffer("init ");
        StringBuffer stringBuffer1=stringBuffer;//和stringBuffer指向同一个引用对象,修改相互受影响
        StringBuffer stringBuffer2=new StringBuffer(stringBuffer);//新建一个引用对象 添加值

        stringBuffer.append("test reference");
        System.out.println(stringBuffer);
        System.out.println(stringBuffer1);
        System.out.println(stringBuffer2);

        stringBuffer1.append(" test reference");
        System.out.println(stringBuffer);
        System.out.println(stringBuffer1);
        System.out.println(stringBuffer2);

    }

    @Test
    public void testArray(){
//        HighArray array=new HighArray(5);
//        array.insert(1,1);
//        array.insert(4,4);
//        array.display();
//
//        array.deleteFirst(1);
//        array.display();

//
//        OrderArray orderArray=new OrderArray(5);
//        orderArray.insert(2);
//        orderArray.display();
//        orderArray.insert(3);
//        orderArray.display();
//        orderArray.insert(1);
//        orderArray.display();
//        orderArray.insert(1);
//        orderArray.display();
//        orderArray.insert(5);
//        orderArray.insert(5);
//        orderArray.display();
//
//       orderArray.findIndex(4);
//        BubbleSort bubbleSort=new BubbleSort(17);
//        bubbleSort.initRandom(17);
//        bubbleSort.display();
//        bubbleSort.sort();
//        bubbleSort.display();
//        SelectOrder selectOrder=new SelectOrder(19);
//        selectOrder.initRandom(19);
//        selectOrder.display();
//        selectOrder.sort();
//        selectOrder.display();

//        InsertSort insertSort=new InsertSort(19);
//        insertSort.initRandom(19);;
//        insertSort.display();
//        insertSort.sort();
//        insertSort.display();

        SortedList sortedList=new SortedList();
        sortedList.insert(9);
        sortedList.insert(2);
        sortedList.insert(11);
        sortedList.insert(0);
        sortedList.insert(33);
        sortedList.displayList();
    }


    @Test
    public void TestTreeBuild(){
        String path = "F:\\WEB\\product20170214\\img\\product_img";
        List<FileDto> tree = new ArrayList<FileDto>();
        File file = new File(path);
        File[] files = file.listFiles();
        Long id=0L;
        for (File item : files) {
            String type = "directory";
            if (item.isFile()) {
                type = "file";
            }
            FileDto fileDto = new FileDto(id++,item.getName(), item.getPath(), null, type, 0);
            recursive(fileDto);
            tree.add(fileDto);
        }
        ObjectMapper mapper = new ObjectMapper();
            String json = null;
            try {
                json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tree);
                 String jsonFileName="fileJson.json";
                FileUtils.writeStringToFile(new File("F:\\WEB\\product20170214"+File.separator+jsonFileName),json);
                System.out.println(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private void recursive(FileDto fileDto){
          if(fileDto.getType().equals("directory")){
              File file=new File(fileDto.getFilePath());
              File [] files=file.listFiles();
              for(File item:files){
                  String type="directory";
                  if(item.isFile()){
                      type="file";
                  }
                  FileDto dto=new FileDto(item.getName(),item.getPath(),item.getPath(),type,fileDto.getDepth()+1);
                  if(StringUtils.isBlank(fileDto.getIcon())){
                      String path = item.getName();
                      String[] temp = path.split("\\.");
                      //防止路径中出现点
                      for(int i=1;i<temp.length-1;i++){
                          temp[0]+="."+temp[i];
                      }
                      temp[1]=temp[temp.length-1];
                      String newPath="F:\\WEB\\product20170214\\img\\fileIcon\\";
                      String smallIcon="_m";
//                      String icon=MyFileUtils.doCompress("F:\\WEB\\product20170214\\img\\fileIcon\\",item.getPath(),
//                              312*2,
//                              220*2,1f,
//                              temp, "_m",
//                              false);
                      String icon=ImgCutUtil.cut(item.getPath(), newPath+fileDto.getFileName()+"_"+temp[0]+smallIcon+"" +
                              "."+temp[1]);
                      fileDto.setIcon(icon);
                  }
                  recursive(dto);
                  fileDto.addChild(dto);
              }
          }
    }
}
