

/**
 * Initialize Mindmaster game and take control of the flow control Create
 * instances, only declaring the reference name to deliver null value
 * 
 * @author Joon An
 *
 */
public class MainControl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			GameNew gn1 = new GameNew();
			gn1.newGame();

			String[][] in = new String[10][8];

			gn1.drawGame(in);
			gn1.updateGame();

		} catch (NumberFormatException nfe) {
			System.out.println(nfe.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
