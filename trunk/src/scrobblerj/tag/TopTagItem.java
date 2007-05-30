package scrobblerj.tag;


public class TopTagItem {
	private Tag tag;
	private long count;
	public TopTagItem(Tag tag, long count) {
		this.tag = tag;
		this.count = count;
	}
	public long getCount() {
		return count;
	}
	public Tag getTag() {
		return tag;
	}
}
