package scrobblerj.user;

import scrobblerj.artist.Artist;

public interface ITasteOMeter {
	public float getScore();
	public Artist[] getCommonArtists();
}
