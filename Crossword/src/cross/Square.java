package cross;

//This is the class that each object in the crossword 2D array is set up with: a boolean for if the square is black and an int for what number word it is (across/down).
public class Square {

	private boolean _isBlack;
	private int _num;
	
	public Square(boolean isBlack, int num) {
		_isBlack = isBlack;
		_num = num;
	}
	
	public boolean getBlack() {return _isBlack;}
	public int getNum() {return _num;}
}	
