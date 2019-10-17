package production.Factories.building;

import ImportandEnums.BuildingTypes;
import ImportandEnums.DroneTypes;
import management.ManagementSystems.DroneManagement;
import management.ManagementSystems.ResourceManagement;
import production.Dronen.Drone;
import specificexceptions.DroneNotEnoughEnergyException;
import specificexceptions.NotEnoughResourceException;

import java.util.List;

public class BuildingConstructionHandler implements Updatable {
    private Building building;
    private int constructionCost;
    private int construction;

    private List<Drone> workers;

    private boolean resourcesForBuildingAvailable;
    private boolean inConstruction;

    public BuildingConstructionHandler() {

    }

    @Override
    public void update() throws DroneNotEnoughEnergyException {
        if (inConstruction) {
            build();
        }
    }

    public void addWorkers(List<Drone> workers) {
        if (workers != null) {
            this.workers.addAll(workers);
        }
    }

    public void startConstruction(DroneTypes droneId, int droneCount) throws NotEnoughResourceException {
        workers.addAll(DroneManagement.giveDronesWork(droneId, droneCount));
        if (!resourcesForBuildingAvailable) {
            if (ResourceManagement.hasResources(constructionCost)) {
                ResourceManagement.removeResources(constructionCost);
                resourcesForBuildingAvailable = true;
            }
        }
    }

    private void build() throws DroneNotEnoughEnergyException {
        for (Drone worker : workers) {
            if (wontBeFinshed(worker)) {
                construction -= worker.workEfficiency();
            } else {
                worker.workEfficiency();
                construction = 0;
                worker.hasFinishedWork();
                inConstruction = false;
                break;
            }
        }
    }

    private boolean wontBeFinshed(Drone worker) {
        return (construction - worker.efficiency()) > 0;
    }

}
