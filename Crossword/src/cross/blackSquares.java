package cross;

//This enum has each boolean 2D array for each level, with true being where a letter is not there and false being where a letter will be.
public enum blackSquares {

	Easy(new boolean[][] { 
			{  false, false,  false,  false, true},
			}), // semi-colon ends the number of variables!!!

	Medium(new boolean[][] { 
		{  true, false, false, false,  true},
		{ false, false, false, false, false},
		{ false, false, false, false, false},
		{ false, false, false, false, false},
		{  true, false, false, false,  true},
		}), // semi-colon ends the number of variables!!!
	
	Hard(new boolean[][] { 
		{  true, false, false, false, false},
		{ false, false, false, false, false},
		{ false, false, false, false, false},
		{ false, false, false, false, false},
		{ false, false, false, false,  true},
		});
	
	private boolean[][] _blackSquares;

	public boolean[][] getBlackSquaresBS() {
		return _blackSquares;
	}

	private blackSquares(boolean[][] blackSquares) {
		_blackSquares = blackSquares;
	}
}
