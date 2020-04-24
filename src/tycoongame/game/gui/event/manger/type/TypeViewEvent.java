package tycoongame.game.gui.event.manger.type;

import tycoongame.controller.event.InputEvent;

public class TypeViewEvent extends InputEvent {

    private String bT;

    public TypeViewEvent(String bT)
    {
        this.bT = bT;
    }

    /**
     * @return the bT
     */
    public String getbT() {
        return bT;
    }
}
