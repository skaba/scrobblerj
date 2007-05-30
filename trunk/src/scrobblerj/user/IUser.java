package scrobblerj.user;

import java.awt.image.RenderedImage;
import java.net.URL;
import java.util.Date;

import scrobblerj.album.Album;
import scrobblerj.artist.Artist;
import scrobblerj.tag.TopTagItem;

public interface IUser {
	public int getAge();
	public String getCountry();
	public Gender getGender();
	public long getPlayCount();
	public URL getURL();
	public String getRealname();
	public Date getRegisterationDate();
	public RenderedImage getAvatar();
	public String getUsername();
	public User[] getFriends();
	public Neighbour[] getNeighbours();
	public TasteOMeter getTasteOMeter(User userB);
	public TopTagItem[] getTopTags();
	public TopTagItem[] getTopTagsForArtist(Artist artist);
	//public TopTagItem[] getTopTagsForAlbum(Album album);
}
