import java.io.Serializable;

public class User implements Serializable{
	
	private String username;
	private char[] password;
	private int score;
	
	
	User(String username, char[] password){
		this.username=username;
		this.password=password;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getUsername() {
		return username;
	}

	public char[] getPassword() {
		return password;
	}
}
