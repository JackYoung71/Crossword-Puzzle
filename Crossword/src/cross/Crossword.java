package cross;

import javax.swing.JOptionPane;
import java.util.ArrayList;

//This is the class that makes a crossword puzzle and uses player input to complete the crossword puzzle.
public class Crossword {

	


	private Square[][] _puzzle;
	private String guess;
	private boolean correct = false;
	private String[][] _MAP;
	
	private int guessesWrong = 0;
	
	private int answersGuessed = 0;
	private int lettersInGuess;
	private ArrayList<Integer> previousGuesses = new ArrayList<Integer>();
	boolean bool = false;
	private int hintsShown = 0;
	
	//random instance variables for the print maze thing
	boolean horizontal = false;
	
	char charLetter1;
	String stringLetter1;  
	int rowOfAnswer1 = 10;
	int colOfAnswer1 = 10;
	boolean firstLetter = false;
	
	char charLetter2;
	String stringLetter2;  
	int rowOfAnswer2 = 10;
	int colOfAnswer2 = 10;
	boolean secondLetter = false;
	
	char charLetter3;
	String stringLetter3;  
	int rowOfAnswer3 = 10;
	int colOfAnswer3 = 10;
	boolean thirdLetter = false;
	
	char charLetter4;
	String stringLetter4;  
	int rowOfAnswer4 = 10;
	int colOfAnswer4 = 10;
	boolean fourthLetter = false;
	
	char charLetter5;
	String stringLetter5;  
	int rowOfAnswer5 = 10;
	int colOfAnswer5 = 10;
	boolean fifthLetter = false;
	
	int num = 0;
	
	public String getGuess() {return guess;}
	
	
	
	//The constructor sets up the 2D array with square objects, each with a number and a boolean for if it is black or not.
	//It also starts the maze.
	public Crossword(boolean[][] blackSquares)
	{
		hintsShown = 0;
	  _puzzle = new Square[blackSquares.length][blackSquares[0].length];
	   
	  int num = 1;
	  
	  for(int r = 0; r < _puzzle.length; r++)
	  {
	    for(int c = 0; c < _puzzle[0].length; c++)
	    {
	      if(toBeLabeled(r, c, blackSquares))//if square gonna be labeled
	      {
	        _puzzle[r][c] = new Square(false, num);//make a new square in the puzzle, that is not black and with a number
	        num++;
	      }
	      else
	        _puzzle[r][c] = new Square(blackSquares[r][c], 0);//make a new square in the puzzle that is black or white depending on blacksquares and has no number
	    }
	  }
	  _MAP = new String[_puzzle.length][_puzzle[0].length];
	  startMaze(Overseer.getLvl());
	}
	
	//This returns true if a square on the array has to be labeled with a number.
	private boolean toBeLabeled(int r, int c, boolean[][] blackSquares)
	{
	  if(blackSquares[r][c]) //if its a blacksquare, no label
	    return false;

	  else if((Overseer.getNum() != 0) && (r == 0 || blackSquares[r - 1][c]) && (!(r == blackSquares.length) || !blackSquares[r + 1][c]))// if its at the top or its black to the left then label
		  
		  return true;
	  

	  else if(c == 0 || blackSquares[r][c - 1] && (!(r == blackSquares[0].length) || !blackSquares[r + 1][c]))// if its at the left or its black to the up then label
		  //JOptionPane.showMessageDialog(null, "row: "+ r + " col: " + c + " " + true + "\n");
		  return true;

	  return false; //if nothing works above, then say its false
	}
	
