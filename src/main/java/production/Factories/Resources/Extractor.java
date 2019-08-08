package production.Factories.Resources;

import ImportandEnums.BuildingTypes;
import production.Factories.*;
import specificexceptions.DroneNotEnoughEnergyException;
import specificexceptions.NotEnoughEnergyException;
import specificexceptions.NotEnoughResourceException;

/**
 * <h3>Extractor</h3>
 * <p>Produziert Resourcen, wenn genug Energie vorhanden ist.
 * <p/>
 * ID: 3
 */
public class Extractor extends Building {
    private static int cc = -1;

    public Extractor() {
        super(BuildingTypes.EXTRACTOR);
        cc++;
        id = cc;
    }

    @Override
    public void updateBuilding() throws NotEnoughEnergyException, NotEnoughResourceException, DroneNotEnoughEnergyException {
        if (isReady()) {
            produceResources();
        }
    }

    @Override
    public BuildingInformationElement inform() {
        return null;
    }

    private void produceResources() throws NotEnoughEnergyException, NotEnoughResourceException, DroneNotEnoughEnergyException {
        if (!storage.isFull()) {
                energy.useEnergy();
                extractResource();
        }
    }

    private void extractResource() throws NotEnoughResourceException, DroneNotEnoughEnergyException {
        if (storage.canStore(efficiency)) {
            storage.storeResources(efficiency);
        }
    }


    /**
     * @return: Fertige Ausgabe
     */
//    public String toString() {
//        return "[ " + buildingTypes.getIcon() + " |" + printResource() + " ]" + constructionStatus();
//    }

}
