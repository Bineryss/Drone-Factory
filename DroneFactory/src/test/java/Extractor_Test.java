import Management.*;
import Management.Resources.ResourceManagement;
import Production.Dronen.Normal.DefaultDrone;
import Production.Factories.Resources.Extractor;
import Production.Factories.Resources.ExtractorTyp;
import org.junit.Before;
import org.junit.Test;

public class Extractor_Test {
    private Extractor extr;

    @Before
    public void start() {
        ResourceManagement.start();
        ResourceManagement.addEnergy(200);
        String resource = "0.200,1.200,2.200";
        ResourceManagement.addResources(ResourceManagement.generateResourceArray(resource));
        DroneManagement.start();
        DroneManagement.addDrone(new DefaultDrone());
        DroneManagement.addDrone(new DefaultDrone());
        DroneManagement.addDrone(new DefaultDrone());
        DroneManagement.addDrone(new DefaultDrone());

        extr = new Extractor(ExtractorTyp.CARBON);
        extr.build(0);
        extr.addDrone(0);
        extr.loadEnergy(100);
        System.out.println(extr);
    }

    @Test
    public void testUpdate() {
        for(int i = 0; i < 3; i++) {
            extr.update();
            System.out.println(extr);
        }
        extr.storeResources();
        System.out.println(extr);
    }
}
