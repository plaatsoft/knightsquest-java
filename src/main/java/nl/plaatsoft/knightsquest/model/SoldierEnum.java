package nl.plaatsoft.knightsquest.model;

/**
 * The Enum SoldierEnum
 * 
 * @author wplaat
 */
public enum SoldierEnum {
	
	/** The cross. */
	CROSS(0),	
	
	/** The pawn. */
	PAWN(1),
	
	/** The bishop. */
	BISHOP(2),
	
	/** The horse. */
	HORSE(3),
	
	/** The queen. */
	QUEEN(4),
	
	/** The king. */
	KING(5),
	
	/** The tower. */
	TOWER(6);
	
	/** The value. */
	private final int value;
	
    /**
     * Instantiates a new soldier enum.
     *
     * @param value the value
     */
    private SoldierEnum(int value) {
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
