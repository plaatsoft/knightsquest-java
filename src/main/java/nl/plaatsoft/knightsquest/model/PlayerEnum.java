package nl.plaatsoft.knightsquest.model;

/**
 * The Enum PlayerEnum
 * 
 * @author wplaat
 */
public enum PlayerEnum {

	/** The human local. */
	HUMAN_LOCAL(0),	
	
	/** The human remote. */
	HUMAN_REMOTE(1),
	
	/** The bot. */
	BOT(2);
	
	/** The value. */
	private final int value;
	
    /**
     * Instantiates a new player enum.
     *
     * @param value the value
     */
    private PlayerEnum(int value) {
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
