package production.Factories;

import production.Dronen.Drone;
import production.Factories.Connector.EnergyConnection;
import production.Factories.Connector.ResourceConnection;

import java.util.List;

public abstract class BuildingInformationElement {
    protected EnergyConnection energy;
    protected ResourceConnection storage;

    protected int construction;
    protected int efficiency;

    protected List<Drone> workers;

    protected BuildingInformationElement update(EnergyConnection energy, ResourceConnection storage,
                                                int construction, int efficiency, List<Drone> workers) {
        this.energy = energy;
        this.storage = storage;
        this.construction = construction;
        this.efficiency = efficiency;
        this.workers = workers;
        return this;
    }
}
