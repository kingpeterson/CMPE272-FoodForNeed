package dao;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



// CRUD API at /api/todolist
@Path("/ordersDao")
/**
 * RESTful CRUD service of database.
 *
 */
public class OrdersDao {

	private UserTransaction utx;
	private EntityManager em;

	public OrdersDao() {
		utx = getUserTransaction();
		em = getEm();
	}

	@POST
	public Response create(@FormParam("seafood") Integer seafood, 
			@FormParam("meat") Integer meat,
			@FormParam("vegetable") Integer vegetable,
			@FormParam("fruit") Integer fruit,
			@FormParam("others") Integer others,
			@FormParam("marketId") Long marketId,
			@FormParam("orderDate") String orderDate,
			@FormParam("srcLatitude") Double srcLatitude,
			@FormParam("srcLongitude") Double srcLongitude,
			@FormParam("customerId") Long customerId,
			@FormParam("destLatitude") Double destLatitude,
			@FormParam("destLongitude") Double destLongitude
			) {
		Orders order = new Orders();
//		id, seafood, meat, vegetable, fruit, others, marketId, orderDate, srcLatitude, srcLongitude, customerId, destLatitude, destLongitude);
		order.setSeafood(seafood);
		order.setMeat(meat);
		order.setVegetable(vegetable);
		order.setFruit(fruit);
		order.setOthers(others);
		order.setMarketId(marketId);
		order.setOrderDate(orderDate);
		order.setSrcLatitude(srcLatitude);
		order.setSrcLongitude(srcLongitude);
		order.setCustomerId(customerId);
		order.setDestLatitude(destLatitude);
		order.setDestLongitude(destLongitude);
		try {
			utx.begin();
			em.persist(order);
			utx.commit();
			return Response.ok(order.toString()).build();
		} catch (Exception e) {
			e.printStackTrace();			
			return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).build();
		} finally {
			try {
				if (utx.getStatus() == javax.transaction.Status.STATUS_ACTIVE) {
					utx.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@DELETE
	public Response delete(@QueryParam("id") long id) {
		
		try {
			utx.begin();
			Orders order = em.find(Orders.class, id);
			if (order != null) {
				em.remove(order);
				utx.commit();
				return Response.ok().build();
			} else {
				return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).build();
		} finally {
			try {
				if (utx.getStatus() == javax.transaction.Status.STATUS_ACTIVE) {
					utx.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@PUT
	public Response update(@FormParam("id") long id, 
			@FormParam("seafood") Integer seafood, 
			@FormParam("meat") Integer meat,
			@FormParam("vegetable") Integer vegetable,
			@FormParam("fruit") Integer fruit,
			@FormParam("others") Integer others,
			@FormParam("marketId") Long marketId,
			@FormParam("orderDate") String orderDate,
			@FormParam("srcLatitude") Double srcLatitude,
			@FormParam("srcLongitude") Double srcLongitude,
			@FormParam("customerId") Long customerId,
			@FormParam("destLatitude") Double destLatitude,
			@FormParam("destLongitude") Double destLongitude) {
		
		try {
			utx.begin();
			Orders order = em.find(Orders.class, id);
			if (order != null) {
//				product.setName(name);// TODO check if null
				order.setSeafood(seafood);
				order.setMeat(meat);
				order.setVegetable(vegetable);
				order.setFruit(fruit);
				order.setOthers(others);
				order.setMarketId(marketId);
				order.setOrderDate(orderDate);
				order.setSrcLatitude(srcLatitude);
				order.setSrcLongitude(srcLongitude);
				order.setCustomerId(customerId);
				order.setDestLatitude(destLatitude);
				order.setDestLongitude(destLongitude);
				em.merge(order);
				utx.commit();
				return Response.ok().build();
			} else {
				return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).build();
		} finally {
			try {
				if (utx.getStatus() == javax.transaction.Status.STATUS_ACTIVE) {
					utx.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") long id) {
		if (id == 0) {
			List<Orders> list = em.createQuery("SELECT o FROM Orders o", Orders.class).getResultList();
			//TODO use JSON util like Gson to render objects and use REST Response Writer
			String json = "{\"id\":\"all\", \"body\":" + list.toString() + "}";
			System.out.println("I am at id == 0");
			System.out.println(json);
			return Response.ok(json).build();
		}
		Orders order = null;
		try {
			utx.begin();
			order = em.find(Orders.class, id);
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR).build();
		} finally {
			try {
				if (utx.getStatus() == javax.transaction.Status.STATUS_ACTIVE) {
					utx.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (order != null){
			System.out.println("I am at found");
			System.out.println(order.toString());
			return Response.ok(order.toString()).build();
		}
		else
			return Response.status(javax.ws.rs.core.Response.Status.NOT_FOUND).build();
	}
	
	
	/*
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String get(@QueryParam("id") long id) {
		if (id == 0) {
			List<Customer> list = em.createQuery("SELECT t FROM Customers t", Customer.class).getResultList();
			//TODO use JSON util like Gson to render objects and use REST Response Writer
			String json = "{\"id\":\"all\", \"body\":" + list.toString() + "}";
			System.out.println("I am at id == 0");
			System.out.println(json);
			return json;
		}
		Customer customer = null;
		try {
			utx.begin();
			customer = em.find(Customer.class, id);
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "INTERNAL SERVER ERROR";
		} finally {
			try {
				if (utx.getStatus() == javax.transaction.Status.STATUS_ACTIVE) {
					utx.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (customer != null){
			System.out.println("I am at found");
			System.out.println(customer.toString());
			return customer.toString();
		}
		else
			return "NOT FOUND";
	}
	*/
	/*
	public void populateDB() {
		List<TODO> list = em.createQuery("SELECT t FROM TODO t", TODO.class).getResultList();
		if (list.size() == 0) {
			create("sample entry #1");
			create("sample entry #2");
			create("sample entry #3");
		}
	}
	*/
	private UserTransaction getUserTransaction() {
		InitialContext ic;
		try {
			ic = new InitialContext();
			return (UserTransaction) ic.lookup("java:comp/UserTransaction");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// There are two ways of obtaining the connection information for some services in Java 
	
	// Method 1: Auto-configuration and JNDI
	// The Liberty buildpack automatically generates server.xml configuration 
	// stanzas for the SQL Database service which contain the credentials needed to 
	// connect to the service. The buildpack generates a JNDI name following  
	// the convention of "jdbc/<service_name>" where the <service_name> is the 
	// name of the bound service. 
	// Below we'll do a JNDI lookup for the EntityManager whose persistence 
	// context is defined in web.xml. It references a persistence unit defined 
	// in persistence.xml. In these XML files you'll see the "jdbc/<service name>"
	// JNDI name used.

	private EntityManager getEm() {
		InitialContext ic;
		try {
			ic = new InitialContext();
			return (EntityManager) ic.lookup("java:comp/env/openjpa-todo/entitymanager");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method 2: Parsing VCAP_SERVICES environment variable
    // The VCAP_SERVICES environment variable contains all the credentials of 
	// services bound to this application. You can parse it to obtain the information 
	// needed to connect to the SQL Database service. SQL Database is a service
	// that the Liberty buildpack auto-configures as described above, so parsing
	// VCAP_SERVICES is not a best practice.

	// see HelloResource.getInformation() for an example

}
