package production.Factories.Produktion.Dronefactory;

import BuildingExtensions.DroneProducerExt;
import ImportandEnums.BuildingTypes;
import ImportandEnums.DroneTypes;
import lombok.Data;
import production.Dronen.Drone;
import production.Factories.BuildingDataEntity;

import java.util.ArrayList;
import java.util.List;

@Data
class DronefactoryDataEntity extends BuildingDataEntity {
    private int productionStatus;
    private boolean isProducing;

    private static List<DroneTypes> producibleDrones;
    private Drone producedElement;

    private DroneProducerExt prod;
    private boolean activateProd;

    DronefactoryDataEntity(BuildingTypes type) {
        super(type);
        producibleDrones = new ArrayList<>();
        producibleDrones.add(DroneTypes.BUILDINGDRONE);
        producibleDrones.add(DroneTypes.CARRIERDRONE);
        producibleDrones.add(DroneTypes.DEFAULTDRONE);
    }

    public static List<DroneTypes> getProducibleDrones() {
        return producibleDrones;
    }

    String convertProducibleDrones() {
        StringBuilder out = new StringBuilder();
        for (DroneTypes dro : producibleDrones) {
            out.append(dro.getIcon() + ", ");
        }
        return out.toString().substring(0, out.length() - 2);
    }

    void factorise() {
        productionStatus -= efficiency;
    }

    boolean isProducing() {
        return isProducing;
    }

    void startProducing() {
        isProducing = true;
    }

    boolean isProductionFinished() {
        return productionStatus == 0;
    }

    boolean prodIsActive() {
        return activateProd;
    }

    boolean prodHasAcces() {
        return prod != null;
    }

    void addCurrentDroneToProd() {
        prod.addDrone(producedElement);
    }

    void clearProducedElement() {
        producedElement = null;
    }

    void addProductionTime(int amount) {
        productionStatus += amount;
    }
}
