package scrobblerj.album;

import java.net.URL;
import java.util.Date;

public interface IAlbum {
	public String getTitle();
	public String getArtist();
	public Date getReleaseDate();
	public URL getURL();
}
