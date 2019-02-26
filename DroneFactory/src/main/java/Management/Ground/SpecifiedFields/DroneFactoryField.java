//package Management.Ground.SpecifiedFields;
//
//import ImportandEnums.BuildingTypes;
//import Management.ManagementSystems.BuildingManagement;
//import Management.Ground.Field;
//import Production.Dronen.Drone;
//import Production.Factories.Produktion.DroneFactory;
//
//public class DroneFactoryField extends Field {
//
//    protected DroneFactory placedBuilding;
//
//    public DroneFactoryField(DroneFactory building) {
//        super(building);
//        placedBuilding = building;
//    }
//
//    public void addBulding(DroneFactory tmp, Drone drone, int count) {
//        tmp.startConstruction(drone.getBuildingTypes(), count);
//        BuildingManagement.addBuilding(tmp);
//    }
//
//    protected void action(int wahl) {
//        switch (wahl) {
//            case 0:
//                //placedBuilding.loadEnergy(intEnergy());
//                //placedBuilding.loadResources(intResources());
//                break;
//            case 1:
//                placedBuilding.startProduction(BuildingTypes.DEFAULTDRONE);
//                break;
//        }
//    }
//
//    protected String getOptions() {
//        return ("0 - Load Dronefactory\n1 - Choose production Drone");
//    }
//}
