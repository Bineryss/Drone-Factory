package production.Factories.Produktion.Dronefactory;

import BuildingExtensions.DroneProducerExt;
import ImportandEnums.DroneTypes;
import production.Dronen.Drone;
import production.Factories.BuildingInformationElement;
import production.Factories.Connector.EnergyConnection;
import production.Factories.Connector.ResourceConnection;

import java.util.List;

class DroneFactoryInformationElement extends BuildingInformationElement {
    private int workStatus;
    private boolean isWorking;

    private Drone producedElement;
    private DroneProducerExt prod;

    private List<DroneTypes> producibleDrones;


    public DroneFactoryInformationElement update(EnergyConnection energy, ResourceConnection storage,
                                             int construction, int efficiency, List<Drone> workers,
                                             int workStatus, boolean isWorking, Drone producedElement,
                                             DroneProducerExt prod, List<DroneTypes> producibleDrones) {
        super.update(energy, storage, construction, efficiency, workers);
        this.workStatus = workStatus;
        this.isWorking = isWorking;
        this.producedElement = producedElement;
        this.prod = prod;
        this.producibleDrones = producibleDrones;
        return this;
    }

    public int getWorkStatus() {
        return workStatus;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public Drone getProducedElement() {
        return producedElement;
    }

    public DroneProducerExt getProd() {
        return prod;
    }

    public String getProducibleDrones() {
        StringBuilder out = new StringBuilder();
        for (DroneTypes dro : producibleDrones) {
            out.append(dro.getIcon() + ", ");
        }
        return out.toString().substring(0, out.length() - 2);
    }

    int getEnergy() {
        return energy.availableEnergy();
    }

    int getStorage() {
        try {
            return storage.inStorage();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    int getConstruction() {
        return construction;
    }
}
