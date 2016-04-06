package dao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/database")
public class Database {
	
	@GET
	public void helloWorld(){
		System.out.println("Hello World");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
