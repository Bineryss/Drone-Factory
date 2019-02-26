package Production.Factories.Resources;

import ImportandEnums.BuildingTypes;
import Production.Factories.*;

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
    public void updateBuilding() {
        if (isReady()) {
            produceResources();
        }
    }

    private void produceResources() {
        if (!storage.isFull()) {
            try {
                energy.useEnergy();
                extractResource();
            } catch (IllegalArgumentException e) {
                System.out.println("So viel Energie ist nicht vorhanden!");
            }
        }
    }

    private void extractResource() {
        if (storage.canStore(efficiency)) {
            storage.storeResources(efficiency);
        }
    }


    /**
     * @return: Fertige Ausgabe
     */
    public String toString() {
        return "[ " + buildingTypes.getIcon() + " |" + printResource() + " ]" + constructionStatus();
    }

}
