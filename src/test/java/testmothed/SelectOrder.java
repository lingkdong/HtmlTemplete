package testmothed;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Random;

/**
 * Created by DT254 on 2017/1/3.
 */
public class SelectOrder {
    private int number;
    private long [] arr;

    public SelectOrder(int max) {
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
       for(int out=0;out<number-1;out++){
           for(int in=out+1;in<number;in++){
               if(arr[in]<arr[out]){
                   long temp=arr[in];
                   arr[in]=arr[out];
                   arr[out]=temp;
                   display();
               }
           }
       }
    }

    public void  display(){
        System.out.println(ToStringBuilder.reflectionToString(arr, ToStringStyle.SHORT_PREFIX_STYLE));
    }
}
