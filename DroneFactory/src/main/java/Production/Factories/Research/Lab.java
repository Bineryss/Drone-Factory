package Production.Factories.Research;

import Management.Type;
import Production.Factories.Building;

/**
 * Kleines Labor
 * <p>
 * ID: 2
 */
public class Lab extends Building {
    private static int cc = -1;


    public Lab() {
        super();
        cc++;
        id = cc;

        type = Type.LABORATORIUM;
    }

    public void updateBuilding() {

    }

    public String toString() {
        return "[ " + type.getIcon() + " ]" + constructionStatus();
    }

}
