package tycoongame.game.gui.event.manger.building;

import tycoongame.controller.event.InputListener;

public interface BuildingListner extends InputListener {

    public void onBuildingSold ( PropertyExchangeEvent e);
    public void onBuildingBought ( PropertyExchangeEvent e);

    public void onBuildingUpgrade ( PropertyUpgradeEvent e );

    public void onBuildingView ( BuildingViewEvent e );

}