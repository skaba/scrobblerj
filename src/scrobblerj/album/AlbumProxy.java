package scrobblerj.album;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;



import org.w3c.dom.Node;

import scrobblerj.XMLParserProxy;

public class AlbumProxy extends XMLParserProxy implements IAlbum {
	
	private String artist,title;
	
	public AlbumProxy(String artist, String title) {
		this.artist = artist;
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public Date getReleaseDate() {
		return (Date)getProperty("releasedate");
	}

	public String getTitle() {
		return title;
	}

	public URL getURL() {
		return (URL)getProperty("url");
	}

	@Override
	protected String getDataURL() {
		return "http://ws.audioscrobbler.com/1.0/album/"+artist +"/"+title+"/info.xml";
	}

	@Override
	protected void nodeVisited(Node node) {
		if(node.getNodeName().equals("url"))
			try {
				setProperty("url", new URL(node.getTextContent()));
			} catch (MalformedURLException e) {
			}
		else if(node.getNodeName().equals("releasedate"))
			setProperty("releasedate", new Date(Date.parse(node.getTextContent().substring(0, node.getTextContent().indexOf(',')))));	
	}

	@Override
	protected String getRootTag() {
		return "album";
	}

}
