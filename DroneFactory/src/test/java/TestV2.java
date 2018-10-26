import Management.BuildingManagement;
import Management.DroneManagement;
import Management.ResourceManagement;
import Production.Dronen.Drone;
import Production.Dronen.Normal.DefaultDrone;
import Production.Factories.Building;
import Production.Factories.Energy.Solarpannels;
import Production.Factories.Produktion.DroneFactory;
import Production.Factories.Resources.Extractor;
import java.util.Scanner;


public class TestV2 {

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

    public static void main(String[] args) {
        ResourceManagement.addENERGY(100);
        ResourceManagement.addRESOURCES(20);

        DroneManagement.start();
        BuildingManagement.start();
        newDrones(5);
        int zug = 0;
        while (true) {
        zug(zug++);
        }
    }

    private static void zug(int zug) {
        BuildingManagement.update();
        boolean run = true;
        do {
            System.out.println("-----------Zug: " + zug + "---------------");
            System.out.println(ResourceManagement.print());
            System.out.println(DroneManagement.print());
            System.out.println(BuildingManagement.print());
            try {
                run = runEingabe();
            }catch(Exception e) {
                System.out.println("Nicht vorhanden!");
            }
        }while (run);
        zug ++;
    }

    private static boolean runEingabe() {
        int eingabe;
        switch (intEingabe()) {
            case 0:
                addBulding(new Solarpannels(), DroneManagement.getDrone(0));
                break;
            case 1:
                addBulding(new DroneFactory(), DroneManagement.getDrone(0));
                break;
            case 2:
                System.out.println("Labor kommt noch");
                break;
            case 3:
                addBulding(new Extractor(), DroneManagement.getDrone(0));
                break;
            case 4:
                System.out.println("Storage kommt noch");
                break;
            case 5:
                eingabe = intEingabe();
                BuildingManagement.getBuilding(new int[]{3, eingabe}).loadEnergy(intEingabe());
                ((Extractor) BuildingManagement.getBuilding(new int[]{3, eingabe})).addDrone(0);
                break;
            case 6:
                BuildingManagement.getBuilding(new int[]{intEingabe(), intEingabe()}).build(new Drone[]{DroneManagement.getDrone(0)});
                break;
            case 7:
                eingabe = intEingabe();
                BuildingManagement.getBuilding(new int[]{1, eingabe}).loadEnergy(intEingabe());
                BuildingManagement.getBuilding(new int[]{1, eingabe}).loadResources(intEingabe());
                break;
            case 8:
                DroneFactory tmp = ((DroneFactory) BuildingManagement.getBuilding(new int[]{1, intEingabe()}));
                tmp.startProduction(new DefaultDrone());
                break;
            case 9:
                return false;
        }
        return true;
    }


    private static int intEingabe() {
        Scanner sc = new Scanner(System.in);
        int ausgabe = 0;
        boolean fertig = false;
        while (!fertig) {
            try {
                System.out.print("Zahl: ");
                ausgabe = Integer.parseInt(sc.nextLine());
                return ausgabe;
            } catch (NumberFormatException e) {
                System.out.println("Bitte eine ganze Zahl eingeben!");
            }
        }
        return ausgabe;
    }
}
