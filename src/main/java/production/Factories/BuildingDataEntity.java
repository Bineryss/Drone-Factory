package production.Factories;

import ImportandEnums.BuildingTypes;
import lombok.Data;
import production.Dronen.Drone;
import production.Factories.Connector.EnergyConnection;
import production.Factories.Connector.ResourceConnection;
import specificexceptions.NotEnoughEnergyException;

import java.util.ArrayList;
import java.util.List;

@Data
public class BuildingDataEntity {
    protected int efficiency;
    protected int constructionCost;
    protected int construction;

    protected boolean resourcesForBuildingAvailable;

    protected ResourceConnection storage;
    protected EnergyConnection energy;

    protected List<Drone> workers;

    public BuildingDataEntity(BuildingTypes type) {
        constructionCost = type.getCosts();
        construction = type.getConstructionTime();
        efficiency = type.getEfficiency();
        energy = new EnergyConnection(type);
        workers = new ArrayList<>();
    }

    public void clearWorkers() {
        workers = null;
    }

    public void addWorkers(List<Drone> workers) {
        if (workers != null) {
            this.workers.addAll(workers);
        }
    }

    public boolean inConstruction() {
        return construction != 0;
    }

    public boolean wontBeFinshed(Drone worker) {
        return (construction - worker.efficiency()) > 0;
    }

    public void work(int ammount) {
        construction -= ammount;
    }

    public boolean hasResourcesForConstruction() {
        return resourcesForBuildingAvailable;
    }

    public boolean hasEnergy() {
        return energy.hasEnergy();
    }

    public void useEnergy() throws NotEnoughEnergyException {
        energy.useEnergy();
    }
}
