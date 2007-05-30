package scrobblerj.user;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import scrobblerj.XMLParserProxy;
import scrobblerj.album.Album;
import scrobblerj.artist.Artist;
import scrobblerj.tag.TopTagItem;


class UserProxy extends XMLParserProxy implements IUser {
	
	private String username;
	private boolean imageFetched;
	private RenderedImage image;
	UserProxy(String userName) {
		this.username = userName;
	}

	public int getAge() {
		return (Integer)getProperty("age");
	}

	public String getCountry() {
		return (String)getProperty("country");
	}

	public Gender getGender() {
		return (Gender)getProperty("gender");
	}

	public long getPlayCount() {
		return (Long)getProperty("playcount");
	}

	public URL getURL() {
		return (URL)getProperty("url");
	}

	public String getRealname() {
		return (String)getProperty("realname");
	}

	public Date getRegisterationDate() {
		return (Date)getProperty("registered");
	}

	public String getUsername() {
		return username;
	}
	
	public RenderedImage getAvatar() {
		if(!imageFetched) {
			try {
				image= ImageIO.read(new URL((String)getProperty("avatar")));
				imageFetched=true;
				return image;
			} catch (IOException e) {
				imageFetched=false;
				e.printStackTrace();
				return null;
			}
		}
		else
			return image;
	}

	@Override
	protected String getDataURL() {
		return "http://ws.audioscrobbler.com/1.0/user/"+username+"/profile.xml";
	}

	@Override
	protected void nodeVisited(Node node) {
		if(node.getNodeName().equals("url"))
			try {
				setProperty("url", new URL(node.getTextContent()));
			} catch (MalformedURLException e) {
			}
		else if(node.getNodeName().equals("registered")) {
			setProperty("registered", new Date(Date.parse(node.getTextContent())));
			//System.out.println(node.getAttributes().getNamedItem("unixtime").getTextContent());
			//setProperty("registered", new Date(Long.parseLong(node.getAttributes().getNamedItem("unixtime").getTextContent())));
		}
		else if(node.getNodeName().equals("age"))
			setProperty("age", Integer.parseInt(node.getTextContent()));
		else if(node.getNodeName().equals("playcount"))
			setProperty("playcount", Long.parseLong(node.getTextContent()));
		else if(node.getNodeName().equals("gender")) {
			if(node.getTextContent().equals("m"))
				setProperty("gender", Gender.MALE);
			else if(node.getTextContent().equals("f"))
				setProperty("gender", Gender.FEMALE);
		}
		else if(
				node.getNodeName().equals("realname") ||
				node.getNodeName().equals("country") ||
				node.getNodeName().equals("avatar")
		)
			setProperty(node.getNodeName(), node.getTextContent());
	}
	
	public User[] getFriends() {
		//TODO: Should this be cached?
		Vector<User> friends = new Vector<User>();
		try {
			Document document =DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("http://ws.audioscrobbler.com/1.0/user/"+username+"/friends.xml");
			NodeList nodes=document.getElementsByTagName("user");
			for(int i=0;i<nodes.getLength();i++)
				friends.add(new User(nodes.item(i).getAttributes().getNamedItem("username").getTextContent()));
		} catch(Exception e) {
			e.printStackTrace();
		}
		User u[] = new User[1];
		return friends.toArray(u);
	}
	

	@Override
	protected String getRootTag() {
		return "profile";
	}

	public Neighbour[] getNeighbours() {
		Vector<Neighbour> neighbours= new Vector<Neighbour>();
		try {
			Document document =DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("http://ws.audioscrobbler.com/1.0/user/"+username+"/neighbours.xml");
			NodeList nodes=document.getElementsByTagName("user");
			for(int i=0;i<nodes.getLength();i++) {
				String un=nodes.item(i).getAttributes().getNamedItem("username").getTextContent();
				float match =Float.parseFloat(nodes.item(i).getChildNodes().item(5).getTextContent());
				neighbours.add(new Neighbour(new User(un),match));
			}
				
		} catch(Exception e) {
			e.printStackTrace();
		}
		Neighbour n[] = new Neighbour[1];
		return neighbours.toArray(n);
	}

	public TasteOMeter getTasteOMeter(User userB) {
		return new TasteOMeter(this,userB);
	}

	public TopTagItem[] getTopTags() {
		return getTopTagsByURL("http://ws.audioscrobbler.com/1.0/user/"+username+"/tags.xml");
	}

	public TopTagItem[] getTopTagsForArtist(Artist artist) {
		return getTopTagsByURL("http://ws.audioscrobbler.com/1.0/user/"+username+"/artisttags.xml?artist="+artist.getName());
	}
	
}
