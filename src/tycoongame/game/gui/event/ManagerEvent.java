package tycoongame.game.gui.event;

import tycoongame.buildings.Building;

public class ManagerEvent {

	public static final int BUILDING_SELECTED = 3;
    private String buildingName;
    private int action;
    private Building builfing;

    public ManagerEvent(String name, Building building, int action) 
    {
        this.builfing = building;
        this.action = action;
        this.buildingName = name;
    }
    
    /**
     * @return the action
     */
    public int getAction() {
        return action;
    }

    /**
     * @return the buildingName
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * @return the builfing
     */
    public Building getBuilfing() {
        return builfing;
    }
}