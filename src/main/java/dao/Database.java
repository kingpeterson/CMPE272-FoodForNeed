package dao;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/database")
public class Database {
	
	@GET
	public void helloWorld(){
		System.out.println("Hello World");
	}

    public static void main(String[] args) {
		String envServices = System.getenv("VCAP_SERVICES");

    	System.out.println(envServices);
    	
    	String ServerName = "75.126.155.153";
		int PortNumber = 50001;
		String DatabaseName = "SQLDB";
		String user = "user17320";
		String userPassword = "hLklVWxIHhlu";

		java.util.Properties properties = new java.util.Properties();

		properties.put("user", user);
		properties.put("password", userPassword);
		properties.put("sslConnection", "true");

		String url = "jdbc:db2://" + ServerName + ":"+ PortNumber + "/" +
		DatabaseName + ";";

		java.sql.Connection con = null; 
		try
		{
			Class.forName("com.ibm.db2.jcc.DB2Driver");

//			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance(); 
		}
		catch ( Exception e )
		{
			System.out.println("Error: failed to load Db2 jcc driver."); 
			System.out.println(e);

		}
		
		try
		{
			System.out.println("url: " + url);
			con = java.sql.DriverManager.getConnection(url, properties);
			if (con != null) {
				System.out.println("Success");
			} else { 
				System.out.println("Failed to make the connection");
			}
			con.close();
		}
		catch (Exception e)
		{
			if (con != null) {
				try {
					con.close(); 
				} catch (Exception e2) {
					e2.printStackTrace(); 
		}
		 }
		e.printStackTrace();
		}
        
    }
	

}
