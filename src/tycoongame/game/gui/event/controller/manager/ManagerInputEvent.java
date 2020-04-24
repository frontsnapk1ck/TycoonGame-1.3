package tycoongame.game.gui.event.controller.manager;

public class ManagerInputEvent {

    public static final String HIRE_COMMAND = "hire";
    public static final String UPGRADE_MULT = "upgrade multipler";
    public static final String UPGRADE_NUM = "upgrade max num buildings";
    public static final String VIEW_BUILDING = "view";

    private String command;
    private String sMan;

    public ManagerInputEvent ( String command , String sMan )
    {
        this.sMan = sMan;
        this.command = command;
    }

    /**
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * @return the sMan
     */
    public String getsMan() {
        return sMan;
    }

}   