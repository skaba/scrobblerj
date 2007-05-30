package scrobblerj.test;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import scrobblerj.album.Album;
import scrobblerj.artist.Artist;
import scrobblerj.tag.TopTagItem;
import scrobblerj.user.Neighbour;
import scrobblerj.user.TasteOMeter;
import scrobblerj.user.User;

public class Test {
	public static void main(String[] args) throws IOException, InterruptedException {
		User u = new User("Firari");
		System.out.println(u.getPlayCount());
		System.out.println(u.getRegisterationDate());
		System.out.println(u.getGender());
		Thread.sleep(1000);
		Album a = new Album("Iron Maiden","Dance of Death");
		System.out.println(a.getReleaseDate());
		Thread.sleep(1000);
		User[] friends = u.getFriends();
		for(int i=0;i<friends.length;i++)
			System.out.println(friends[i].getUsername()+": "+friends[i].getRealname());
		Thread.sleep(1000);
		Neighbour[] neighbours = u.getNeighbours();
		for(int i=0;i<neighbours.length;i++)
			System.out.println(neighbours[i].getUser().getUsername()+" "+neighbours[i].getMatch());
		Thread.sleep(1000);
		ImageIO.write(u.getAvatar(),"jpg",new File("test.jpg"));
		Thread.sleep(1000);
		TasteOMeter meter = u.getTasteOMeter(new User("Crims"));
		System.out.println(meter.getScore());
		Artist[] common=meter.getCommonArtists();
		for(Artist ar:common)
			System.out.println(ar.getName());
		Thread.sleep(1000);
		TopTagItem[] topTags=u.getTopTags();
		for(TopTagItem t:topTags)
			System.out.println(t.getTag().getTagName()+": "+t.getCount());
		Thread.sleep(1000);
		System.out.println("------------------");
		topTags=u.getTopTagsForArtist(new Artist("Nightwish"));
		for(TopTagItem t:topTags)
			System.out.println(t.getTag().getTagName()+": "+t.getCount());
		
	}
}
