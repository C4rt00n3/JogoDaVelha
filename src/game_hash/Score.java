package game_hash;

public class Score {
	int EMPATE = 0;
	int X = 1;
	int O = -1;
	
	public int get(String value) {
		if(value == "O")
			return EMPATE;
		if(value == "X")
			return X;
		if(value == "O")
			return O;
		return 3;
	}
}
