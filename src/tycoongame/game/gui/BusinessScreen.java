package tycoongame.game.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import tycoongame.game.gui.component.TitleBanner;
import tycoongame.buildings.BuildingType;
import tycoongame.buildings.StoreManager;
import tycoongame.game.gui.component.InfoPanel;
import tycoongame.game.gui.component.ManagerPanel;
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

    private List<StoreManager> storeManagers;
    private List<JButton> buttons;

    public BusinessScreen( BuildingType bT , List<StoreManager> sMans )
	{
        String titleText = configureTitleText(bT);
		// Remove the layout managerScreen
		this.getPanel().setLayout( null );
		this.setBackground( new Color(200, 100, 40 ) );
		
		// Create the title banner across the top
		this.titlePanel = new TitleBanner();
		this.titlePanel.setFont( new Font( "Ariel", Font.BOLD, 20 ) );
		this.titlePanel.setText( titleText );
		this.titlePanel.setBackground( new Color( 50, 50, 50 ) );
		this.titlePanel.setForeground( new Color( 100, 220, 100 ) );
		this.getPanel().add( this.titlePanel );
		
		// Create the manager screen on left
		this.managerPanel = new ManagerPanel();
		this.managerPanel.setBackground( new Color( 134, 173, 134 ) );
		this.getPanel().add( this.managerPanel.getPanel() );

		// Create the info screen on right
		this.infoPanel = new InfoPanel();
		this.infoPanel.setBackground( new Color( 100, 220, 100 ) );
        this.getPanel().add( this.infoPanel.getPanel() );
        
        this.storeManagers = sMans;
        configureManagerPanel();
	}

//=============================================================
//					RESIZING
//=============================================================

    /**
     * Resizes the title banner and main game screen sections when the screen was
     * resized.
     */
	@Override protected void onScreenResize()
	{
		// Grab screen details
		int padLeft = this.getPanel().getInsets().left;
		int padRight = this.getPanel().getInsets().right;
		int padTop = this.getPanel().getInsets().top;
		int padBot = this.getPanel().getInsets().bottom;
		int myWidth = this.getWidth() - padLeft - padRight;
		int myHeight = this.getHeight() - padTop - padBot;
		int padX = (int)(myWidth * RELATIVE_PAD_X);
		int padY = (int)(myHeight * RELATIVE_PAD_Y);
		
		// Update the banner to fill entire screen
		int titleW = myWidth;
		int titleH = (int)(myHeight * TITLE_RELATIVE_HEIGHT);
		int titleX = (myWidth - titleW) / 2;
		int titleY = 0;
		this.titlePanel.setBounds( titleX, titleY, titleW, titleH );
		
		// Update manager screen on left
		int managerW = (int) ( myWidth * MANAGER_RELATIVE_WIDTH ) - padX / 2;
		int managerH = myHeight - ( titleH + padY );
		int managerX = 0;
		int managerY = myHeight - managerH;
		this.managerPanel.setBounds( managerX, managerY, managerW, managerH );
		
		// Update info screen on right size
		int infoW = (int) ( myWidth * INFO_RELATIVE_WIDTH ) - padX / 2;
		int infoH = managerH;
		int infoX = myWidth - infoW;
		int infoY = managerY;
		this.infoPanel.setBounds( infoX, infoY, infoW, infoH );
        
        sizeButtons (managerW , managerH );
    }
    
    //=======================================
    //      Screen Specifics
    //=======================================


    private void sizeButtons(int maxW, int maxH) 
    {
        for (int i = 0; i < buttons.size(); i++ ) 
            sizeButton( buttons.get(i) , maxW , maxH , i );
    }

    private void sizeButton(JButton b, int maxW, int maxH , int pos) 
    {
        final int BUTTONS_PER_COL = 3;
        final int BUTTONS_PER_ROW = 5;

        int r = (int) (pos/BUTTONS_PER_ROW);
        int c = pos % BUTTONS_PER_ROW;

        int padLeft     = (int) RELATIVE_PAD_X * maxW;
        int padRight    = (int) RELATIVE_PAD_X * maxW;
        int padTop      = (int) RELATIVE_PAD_Y * maxH;
        int padBot      = (int) RELATIVE_PAD_Y * maxH;

        int w = (maxW - padLeft - padRight) / BUTTONS_PER_ROW;
        int h = (maxH - padTop - padBot) / BUTTONS_PER_COL;

        int x = (int) RELATIVE_PAD_X * (r+1) + (w * r);
        int y = (int) RELATIVE_PAD_Y * (c+1) + (h * c);

        b.setBounds(x, y, w, h);

    }

    private String configureTitleText(BuildingType bT) 
    {
        String name = bT.toString();
        String out = "";
        for ( int i = 0; i < name.length(); i++ )
        {
            out += name.charAt(i);
            out += "";
        }
        return out;
    }

    private void configureManagerPanel() 
    {
        List<JButton> buttons = new ArrayList<JButton>();
        for (StoreManager sMan : storeManagers) 
        {
            String name = sMan.getName ();
        }
    }

}