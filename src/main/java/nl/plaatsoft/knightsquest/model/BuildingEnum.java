package nl.plaatsoft.knightsquest.model;

/**
 * The Enum BuildingEnum
 * 
 * @author wplaat
 */
public enum BuildingEnum {

	/** The harbor. */
	HARBOR(1);
	
	/** The value. */
	private final int value;
	
    /**
     * Instantiates a new building enum.
     *
     * @param value the value
     */
    private BuildingEnum(int value) {
        this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }
    
}