	//This starts the crossword puzzle and runs the whole game, with player inputted guesses and a win/loss for if the player won or not.
	//It also has hints and can go to new levels.
	public void startMaze(int lvl) {
		
		guess = JOptionPane.showInputDialog(null, printPuzzle() + "\n");
		
		for(int i = 0; i < Answers.getAnswersWords().length; i++) {
			//if its a hint
			if(guess.equalsIgnoreCase("hint")) {
				showHint();
				i = Answers.getAnswersWords().length-1;
			}
			//if its the first level and you guessed rico
			else if(guess.equalsIgnoreCase("rico") && Overseer.getLvl() == 1) {
				correct = true;
				JOptionPane.showMessageDialog(null, "Good Job, you passed the tutorial. Lets go to the first crossword puzzle."+ "\n");
				i = Answers.getAnswersWords().length-1;
			}
			//if its a correct guess
			else if(Answers.getAnswersWords()[i].equalsIgnoreCase(guess)){
				
				correct = true;
				i = Answers.getAnswersWords().length-1;
				JOptionPane.showMessageDialog(null, printPuzzle() + "You got a word!"+ "\n");
				
				boolean difMap = false;
				for(int r = 0; r < Answers.getAnswersLetters().length; r++) {
					for(int c = 0; c < Answers.getAnswersLetters()[0].length; c++) {
						if(!(Answers.getAnswersLetters()[r][c].equals(_MAP[r][c]))) {
							difMap = true;
						}
					}
				}
				if(difMap) {
				startMaze(Overseer.getLvl());
				}
				else {
					if(Overseer.getLvl() == 2) {
						JOptionPane.showMessageDialog(null, "Good Job, you passed this level. Let's go to the next one."+ "\n");
						correct = true;
						i = Answers.getAnswersWords().length-1;
					}
					else if(Overseer.getLvl() == 3) {
						correct = true;
						JOptionPane.showMessageDialog(null, "Good Job, you finished all the crossword puzzles!"+ "\n");
						i = Answers.getAnswersWords().length-1;
						victory();
					}
				}
			}
		}
			if(!correct){
				if(Overseer.getName().equalsIgnoreCase("rico")) {
					JOptionPane.showMessageDialog(null, "That guess was wrong. But, you're Rico, so you get unlimited guesses!!");
					startMaze(Overseer.getLvl());
				}
				else if(Overseer.getLvl() == 1) {
					JOptionPane.showMessageDialog(null, "That guess was wrong. Don't worry, you won't get any guesses wrong in the tutorial level.");
					startMaze(Overseer.getLvl());
				}
				else {
					if(guessesWrong == 4) {
						JOptionPane.showMessageDialog(null, "That guess was wrong... again. You have lost the crossword game." + "\n" + "No cookie for you.");
					}
					else {
						guessesWrong++;
						JOptionPane.showMessageDialog(null, "That guess was wrong. You have " + guessesWrong + " guesses wrong." + "\n" + "That means you only have " + (5 - guessesWrong) + " guesses wrong before you lose.");
						startMaze(Overseer.getLvl());
					}
				}
			}
			
		
	}

	//This shows a panel with a victory screen.
	public void victory() {
		JOptionPane.showMessageDialog(null, "Go get a cookie, you deserve it! "+ "\n");
	}
	
	//This gives the player a hint if needed.
	public void showHint() {
		JOptionPane.showMessageDialog(null, Answers.getHints()[hintsShown]);
		hintsShown++;
		startMaze(Overseer.getLvl());
	}
	

