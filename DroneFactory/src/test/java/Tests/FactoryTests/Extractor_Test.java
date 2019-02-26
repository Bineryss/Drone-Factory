package Tests.FactoryTests;

import ImportandEnums.DroneTypes;
import Management.ManagementSystems.ResourceManagement;
import Production.Factories.Connector.InternalStorage;
import Production.Factories.Resources.Extractor;
import SpecificExceptions.BuildingUnfinishedException;
import org.junit.Test;

public class Extractor_Test extends BuildingTest_Setup{

    @Test
    public void testUpdateExtractor() {
        Extractor extr = new Extractor();
        extr.startConstruction(DroneTypes.DEFAULTDRONE, 2);
        for (int i = 0; i < 6; i++) {
            extr.update();
        }
        loadBuilding(extr);
        try {
            InternalStorage stor = (InternalStorage) extr.getStorage();
            stor.addTransportDrone(DroneTypes.DEFAULTDRONE);
        } catch (BuildingUnfinishedException e) {
            assert false;
        }

        System.out.println(ResourceManagement.print() + "\n");
        System.out.println(extr + "\n");

        for (int i = 0; i < 20; i++) {
            extr.update();
            System.out.printf("%2d: %s%n", i, extr);
        }
        System.out.println(ResourceManagement.print());
        System.out.println(extr);
        System.out.println();
    }
}
