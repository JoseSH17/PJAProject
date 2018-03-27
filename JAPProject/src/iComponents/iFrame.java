/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iComponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import static java.awt.Window.Type.UTILITY;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.swing.IconFontSwing;

/**
 *
 * @author Lic. Jorge Isaac Vásquez Valenciano
 */
public final class iFrame extends JFrame implements ComponentInterfaz {

    InitComponents ic = null;
    private Point initialClick;

    private int width, tmpWidth;
    private int height, tmpHeight;
    private boolean isMaximized = false;
    private boolean isShadowWindowActivated = false;
    private iFrame shwdw;
    private int tmp = 0;

    protected iPanel UndecoredPanel;
    private JLabel lbl_close, lbl_minimize, lbl_maximize;

    /**
     * @param w JFrame (Main) Width
     * @param h JFrame (Main) Height
     * @param margin Elements Margin (top-bottom) between them.
     * @param CloseOperation: EXIT_ON_CLOSE DISPOSE_ON_CLOSE ...
     */
    public iFrame(int w, int h, int margin, int CloseOperation) {
        initFrame(w, h, margin, 0, "", CloseOperation);
    }

    /**
     * @param w JFrame (Main) Width
     * @param h JFrame (Main) Height
     * @param margin Elements Margin (top-bottom) between them.
     * @param Title
     * @param CloseOperation: EXIT_ON_CLOSE DISPOSE_ON_CLOSE ...
     */
    public iFrame(int w, int h, int margin, String Title, int CloseOperation) {
        initFrame(w, h, margin, 0, Title, CloseOperation);
    }

    /**
     * @param w JFrame (Main) Width
     * @param h JFrame (Main) Height
     * @param margin Elements Margin (top-bottom) between them.
     * @param InitMarginTop deja un espacio entre el JFrame y el primer
     * componente.
     * @param CloseOperation: EXIT_ON_CLOSE DISPOSE_ON_CLOSE ...
     */
    public iFrame(int w, int h, int margin, int InitMarginTop, int CloseOperation) {
        initFrame(w, h, margin, InitMarginTop, "", CloseOperation);
    }

    /**
     * @param w JFrame (Main) Width
     * @param h JFrame (Main) Height
     * @param margin Elements Margin (top-bottom) between them.
     * @param InitMarginTop deja un espacio entre el JFrame y el primer
     * componente.
     * @param Title Titulo del JFrame
     * @param CloseOperation: EXIT_ON_CLOSE DISPOSE_ON_CLOSE ...
     */
    public iFrame(int w, int h, int margin, int InitMarginTop, String Title, int CloseOperation) {
        initFrame(w, h, margin, InitMarginTop, Title, CloseOperation);
    }
    
    /**
     * @param w JFrame (Main) Width
     * @param h JFrame (Main) Height
     * @param margin Elements Margin (top-bottom) between them.
     * @param InitMarginTop deja un espacio entre el JFrame y el primer
     * componente.
     * @param Title Titulo del JFrame
     * @param CloseOperation: EXIT_ON_CLOSE DISPOSE_ON_CLOSE ...
     */
    public iFrame(float w, float h, int margin, int InitMarginTop, String Title, int CloseOperation) {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        int iwidth = (int) ((env.getMaximumWindowBounds().width * w) / 100);
        int iheight = (int) ((env.getMaximumWindowBounds().height * h) / 100);
        
        initFrame(iwidth, iheight, margin, InitMarginTop, Title, CloseOperation);
    }

