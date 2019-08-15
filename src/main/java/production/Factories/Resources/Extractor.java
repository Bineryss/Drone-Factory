package production.Factories.Resources;

import ImportandEnums.BuildingTypes;
import production.Factories.*;
import specificexceptions.DroneNotEnoughEnergyException;
import specificexceptions.NotEnoughEnergyException;
import specificexceptions.NotEnoughResourceException;
import specificexceptions.NotEnoughStorageException;

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
    protected void updateBuilding() throws NotEnoughEnergyException, NotEnoughResourceException, DroneNotEnoughEnergyException {
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
        try {
            dataEntity.getStorage().storeResources(dataEntity.getEfficiency());
        } catch (NotEnoughStorageException e) {
            try {
            dataEntity.getStorage().storeResources(
                    BuildingTypes.EXTRACTOR.getMaxCapacity() - dataEntity.getStorage().inStorage());
            } catch (NotEnoughStorageException f) {
                f.printStackTrace();
            }
        }
    }

    @Override
    public BuildingInformationElement getInformation() {
        BuildingInformationElement<BuildingDataEntity> out = new BuildingInformationElement<>(dataEntity);
        return out;
    }
}
