package testmothed;

import com.htmltemp.webapp.dto.DirectoyDto;
import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * Created by DT254 on 2016/9/18.
 */
public class MyTest {
    //final的数据禁止克隆  test
    @Test
    public  void testFinal() throws NoSuchMethodException {
        Constructor constructor=DirectoyDto.class.getConstructor();
        System.out.println(111);
    }

}
