package tycoongame.game.gui.screeen;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import tycoongame.buildings.Building;
import tycoongame.game.gui.component.InfoPanel;
import tycoongame.game.gui.component.ManagerPanel;
import tycoongame.game.gui.component.TitleBanner;
import tycoongame.game.gui.event.controller.building.BuildingInputListener;
import tycoongame.game.gui.event.manger.building.BuildingListner;
import tycoongame.gui.ScreenFramework;

public class BuildingScreen extends ScreenFramework {

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

    private Building building;
    private List<BuildingInputListener> listerns;

    public BuildingScreen(String title) {
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
        this.listerns = new ArrayList<BuildingInputListener>();

        configureText();
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
    }

    //=============================================
    //  Screen Specifics
    //=============================================

    private void configureText() 
    {
        String text = "";
        if (building != null)
        {
            text += this.building.getBuildingType() + "\n";
            text += "Level: " + this.building.getLevelType() + "\n";
            text += "Upgrade: " + this.building.getLevelUpgradeValue();
        }
        
        JLabel lable = new JLabel();
        lable.setText( text );
        this.infoPanel.getPanel().add(lable);
    }

    public void removeListner(BuildingInputListener obserber) 
    {
        this.listerns.remove(obserber);
	}

    public void addLisener(BuildingInputListener obserber) 
    {
        this.listerns.add(obserber);
	}

    public void loadBuilding(Building building) 
    {
        this.building = building;
        configureText();

	}
}