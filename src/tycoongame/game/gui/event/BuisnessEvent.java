package tycoongame.game.gui.event;

public class BuisnessEvent {

	public static final int MANAGER_SELECTED = 3;
    private String managerName;
    private int action;
    private String id;

    public BuisnessEvent(String name , String id , int action) 
    {
        this.id = id;
        this.action = action;
        this.managerName = name;
    }
    
    /**
     * @return the action
     */
    public int getAction() {
        return action;
    }

    /**
     * @return the managerName
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
}
