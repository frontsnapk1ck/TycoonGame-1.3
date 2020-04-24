package tycoongame.game.gui.event.manger.building;

public class BuildingViewEvent extends BuildingEvent {

    private String name;
    private String sManID;

    public BuildingViewEvent(String name, String sManID) 
    {
        this.name = name;
        this.sManID = sManID;
	}

    /**
     * @return the name
     */
    public String getName() 
    {
        return name;
    }

    /**
     * @return the sManID
     */
    public String getsManID() 
    {
        return sManID;
    }
}