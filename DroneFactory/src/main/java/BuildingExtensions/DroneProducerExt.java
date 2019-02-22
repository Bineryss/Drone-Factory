package BuildingExtensions;

import ImportandEnums.DroneTypes;
import Management.DroneManagement;
import Production.Dronen.Drone;

import java.util.LinkedList;
import java.util.List;

public class DroneProducerExt implements Extension{
    private static final String ICON = "(%s): %d";
    private static int maxDronestorable;

    private Drone blueprint;
    private List<Drone> drones;
    private List<DroneTransferExt> transfers;

    public DroneProducerExt(DroneTypes factoricedDrone) {
        maxDronestorable = 5;
        this.blueprint = DroneManagement.getBlueprint(factoricedDrone);
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
        blueprint = DroneManagement.getBlueprint(drone);
    }

    public void setTransfer(DroneTransferExt transfer) {
        transfers.add(transfer);
    }


    public DroneTypes getDroneType() {
        return blueprint.getType();
    }

    public void addDrone(Drone drone) {
        drones.add(drone);
    }

    public String toString() {
        return String.format(ICON, blueprint.getIcon(),drones.size());
    }

    public boolean isFull() {
        return drones.size() == maxDronestorable;
    }
}
