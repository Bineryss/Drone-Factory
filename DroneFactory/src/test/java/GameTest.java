
import ImportandEnums.DroneTypes;
import Management.BuildingManagement;
import Management.DroneManagement;
import Management.Resources.ResourceManagement;
import Production.Dronen.Drone;
import Production.Factories.Connector.Batteries;
import Production.Factories.Building;
import Production.Factories.Energy.Solarpannels;
import Production.Factories.Connector.InternalStorage;
import Production.Factories.Produktion.DroneFactory;
import Production.Factories.Resources.Extractor;
import SpecificExceptions.BuildingUnfinishedException;
import org.springframework.context.annotation.ComponentScan;

import javax.inject.Inject;
import java.util.Scanner;

@ComponentScan("Management")
public class GameTest {
    @Inject
    BuildingManagement buildingManagement;
    @Inject
    DroneManagement droneManagement;
    @Inject
    ResourceManagement resourceManagement;

    public GameTest() {
        resourceManagement.addEnergy(200);
        resourceManagement.addResources(800);
    }

    private void addBulding(Building tmp, Drone drone, int count) {
        tmp.startConstruction(drone.getType(), count);
        buildingManagement.addBuilding(tmp);
    }

    private void newDrones(int n) {
        for (int i = 0; i < n; i++) {
            droneManagement.addDrone(DroneManagement.getBlueprint(DroneTypes.DEFAULTDRONE));
        }
    }

    public static void main(String[] args) {
        GameTest game = new GameTest();
        game.getRes();
        game.newDrones(10);
        int zug = 0;
        while (true) {
            game.zug(zug++);
        }
    }

    private void getRes() {
        System.out.println(resourceManagement);
    }

    private void zug(int zug) {
        buildingManagement.update();
        boolean run = true;
        do {
            System.out.println("-----------Zug: " + zug + "---------------");
            System.out.println(droneManagement);
            System.out.println(droneManagement);
            System.out.println(buildingManagement);
            try {
                run = runEingabe();
            } catch (Exception e) {
                System.out.println("Nicht vorhanden!");
            }
        } while (run);
        zug++;
    }

    private boolean runEingabe() {
        int eingabe;
        try {
            switch (intEingabe("Waehle Aktion")) {
                case 0:
                    addBulding(new Solarpannels(), droneManagement.getDrone(DroneTypes.DEFAULTDRONE), 1);
                    break;
                case 1:
                    addBulding(new DroneFactory(), droneManagement.getDrone(DroneTypes.DEFAULTDRONE), 1);
                    break;
                case 2:
                    System.out.println("Labor kommt noch");
                    break;
                case 3:
                    addBulding(new Extractor(), droneManagement.getDrone(DroneTypes.DEFAULTDRONE), 1);
                    break;
                case 4:
                    System.out.println("Vault kommt noch");
                    break;
                case 5:
                    //Extractor aufladen
                    eingabe = intEingabe("Waehle Extractor");
                    Batteries tmp = (Batteries) buildingManagement.getBuilding(new int[]{3, eingabe}).getEnergy();
                    tmp.loadEnergy(intEingabe("Anzahl an Energy"));
                    InternalStorage tmpS = (InternalStorage) ((Extractor) buildingManagement.getBuilding(new int[]{3, eingabe})).getStorage();
                    tmpS.addTransportDrone(DroneTypes.DEFAULTDRONE);
                    break;
                case 6:
                    buildingManagement.getBuilding(new int[]{intEingabe("Waehle Gebaeude Typ"), intEingabe("Waehle Gebaeude")}).addMoreWorkers(DroneTypes.DEFAULTDRONE, intEingabe("Anzahl der Dronen"));
                    break;
                case 7:
                    //Dronefactory Laden
                    eingabe = intEingabe("Waehle Droneactory");
                    Batteries tmp2 = (Batteries) buildingManagement.getBuilding(new int[]{1, eingabe}).getEnergy();
                    tmp2.loadEnergy(intEingabe("Anzahl an Energy"));
                    InternalStorage tmp3 = (InternalStorage) buildingManagement.getBuilding(new int[]{1, eingabe}).getStorage();
                    tmp3.loadResources(100);
                    break;
                case 8:
                    DroneFactory tmpD = ((DroneFactory) buildingManagement.getBuilding(new int[]{1, intEingabe("Waehle Dronefactory")}));
                    tmpD.startProduction(DroneTypes.DEFAULTDRONE);
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


    private static int intEingabe(String text) {
        Scanner sc = new Scanner(System.in);
        int ausgabe;
        while (true) {
            try {
                System.out.printf("%s: ", text);
                ausgabe = Integer.parseInt(sc.nextLine());
                return ausgabe;
            } catch (NumberFormatException e) {
                System.out.println("Bitte eine ganze Zahl eingeben!");
            }
        }
    }
}
