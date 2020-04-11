package tycoongame.game.gui.event;

import tycoongame.buildings.StoreManager;

public class BuisnessEvent {

	public static final int MANAGER_SELECTED = 3;
    private String managerName;
    private int action;
    private StoreManager manager;

    public BuisnessEvent( StoreManager storeManager , int action) 
    {
        this.manager = storeManager;
        this.action = action;
    }
    
    /**
     * @return the action
     */
    public int getAction() {
        return action;
    }

    /**
     * @return the manager
     */
    public StoreManager getManager() {
        return manager;
    }
}
