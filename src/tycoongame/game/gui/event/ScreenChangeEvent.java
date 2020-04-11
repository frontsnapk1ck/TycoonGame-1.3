package tycoongame.game.gui.event;

import tycoongame.controller.event.ScreenEvent;

public class ScreenChangeEvent extends ScreenEvent{

    public static final int SWITCH_TO_MANAGER = 2;
    public static final int SWITCH_TO_BUSINESS = 4;

    private Object source;
    private String name;
    private int action;

    public ScreenChangeEvent(Object source, String name , int action)
    {
        this.source = source;
        this.name = name;
        this.action = action;
    }

    /**
     * @return the source
     */
    public Object getSource() {
        return source;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the action
     */
    public int getAction() {
        return action;
    }
}
