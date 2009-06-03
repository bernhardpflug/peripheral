package peripheral.logic.sensor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import peripheral.logic.Logging;
import peripheral.logic.sensor.SensorServer.status;


public class XmlMetaParser extends Thread{

    private SensorServer server;
    private Sensor tempSensor;

    public XmlMetaParser (SensorServer server) {
    	this.server = server;
    }
    
    public void run(){
    	createInstancesFromXML();
    }
    
    public void createInstancesFromXML () {
    	
    	server.setConnectionStatus(status.connecting);
    	
    	DataInputStream xml = getXmlFromServer();
    	parseXmlAndCreateInstances(xml);
    	
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
			Logging.getLogger().fine("Malformed URL: " + e.getMessage());
			server.setConnectionStatus(status.error);
		} catch (IOException e) {
			server.setConnectionStatus(status.error);
			Logging.getLogger().fine("Corrput inputstream retrieved from Server: " + e.getMessage());
		}
    	
    	return is;
    }
    
    protected void parseXmlAndCreateInstances(DataInputStream xmlInputStream){
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse((xmlInputStream));
			
			// Get Sensor tags
			NodeList sensors = document.getElementsByTagName("sensor");
			
			// Iterate through sensor nodes
			for(int i = 0; i<sensors.getLength();i++){
				
				// Get child nodes of sensornode
				NodeList s_children = sensors.item(i).getChildNodes();
				
				// Create attributes for Sensor constructor
				String sensorname = "";
				String pid = "";
				
				// Create Sensor Instance
				for(int j = 0; j<s_children.getLength(); j++){
					
					// Get Sensor name
					if(s_children.item(j).getNodeType()==Node.ELEMENT_NODE && s_children.item(j).getNodeName().compareTo("name")==0){
						sensorname = s_children.item(j).getTextContent();
					}
					
					if(s_children.item(j).getNodeType()==Node.ELEMENT_NODE && s_children.item(j).getNodeName().compareTo("pid")==0){
						pid = s_children.item(j).getTextContent();
					}
					
					if(sensorname.compareTo("")!=0 && pid.compareTo("")!=0)
						break;
				}
				
				// Create Sensor instance
				if(sensorname.compareTo("")!=0 && pid.compareTo("")!=0){
					tempSensor = new Sensor(Long.parseLong(pid), sensorname, server);
				}
				
				// Create Channels if available
				for(int j=0; j<s_children.getLength();j++){
					if(s_children.item(j).getNodeType() == Node.ELEMENT_NODE && s_children.item(j).getNodeName().compareTo("channel")==0){
						
						// Get child nodes of channel node
						NodeList c_children = s_children.item(j).getChildNodes();
						
						// Attributes for channel constructor
						String mid = "";
						String fullname = "";
						
						// Iterate through children of channel node to get mid and fullname
						for(int k = 0; k<c_children.getLength(); k++){
							
							// Get mid
							if(c_children.item(k).getNodeType()==Node.ELEMENT_NODE && c_children.item(k).getNodeName().compareTo("mid")==0){
								mid = c_children.item(k).getTextContent();
							}
							
							// Get fullname
							if(c_children.item(k).getNodeType()==Node.ELEMENT_NODE && c_children.item(k).getNodeName().compareTo("fullname")==0){
								fullname = c_children.item(k).getTextContent();
							}
							
							if(mid.compareTo("")!=0 && fullname.compareTo("")!=0)
								break;
						}
						
						// Create Channel
						SensorChannel channel = new SensorChannel(Long.parseLong(mid), fullname, tempSensor);
						
						// Add Metadata to Channel
						TreeMap<String, String> metadata = channel.getMetadata();
						
						// Iterate through channel's children to generate metadata
						for(int k = 0; k<c_children.getLength(); k++){
							Node node = c_children.item(k);
							// Get shortname
							if(c_children.item(k).getNodeType()==Node.ELEMENT_NODE && c_children.item(k).getNodeName().compareTo("shortname")==0){
								metadata.put("shortname", c_children.item(k).getTextContent());
							}
							
							// Get description
							if(c_children.item(k).getNodeType()==Node.ELEMENT_NODE && c_children.item(k).getNodeName().compareTo("description")==0){
								metadata.put("description", c_children.item(k).getTextContent());
							}
							
							// Get datatype
							if(c_children.item(k).getNodeType()==Node.ELEMENT_NODE && c_children.item(k).getNodeName().compareTo("datatype")==0){
								metadata.put("datatype", c_children.item(k).getTextContent());
							}
							
							// Get units
							if(c_children.item(k).getNodeType()==Node.ELEMENT_NODE && c_children.item(k).getNodeName().compareTo("units")==0){
								metadata.put("units", c_children.item(k).getTextContent());
							}
							
							// Get location
							if(c_children.item(k).getNodeType()==Node.ELEMENT_NODE && c_children.item(k).getNodeName().compareTo("location")==0){
								metadata.put("location", c_children.item(k).getTextContent());
							}
							
							// Get upperlimit
							if(c_children.item(k).getNodeType()==Node.ELEMENT_NODE && c_children.item(k).getNodeName().compareTo("upperlimit")==0){
								metadata.put("upperlimit", c_children.item(k).getTextContent());
							}
							
							// Get lowerlimit
							if(c_children.item(k).getNodeType()==Node.ELEMENT_NODE && c_children.item(k).getNodeName().compareTo("lowerlimit")==0){
								metadata.put("lowerlimit", c_children.item(k).getTextContent());
							}
						}
						
						// Add channel to sensor
						tempSensor.getSensorChannels().add(channel);
					}
				}
				
				// Add sensor to server
				server.getSensorList().add(tempSensor);
				server.setConnectionStatus(status.online);
			}
			
		} catch (ParserConfigurationException e) {
			Logging.getLogger().fine(e.getMessage());
			server.setConnectionStatus(status.error);
		} catch (SAXException e) {
			Logging.getLogger().fine(e.getMessage());
			server.setConnectionStatus(status.error);
		} catch (IOException e) {
			Logging.getLogger().fine(e.getMessage());
			server.setConnectionStatus(status.error);
		} catch (IllegalArgumentException e){
			Logging.getLogger().fine(e.getMessage());
			server.setConnectionStatus(status.error);
		}
    }

    
    // GETTERS AND SETTERS
    public SensorServer getServer () {
        return server;
    }
}

