package tycoongame.game.gui.screeen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JToolTip;

import tycoongame.buildings.StoreManager;
import tycoongame.game.controllers.BusinessScreenController;
import tycoongame.game.gui.component.InfoPanel;
import tycoongame.game.gui.component.ManagerPanel;
import tycoongame.game.gui.component.TitleBanner;
import tycoongame.game.gui.event.BuisnessEvent;
import tycoongame.game.gui.event.BusinessLister;
import tycoongame.gui.ScreenFramework;

/**
 * Class represents the main screen of the game with the three part layout
 * consistenting of a banner label across the top and two seperate screens split
 * down middle.
 */

public class BusinessScreen extends ScreenFramework {

    /** Amount of padding between components */
    private static final double RELATIVE_PAD_X = 0.01;
    private static final double RELATIVE_PAD_Y = 0.01;

    /** Title banner height relative to this screen */
    private static final double TITLE_RELATIVE_HEIGHT = 0.2;

    /** Manager screen size on left */
    private static final double MANAGER_RELATIVE_WIDTH = 0.66;

    /** Info screen size on right */
    private static final double INFO_RELATIVE_WIDTH = 0.33;

    /** Banner label across top of screen */
    private TitleBanner titlePanel;
    /** Right screen that holds game infoScreen */
    private InfoPanel infoPanel;
    /** Left screen with all managerScreen infoScreen */
    private ManagerPanel managerPanel;

    private List<JButton> buttons;
    private int widthM;
    private int heightM;
    private List<BusinessLister> listeners;
    private List<StoreManager> managers;

    public BusinessScreen(String title) {
        // Remove the layout managerScreen
        this.getPanel().setLayout(null);
        this.setBackground(new Color(200, 100, 40));

        // Create the title banner across the top
        this.titlePanel = new TitleBanner();
        this.titlePanel.setFont(new Font("Ariel", Font.BOLD, 20));
        this.titlePanel.setText(title);
        this.titlePanel.setBackground(new Color(50, 50, 50));
        this.titlePanel.setForeground(new Color(100, 220, 100));
        this.getPanel().add(this.titlePanel);

        // Create the manager screen on left
        this.managerPanel = new ManagerPanel();
        this.managerPanel.setBackground(new Color(134, 173, 134));
        this.getPanel().add(this.managerPanel.getPanel());

        // Create the info screen on right
        this.infoPanel = new InfoPanel();
        this.infoPanel.setBackground(new Color(100, 220, 100));
        this.getPanel().add(this.infoPanel.getPanel());

        this.buttons = new ArrayList<JButton>();
        this.listeners = new ArrayList<BusinessLister>();
    }

    // =============================================================
    // RESIZING
    // =============================================================

    /**
     * Resizes the title banner and main game screen sections when the screen was
     * resized.
     */
    @Override
    protected void onScreenResize() {
        // Grab screen details
        int padLeft = this.getPanel().getInsets().left;
        int padRight = this.getPanel().getInsets().right;
        int padTop = this.getPanel().getInsets().top;
        int padBot = this.getPanel().getInsets().bottom;
        int myWidth = this.getWidth() - padLeft - padRight;
        int myHeight = this.getHeight() - padTop - padBot;
        int padX = (int) (myWidth * RELATIVE_PAD_X);
        int padY = (int) (myHeight * RELATIVE_PAD_Y);

        // Update the banner to fill entire screen
        int titleW = myWidth;
        int titleH = (int) (myHeight * TITLE_RELATIVE_HEIGHT);
        int titleX = (myWidth - titleW) / 2;
        int titleY = 0;
        this.titlePanel.setBounds(titleX, titleY, titleW, titleH);

        // Update manager screen on left
        int managerW = (int) (myWidth * MANAGER_RELATIVE_WIDTH) - padX / 2;
        int managerH = myHeight - (titleH + padY);
        int managerX = 0;
        int managerY = myHeight - managerH;
        this.managerPanel.setBounds(managerX, managerY, managerW, managerH);
        this.heightM = managerH;
        this.widthM = managerW;

        // Update info screen on right size
        int infoW = (int) (myWidth * INFO_RELATIVE_WIDTH) - padX / 2;
        int infoH = managerH;
        int infoX = myWidth - infoW;
        int infoY = managerY;
        this.infoPanel.setBounds(infoX, infoY, infoW, infoH);

        for (int i = 0; i < this.buttons.size(); i++) 
        {
            JButton jButton = this.buttons.get(i);
            sizeButton(jButton , this.widthM , this.heightM , i);
    
            this.managerPanel.getPanel().add(this.buttons.get(i));
        }
    }

    // =======================================
    // Screen Specifics
    // =======================================

    private void sizeButton(JButton b, int maxW, int maxH, int pos) {
        final int BUTTONS_PER_COL = 5;
        final int BUTTONS_PER_ROW = 3;

        int r = (int) (pos / BUTTONS_PER_ROW);
        int c = pos % BUTTONS_PER_ROW;

        int padLeft = (int) (RELATIVE_PAD_X * maxW * 2);
        int padRight = (int)(RELATIVE_PAD_X * maxW * 2);
        int padTop = (int)  (RELATIVE_PAD_Y * maxH * 2);
        int padBot = (int)  (RELATIVE_PAD_Y * maxH * 2);

        int w = (maxW - padLeft - padRight) / BUTTONS_PER_ROW;
        int h = (maxH - padTop - padBot) / BUTTONS_PER_COL;

        int x = (int) (RELATIVE_PAD_X * (r + 1) * maxW);
        x += (w * r);
        
        int y = (int) (RELATIVE_PAD_Y * (c + 1) * maxH); 
        y += (h * c);

        b.setBounds(x, y, w, h);

    }

    public void loadManager ( String name , StoreManager storeManager) {
        JButton managerButton = new JButton(name);
        sizeButton(managerButton, widthM, heightM, this.buttons.size());
        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whenManagerButtonClicked(name, storeManager);
            }
        });
        managerButton.setBackground( new Color ( 34, 234, 23));
        managerButton.setVisible(true);
        this.buttons.add(managerButton);
    }

    private void whenManagerButtonClicked ( String name , StoreManager storeManager) {
        BuisnessEvent event = new BuisnessEvent( storeManager, BuisnessEvent.MANAGER_SELECTED );
        if ( this.listeners != null )
        {
            for (BusinessLister listerner : this.listeners )
                listerner.onManagerSelect( event );
        }
    }

    public void setListener ( BusinessLister lister )
    {
        this.listeners.add( lister );
    }

    public void removeListener(BusinessScreenController listener ) 
    {
        this.listeners.remove( listener );
	}

    public void setName(String name) 
    {
        this.titlePanel.setText(name);
	}

    public void loadManagers(List<StoreManager> sMans) 
    {
        this.managers = sMans;
        for (StoreManager storeManager : sMans)
            loadManager(storeManager.getName(), storeManager);
	}
}