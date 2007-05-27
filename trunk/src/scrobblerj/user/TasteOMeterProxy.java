package scrobblerj.user;

import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import scrobblerj.XMLParserProxy;
import scrobblerj.artist.Artist;

class TasteOMeterProxy extends XMLParserProxy implements ITasteOMeter {
	
	private IUser userA,userB;
	
	TasteOMeterProxy(IUser userA, IUser userB) {
		this.userA = userA;
		this.userB = userB;
	}

	@Override
	protected String getDataURL() {
		return "http://ws.audioscrobbler.com/1.0/user/" +
			userA.getUsername()+
			"/tasteometer.xml?with="+
			userB.getUsername();
	}

	@Override
	protected String getRootTag() {
		return "tasteometer";
	}

	@Override
	protected void nodeVisited(Node node) {
		if(node.getNodeName().equals("score"))
			setProperty("score", Float.parseFloat(node.getTextContent()));
		else if (node.getNodeName().equals("commonArtists")) {
			NodeList children=node.getChildNodes();
			Vector<Artist> commons=new Vector<Artist>();
			for(int i=0;i<children.getLength();i++)
				if(children.item(i).getNodeName().equals("artist"))
					commons.add(new Artist(children.item(i).getTextContent().trim()));
			Artist[] tmp= new Artist[1];
			setProperty("commonArtists", commons.toArray(tmp));
		}
	}

	public Artist[] getCommonArtists() {
		return (Artist[])getProperty("commonArtists");
	}

	public float getScore() {
		return (Float)getProperty("score");
	}

}
