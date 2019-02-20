package BuildingExtensions;

import Production.Dronen.Drone;

import java.util.LinkedList;
import java.util.List;

public class DroneProducerExt implements Extension{
    private static final String ICON = "(>#)";
    private static int maxDronestorable;

    private Drone factorisedDrone;
    private List<Drone> drones;
    private List<DroneTransferExt> transfers;

    public DroneProducerExt(Drone factoricedDrone) {
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


    public void setProducion(Drone drone) {
        factorisedDrone = drone;
    }

    public void setTransfer(DroneTransferExt transfer) {
        transfers.add(transfer);
    }


    public Drone getDrone() {
        return factorisedDrone;
    }

    public void addDrone(Drone drone) {
        drones.add(drone);
    }
}
