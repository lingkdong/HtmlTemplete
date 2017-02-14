package testmothed;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Random;

/**
 * Created by DT254 on 2017/1/3.
 */
public class BubbleSort {
    private int number;
    private long [] arr;

    public BubbleSort(int max) {
       arr=new long[max];
        number=0;
    }

    public void initRandom(int number){
        for(int i=0;i<number;i++){
            Random r=new Random();
            arr[i]=r.nextInt(100);
        }
        this.number=number;
    }

    public void sort(){
        //number -1 到1
        //自身 和自身的下一个比较
      for(int out=number-1;number>1;number--){
          for(int in=0;in<out;in++){
              if(arr[in]>arr[in+1]){
                  long temp=arr[in];
                  arr[in]=arr[in+1];
                  arr[in+1]=temp;
              }
          }
      }
    }


    public void  display(){
        System.out.println(ToStringBuilder.reflectionToString(arr, ToStringStyle.SHORT_PREFIX_STYLE));
    }
}
