package net.java.jddac.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.Port;

import net.java.jddac.jmdi.fblock.MeasInfoBlock;
import net.java.jddac.jmdi.fblock.MetadataStoreBlock;
import net.java.jddac.jmdi.fblock.NCAPBlock;
import net.java.jddac.jmdi.fblock.ProbeBlock;
import net.java.jddac.jmdi.fblock.UserBlock;

/**
 * Servlet implementation class ConnectorServlet
 */
public class ConnectorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// Instantiate F-Blocks
	private NCAPBlock ncapBlock;
    private UserBlock userBlock;
    private ProbeBlock probeBlock;
    private MeasInfoBlock measInfoBlock;
    private MetadataStoreBlock metadataStoreBlock;
	
    public ConnectorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Create HTML for Output
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("<head><title>JDDACConnector</title></head>");
		writer.println("<body style=\"font-family:'Trebuchet MS', Verdana, Arial\">");
		writer.println("<div style=\"width:100%; height:auto; text-align:left; margin-top:20px;\">");
		writer.println("<h1>ConnectorServlet</h1>");
		
		try{
			
			if(request.getParameter("method").compareTo("getAll")==0){
				
				// Get Server's NCAPBlock
				ncapBlock = NCAPBlock.getNCAPBlock();
				
				// Lookup registered F-Blocks
				userBlock = (UserBlock) ncapBlock.lookupObject("User");
				probeBlock = (ProbeBlock) ncapBlock.lookupObject("ProbeBlock");
				measInfoBlock = (MeasInfoBlock) ncapBlock.lookupObject("MeasInfoBlock");
				metadataStoreBlock = (MetadataStoreBlock) ncapBlock.lookupObject("MetadataStoreBlock");
				
				// Get all registered Blocks as Vector and an Iterator over the latter
				Iterator allobj = ncapBlock.getAllObjects().iterator();
				
				// adding HTML code to Output
				writer.println("<div style=\"height:auto; text-align:left; margin-top:40px; font-size: 12px;\">");
				
				writer.println("<h2>Information Retrival Chain</h2>");
				writer.println("<p><b>UserBlock.getUserCount: " + userBlock.getAllCount() + "</p></b>");
				writer.println("<p><b>UserBlock.getIdByName(admin): </b>" + userBlock.getIdByName("admin") +"</p>");
				writer.println("<p><b>ProbeBlock.getProbeList(uid:2): </b>" + probeBlock.getProbeList(2) +"</p>");
				writer.println("<p><b>MeasInfoBlock.getMeasList(pid:3): </b>" + measInfoBlock.getMeasList(3) +"</p>");
				writer.println("<p><b>MeasInfoBlock.getIdByName: </b>" + measInfoBlock.getIdByName("admin:TNMB Sudden Motion Sensor:My Sudden Motion Sensor Stim:AccZ") +"</p>");
				writer.println("<p><b>MeasInfoBlock.getMeasList(pid:3).lastkey: </b>" + measInfoBlock.getMeasList(3).lastKey().toString() +"</p>");
				writer.println("<p><b>MeasInfoBlock.getMeasList(pid:3).keySet().size(): </b>" + measInfoBlock.getMeasList(3).keySet().size() +"</p>");
				writer.println("<p><b>MeasInfoBlock.getMeasList(pid:3).keySet().toString(): </b>" + measInfoBlock.getMeasList(3).keySet().toString() +"</p>");
				writer.println("<p><b>MeasInfoBlock.getMeasList(pid:3).entrySet(): </b>" + measInfoBlock.getMeasList(3).entrySet().toString() +"</p>");
				writer.println("<p><b>MeasInfoBlock.getMeasList(pid:3).values().toString(): </b>" + measInfoBlock.getMeasList(3).values().toString() +"</p>");
				writer.println("<p><b>MetaDataStoreBlock.getMetaData(12): </b>" + metadataStoreBlock.getMetaData(12) +"</p>");
				
				writer.println("<h2>Method Information Content</h2>");
				writer.println("<p><b>MetaDataStoreBlock.getMetaData(12): </b>" + metadataStoreBlock.getMetaData(12) + "</p>");
				
				writer.println("<h2>Currently registered objects at Servers NCAP:</h2>");
				writer.println("<ul>");

				while(allobj.hasNext()){
					writer.println("<li>" + allobj.next().getClass().toString() + "</li>");
				}
				
				writer.println("</ul>");
				writer.println("</div>");
			}else if(request.getParameter("method").compareTo("getAllObjects")==0){
				
			}
			
		}catch(NullPointerException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Finish HTML document output
		writer.println("</div>");
		writer.println("<body>");
		writer.println("</html>");
			
		writer.close();	
	}
}
