package tycoongame.game.gui.event.manager;

import tycoongame.buildings.Building;

public class ManagerEvent {

    private String buildingName;
    private Building builfing;

    public ManagerEvent(String name, Building building ) 
    {
        this.builfing = building;
        this.buildingName = name;
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