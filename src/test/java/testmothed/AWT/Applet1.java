package testmothed.AWT;

//: Applet1.java
// Very simple applet
import java.awt.*;
import java.applet.*;
public class Applet1 extends Applet {
    public void paint(Graphics g) {
        g.drawString("First applet", 10, 10);
    }

    public static void main(String[] args) {
        System.out.println(1);
    }
} ///:~
