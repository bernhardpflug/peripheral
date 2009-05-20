package net.java.jddac.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import javax.naming.NameNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.jddac.common.type.ArgArray;
import net.java.jddac.jmdi.fblock.MeasInfoBlock;
import net.java.jddac.jmdi.fblock.MetadataStoreBlock;
import net.java.jddac.jmdi.fblock.NCAPBlock;
import net.java.jddac.jmdi.fblock.ProbeBlock;
import net.java.jddac.jmdi.fblock.UserBlock;

public class MetadataServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	// Instantiate F-Blocks
	private NCAPBlock ncapBlock;
    private UserBlock userBlock;
    private ProbeBlock probeBlock;
    private MeasInfoBlock measInfoBlock;
    private MetadataStoreBlock metadataStoreBlock;
    
    public MetadataServlet() {
        super();
        
        // Get Server's NCAPBlock
		ncapBlock = NCAPBlock.getNCAPBlock();
		
		// Lookup registered F-Blocks
		userBlock = (UserBlock) ncapBlock.lookupObject("User");
		probeBlock = (ProbeBlock) ncapBlock.lookupObject("ProbeBlock");
		measInfoBlock = (MeasInfoBlock) ncapBlock.lookupObject("MeasInfoBlock");
		metadataStoreBlock = (MetadataStoreBlock) ncapBlock.lookupObject("MetadataStoreBlock");
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			
			if(request.getParameter("method").compareTo("getMetadata")==0){
				
				String username;
				long uid = -1;
				
				// Retrieve username from http request
				
				username = request.getParameter("username");
				
				try {
					uid = userBlock.getIdByName(username);
					if(uid==-1){
						throw new NameNotFoundException("User not existing");
					}
				} catch (SQLException e) {
					// Error when querying userBlock for IdByName
					response.sendError(500, e.getMessage());
				} catch (NameNotFoundException e){
					// Error when username is not existing
					response.sendError(400, e.getMessage());
				}
				
				// Valid request, create XML output
				response.setContentType("text/xml; charset=UTF-8");
				
				PrintWriter writer = collectMetadata(uid, response.getWriter(), request);
				writer.close();
				
			}else{
				// Send 501 Error: Method not Implemented
				response.sendError(501);
			}
		}catch(NullPointerException e){
			// Send 400 Error: Method parameter missing
			response.sendError(400, "Method parameter missing");
		}
	}
	
	
	private PrintWriter collectMetadata(long uid, PrintWriter writer, HttpServletRequest request){
		
		// TODO Exception handling
		try{
			// Create HTML Output
			writer.println("<?xml version=\"1.0\" ?>" + "\n");
			
			// Add sensorserver Block
			writer.println("<sensorserver>");
			writer.println("	<address>" + request.getLocalAddr() + "</address>");
			writer.println("	<port>" + request.getLocalPort() + "</port>");
			writer.println("	<user>");
			writer.println("		<name>" + request.getParameter("username") + "</name>");
			writer.println("		<uid>" + uid + "</uid>");
			
			// Add sensorblock
			// TODO Nullpointerexception if no probes are available
			TreeMap probelist = probeBlock.getProbeList(uid);
			Set probeset = probelist.entrySet();
			Iterator probeit = probeset.iterator();
			
			// Iterate through probes
			while(probeit.hasNext()){
				
				writer.println("		<sensor>");
				
				// String actions to retrieve information
				String temp = probeit.next().toString();
				String pid = temp.substring(temp.indexOf('=')+1);
				String name = temp.substring(0, temp.indexOf('='));
				
				writer.println("			<pid>" + pid + "</pid>");
				writer.println("			<name>" + name + "</name>");
				
				// Add Channelblock for current probe
				
				TreeMap channellist = measInfoBlock.getMeasList(Long.parseLong(pid));
				Set channelset = channellist.entrySet();
				Iterator channelit = channelset.iterator();
				
				// Iterate through channels
				while(channelit.hasNext()){
					
					// String actions to retrieve channel information
					String temp2 = channelit.next().toString();
					String mid = temp2.substring(temp2.indexOf('=')+1);
					String name2 = temp2.substring(0, temp2.indexOf('='));
					
					// Retrieve metadata from MetaDataStoreBlock
					ArgArray metadata = metadataStoreBlock.getMetaData(Long.parseLong(mid));
					
					writer.println("			<channel>");
					writer.println("				<mid>" + mid + "</mid>");
					writer.println("				<fullname>" + name2 + "</fullname>");
					
					// Shortname
					if(metadata.getString("name")==null){
						writer.println("				<shortname></shortname>");
					}else{
						writer.println("				<shortname>" + metadata.getString("name") + "</shortname>");
					}
					
					// Description
					if(metadata.getString("description")==null){
						writer.println("				<description></description>");
					}else{
						writer.println("				<description>" + metadata.getString("description") + "</description>");
					}
					
					// DataType
					if(metadata.getString("dataType")==null){
						writer.println("				<datatype></datatype>");
					}else{
						writer.println("				<datatype>" + metadata.getString("dataType") + "</datatype>");
					}
					
					// Units
					if(metadata.getString("units")==null){
						writer.println("				<units></units>");
					}else{
						writer.println("				<units>" + metadata.getString("units") + "</units>");
					}
					
					// location
					if(metadata.getString("location")==null){
						writer.println("				<location></location>");
					}else{
						writer.println("				<location>" + metadata.getString("location") + "</location>");
					}
					
					// Upperlimit
					if(metadata.getString("upperlimit")==null){
						writer.println("				<upperlimit></upperlimit>");
					}else{
						writer.println("				<upperlimit>" + metadata.getString("upperlimit") + "</upperlimit>");
					}
					
					// Lowerlimit
					if(metadata.getString("lowerlimit")==null){
						writer.println("				<lowerlimit></lowerlimit>");
					}else{
						writer.println("				<lowerlimit>" + metadata.getString("lowerlimit") + "</lowerlimit>");
					}
					
					writer.println("			</channel>");
				}
				
				writer.println("		</sensor>");
			}
			
			writer.println("	</user>");
			writer.println("</sensorserver>");
			
			return writer;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

}
