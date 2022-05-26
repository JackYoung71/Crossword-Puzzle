package cross;

//This class controls the whole crossword program.
import javax.swing.JOptionPane;

public class Overseer {

	
	private Crossword _cross;
	//private static String _name;
	private static int _lvl;
	private Answers _maze;
	private boolean[][] _blacks;
	private static String _name;
	private static int num = 0;
	
	//The constructor sets up the game with a player's given level and name.
	public Overseer() {
		//_name = JOptionPane.showInputDialog(null, "gimmie ya name:");
	
		JOptionPane.showMessageDialog(null, "Hello! Welcome to Rico's Crossword Puzzles!" + "\n" + "Before we start, lets see if you have already played before." + "\n");
		_lvl = Integer.parseInt(JOptionPane.showInputDialog(null, "Have you played before?" + "\n" + "type 1 if you have not, 2 to start on the first puzzle, or 3 to jump to the last pizzle."));
		_name = JOptionPane.showInputDialog(null, "Type in your name." + "\n" + "If you type in the name of a certain twizzler loving teacher, you get unlimited guesses!.");
		if(_lvl == 1) {
			JOptionPane.showMessageDialog(null, "You have not played before, so lets get you started with the basics." + "\n" + "This is a very simple crossword puzzle game. " + "\n" + "Just try to guess words you think are there. " + "\n" + "If you need a hint to find one of the words, then just type hint. " + "\n" + "If you get one of the words, it will show up on the map. " + "\n" + " \"◼︎'s are where no words/letters exist, ◻︎'s are where the words/letters are." + "\n" + "Numbers are where the start to each word is." + "\n" + "Once you find all the words, you move onto the next level. " + "\n" + "Lets start off with an easy one to get you started." + "\n");
		}
		num = _lvl-1;
		_lvl--;
		for(int i = num; i < 3; i++) {
			_lvl++;
			_maze = new Answers(i);
			
			_blacks = _maze.getBlackSquaresMaze().getBlackSquaresBS();

			_cross = new Crossword(_blacks);
			num++;
		}
	}
	

	public boolean[][] getBlackBlackSquares() { return _blacks; }
	//public static String getName() { return _name; }
	public static int getLvl() {return _lvl;}
	public static int getNum() {return 
			num;}
	public static String getName() {return _name;}

}
