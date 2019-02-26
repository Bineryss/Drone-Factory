package Production.Dronen;

import ImportandEnums.DroneTypes;
import Management.Resources.Energy;
import Management.Resources.Storage;
import SpecificExceptions.DroneNotEnoughEnergyException;
import SpecificExceptions.NotEnoughEnergyException;

/**
 * <h3>Drone</h3>
 * Eine Drone, verwaltet produktionskosten, erhaltungskosten, produktivität, lebensspanne und noch mehr.
 * <p>
 */
public class Drone {
    private DroneTypes type;
    private int costs;
    private int productionTime;
    private int efficiency;
    private boolean isOccupied;

    //Energie Speicher
    protected Energy energy;

    //Resource Speicher
    private Storage resource;


    public Drone(DroneTypes type) {
        this.type = type;
        costs = type.getCosts();
        efficiency = type.getEfficiency();
        isOccupied = false;
        energy = new Energy(type.getMaxCapacityEnergy(), type.getEnergyUse(), type.getMaxCapacity());
        resource = new Storage(type.getMaxCapacity());
        productionTime = type.getConstructionTime();
    }


    public boolean hasEnergy() {
        return energy.hasEnergy();
    }

    public int energyLeft() {
        return energy.availableEnergy();
    }

    public boolean hasMaxEnergy() {
        return energy.isFull();
    }

    public int getProductionTime() {
        return productionTime;
    }

    public int getCosts() {
        return costs;
    }

    public int workEfficiency() throws DroneNotEnoughEnergyException {
        if (!isDead()) {
            if (hasEnergy()) {
                try {
                    energy.useEnergy();
                } catch (NotEnoughEnergyException e) {
                    throw new DroneNotEnoughEnergyException();
                }
            }
            return efficiency;
        }
        return 0;
    }

    public void occupied() {
        isOccupied = true;
    }

    public boolean hasWorkToDo() {
        return isOccupied;
    }

    public void hasFinishedWork() {
        isOccupied = true;
    }

    public int efficiency() {
        return efficiency;
    }

    //Gibt den Status der Drone an
    public boolean isDead() {
        return energy.availableEnergy() == 0;
    }

    public DroneTypes getType() {
        return type;
    }

    /**
     * @return: " {D}: Symbol einer Drone und uebrige arbeitskraft.
     */
    public String toString() {
        return type.getIcon() + " : " + energyLeft();
    }
}
