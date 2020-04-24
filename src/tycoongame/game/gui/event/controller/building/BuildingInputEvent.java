package tycoongame.game.gui.event.controller.building;

public class BuildingInputEvent {

    public static final String VIEW_COMMAND = "view";
    public static final String UPGRADE_COMMAND = "upgrade";
    public static final String SELL_COMMAND = "sell";

    private String command;
    private String building;

    public BuildingInputEvent ( String command , String building )
    {
        this.command = command;
        this.building = building;
    }

    /**
     * @return the building
     */
    public String getBuilding() {
        return building;
    }

    /**
     * @return the command
     */
    public String getCommand() {
        return command;
    }
}   