	//This method prints the whole puzzle in a joptionpane with words if guessed, blacksquares if no letter should be guessed there, numbers for each word, and white squares.
	public String printPuzzle() {
		
		String MAP = "";
		String white = "◻︎";
		String black = "◼︎";
		String space = " ";
		String dspace = "  ";
		Square[][] map = _puzzle;

		
		if(correct) {
			
			previousGuesses.clear();
			previousGuesses.add(94);
			lettersInGuess = guess.length();
			//JOptionPane.showMessageDialog(null, lettersInGuess + "");
			findAnswer();
		}
		
		//print the puzzle at the start
		if(answersGuessed == 0) {
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[r].length; c++) {
				//if its not black &, not a guess, and it has a number, then print the number
				if((!(_puzzle[r][c].getBlack()) || (!(r == rowOfAnswer1) && (!(c == colOfAnswer1))) ) && _puzzle[r][c].getNum() !=0) {
					_MAP[r][c] = _puzzle[r][c].getNum() + "";
				}
				
				//if it is black, then print black
				else if(_puzzle[r][c].getBlack()) {
					_MAP[r][c] = black;
				}
				//everything else is white
				else{
				_MAP[r][c] = white;
				}
			}
		}
		}
		
		
		//print it with guesses
		if(answersGuessed > 0) {
			//JOptionPane.showMessageDialog(null, rowOfAnswer1 + colOfAnswer1 + "\n" + rowOfAnswer2 + colOfAnswer2 +  "\n" + rowOfAnswer3 + colOfAnswer3 +  "\n" + rowOfAnswer4 + colOfAnswer4 +  "\n" +rowOfAnswer5 + colOfAnswer5);
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[r].length; c++) {
				

				//if its not black &, not a guess, and it has a number, then print the number
				//((!correct) || (!(r == rowOfAnswer1) && (!(c == colOfAnswer1)))) &&
				if((!(_puzzle[r][c].getBlack()) &&  _puzzle[r][c].getNum() !=0)) {
					if(!(_MAP[r][c] == "A" ||
							_MAP[r][c] == "B" ||
							_MAP[r][c] == "C" ||
							_MAP[r][c] == "D" ||
							_MAP[r][c] == "E" ||
							_MAP[r][c] == "F" ||
							_MAP[r][c] == "G" ||
							_MAP[r][c] == "H" ||
							_MAP[r][c] == "I" ||
							_MAP[r][c] == "J" ||
							_MAP[r][c] == "K" ||
							_MAP[r][c] == "L" ||
							_MAP[r][c] == "M" ||
							_MAP[r][c] == "N" ||
							_MAP[r][c] == "O" ||
							_MAP[r][c] == "P" ||
							_MAP[r][c] == "Q" ||
							_MAP[r][c] == "R" ||
							_MAP[r][c] == "S" ||
							_MAP[r][c] == "T" ||
							_MAP[r][c] == "U" ||
							_MAP[r][c] == "V" ||
							_MAP[r][c] == "W" ||
							_MAP[r][c] == "X" ||
							_MAP[r][c] == "Y" ||
							_MAP[r][c] == "Z"
							)) {
					_MAP[r][c] = _puzzle[r][c].getNum() + "";
					}
				}
				
				//if it is black, then print black
				else if(_puzzle[r][c].getBlack()) {
					_MAP[r][c] = black;
				}
				//everything else is white
				else{
					if(!(_MAP[r][c] == "A" ||
							_MAP[r][c] == "B" ||
							_MAP[r][c] == "C" ||
							_MAP[r][c] == "D" ||
							_MAP[r][c] == "E" ||
							_MAP[r][c] == "F" ||
							_MAP[r][c] == "G" ||
							_MAP[r][c] == "H" ||
							_MAP[r][c] == "I" ||
							_MAP[r][c] == "J" ||
							_MAP[r][c] == "K" ||
							_MAP[r][c] == "L" ||
							_MAP[r][c] == "M" ||
							_MAP[r][c] == "N" ||
							_MAP[r][c] == "O" ||
							_MAP[r][c] == "P" ||
							_MAP[r][c] == "Q" ||
							_MAP[r][c] == "R" ||
							_MAP[r][c] == "S" ||
							_MAP[r][c] == "T" ||
							_MAP[r][c] == "U" ||
							_MAP[r][c] == "V" ||
							_MAP[r][c] == "W" ||
							_MAP[r][c] == "X" ||
							_MAP[r][c] == "Y" ||
							_MAP[r][c] == "Z"
							)) {
						_MAP[r][c] = white;
					}
				}
				//letters of guess first
				
				//1
				if(correct && r == rowOfAnswer1 && c == colOfAnswer1) {
					_MAP[r][c] =  Answers.getAnswersLetters()[r][c];
				}
				//2
				else if(correct && r == rowOfAnswer2 && c == colOfAnswer2) {
					_MAP[r][c] =  Answers.getAnswersLetters()[r][c];
				}
				//3
				else if(correct && r == rowOfAnswer3 && c == colOfAnswer3) {
					_MAP[r][c] =  Answers.getAnswersLetters()[r][c];
				}
				//4
				else if(correct && r == rowOfAnswer4 && c == colOfAnswer4) {
					_MAP[r][c] =  Answers.getAnswersLetters()[r][c];
				}
				//5
				else if(correct && r == rowOfAnswer5 && c == colOfAnswer5) {
					_MAP[r][c] =  Answers.getAnswersLetters()[r][c];
				}
				
			}
		}
		}
		
		//add the array to the string and return string
		for(int r = 0; r < map.length; r++) {
			for(int c = 0; c < map[r].length; c++) {
				if(_MAP[r][c] == white) {
				MAP += _MAP[r][c] + dspace;
				}
				else {
					MAP += _MAP[r][c] + dspace + space;
				}
				
			}
			MAP += "\n";
		}	
		correct = false;
		answersGuessed++;
		
		return MAP;
	
	
	}
	
	//This method takes the answer that the person inputted, and goes through the whole puzzle to look for where that word is so it can be printed with a correct answer.
	public void findAnswer() {

			
			if(guess.equals("get")) {
				rowOfAnswer1 = 4;
				colOfAnswer1 = 1;
				rowOfAnswer2 = 4;
				colOfAnswer2 = 2;
				rowOfAnswer3 = 4;
				colOfAnswer3 = 3;
			}
			else if(guess.equals("angst")) {
				rowOfAnswer1 = 3;
				colOfAnswer1 = 0;
				rowOfAnswer2 = 3;
				colOfAnswer2 = 1;
				rowOfAnswer3 = 3;
				colOfAnswer3 = 2;
				rowOfAnswer4 = 3;
				colOfAnswer4 = 3;
				rowOfAnswer5 = 3;
				colOfAnswer5 = 4;

			}
			else {
				findAnswer1();
				if(firstLetter) {
				findAnswer2();
				if(rowOfAnswer1 == rowOfAnswer2 || colOfAnswer1 == colOfAnswer2) {
					//JOptionPane.showMessageDialog(null, "found second letter");
					findAnswer3();
					if((rowOfAnswer2 == rowOfAnswer3 || colOfAnswer2 == colOfAnswer3)) {
						if(lettersInGuess > 3) {
						findAnswer4();
						
						if((rowOfAnswer3 == rowOfAnswer4 || colOfAnswer3 == colOfAnswer4)) {
							if(lettersInGuess > 4) {
							findAnswer5();
							if((rowOfAnswer4 == rowOfAnswer5 || colOfAnswer4 == colOfAnswer5)) {
								//do nothing good job
							}
							else {
								findAnswer();
							}
							}
							
							
						}
						else {
							findAnswer();
						}
						}
					}
					else {
						findAnswer();
					}
				}
				else {
					//JOptionPane.showMessageDialog(null, "did not find second letter");
					findAnswer();
				}
			}
			
		}	
	}
	
	//This is a method that findAnswer calls to find the first letter of the answer in the maze.
	public void findAnswer1() {
	
		
		//find where the first letter of the guess is
			firstLetter = false;
			secondLetter = false;
			thirdLetter = false;
			fourthLetter = false;
			fifthLetter = false;
			boolean bool = false;
		charLetter1 = guess.charAt(0);
		stringLetter1 = String.valueOf(charLetter1);
		//JOptionPane.showMessageDialog(null, "starting the method again. you have made " + previousGuesses.size() + " guesses");
		for(int row = 0; row < Answers.getAnswersLetters().length; row++) {
			for(int col = 0; col < Answers.getAnswersLetters()[0].length; col++) {
				
					if((Answers.getAnswersLetters()[row][col].equalsIgnoreCase(stringLetter1))){
						bool = false;
						for(int i = 0; i < previousGuesses.size(); i++) {
							if(row == previousGuesses.get(i)) {
								//JOptionPane.showMessageDialog(null, "it was a previous guess lets try again");
									bool = true;
							}
						}
						
						
						if(!bool) {
							rowOfAnswer1 = row;
							//JOptionPane.showMessageDialog(null, "row of answer 1: " + rowOfAnswer1);
							colOfAnswer1 = col;
							previousGuesses.add(row);
							firstLetter = true;
							
							//JOptionPane.showMessageDialog(null, "you found where the first letter is, that is not a previous guess at Row: " + rowOfAnswer1 + " col: " + colOfAnswer1);
							row = Answers.getAnswersLetters().length-1;
							col = Answers.getAnswersLetters()[0].length-1;
							
						}
					}
			}
			//JOptionPane.showMessageDialog(null, "new line being searched");
			
			
		}
		/*for(int q = 1; q < previousGuesses.size(); q++) {
			JOptionPane.showMessageDialog(null,  "rows checked: " + previousGuesses.get(q));
		}*/
		
		
	}
	
	//This is a method that findAnswer calls after it found the first letter to find the second letter of the answer in the maze.
	public void findAnswer2() {		
			//see if the word is vertical or horizontal
			//find where the second letter of the guess is
			if(firstLetter){
			charLetter2 = guess.charAt(1);
			stringLetter2 = String.valueOf(charLetter2);
			
			//find the next letter in the row, set horiztonal = true
			//if its not right, then find the next letter in the collumn
			//if it finds it in the collumn, set horizontal = false
			//if that isnt right, then find the first letter again 
			
			//JOptionPane.showMessageDialog(null, stringLetter2);
			//JOptionPane.showMessageDialog(null, rowOfAnswer1 + colOfAnswer1);
			//JOptionPane.showMessageDialog(null, "row of answer 1: " + rowOfAnswer1);
			
			for(int col = 0; col < Answers.getAnswersLetters()[0].length; col++) {
				//JOptionPane.showMessageDialog(null, Maze.getAnswersLetters()[rowOfAnswer1][col]);
				
				if(Answers.getAnswersLetters()[rowOfAnswer1][col].equalsIgnoreCase(stringLetter2)) {
					rowOfAnswer2 = rowOfAnswer1;
					colOfAnswer2 = col;
					col = Answers.getAnswersLetters().length-1;
					horizontal = true;
					secondLetter = true;
					//JOptionPane.showMessageDialog(null, "second letter found in col");
					
				}
			}
			//if you didnt find it....
			
			if(!secondLetter) {
				//JOptionPane.showMessageDialog(null, "not the same row, lets try collumn");
				for(int row = 0; row < Answers.getAnswersLetters().length; row++) {
					//JOptionPane.showMessageDialog(null, Maze.getAnswersLetters()[row][colOfAnswer1]);
					if(Answers.getAnswersLetters()[row][colOfAnswer1].equalsIgnoreCase(stringLetter2)) {
						rowOfAnswer2 = row;
						colOfAnswer2 = colOfAnswer1;
						row = Answers.getAnswersLetters().length-1;
						horizontal = false;
						secondLetter = true;
						//JOptionPane.showMessageDialog(null, "second letter found at Row: " + rowOfAnswer2 + " col: " + colOfAnswer2);
					}
				}
			}
			//if you still didnt find it, go find the second letter again
			if(!secondLetter) {
				//JOptionPane.showMessageDialog(null, "second letter not found");
				//findAnswer1();
			}
			
		}
	}	
	
	//This is a method that findAnswer calls after it found the second letter to find the third letter of the answer in the maze.
	public void findAnswer3() {
		
			//find where the third letter of the guess is
			
			if(secondLetter) {
			charLetter3 = guess.charAt(2);
			stringLetter3 = String.valueOf(charLetter3);
			if(horizontal) {
			for(int col = 0; col < Answers.getAnswersLetters()[0].length; col++) {
				if(Answers.getAnswersLetters()[rowOfAnswer2][col].equalsIgnoreCase(stringLetter3)) {
					rowOfAnswer3 = rowOfAnswer2;
					colOfAnswer3 = col;
					col = Answers.getAnswersLetters().length-1;
					
					thirdLetter = true;
				}
			}	
			}
			//if you didnt find it....
			if(!horizontal) {
			if(!thirdLetter) {
				for(int row = 0; row < Answers.getAnswersLetters().length; row++) {
					if(Answers.getAnswersLetters()[row][colOfAnswer2].equalsIgnoreCase(stringLetter3)) {
						rowOfAnswer3 = row;
						colOfAnswer3 = colOfAnswer2;
						row = Answers.getAnswersLetters().length-1;
						
						thirdLetter = true;
						//JOptionPane.showMessageDialog(null, "third letter found at Row: " + rowOfAnswer3 + " col: " + colOfAnswer3);
					}
				}
			}	
			}
			//if you still didnt find it, go find the second letter again
			if(!thirdLetter) {
				//findAnswer1();
			}
			
			
			}
		}
	
	//This is a method that findAnswer calls after it found the third letter to find the fourth letter of the answer in the maze.
	public void findAnswer4() {
		
			//find where the forth letter of the guess is
	
			if(thirdLetter) {
			charLetter4 = guess.charAt(3);
			stringLetter4 = String.valueOf(charLetter4);
			if(horizontal) {
			for(int col = 0; col < Answers.getAnswersLetters()[0].length; col++) {
				if(Answers.getAnswersLetters()[rowOfAnswer3][col].equalsIgnoreCase(stringLetter4)) {
					
						rowOfAnswer4 = rowOfAnswer3;
						colOfAnswer4 = col;
						if(colOfAnswer4 == colOfAnswer3) {
							colOfAnswer4++;
						}
						col = Answers.getAnswersLetters().length-1;
						//JOptionPane.showMessageDialog(null, "found 4th letter col");
						fourthLetter = true;
					
				}
			}	
			}
			//if you didnt find it....
			if(!horizontal) {
			if(!fourthLetter) {
				for(int row = 0; row < Answers.getAnswersLetters().length; row++) {
					if(Answers.getAnswersLetters()[row][colOfAnswer3].equalsIgnoreCase(stringLetter4)) {
						
							rowOfAnswer4 = row;
							colOfAnswer4 = colOfAnswer3;
							if(rowOfAnswer4 == rowOfAnswer3) {
								rowOfAnswer4++;
							}
							row = Answers.getAnswersLetters().length-1;
							//JOptionPane.showMessageDialog(null, "forth letter found at Row: " + rowOfAnswer4 + " col: " + colOfAnswer4);
						
							fourthLetter = true;
						
						
					}
				}
			}	
			}
			//if you still didnt find it, go find the second letter again
			if(!fourthLetter) {
				//findAnswer1();
			}
			
			}

			
		}
	
	
	//This is a method that findAnswer calls after it found the fourth letter to find the fifth letter of the answer in the maze.
	public void findAnswer5() {
		
			//find where the fifth letter of the guess is
			if(fourthLetter) {
			charLetter5 = guess.charAt(4);
			stringLetter5 = String.valueOf(charLetter5);
			if(horizontal) {
				for(int col = 0; col < Answers.getAnswersLetters()[0].length; col++) {
					if(Answers.getAnswersLetters()[rowOfAnswer4][col].equalsIgnoreCase(stringLetter5)) {
						rowOfAnswer5 = rowOfAnswer4;
						colOfAnswer5 = col;
						if(colOfAnswer5 == colOfAnswer3) {
							colOfAnswer5+=2;
						}
						else if(colOfAnswer5 == colOfAnswer2) {
							colOfAnswer5+=3;
						}
						col = Answers.getAnswersLetters().length-1;
						
						fifthLetter = true;
					}
				}
				}
				//if you didnt find it....
			if(!horizontal) {
				if(!fifthLetter) {
					for(int row = 0; row < Answers.getAnswersLetters().length; row++) {
						if(Answers.getAnswersLetters()[row][colOfAnswer4].equalsIgnoreCase(stringLetter5)) {
							rowOfAnswer5 = row;
							colOfAnswer5 = colOfAnswer4;
							if(rowOfAnswer5 == rowOfAnswer3) {
								rowOfAnswer5+=2;
							}
							else if(rowOfAnswer5 == rowOfAnswer2) {
								rowOfAnswer5+=3;
							}
							row = Answers.getAnswersLetters().length-1;
							
							fifthLetter = true;
							//JOptionPane.showMessageDialog(null, "fifth letter found at Row: " + rowOfAnswer5 + " col: " + colOfAnswer5);
						}
					}
					}
				}
				//if you still didnt find it, go find the second letter again
				if(!fifthLetter) {
					//findAnswer1();
				}
			}
		
	}
}