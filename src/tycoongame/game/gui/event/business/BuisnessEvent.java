package tycoongame.game.gui.event.business;

import tycoongame.buildings.StoreManager;

public class BuisnessEvent {

    private StoreManager manager;

    public BuisnessEvent( StoreManager storeManager ) 
    {
        this.manager = storeManager;
    }

    /**
     * @return the manager
     */
    public StoreManager getManager() {
        return manager;
    }

}