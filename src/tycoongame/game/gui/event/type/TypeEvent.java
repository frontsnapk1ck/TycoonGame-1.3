package tycoongame.game.gui.event.type;

import java.util.List;

import tycoongame.buildings.BuildingType;
import tycoongame.buildings.StoreManager;

public class TypeEvent {

    private int action;
    private List<StoreManager> sMans;
    private BuildingType bT;

    public TypeEvent( BuildingType bT, List<StoreManager> sMans, int action)
    {
        this.bT = bT;
        this.sMans = sMans;
        this.action = action;
    }

    /**
     * @return the action
     */
    public int getAction() {
        return action;
    }

    /**
     * @return the sMans
     */
    public List<StoreManager> getsMans() {
        return sMans;
    }

    /**
     * @return the bT
     */
    public BuildingType getbT() {
        return bT;
    }
}
