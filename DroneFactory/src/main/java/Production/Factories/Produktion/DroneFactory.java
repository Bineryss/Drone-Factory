package Production.Factories.Produktion;

import BuildingExtensions.DroneProducerExt;
import ImportandEnums.DroneTypes;
import Management.ManagementSystems.DroneManagement;
import ImportandEnums.BuildingTypes;
import Production.Dronen.Drone;
import Production.Factories.Building;
import Production.Factories.Connector.InternalStorage;
import SpecificExceptions.BuildingUnfinishedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Dronefactory - Produziert Dronen aller art.
 * <p>
 * ID: 1
 */
public class DroneFactory extends Building {
    private static int cc = -1;

    private int workStatus;
    private boolean isWorking;

    private static List<DroneTypes> produceableDronesId;
    private Drone producedElement;

    private DroneProducerExt prod;
    private boolean activateProd;


    public DroneFactory() {
        super(BuildingTypes.DRONEFACTORY);
        cc++;
        id = cc;
        produceableDronesId = new ArrayList<>();
        produceableDronesId.add(DroneTypes.BUILDINGDRONE);
        produceableDronesId.add(DroneTypes.CARRIERDRONE);
        produceableDronesId.add(DroneTypes.DEFAULTDRONE);
    }

    /**
     * Jeweils ein Zyclus beudeutet eine Runde
     * Die Fabrik benoetigt eine gewisse anzahl an Runden um die entschprechende Drone zu Produzieren
     * Die Fabrik verbraucht pro Runde jeweils einen gewissen betrag an Energie
     * Ist keine Energie mehr vorhanden, wird nicht weitergearbeitet
     */
    @Override
    protected void updateBuilding() {
        if (isReady()) {
            if (isWorking && energy.hasEnergy()) {
                energy.useEnergy();
                workStatus -= efficiency;
                finishDrone();
            }
            if (activateProd) {
                droneExtension();
            }
        }
    }

    public void addDroneProducerExtension(DroneTypes drone) {
        if (isReady()) {
            prod = new DroneProducerExt(drone);
        }
    }

    private void droneExtension() {
        if (prod != null) {
            if (!isWorking && !prod.isFull()) {
                startProductionAutomatic(prod.getDroneType());
            }
        }
    }

    public void startProduction(DroneTypes drone) throws BuildingUnfinishedException {
        if (isReady() && !activateProd) {
            startProductionAutomatic(drone);
        } else {
            throw new BuildingUnfinishedException();
        }
    }

    /**
     * Started die Produktion einer Drone und verbraucht schonmal die benoetigten Resourcen
     *
     * @param drone: Typ der Drone
     */
    private void startProductionAutomatic(DroneTypes drone) {
        if (canBeBuild(drone)) {
            if (!isWorking && isReady()) {
                if (storage.hasResources(drone.getCosts())) {
                    isWorking = true;
                    producedElement = DroneManagement.typeToDrone(drone);
                    workStatus += drone.getConstructionTime();
                    storage.useResources(drone.getCosts());
                } else {
                    System.out.println("Du hast nicht genuegend Resourcen fuer diese Drone!");
                }
            } else {
                System.out.println("Es wird bereits eine Drone Produziert!");
            }
        }
    }

    /**
     * Fertigt die Drone an
     */
    private void finishDrone() {
        if (isWorking) {
            if (workStatus == 0) {
                isWorking = false;
                if (!activateProd) {
                    DroneManagement.addDrone(producedElement);
                } else {
                    prod.addDrone(producedElement);
                }
                producedElement = null;
            }
        }
    }

    private boolean canBeBuild(DroneTypes tmp) {
        return produceableDronesId.contains(tmp);
    }

    public String toString() {
        String print = "[ " + buildingTypes.getIcon() + " |"+ printResource() + "| (";
        if (producedElement != null) {
            print += isWorkRemaining();
        }
        print += ") ";
        if (prod != null) {
            print += prod + " ";
        }
        print += "]" + constructionStatus();
        return print;
    }

    private String isWorkRemaining() {
        StringBuilder str = new StringBuilder(producedElement.getType().getIcon());
        if (isWorking) {
            str.append(": ");
            for (int i = 0; i < workStatus; i++) {
                str.append("|");
            }
        }
        return str.toString();
    }

    public void activatedProducer() {
        activateProd = true;
    }

    public void deactivatedProducer() {
        activateProd = false;
    }

    public void loadResources(int amount) {
//        if(!storage instanceof DirectResourceConnection) {
        if (storage.canStore(amount) && isReady()) {
            ((InternalStorage) storage).loadResources(amount);
        } else {
            System.out.println("So viel kannst du nicht lagern!");
        }
    }
}
