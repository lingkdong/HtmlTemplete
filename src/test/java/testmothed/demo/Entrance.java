package testmothed.demo;

import org.junit.Test;
import org.springframework.util.CollectionUtils;
import testmothed.Counter;
import testmothed.RandomUtil;

import java.util.*;

/**
 * Created by DT254 on 2016/9/18.
 * //模拟错误收集器
 * 将错误分类收集并统计器数量
 */
public class Entrance {
    private static final List<Object> list=new ArrayList<Object>();
    private static final Map<String, Counter> counterMap=new HashMap<String, Counter>();
    @Test
    public void run() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
      collectException();
        groupException();
        print(counterMap);
    }

    //收集Exception
    public void collectException() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String packageName="testmothed.demo";
       String [] classNames=new String[]{"BlankException","IilegalException","NullException"};
       for(int i=0;i<2000;i++){
           String className= classNames[RandomUtil.getRandom(0,classNames.length-1)];
           Class c=Class.forName(packageName+"."+className);
           list.add(c.newInstance());
       }
    }

    //分类 Exception
    public void groupException(){
       if(!CollectionUtils.isEmpty(list)){
           for(Object exception:list){
               String className=exception.getClass().getSimpleName();
               if(counterMap.get(className)==null){
                   counterMap.put(className,new Counter());
               }
               counterMap.get(className).i++;
           }
       }
    }


    //打印
    public void print(Map<String,Counter> counterMap){
        for(Map.Entry<String,Counter> entry:counterMap.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }

}

