package nl.plaatsoft.knightsquest.model;

/**
 * The Enum LandEnum
 * 
 * @author wplaat
 */
public enum LandEnum {	
	
	/** The mountain. */
	MOUNTAIN(1),
	
	/** The forest. */
	FOREST(2),
	
	/** The grass. */
	GRASS(3),
	
	/** The coast. */
	COAST(4),
	
	/** The water. */
	WATER(5),
	
	/** The ocean. */
	OCEAN(6);
	
	/** The value. */
	private final int value;
	
    /**
     * Instantiates a new land enum.
     *
     * @param value the value
     */
    private LandEnum(int value) {
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
