package tycoongame.game.gui.event.manger.type;

import tycoongame.buildings.BuildingType;
import tycoongame.controller.event.InputEvent;

public class TypeViewEvent extends InputEvent {

    private BuildingType bT;

    public TypeViewEvent(BuildingType bT)
    {
        this.bT = bT;
    }

    /**
     * @return the bT
     */
    public BuildingType getbT() {
        return bT;
    }
}
