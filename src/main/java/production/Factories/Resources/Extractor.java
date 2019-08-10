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
        if (!dataEntity.inConstruction()) {
            produceResources();
        }
    }

    private void produceResources() throws NotEnoughEnergyException, NotEnoughResourceException, DroneNotEnoughEnergyException {
        if (!dataEntity.getStorage().isFull()) {
                dataEntity.getEnergy().useEnergy();
                extractResource();
        }
    }

    private void extractResource() throws NotEnoughResourceException, DroneNotEnoughEnergyException {
        if (dataEntity.getStorage().canStore(dataEntity.getEfficiency())) {
            dataEntity.getStorage().storeResources(dataEntity.getEfficiency());
        }
    }

    @Override
    public BuildingInformationElement getInformation() {
        return null;
    }

    /**
     * @return: Fertige Ausgabe
     */
//    public String toString() {
//        return "[ " + buildingType.getIcon() + " |" + printResource() + " ]" + constructionStatus();
//    }

}
