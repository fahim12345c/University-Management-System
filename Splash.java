

import java.awt.Image;
import javax.swing.*;

public class Splash extends JFrame implements Runnable {
    Thread t;
    Splash()
    {
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/RU.jpg"));
        Image i2=i1.getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel image= new JLabel(i3);
        add(image);
        setVisible(true);
        int x=1;
        t=new Thread(this);
        t.start();
        for(int i=2;i<=600;i+=4,x++)
        {
             setLocation(600-((i+x)/2),350-i/2);
             setSize(i+2*x,i+x);
             try {
                 Thread.sleep(10);
             } catch (Exception e) {
             }
        }

    }
    public void run()
    {
        try {
            Thread.sleep(6000);
            setVisible(false);
            new Login();
        } catch (Exception e) {
        }
    }
    public static void main(String[] args) {
        new Splash();
    }
}
