package ImportandEnums;

public enum DroneTypes {
    DEFAULTDRONE("defaultDrone"),
    BUILDINGDRONE("buildingDrone"),
    CARRIERDRONE("carrierDrone");

    private final String xmlName;

    DroneTypes(String xmlName) {
        this.xmlName = xmlName;
    }

    public String getXmlName() {
        return xmlName;
    }
}
