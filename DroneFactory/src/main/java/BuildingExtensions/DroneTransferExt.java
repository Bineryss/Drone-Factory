package BuildingExtensions;

import Production.Dronen.Drone;

public class DroneTransferExt implements Extension{
    private static final String ICON = "(<<)";

    private boolean needsDrone;

    public DroneTransferExt() {
        this.needsDrone = false;
    }

    public void isInNeed() {
        needsDrone = false;
    }

    public boolean needsDrone() {
        return needsDrone;
    }

    public void giveDrone(Drone drone) {

    }
}