    public void initFrame(int w, int h, int margin, int InitMarginTop, String Title, int CloseOperation) {
        ic = new InitComponents(w, h, margin, InitMarginTop);
        setPreferredSize(ic.setComponentDimension());
        setLayout(ic.getLayOut());
        setResizable(false);
        setTitle(Title);
        width = w;
        height = h;
        if (CloseOperation != 999) {
            initShadowFrame();
            setDefaultCloseOperation(CloseOperation);
        }

        setFont(new Font("Segoe UI", Font.PLAIN, 13));

        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = centerPoint.x - w / 2;
        int dy = centerPoint.y - h / 2;
        setLocation(dx, dy);

        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        lbl_minimize = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.REMOVE, 16, new Color(240, 240, 240)));
        lbl_close = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.POWER_SETTINGS_NEW, 16, new Color(240, 240, 240)));
        lbl_maximize = new JLabel(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.TV, 16, new Color(240, 240, 240)));
        UndecoredPanel = new iPanel(0, 0, 100.0f, 30, 0, 0, this);
        addUndecoredStyle(Color.white);
    }

    public iPanel getUndecoredPanel() {
        return UndecoredPanel;
    }

    public void setHeaderBackground(Color panelColor) {
        UndecoredPanel.setBackground(panelColor);
        UndecoredPanel.border(0, 0, 1, 0, panelColor.darker());
    }

    @Override
    public void setVisible(boolean bln) {
        super.setVisible(bln);
        if (!bln) {
            try {
                shwdw.setVisible(false);
            } catch (AbstractMethodError | UnsupportedOperationException | NullPointerException ex) {
            }
        }
    }

    @Override
    public void newLine() {
        ic.newLine();
        for (Object[] element : this.getObject()) {
            if (element[1] != null && element[2] != null) {
                Component comp = (Component) element[0];
                System.out.println(comp);
                add(comp);
            }
        }
        ic.deleteObject();
    }

    @Override
    public void finalice() {
        setVisible(true);
        pack();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public int getTmpWidth() {
        return tmpWidth;
    }

    public int getTmpHeight() {
        return tmpHeight;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setTmpWidth(int tmpWidth) {
        this.tmpWidth = tmpWidth;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setTmpHeight(int tmpHeight) {
        this.tmpHeight = tmpHeight;
    }

    @Override
    public Component AddObject(Component component, int width, int height, int Position) {
        return ic.AddObject(component, width, height, Position);
    }

    @Override
    public Component AddObject(Component component, int width, int height) {
        return ic.AddObject(component, width, height, CENTER);
    }

    @Override
    public Object[][] getObject() {
        return ic.getObject();
    }

    @Override
    public void deleteObject() {
        ic.deleteObject();
    }

    @Override
    public void border(int top, int left, int bottom, int right, Color c) {
        getRootPane().setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, c));
    }

    @Override
    public void dispose() {
        ic.dispose();

        removeAll();
        revalidate();
        repaint();
    }

    @Override
    public Component AddSingleObject(Component component, int width, int height, int Position) {
        return add(ic.AddSingleObject(component, width, height, Position));
    }

    public static List<Component> getAllComponents(final Container c) {
        Component[] comps = c.getComponents();
        List<Component> compList = new ArrayList<>();
        for (Component comp : comps) {
            compList.add(comp);
            if (comp instanceof Container) {
                compList.addAll(getAllComponents((Container) comp));
            }
        }
        return compList;
    }

    private void responsiveMaker() {
        shwdw.setVisible(false);
        setVisible(false);
        if (!isMaximized) 
        {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            setMaximizedBounds(env.getMaximumWindowBounds());
            
            setTmpWidth(getWidth());
            setTmpHeight(getHeight());
            setWidth((int) env.getMaximumWindowBounds().width);
            setHeight((int) env.getMaximumWindowBounds().height);
            
            setExtendedState(MAXIMIZED_BOTH);
            isMaximized = true;
        } else {
            setVisible(false);
            setWidth(getTmpWidth());
            setHeight(getTmpHeight());
            setMaximumSize(new Dimension(getWidth(), getHeight()));
            setExtendedState(NORMAL);
            isMaximized = false;
        }
        
        shwdw.setVisible(true);
        setVisible(true);

        List<Component> compList = getAllComponents(getRootPane());

        compList.forEach((comp) -> {
            if (comp instanceof iPanel) 
            {    
                iPanel ip = (iPanel) comp;
                ip.setComponentWidth(getWidth());
                ip.setComponentHeight(getHeight());
                
                if (ip.isResponsiveWidth() || ip.isResponsiveHeight())
                {
                    if (ip.isResponsiveWidth())
                        ip.setBounds(ip.getHorizontal(),ip.getVertical(), getWidth(), ip.getHeight());

                    if (ip.isResponsiveHeight())
                     ip.setBounds(ip.getHorizontal(),ip.getVertical(),ip.getWidth(),getHeight());
                } 
                else if (ip.isResponsiveExtendedWidth() || ip.isResponsiveExtendedHeight()) 
                {
                    if (ip.isResponsiveExtendedWidth())
                        ip.setResponsiveWidth(ip.getResponsiveExtendedPercentWidth(),ip.getResponsiveExtendedPixelWidth());

                    if (ip.isResponsiveExtendedHeight())
                        ip.setResponsiveHeight(ip.getResponsiveExtendedPercentHeight(), ip.getResponsiveExtendedPixelHeight());
                }
                
                if (ip.isResponsiveAlwaysOnBottom() && !(ip.isResponsiveExtendedWidth() || ip.isResponsiveExtendedHeight())) 
                {
                    ip.setBounds(ip.getHorizontal(), getHeight() - ip.getHeight(), getWidth(), ip.getHeight());
                }
                
            } 
            else if (comp.getParent() instanceof iPanel) 
            {
                iPanel iptmp = (iPanel) comp.getParent();

                if (comp instanceof iScrollPane) 
                {
                    iScrollPane ips = (iScrollPane) comp;
                    ips.setBounds(ips.getX(), ips.getY(), iptmp.calcWidth(ips.getresponsivePercentWidth(), 0), ips.getHeight());
                }
                else if (comp instanceof iTextField) 
                {
                    iTextField itf = (iTextField) comp;
                    switch (itf.getPositon()) 
                    {
                        case RIGHT:
                            itf.setBounds((iptmp.getWidth() - itf.getWidth()), itf.getY(), itf.getWidth(), itf.getHeight());
                            break;
                            
                        case CENTER:
                            itf.setBounds((iptmp.getWidth()/2) - (itf.getWidth() /2),itf.getY(),itf.getWidth(),itf.getHeight());                                
                            break;
                    }
                }
                else if (comp instanceof iPasswordField) 
                {
                    iPasswordField itf = (iPasswordField) comp;
                    switch (itf.getPositon()) 
                    {
                        case RIGHT:
                            itf.setBounds((iptmp.getWidth() - itf.getWidth()),itf.getY(),itf.getWidth(),itf.getHeight());                            
                            break;
                            
                        case CENTER:
                            itf.setBounds((iptmp.getWidth()/2) - (itf.getWidth() /2),itf.getY(),itf.getWidth(),itf.getHeight());                                                            
                            break;
                    }
                }
                else if (comp instanceof iButton) 
                {
                    iButton itf = (iButton) comp;
                    switch (itf.getPositon()) 
                    {
                        case RIGHT:
                            itf.setBounds((iptmp.getWidth() - itf.getWidth()),itf.getY(),itf.getWidth(),itf.getHeight());                            
                            break;
                            
                        case CENTER:
                            itf.setBounds((iptmp.getWidth()/2) - (itf.getWidth() /2),itf.getY(),itf.getWidth(),itf.getHeight());                                                            
                            break;
                    }
                }
                else if (comp instanceof iCalendar) 
                {
                    iCalendar itf = (iCalendar) comp;
                    switch (itf.getPositon()) 
                    {
                        case RIGHT:
                            itf.setBounds((iptmp.getWidth() - itf.getWidth()),itf.getY(),itf.getWidth(),itf.getHeight());                            
                            break;
                            
                        case CENTER:
                            itf.setBounds((iptmp.getWidth()/2) - (itf.getWidth() /2),itf.getY(),itf.getWidth(),itf.getHeight());                                                            
                            break;
                    }
                }
                else if (comp instanceof iLabel) 
                {
                    iLabel itf = (iLabel) comp;
                    switch (itf.getPositon()) 
                    {
                        case RIGHT:
                            itf.setBounds((iptmp.getWidth() - itf.getWidth()),itf.getY(),itf.getWidth(),itf.getHeight());                            
                            break;
                            
                        case CENTER:
                            itf.setBounds((iptmp.getWidth()/2) - (itf.getWidth() /2),itf.getY(),itf.getWidth(),itf.getHeight());                                                            
                            break;
                        case LEFT:
                            itf.setBounds(itf.getX(), itf.getY(), iptmp.getWidth(), itf.getHeight());
                            break;
                    }
                }
                else if (comp instanceof JLabel) 
                {
                    JLabel jlbl = (JLabel) comp;
                    if (jlbl.getHorizontalAlignment() == SwingConstants.RIGHT)
                        jlbl.setBounds(
                                jlbl.getX(),
                                jlbl.getY(),
                                jlbl.getParent().getWidth(),
                                jlbl.getHeight()
                        );
                }
            }
        });
        UndecoredPanel.setBounds(
                UndecoredPanel.getHorizontal(),
                UndecoredPanel.getVertical(),
                getWidth(),
                UndecoredPanel.getHeight()
        );

        lbl_close.setBounds(getWidth() - 40, 0, 40, 30);
        lbl_maximize.setBounds(getWidth() - 80, 0, 40, 30);
        lbl_minimize.setBounds(getWidth() - 120, 0, 40, 30);

    }

    /**
     * Isaac Vásquez.
     *
     * @param panelColor the color of the top generated panel
     * @return
     */
    private void addUndecoredStyle(Color panelColor) {
        UndecoredPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });

        UndecoredPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // get location of Window
                int thisX = getLocation().x;
                int thisY = getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
                int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

                // Move window to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                setLocation(X, Y);
                shwdw.setLocation(X - 9, Y - 9);
            }
        });

        if (!this.getTitle().isEmpty()) {
            JLabel lblTitle = new JLabel("        " + this.getTitle());
            lblTitle.setSize(lblTitle.getPreferredSize());
            lblTitle.setLocation(0, 6);
            lblTitle.setForeground(new Color(240, 240, 240));
            UndecoredPanel.add(lblTitle);

        }

        lbl_maximize.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                responsiveMaker();
            }
        }
        );

        lbl_close.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me
            ) {
                lbl_close.setOpaque(true);
                lbl_close.setBackground(new Color(0xE81123));
                Icon icon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.POWER_SETTINGS_NEW, 16, Color.white);
                lbl_close.setIcon(icon);
            }

            @Override
            public void mouseExited(MouseEvent me
            ) {
                lbl_close.setOpaque(false);
                lbl_close.setBackground(panelColor);
                Icon icon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.POWER_SETTINGS_NEW, 16, new Color(240, 240, 240));
                lbl_close.setIcon(icon);
            }

            @Override
            public void mouseClicked(MouseEvent me
            ) {
                System.exit(0);
            }
        }
        );

        lbl_maximize.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me
            ) {
                lbl_maximize.setOpaque(true);
                lbl_maximize.setBackground(new Color(0x888888));
                Icon icon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.TV, 16, Color.white);
                lbl_maximize.setIcon(icon);
            }

            @Override
            public void mouseExited(MouseEvent me
            ) {
                lbl_maximize.setOpaque(false);
                lbl_maximize.setBackground(panelColor);
                Icon icon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.TV, 16, new Color(240, 240, 240));
                lbl_maximize.setIcon(icon);
            }
        }
        );

        lbl_minimize.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me
            ) {
                setState(ICONIFIED);
                shwdw.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent me
            ) {
                lbl_minimize.setOpaque(true);
                lbl_minimize.setBackground(new Color(0x888888));
                Icon icon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.REMOVE, 16, Color.white);
                lbl_minimize.setIcon(icon);
            }

            @Override
            public void mouseExited(MouseEvent me
            ) {
                lbl_minimize.setOpaque(false);
                lbl_minimize.setBackground(panelColor);
                Icon icon = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.REMOVE, 16, new Color(240, 240, 240));
                lbl_minimize.setIcon(icon);
            }
        }
        );

        setUndecorated(
                true);
        UndecoredPanel.AddObject(lbl_close,
                40, 30, RIGHT);
        UndecoredPanel.AddObject(lbl_maximize,
                40, 30, RIGHT);
        UndecoredPanel.AddObject(lbl_minimize,
                40, 30, RIGHT);
        UndecoredPanel.newLine();

    }

    @Override
    public void addSpace(int height) {
        ic.addSpace(height);
    }

    public void initShadowFrame() {
        shwdw = new iFrame(this.width + 18, this.height + 18, 4, 999);
        shwdw.setUndecorated(true);
        shwdw.setBackground(new Color(0, 0, 0, 0));
        shwdw.setContentPane(new iShadowPane());
        shwdw.setType(UTILITY);
        shwdw.setName("0");
        setName("Main_Frame");

        shwdw.setEnabled(false);
        // 1 verde puede mostrarse
        
        WindowAdapter adapter = new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent we) {
                if (Integer.parseInt(shwdw.getName()) % 3 == 0) {
                    shwdw.toFront();
                    tmp++;
                    shwdw.setName(String.valueOf(tmp));
                }
                toFront();
            }

            @Override
            public void windowLostFocus(WindowEvent we) {
                tmp++;
                shwdw.setName(String.valueOf(tmp));
            }

            @Override
            public void windowDeiconified(WindowEvent we) {
                shwdw.setVisible(true);
                requestFocus();
            }
        };

        addWindowListener(adapter);
        addWindowFocusListener(adapter);

        shwdw.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent we) {
                if (!isShadowWindowActivated) {
                    isShadowWindowActivated = true;
                } else {
                    toFront();
                }
            }
        });

        shwdw.finalice();
    }

}
