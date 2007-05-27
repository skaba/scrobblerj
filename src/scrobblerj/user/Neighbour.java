package scrobblerj.user;

public class Neighbour {
	private User user;
	private float match;
	public Neighbour(User user, float match) {
		this.user = user;
		this.match = match;
	}
	public float getMatch() {
		return match;
	}
	public User getUser() {
		return user;
	}
	
}
