package specificexceptions;

public class DroneNotEnoughEnergyException extends Exception {

    public DroneNotEnoughEnergyException() {
        super("Die Drone hat nicht genug Energie!");
    }
}
