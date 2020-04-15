package tycoongame.zzzzzz.event;

public class GameEvent {

    public static final int NEW_DAY = 12;
    public static final int SELL_BUILDING = 2;
    // public static final int 
    // public static final int 
    // public static final int 

    private Object source;
    private int action;

    public GameEvent(Object source , int action)
    {
        this.source = source;
        this.action = action;
    }

    /**
     * @return the action
     */
    public int getAction() {
        return action;
    }

    /**
     * @return the source
     */
    public Object getSource() {
        return source;
    }
}