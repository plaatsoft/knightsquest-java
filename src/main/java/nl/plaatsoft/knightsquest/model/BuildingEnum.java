package nl.plaatsoft.knightsquest.model;

public enum BuildingEnum {

	HARBOR(1);

	private final int value;
	
    private BuildingEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
}
