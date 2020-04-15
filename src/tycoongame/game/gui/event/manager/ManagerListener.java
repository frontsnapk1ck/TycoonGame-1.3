package tycoongame.game.gui.event.manager;

import tycoongame.game.gui.event.screenchange.ScreenChangeEvent;

public interface ManagerListener {

    public void onBuildingSelect ( ManagerEvent event );

    public void onBackButtonSelect ( ScreenChangeEvent event );

}