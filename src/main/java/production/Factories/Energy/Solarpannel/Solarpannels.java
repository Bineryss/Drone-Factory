package production.Factories.Energy.Solarpannel;

import ImportandEnums.BuildingTypes;
import management.ManagementSystems.ResourceManagement;
import production.Factories.Building;
import production.Factories.BuildingInformationElement;
import specificexceptions.NotEnoughStorageException;

/**
 * Solarpannel - Produziert Energie
 * <p>
 * ID: 0
 */
public class Solarpannels extends Building {
    private static int cc = -1;

    public Solarpannels() {
        super(BuildingTypes.SOLARPANNEL);
        cc++;
        id = cc;
    }

    @Override
    public void updateBuilding() throws NotEnoughStorageException {
        if (!dataEntity.inConstruction()) {
            generateEnergy();
        }
    }

    private void generateEnergy() throws NotEnoughStorageException {
        ResourceManagement.addEnergy(dataEntity.getEfficiency());
    }

    @Override
    public BuildingInformationElement getInformation() {
        return new BuildingInformationElement(dataEntity);
    }

}
