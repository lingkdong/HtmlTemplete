package testmothed;

import java.util.Random;

/**
 * Created by DT254 on 2016/9/18.
 */
public class RandomUtil {
    public static int getRandom(int min,int max){
        Random random=new Random();
        return random.nextInt(max-min+1)+min;
    }
}
