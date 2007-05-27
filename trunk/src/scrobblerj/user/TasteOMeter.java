package scrobblerj.user;

import scrobblerj.artist.Artist;

public class TasteOMeter implements ITasteOMeter {
	
	private TasteOMeterProxy proxy;
	TasteOMeter(IUser userA,IUser userB) {
		proxy = new TasteOMeterProxy(userA,userB);
	}
	
	public Artist[] getCommonArtists() {
		return proxy.getCommonArtists();
	}

	public float getScore() {
		return proxy.getScore();
	}
}
