import Management.BuildingManagement;
import Management.DroneManagement;
import Management.ResourceManagement;
import Production.Dronen.Drone;
import Production.Dronen.Normal.DefaultDrone;
import Production.Factories.Building;
import Production.Factories.Produktion.DroneFactory;

import java.util.Scanner;

public class TestDroneFactory {

    private static void addBulding(Building tmp, Drone drone) {
        BuildingManagement.addBuilding(tmp);
        Drone[] tmpD = new Drone[]{drone};
        tmp.build(tmpD);
    }

    private static void newDrones(int n) {
        for (int i = 0; i < n; i++) {
            DefaultDrone tmp = new DefaultDrone();
            DroneManagement.addDrone(tmp);
        }
    }

    public static void main(String[] args) {/*
        ResourceManagement.addENERGY(2000);
        ResourceManagement.addRESOURCES(1000);

        DroneManagement.start();
        BuildingManagement.start();
        newDrones(5);

        addBulding(new DroneFactory(), DroneManagement.getDrone(0));
        ((DroneFactory) BuildingManagement.getBuilding(new int[]{1, 0})).loadEnergy(200);
        ((DroneFactory) BuildingManagement.getBuilding(new int[]{1, 0})).loadResources(100);
        ((DroneFactory) BuildingManagement.getBuilding(new int[]{1, 0})).startProduction(new DefaultDrone());

        int zug = 0;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("-----------Zug: " + zug + "---------------");
            System.out.println(ResourceManagement.print());
            System.out.println(DroneManagement.print());
            System.out.println(BuildingManagement.getBuilding(new int[]{1,0}));
            BuildingManagement.update();
            int eingabe = sc.nextInt();
            switch (eingabe) {
                case 0:System.exit(0);
                    break;
                case 1:((DroneFactory) BuildingManagement.getBuilding(new int[]{1, 0})).startProduction(new DefaultDrone());
                    break;
                default:
                    break;
            }
            zug ++;
        }*/

    }
}
