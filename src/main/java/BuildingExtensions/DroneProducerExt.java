package BuildingExtensions;

import ImportandEnums.DroneTypes;
import Production.Dronen.Drone;

import java.util.LinkedList;
import java.util.List;

public class DroneProducerExt implements Extension{
    private static final String ICON = "(%s): %d";
    private static int maxDronestorable;

    private DroneTypes factorisedDrone;
    private final List<Drone> drones;
    private final List<DroneTransferExt> transfers;

    public DroneProducerExt(DroneTypes factoricedDrone) {
        maxDronestorable = 5;
        this.factorisedDrone = factoricedDrone;
        drones = new LinkedList<>();
        transfers = new LinkedList<>();
    }

    public void update() {
        for (DroneTransferExt tmp: transfers) {
            if(tmp.needsDrone() && !drones.isEmpty()) {
                tmp.giveDrone(drones.get(0));
            }
        }
    }


    public void setProducion(DroneTypes drone) {
        factorisedDrone = drone;
    }

    public void setTransfer(DroneTransferExt transfer) {
        transfers.add(transfer);
    }


    public DroneTypes getDroneType() {
        return factorisedDrone;
    }

    public void addDrone(Drone drone) {
        drones.add(drone);
    }

    public String toString() {
        return String.format(ICON, factorisedDrone.getIcon(),drones.size());
    }

    public boolean isFull() {
        return drones.size() == maxDronestorable;
    }
}
