package Production.Factories.Produktion;

import BuildingExtensions.DroneProducerExt;
import Management.DroneManagement;
import Management.Resources.Energy;
import ImportandEnums.Type;
import Management.Resources.Storage;
import Production.Dronen.Drone;
import Production.Factories.Building;

import java.util.LinkedList;
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

    private static List<Type> produceableDronesId;
    private Drone producedElement;

    private DroneProducerExt prod;
    private boolean activateProd;


    public DroneFactory() {
        super();
        cc++;
        id = cc;
        type = Type.DRONEFACTORY;

        constructionCost = Type.DRONEFACTORY.getCosts();
        construction = Type.DRONEFACTORY.getConstructionTime();

        energy = new Energy(200, 10);
        storage = new Storage(Type.DRONEFACTORY.getMaxCapacity());

        efficiency = 1;


        produceableDronesId = new LinkedList<>();
        produceableDronesId.add(Type.DEFAULTDRONE);
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

    public void addDroneProducerExtension(Type drone) {
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

    public void startProduction(Type drone) {
        if (!activateProd) {
            startProductionAutomatic(drone);
        }
    }

    /**
     * Started die Produktion einer Drone und verbraucht schonmal die benoetigten Resourcen
     *
     * @param drone: Typ der Drone
     */
    private void startProductionAutomatic(Type drone) {
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

    private boolean canBeBuild(Type tmp) {
        if (produceableDronesId.contains(tmp)) {
            return true;
        }
        return false;
    }

    public String toString() {
        String print = "[ " + type.getIcon() + " || " + printResource() + " (";
        if (producedElement != null) {
            print += isWorkRemaining();
        }
        print += ") ";
        print += prod;
        print += " ]" + constructionStatus();
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
}
