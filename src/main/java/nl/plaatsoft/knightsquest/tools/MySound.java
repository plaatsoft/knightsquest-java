package nl.plaatsoft.knightsquest.tools;

import javafx.scene.media.AudioClip;
import nl.plaatsoft.knightsquest.model.Player;

/**
 * The Class MySound.
 */
public class MySound {

	/** The Constant CLIP_CASTLE. */
	public static final int CLIP_CASTLE = 1;
	
	/** The Constant CLIP_CREATE. */
	public static final int CLIP_CREATE = 2;
	
	/** The Constant CLIP_FUNERAL. */
	public static final int CLIP_FUNERAL = 3;
	
	/** The Constant CLIP_DIE. */
	public static final int CLIP_DIE = 4;
	
	/** The Constant CLIP_END. */
	public static final int CLIP_END = 5;
	
	/** The Constant CLIP_FIGHT. */
	public static final int CLIP_FIGHT = 6;
	
	/** The Constant CLIP_START. */
	public static final int CLIP_START = 7;
	
	/** The Constant CLIP_STEP. */
	public static final int CLIP_STEP = 8;
	
	/** The Constant CLIP_UPGRADE. */
	public static final int CLIP_UPGRADE = 9;
	
	/** The Constant CLIP_TURN. */
	public static final int CLIP_TURN = 10;

	/** The castle. */
	private static AudioClip castle = new AudioClip(MySound.class.getResource("/sounds/castle.mp3").toExternalForm());
	
	/** The create. */
	private static AudioClip create = new AudioClip(MySound.class.getResource("/sounds/step.mp3").toExternalForm());
	
	/** The funeral. */
	private static AudioClip funeral = new AudioClip(MySound.class.getResource("/sounds/cross.mp3").toExternalForm());
	
	/** The die. */
	private static AudioClip die = new AudioClip(MySound.class.getResource("/sounds/die.mp3").toExternalForm());
	
	/** The end. */
	private static AudioClip end = new AudioClip(MySound.class.getResource("/sounds/end.mp3").toExternalForm());
	
	/** The fight. */
	private static AudioClip fight = new AudioClip(MySound.class.getResource("/sounds/fight.mp3").toExternalForm());
	
	/** The start. */
	private static AudioClip start = new AudioClip(MySound.class.getResource("/sounds/start.mp3").toExternalForm());
	
	/** The step. */
	private static AudioClip step = new AudioClip(MySound.class.getResource("/sounds/step.mp3").toExternalForm());
	
	/** The upgrade. */
	private static AudioClip upgrade = new AudioClip(MySound.class.getResource("/sounds/upgrade.mp3").toExternalForm());
	
	/** The turn. */
	private static AudioClip turn = new AudioClip(MySound.class.getResource("/sounds/turn.mp3").toExternalForm());
		
	/**
	 * Play.
	 *
	 * @param player the player
	 * @param sound the sound
	 */
	static public void play(Player player, int sound) {

		//if (player.getType()!=PlayerEnum.BOT && MyFactory.getSettingDAO().getSettings().isSoundEffectsOn()) {
		if (MyFactory.getSettingDAO().getSettings().isSoundEffectsOn()) {

			switch (sound) {

			case CLIP_CASTLE: 
				castle.play();
				break;

			case CLIP_CREATE:
				create.play();
				break;
				
			case CLIP_FUNERAL:
				funeral.play();
				break;
				
			case CLIP_DIE:
				die.play();
				break;

			case CLIP_END:
				end.play();
				break;

			case CLIP_FIGHT:
				fight.play();
				break;

			case CLIP_START:
				start.play();
				break;

			case CLIP_STEP:
				step.play();
				break;

			case CLIP_UPGRADE:
				upgrade.play();
				break;
				
			case CLIP_TURN:
				turn.play();
				break;				
			}
		}
	}
}
