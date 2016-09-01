/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Resources;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JLabel;

/**
 * created on Dec 7, 2015
 * @author mmh
 */
public class backgroundLabel extends JLabel{

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Image img1 = Toolkit.getDefaultToolkit().getImage(("/src/Image/tellerBack3.jpg"));
//        img1 = img1.getScaledInstance(300, 300, Image.SCALE_REPLICATE);
        g2.drawImage(img1, 0, 0, this);
        g2.finalize();
    }

}
