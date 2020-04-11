package tycoongame.game.gui.event;

import tycoongame.controller.event.ScreenEvent;

public class ScreenChangeEvent extends ScreenEvent{

    private Object source;
    private String name;

    public ScreenChangeEvent(Object source, String name)
    {
        this.source = source;
        this.name = name;
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
}
