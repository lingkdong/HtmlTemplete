package testmothed;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Random;

/**
 * Created by DT254 on 2017/1/3.
 */
public class InsertSort {
    private int number;
    private long [] arr;

    public InsertSort(int max) {
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
       for(int out=1;out<number;out++){
           long temp=arr[out];
           int in=out;
           while (in>0&&arr[in-1]>=temp){
               arr[in]=arr[in-1];
               in--;
           }
           arr[in]=temp;

       }
    }

    public void display(){
        System.out.println(ToStringBuilder.reflectionToString(arr, ToStringStyle.SHORT_PREFIX_STYLE));
    }
}
