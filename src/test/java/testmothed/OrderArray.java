package testmothed;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 有序数组
 */
public class OrderArray {
    private long[] arr;
    private int number;

    public OrderArray() {
        arr = new long[]{};
        number = 0;
    }

    public OrderArray(int size) {
        arr = new long[size];
        number = 0;
    }

    //insert 的时候control sort
    public boolean insert(long value) {
        if (number >= arr.length) {
            return false;
        }
        int i;//先找到标识点
        for (i = 0; i < number; i++) {
            if (arr[i] > value)
                break;
        }
        //标识点以后的数据 后移一位
        for (int j = number; j > i; j--) {
            arr[j] = arr[j - 1];
        }
        //标识点插入新数据
        arr[i] = value;
        number++;
        return true;
    }

    //对有序数组进行二分法查找
    public int findIndex(long value) {
        int  lowIndex=0,upIndex=number-1,curIndex=0;
            while (value!=arr[curIndex]){
                curIndex=(lowIndex+upIndex)/2;
                if(value==arr[curIndex]){
                    return curIndex;
                }else if(value<arr[curIndex]){
                   upIndex=curIndex-1;
                }else {
                    lowIndex=curIndex+1;
                }
                if(value<arr[lowIndex]||value>arr[upIndex]){
                    return -1;
                }

            }
        return -1;
    }

    //delete
    public boolean deleteFirst(long value) {
        //先找到标识点
        // 标识点后面数据数据前移一位
        int i = findIndex(value);
        if (i == -1) return false;
        for (int j = i; j < number - 1; j++) {
            arr[j] = arr[j + 1];
        }
        number--;
        arr[number] = 0;
        return true;
    }

    //display
    public void display() {
        System.out.println(ToStringBuilder.reflectionToString(arr, ToStringStyle.SHORT_PREFIX_STYLE));
    }


}
