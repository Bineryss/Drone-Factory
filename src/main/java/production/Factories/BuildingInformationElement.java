package production.Factories;

public class BuildingInformationElement <T extends BuildingDataEntity> {
    private final T dataEntity;

    public BuildingInformationElement(T dataEntity) {
        this.dataEntity = dataEntity;
    }

    public T getDataEntity() {
        return dataEntity;
    }
}
