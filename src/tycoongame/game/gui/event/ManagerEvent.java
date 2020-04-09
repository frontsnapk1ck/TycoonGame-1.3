package tycoongame.game.gui.event;

public class ManagerEvent {

	public static final int BUILDING_SELECTED = 3;
    private String buildingName;
    private int action;

    public ManagerEvent(String name, int action) 
    {
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

}