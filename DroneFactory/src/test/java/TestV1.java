import Production.Dronen.Drone;
import Production.Dronen.Normal.DefaultDrone;
import Production.Factories.Building;
import Production.Factories.Produktion.DroneFactory;
import Management.ResourceManagement;
import List.*;

public class TestV1 {
    private static List <Building> factories = new List<Building>();
    private static List <Drone> drones = new List<Drone>();

    private static void buildFactory(Building factory, Drone[] drones) {
        factories.append(factory);
        factories.toFirst();
        factories.getContent().build(drones);
    }

    public static void main(String[] args) {
        ResourceManagement.addRESOURCES(300);
        ResourceManagement.addENERGY(500);


        drones.append(new DefaultDrone());
        //drones.append(new DefaultDrone());


        drones.toFirst();
        Drone[] tmp = new Drone[]{drones.getContent()};
        buildFactory(new DroneFactory(), tmp);
        if(drones.getContent().isDead()) {
            drones.remove();
        }


        System.out.println("Die 1. Drone: " + drones.getContent());
        System.out.println("Die 1. Fabrik: " + factories.getContent());


        ((DroneFactory) factories.getContent()).loadEnergy(100);
        ((DroneFactory) factories.getContent()).loadResources(40);

        System.out.println("Die 1. Fabrik, nachdem sie geladen wurde: " + factories.getContent());



        //drones.append(((DroneFactory) factories.getContent()).startProduction(new DefaultDrone()));
        System.out.println("Alle aktuellen Dronen: " + printDrones());
        ((DroneFactory) factories.getContent()).loadResources(40);

        //drones.append(((DroneFactory) factories.getContent()).startProduction(new DefaultDrone()));

        System.out.println("Alle Dronen: " + printDrones());

    }

    private static String printDrones() {
        String str = "";
        drones.toFirst();
        while (drones.hasAccess()) {
            str += drones.getContent().toString() + ", ";
            drones.next();
        }
        return str;
    }
}
