package cross;

//This class contains all the string arrays with the answers, both with a string of words and letters, and the hints for each level.
public class Answers {
	
	private static String[] answersWords;
	private static String[][] answersLetters;
	private static String[] hints;
	
	private String[] answersWords1 = {"rico"};
	private String[][] answersLetters1 = {
		{ "R", "I",  "C",  "O", "◼"}
		};
	private String[] hints1 = {"1 across is the name of a certian bearded teacher"};
	private String[] answersWords2 = {"get", "angst", "wringe", "hedge", "yeast", "gpa", "nyt", "why", "green", "piday"};
	private String[][] answersLetters2 = { 
		{ "◼︎", "W", "H", "Y", "◼︎"},
		{ "G", "R", "E", "E", "N"},
		{ "P", "I", "D", "A", "Y"},
		{ "A", "N", "G", "S", "T"},
		{ "◼︎", "G", "E", "T", "◼︎"},
		};
	private String[] hints2 = { "8 across is to recieve something.", "ill give you a freebee cause I'm so nice. 7 across is: angst", "1 down: a word to describe twisting a towel to get water out.", "2 down is what Homer Simpson falls back into in the meme.", "3 down is a fungi that rises bread.", "4 down is every terra students' prized possesion.", "5 down stands for New York Times.", "1 Across is a question that is pronounced as its last letter.", "4 across is a terra color.", "6 across is a holiday, which is celebrated on March 14 (put as one word, no dashes or spcaes)."};
	private String[] answersWords3 = {"jello", "alien", "robes", "snip", "mast", "jars", "melon", "alibi", "sleep", "tons"};
	private String[][] answersLetters3 = { 
		{ "◼︎", "J", "A", "R", "S"},
		{ "M", "E", "L", "O", "N"},
		{ "A", "L", "I", "B", "I"},
		{ "S", "L", "E", "E", "P"},
		{ "T", "O", "N", "S", "◼︎"},
		};		
	private String[] hints3 = {"1 down is a translucent dessert that jiggles.", "2 down is a creature from space.", "3 down is what wizards wear.", "4 down is the sound that scissors make.", "5 down is that marine biology high school.", "1 across is what you peanut butter is stored in in (plural).", "5 across is a large fruit with many varieties. An example is cantelope.", "6 across is what you say you did the day of the murder.", "7 across is what every high schooler needs a lot of and gets none of (zzzzz).", "8 across is equal to 2000 pounds."};
	private blackSquares _blackSquares;
	
	//This constructor has a switch case, in which each case sets the string arrays to each corresponding level.
	public Answers(int n) {
		switch(n) {
			case 0:
				_blackSquares = blackSquares.Easy;
				answersWords = answersWords1;
				answersLetters = answersLetters1;
				hints = hints1;
				break;
			case 1:
				_blackSquares = blackSquares.Medium;
				answersWords = answersWords2;
				answersLetters = answersLetters2;
				hints = hints2;
				break;
			case 2:
				_blackSquares = blackSquares.Hard;
				answersWords = answersWords3;
				answersLetters = answersLetters3;
				hints = hints3;
				break;
			default:
				_blackSquares = blackSquares.Easy;
				answersWords = answersWords1;
				answersLetters = answersLetters1;
				hints = hints1;
				break;
				
		}
	}
	
	public blackSquares getBlackSquaresMaze() { return _blackSquares; }
	public static String[] getAnswersWords() { return answersWords; }
	public static String[][] getAnswersLetters() { return answersLetters; }
	public static String[] getHints() { return hints; }
	
	
}
