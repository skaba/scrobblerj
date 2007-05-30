package scrobblerj;

import java.util.Vector;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import scrobblerj.tag.Tag;
import scrobblerj.tag.TopTagItem;

public abstract class XMLParserProxy extends Proxy {

	@Override
	protected void fetch() {
		try {
			Document document =DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(getDataURL());
			NodeList nodes=document.getElementsByTagName(getRootTag()).item(0).getChildNodes();
			for(int i=0;i<nodes.getLength();i++)
				nodeVisited(nodes.item(i));
			setFecthed(true);
		} catch (Exception e) {
			e.printStackTrace();
			setFecthed(false);
		}
	}
	
	protected final TopTagItem[] getTopTagsByURL(String url) {
		try {
			Document document =DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
			NodeList nodes=document.getElementsByTagName("tag");
			Vector<TopTagItem> topTags=new Vector<TopTagItem>();
			for(int i=0;i<nodes.getLength();i++) {
				String _tagName=nodes.item(i).getChildNodes().item(1).getTextContent();
				long _count=Long.parseLong(nodes.item(i).getChildNodes().item(3).getTextContent());
				topTags.add(new TopTagItem(new Tag(_tagName),_count));
			}
			TopTagItem[] tmp=new TopTagItem[1];
			return topTags.toArray(tmp);
		} catch(Exception e) {
			return null;
		}
	}
	
	protected abstract String getDataURL();
	protected abstract String getRootTag();
	protected abstract void nodeVisited(Node node);

}
