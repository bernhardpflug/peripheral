package peripheral.logic.sensor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XmlMetaParser {

    private SensorServer server;

    public XmlMetaParser (SensorServer server) {
    	this.server = server;
    }
    
    public void createInstancesFromXML () {
    	
    	DataInputStream xml = getXmlFromServer();
    	parseXml(xml);
    }
    
    protected DataInputStream getXmlFromServer(){
    	
    	URL url = null;
    	URLConnection conn = null;
    	DataInputStream is = null;
    	
    	try {
    		
    		// Create URL
			url = new URL(server.getAddress() + ":" + server.getPort() + 
					"/MetadataServlet?method=getMetadata&username=" + server.getUsername());
			
			// Establish URLConnection
			conn = url.openConnection();
			is = new DataInputStream(conn.getInputStream());
			
		} catch (MalformedURLException e) {
			System.err.println("Malformed URL: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Corrput inputstream retrieved from Server: " + e.getMessage());
		}
    	
    	return is;
    }
    
    protected void parseXml(DataInputStream xmlInputStream){
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse((xmlInputStream));
			
			NodeList sensors = document.getElementsByTagName("sensor");
			
			for(int k = 0; k<sensors.getLength();k++){
				
				System.out.println("<sensor>");
				
				NodeList children = sensors.item(k).getChildNodes();
				
				// Check for Channels first
				for(int i = 0; i<children.getLength(); i++){
					if(children.item(i).getNodeType() == Node.ELEMENT_NODE && children.item(i).getNodeName().compareTo("channel")==0){
						
						NodeList channels = children.item(i).getChildNodes();
						
						for(int j = 0; j<channels.getLength(); j++){
							if(channels.item(j).getNodeType() == Node.ELEMENT_NODE){
								System.out.println(channels.item(j).getNodeName() + ": " + channels.item(j).getTextContent());
							}
						}
						
					}
				}
				
				// Print attributes
				for(int i = 0; i<children.getLength(); i++){
					if(children.item(i).getNodeType() == Node.ELEMENT_NODE && children.item(i).getNodeName().compareTo("channel")!=0){
						System.out.println(children.item(i).getNodeName() + ": " + children.item(i).getTextContent());
					}
				}
				
				System.out.println("</sensor>\n");
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    
    // GETTERS AND SETTERS
    public SensorServer getServer () {
        return server;
    }
}

