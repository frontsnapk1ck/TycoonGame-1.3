package tycoongame.game.gui.event.controller.building;

public interface BuildingInputListener {

    public void onBuildingUpgrade ( BuildingInputEvent e);

    public void onBuildingSell ( BuildingInputEvent e);

    public void onBuildingView (BuildingInputEvent e);

}