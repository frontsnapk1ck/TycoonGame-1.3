package tycoongame.game.gui.event.input;

import tycoongame.buildings.Building;

public class InputEvent {

    private Object source;
    private int action;

    public InputEvent (Building s, int actoin)
    {
        this.source = s;
        this.action = actoin;
    }

    /**
     * @return the action
     */
    public int getAction() {
        return action;
    }

    /**
     * @return the building
     */
    public Object getSource() {
        return source;
    }

}