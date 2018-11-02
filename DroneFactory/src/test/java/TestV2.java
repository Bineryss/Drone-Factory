
import Management.BuildingManagement;
import Management.DroneManagement;
import Management.Resources.ResourceManagement;
import Production.Dronen.Drone;
import Production.Dronen.Normal.DefaultDrone;
import Production.Factories.Building;
import Production.Factories.Energy.Solarpannels;
import Production.Factories.Produktion.DroneFactory;
import Production.Factories.Resources.Extractor;
import Production.Factories.Resources.ExtractorTyp;

import java.util.Scanner;

public class TestV2 {

    private static void addBulding(Building tmp, Drone drone, int count) {
        tmp.startConstruction(drone.getID(),count);
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
        ResourceManagement.addResources(ResourceManagement.generateResourceArray("0.400,1.400,2.400"));

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
                addBulding(new Solarpannels(), DroneManagement.getDrone(0),1);
                break;
            case 1:
                addBulding(new DroneFactory(), DroneManagement.getDrone(0),1);
                break;
            case 2:
                System.out.println("Labor kommt noch");
                break;
            case 3:
                addBulding(new Extractor(ExtractorTyp.CARBON), DroneManagement.getDrone(0),1);
                break;
            case 4:
                System.out.println("Storage kommt noch");
                break;
            case 5:
                //Extractor aufladen
                eingabe = intEingabe();
                BuildingManagement.getBuilding(new int[]{3, eingabe}).loadEnergy(intEingabe());
                ((Extractor) BuildingManagement.getBuilding(new int[]{3, eingabe})).addDrone(0);
                break;
            case 6:
                BuildingManagement.getBuilding(new int[]{intEingabe(), intEingabe()}).startConstruction(0,1);
                break;
            case 7:
                //Dronefactory Laden
                eingabe = intEingabe();
                BuildingManagement.getBuilding(new int[]{1, eingabe}).loadEnergy(intEingabe());
                BuildingManagement.getBuilding(new int[]{1, eingabe}).loadResources(ResourceManagement.generateResourceArray("0.100,1.50,2.10"));
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
