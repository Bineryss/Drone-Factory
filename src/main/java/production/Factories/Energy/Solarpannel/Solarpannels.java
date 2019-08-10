package production.Factories.Energy.Solarpannel;

import ImportandEnums.BuildingTypes;
import management.ManagementSystems.ResourceManagement;
import production.Factories.Building;
import production.Factories.BuildingInformationElement;

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
    public void updateBuilding() {
        if (!dataEntity.inConstruction()) {
            generateEnergy();
        }
    }

    private void generateEnergy() {
        ResourceManagement.addEnergy(dataEntity.getEfficiency());
    }

    @Override
    public BuildingInformationElement getInformation() {
        return new BuildingInformationElement(dataEntity);
    }

}
