package scrobblerj.artist;

import java.net.MalformedURLException;
import java.net.URL;

public class Artist {
	private String name;

	public Artist(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public URL getURL() {
		try {
			return new URL("http://www.last.fm/music/"+name);
		} catch (MalformedURLException e) {
		}
		return null;
	}
}
