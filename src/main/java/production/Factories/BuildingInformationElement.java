package production.Factories;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BuildingInformationElement <T extends BuildingDataEntity> {
    private final T dataEntity;
}
