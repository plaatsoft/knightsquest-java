package nl.plaatsoft.knightsquest.model;

import java.util.Date;

/**
 * The Class Score
 * 
 * @author wplaat
 */
public class Score {
	
	/** The timestamp. */
	private Date timestamp;
	
	/** The score. */
	private int score;
	
	/** The level. */
	private int level;
	
	/** The nickname. */
	private String nickname;
	
	/** The country. */
	private String country;
	
	/**
	 * Instantiates a new score.
	 *
	 * @param timestamp the timestamp
	 * @param score the score
	 * @param level the level
	 * @param nickname the nickname
	 * @param country the country
	 */
	public Score(Date timestamp, int score, int level, String nickname, String country) {
		this.timestamp = timestamp;
		this.score = score;
		this.level = level;
		this.nickname = nickname;
		this.country = country;
	}
	
	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * Gets the nickname.
	 *
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Sets the nickname.
	 *
	 * @param nickname the new nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
