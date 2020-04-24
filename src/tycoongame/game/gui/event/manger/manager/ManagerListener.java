package tycoongame.game.gui.event.manger.manager;

import tycoongame.controller.event.InputListener;
import tycoongame.game.gui.event.manger.building.BuildingViewEvent;

public interface ManagerListener extends InputListener {

    public void onManagerPurchace ( BuyManagerEvent e);

    public void onManagerUpgrade ( ManagerUpgradeEvent e);

    public void onManagerView ( ViewManagerEvent e);

	public void onBuildingView(BuildingViewEvent event);
}