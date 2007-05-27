package scrobblerj.user;

import java.awt.image.RenderedImage;
import java.net.URL;
import java.util.Date;


public interface IUser {
	public abstract int getAge();
	public abstract String getCountry();
	public abstract Gender getGender();
	public abstract long getPlayCount();
	public abstract URL getURL();
	public abstract String getRealname();
	public abstract Date getRegisterationDate();
	public abstract RenderedImage getAvatar();
	public abstract String getUsername();
	public abstract User[] getFriends();
	public abstract Neighbour[] getNeighbours();
	public abstract TasteOMeter getTasteOMeter(User userB);
}