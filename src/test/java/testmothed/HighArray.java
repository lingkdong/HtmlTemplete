package testmothed;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by DT254 on 2016/12/28.
 * 无序数组
 */
public class HighArray {
    private  long [] arr;
    public HighArray() {
        arr=new long[]{};
    }
    public HighArray(int size) {
        arr=new long[size];
    }
    //insert
    public boolean insert(int i,long value){
        if(i<0||i>arr.length-1){
            return false;
        }
        arr[i]=value;
        return true;
    }
    //find
    public int findIndex(long value){
       for(int i=0;i<arr.length;i++){
           if(arr[i]==value){
               return i;
           }
       }
        return -1;
    }
    //delete
    public boolean deleteFirst(long value){
      // find first
        int index=findIndex(value);
        if(-1==index){
            return false;
        }else {
            long[] arr2=new long[arr.length-1];
            for(int i=0;i<arr.length;i++){
                if(i<index){
                    arr2[i]=arr[i];
                }else if(i>=index&&i<arr2.length){
                   arr2[i]=arr[i+1];
                }
            }
            arr=arr2;
        }
    return true;
    }
    //display
   public void display(){
       System.out.println(ToStringBuilder.reflectionToString(arr, ToStringStyle.SHORT_PREFIX_STYLE));
   }


}
