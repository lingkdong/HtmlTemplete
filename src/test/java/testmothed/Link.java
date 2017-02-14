package testmothed;

/**
 * Created by DT254 on 2017/1/4.
 */
public class Link {
    public long dData;
    public Link next;//自引用

    public Link(long dData) {
        this.dData = dData;
    }

    public void display(){
        System.out.println(dData);
    }
}
