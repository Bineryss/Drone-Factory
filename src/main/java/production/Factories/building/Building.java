package production.Factories.building;

import production.Factories.BuildingDataEntity;
import production.Factories.actionstrategy.BuildingActionStrategy;
import production.Factories.loadstrategy.LoadStrategy;

import java.util.List;

public abstract class Building implements Updatable {
    private int id;
    private List<LoadStrategy> loader;
    private List<BuildingActionStrategy> action;

    private BuildingDataEntity dataEntity;

    Building() {
    }

    Building(int id, BuildingDataEntity dataEntity, List<LoadStrategy> loader, List<BuildingActionStrategy> action) {
        this.id = id;

        this.dataEntity = dataEntity;
        this.loader = loader;
        this.action = action;
    }

    @Override
    public void update() {
        for (BuildingActionStrategy current :
                action) {
            current.action(dataEntity);
        }
    }

    public int getId() {
        return id;
    }


}
