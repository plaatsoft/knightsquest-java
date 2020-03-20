package nl.plaatsoft.knightsquest.model;

import java.util.Comparator;

import nl.plaatsoft.knightsquest.model.Score;

/**
 * The Class ScoreSort
 * 
 * @author wplaat
 */
public class ScoreSort implements Comparator<Score> {

	/**
	 * Compare.
	 *
	 * @param score1 the score 1
	 * @param score2 the score 2
	 * @return the int
	 */
	public int compare(Score score1, Score score2) {

		int sc1 = score1.getScore();
		int sc2 = score2.getScore();

		if (sc1 > sc2) {
			return -1;
		} else if (sc1 < sc2) {
			return +1;
		} else {
			return 0;
		}
	}
}