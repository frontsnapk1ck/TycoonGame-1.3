package tycoongame.game.gui.event.manger.manager;

import tycoongame.controller.event.InputListener;

public interface ManagerListener extends InputListener {

    public void onManagerPurchace ( BuyManagerEvent e);

    public void onManagerUpgrade ( ManagerUpgradeEvent e);

    public void onManagerView ( ViewManagerEvent e);
}