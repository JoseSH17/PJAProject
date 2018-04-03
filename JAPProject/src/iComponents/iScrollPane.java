/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iComponents;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

/**
 *
 * @author jorge.vasquez
 */
public class iScrollPane extends JScrollPane 
{
    public int responsiveExtendedPixelHeight;
    private float responsivePercentWidth, responsivePercentHeight,responsiveExtendedPercentHeight;
    private boolean ResponsiveWidth, ResponsiveHeight, responsiveExtendedHeight;

    public int getResponsiveExtendedPixelHeight() {
        return responsiveExtendedPixelHeight;
    }

    public float getResponsivePercentWidth() {
        return responsivePercentWidth;
    }

    public float getResponsivePercentHeight() {
        return responsivePercentHeight;
    }

    public float getResponsiveExtendedPercentHeight() {
        return responsiveExtendedPercentHeight;
    }

    public boolean isResponsiveHeight() {
        return ResponsiveHeight;
    }

    public boolean isResponsiveExtendedHeight() {
        return responsiveExtendedHeight;
    }

    public void setResponsiveExtendedPixelHeight(int responsiveExtendedPixelHeight) {
        this.responsiveExtendedPixelHeight = responsiveExtendedPixelHeight;
    }

    public void setResponsivePercentWidth(float responsivePercentWidth) {
        this.responsivePercentWidth = responsivePercentWidth;
    }

    public void setResponsivePercentHeight(float responsivePercentHeight) {
        this.responsivePercentHeight = responsivePercentHeight;
    }

    public void setResponsiveExtendedPercentHeight(float responsiveExtendedPercentHeight) {
        this.responsiveExtendedPercentHeight = responsiveExtendedPercentHeight;
    }

    public void setResponsiveExtendedHeight(boolean responsiveExtendedHeight) {
        this.responsiveExtendedHeight = responsiveExtendedHeight;
    }
    
    private int positon;

    public void setResponsiveHeight(float percent, int pixel) {
        responsiveExtendedPixelHeight = pixel;
        responsiveExtendedPercentHeight = percent;
        
        int tmpHeight = (int) (100 * (getParent().getHeight() - pixel) / percent);
        System.out.println(tmpHeight);
        setBounds(
                getX(),
                getY(),
                getWidth(),
                tmpHeight
        );
        responsiveExtendedHeight = true;
    }    
    
    public int getPositon() {
        return positon;
    }

    public void setPositon(int positon) {
        this.positon = positon;
    }
    
    
    public iScrollPane(Component c, Color col) 
    {
        super(c);
        setOpaque(true);
        getViewport().setBackground(col);
        setBorder(BorderFactory.createLineBorder(new Color(0xeceeef), 1, true));
    }

    public float getresponsivePercentWidth() {
        return responsivePercentWidth;
    }

    public void setresponsivePercentWidth(float responsiveNumWidth) {
        this.responsivePercentWidth = responsiveNumWidth;
    }

    
    
    public boolean isResponsiveWidth() {
        return ResponsiveWidth;
    }

    public void setResponsiveWidth(boolean Responsive) {
        this.ResponsiveWidth = Responsive;
    }

    public void setResponsiveHeight(boolean ResponsiveHeight) {
        this.ResponsiveHeight = ResponsiveHeight;
    }
    
 
    public void setResponsivHeight(boolean Responsive) {
        this.ResponsiveHeight = Responsive;
    }   
    
}
