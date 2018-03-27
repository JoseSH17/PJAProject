/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

/**
 * @author jorge.vasquez
 */
public class iAlert extends Thread {

    private iFrame tmpAlert = null;
    private JFrame Caller = null;
    boolean infinite;

    public iAlert(JFrame main_frame, String Text) {
        initiAlert(main_frame, Text);
    }

    public void sleeped() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(iClock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        int count = 0;
        while (infinite) {
            if (count < 7) {
                sleeped();
                tmpAlert.requestFocus();
                count++;
            } else {
                break;
            }
        }
        tmpAlert.removeAll();
        tmpAlert.deleteObject();
        tmpAlert.dispose();
        tmpAlert.setVisible(false);
        infinite = false;
        if (Caller != null) {
            Caller.setEnabled(true);
            Caller.requestFocus();
        }
    }

    private void initiAlert(JFrame main_frame, String Text) {
        Caller = main_frame;
        int width;
        infinite = true;

        if (Caller != null) {
            width = main_frame.getWidth() - 20;
        } else {
            width = 300;
        }

        tmpAlert = new iFrame(width, 80, 2, 5, JFrame.HIDE_ON_CLOSE);
        tmpAlert.getContentPane().setBackground(new Color(0xf2dede));
        tmpAlert.setUndecorated(true);

        tmpAlert.setShape(new RoundRectangle2D.Double(0, 0, width, 80, 10, 10));
        tmpAlert.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(0xebcccc)));

        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        Icon icon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.CLEAR, 14, new Color(0xA94442));
        JLabel lbl_close = new JLabel(icon);

        lbl_close.setBounds(width - 40, 0, 40, 40);
        tmpAlert.add(lbl_close);

        JLabel lbl_error = (JLabel) tmpAlert.AddSingleObject(new JLabel("Error!"), 50, 30, 20);
        lbl_error.setForeground(new Color(0xA94442));
        lbl_error.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        JLabel lbl_errormsg = (JLabel) tmpAlert.AddSingleObject(new JLabel("<html>" + Text + "</html>"), width - 20, 30, 20);
        lbl_errormsg.setForeground(new Color(0xA94442));
        lbl_errormsg.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        tmpAlert.finalice();

        tmpAlert.requestFocus();

        lbl_close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                infinite = false;
            }
        });

        if (Caller != null) {
            main_frame.setEnabled(false);
        }

        Thread t = new Thread(this);
        t.start();
    }

}
