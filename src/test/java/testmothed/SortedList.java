package testmothed;

/**
 * Created by DT254 on 2017/1/4.
 */
public class SortedList {
    private Link first;

    public SortedList() {
        first=null;
    }
    public boolean isEmpty(){
        return (first==null);
    }

    public long remove(){
        Link temp=first;
        first=first.next;
        return temp.dData;
    }

    public void displayList(){
        Link current=first;
        while (current!=null){
            current.display();
            current=current.next;
        }
    }

    public void insert(long dData){
        Link newLink=new Link(dData);
        Link previous=null;
        Link current=first;
        //找到标识点 标识点的前一项和后一项
        while (current!=null && dData>current.dData){
            previous=current;
            current=current.next;
        }
        //前一项没有  插入的是first
        if(previous==null){
            first=newLink;
        }else {
            //新项插入前项后面
            previous.next=newLink;
        }
        //新项插入后一项前面
        newLink.next=current;
    }
}
