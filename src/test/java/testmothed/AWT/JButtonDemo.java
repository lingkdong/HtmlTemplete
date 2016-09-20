package testmothed.AWT;

//: JButtonDemo.java
// Looks like Java 1.1 but with J's added
    import java.awt.*;
    import java.awt.event.*;
    import java.applet.*;
    import javax.swing.*;
public class JButtonDemo extends Applet {
    //两个 button组件
    JButton
            button1 = new JButton("JButton 1"),
            button2 = new JButton("JButton 2");
    //一个text 组件
    JTextField text = new JTextField(50);
    public void init() {
        //建立一个监听事件
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e){
                //获取监听控件的 text
                String name =
                        ((JButton)e.getSource()).getText();
                //将 其值赋值到 text控件中
                text.setText(name + " Pressed");
            }
        };
        //add ActionListener to button1
        button1.addActionListener(al);
        //add button1 to frame
        add(button1);
        //add ActionListener to button2
        button2.addActionListener(al);
        //add button2 to frame
        add(button2);
        ////add text to frame
        add(text);
    }
    public static void main(String args[]) {
        JButtonDemo applet = new JButtonDemo();
        JFrame frame = new JFrame("TextAreaNew");//框架的名字
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        frame.getContentPane().add(
                applet, BorderLayout.CENTER);
        frame.setSize(600,600);//框架的大小
        applet.init();
        applet.start();
        frame.setVisible(true);
    }
} ///:~
