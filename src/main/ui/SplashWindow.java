package ui;

import javax.swing.*;
import java.awt.*;

//creates splash window with assigned image
public class SplashWindow extends JFrame {

    // EFFECTS: creates window with assigned image that is scaled to window size and closes after assigned milliseconds
    public SplashWindow() {

        super("SplashScreen");
        setSize(500, 550);

        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(new ImageIcon("./data/bunny1.jpg").getImage()
                .getScaledInstance(500, 550, Image.SCALE_SMOOTH)));

        add(label);
        centreOnScreen();
        setVisible(true);



        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setVisible(false);
        dispose();
    }

    // centres splashscreen window on screen
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }
}
