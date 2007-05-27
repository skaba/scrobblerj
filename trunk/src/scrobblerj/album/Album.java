package scrobblerj.album;

import java.net.URL;
import java.util.Date;

public class Album implements IAlbum {
	
	private AlbumProxy proxy;
	
	public Album(String artist,String title) {
		proxy=new AlbumProxy(artist,title);
	}
	
	public String getArtist() {
		return proxy.getArtist();
	}

	public Date getReleaseDate() {
		return proxy.getReleaseDate();
	}

	public String getTitle() {
		return proxy.getTitle();
	}

	public URL getURL() {
		return proxy.getURL();
	}

}
