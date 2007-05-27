package scrobblerj;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
	
	protected abstract String getDataURL();
	protected abstract String getRootTag();
	protected abstract void nodeVisited(Node node);

}
