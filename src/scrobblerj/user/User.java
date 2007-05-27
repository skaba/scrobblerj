package scrobblerj.user;

import java.awt.image.RenderedImage;
import java.net.URL;
import java.util.Date;

public class User implements IUser {
	private UserProxy proxy;
	public int getAge() {
		return proxy.getAge();
	}
	/*
	public String getAvatarURL() {
		if(!fetched) fetch();
		return avatarURL;
	}
	*/
	public String getCountry() {
		return proxy.getCountry();
	}
	public Gender getGender() {
		return proxy.getGender();
	}
	public long getPlayCount() {
		return proxy.getPlayCount();
	}
	public URL getURL() {
		return proxy.getURL();
	}
	public String getRealname() {
		return proxy.getRealname();
	}
	public Date getRegisterationDate() {
		return proxy.getRegisterationDate();
	}
	public String getUsername() {
		return proxy.getUsername();
	}
	public User(String username) {
		proxy=new UserProxy(username);
	}
	
	public User[] getFriends() {
		return proxy.getFriends();
	}
	
	public RenderedImage getAvatar() {
		return proxy.getAvatar();
	}
	public Neighbour[] getNeighbours() {
		return proxy.getNeighbours();
	}
	public TasteOMeter getTasteOMeter(User userB) {
		return proxy.getTasteOMeter(userB);
	}
	
	
	
}
