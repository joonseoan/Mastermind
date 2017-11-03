

/**
 * Provide the basic game frame
 * @author Joon An
 *
 */

public interface MastermindInterface {

	public abstract void drawGame(String[][] in);

	public abstract void newGame();

	public abstract void checkValues();

	public abstract void updateGame();

}
