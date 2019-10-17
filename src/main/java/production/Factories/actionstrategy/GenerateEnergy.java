package production.Factories.actionstrategy;

import production.Factories.BuildingDataEntity;

//Solarpanel
public class GenerateEnergy implements BuildingActionStrategy {
    private int ammount;

    public GenerateEnergy(int ammount) {
        this.ammount = ammount;
    }

    @Override
    public void action(BuildingDataEntity dataEntity) {

    }
}
