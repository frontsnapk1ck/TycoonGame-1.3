package tycoongame.zzzzzz.event;

public class GameEvent {

    private Object source;
    private double increace;
    private int day;

    public GameEvent(Object source , double increace , int day)
    {
        this.source = source;
        this.increace = increace;
        this.day = day;
    }

    /**
     * @return the change
     */
    public double getChange() {
        return increace;
    }

    /**
     * @return the source
     */
    public Object getSource() {
        return source;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

}