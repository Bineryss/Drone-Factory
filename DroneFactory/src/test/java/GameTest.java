
import Management.ManagementSystems.BuildingManagement;
import Management.ManagementSystems.DroneManagement;
import Management.ManagementSystems.ResourceManagement;
import ImportandEnums.Type;
import Production.Dronen.Drone;
import Production.Dronen.Normal.DefaultDrone;
import Production.Factories.Building;
import Production.Factories.Energy.Solarpannels;
import Production.Factories.Connector.InternalStorage;
import Production.Factories.Produktion.DroneFactory;
import Production.Factories.Resources.Extractor;
import SpecificExceptions.BuildingUnfinishedException;

import java.util.Scanner;

public class GameTest {

    private static void addBulding(Building tmp, Drone drone, int count) {
        tmp.startConstruction(drone.getType(), count);
        BuildingManagement.addBuilding(tmp);
    }

    private static void newDrones(int n) {
        for (int i = 0; i < n; i++) {
            DefaultDrone tmp = new DefaultDrone();
            DroneManagement.addDrone(tmp);
        }
    }

    public static void main(String[] args) {
        ResourceManagement.start();
        ResourceManagement.addEnergy(200);
        ResourceManagement.addResources(800);

        DroneManagement.start();
        BuildingManagement.start();
        newDrones(10);
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
            } catch (Exception e) {
                System.out.println("Nicht vorhanden!");
            }
        } while (run);
        zug++;
    }

    private static boolean runEingabe() {
        int eingabe;
        try {
            switch (intEingabe()) {
                case 0:
                    addBulding(new Solarpannels(), DroneManagement.getDrone(Type.DEFAULTDRONE), 1);
                    break;
                case 1:
                    addBulding(new DroneFactory(), DroneManagement.getDrone(Type.DEFAULTDRONE), 1);
                    break;
                case 2:
                    System.out.println("Labor kommt noch");
                    break;
                case 3:
                    addBulding(new Extractor(), DroneManagement.getDrone(Type.DEFAULTDRONE), 1);
                    break;
                case 4:
                    System.out.println("Vault kommt noch");
                    break;
                case 5:
                    //Extractor aufladen
                    eingabe = intEingabe();
                    Batteries tmp = (Batteries) BuildingManagement.getBuilding(new int[]{3, eingabe}).getEnergy();
                    tmp.loadEnergy(intEingabe());
                    InternalStorage tmpS = (InternalStorage) ((Extractor) BuildingManagement.getBuilding(new int[]{3, eingabe})).getStorage();
                    tmpS.addTransportDrone(Type.DEFAULTDRONE);
                    break;
                case 6:
                    BuildingManagement.getBuilding(new int[]{intEingabe(), intEingabe()}).addMoreWorkers(Type.DEFAULTDRONE, intEingabe());
                    break;
                case 7:
                    //Dronefactory Laden
                    eingabe = intEingabe();
                    Batteries tmp2 = (Batteries) BuildingManagement.getBuilding(new int[]{1, eingabe}).getEnergy();
                    tmp2.loadEnergy(intEingabe());
                    InternalStorage tmp3 = (InternalStorage) BuildingManagement.getBuilding(new int[]{1, eingabe}).getStorage();
                    tmp3.loadResources(100);
                    break;
                case 8:
                    DroneFactory tmpD = ((DroneFactory) BuildingManagement.getBuilding(new int[]{1, intEingabe()}));
                    tmpD.startProduction(Type.DEFAULTDRONE);
                    System.out.println("New Production!");
                    break;
                case 9:
                    return false;
            }
        }catch (BuildingUnfinishedException e) {
            System.out.println(e.getMessage());
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
