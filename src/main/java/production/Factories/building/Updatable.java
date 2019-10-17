package production.Factories.building;

import specificexceptions.DroneNotEnoughEnergyException;

public interface Updatable {
    void update() throws DroneNotEnoughEnergyException;
}
