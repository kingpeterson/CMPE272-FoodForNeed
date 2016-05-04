#Food for Need
http://foodforneed.mybluemix.net/index.html

This project showcases a simple to use interface to donate and request food. See the link above to gain access to the main page. 

The webpage is implemented in Html and Javascript with jQuery library to provide interactive interface. The UI calls a RESTful CRUD JAX-RS API to display the customers, market, orders and products are the objects in the application. 


## Decomposition
**API - JAX-RS Resource**

See `src/main/java/dao/CustomersDao.java` for the customers object CRUD API and obtaining the EntityManager.

See `src/main/java/dao/MarketDao.java` for the market object CRUD API and obtaining the EntityManager.

See `src/main/java/dao/OrdersDao.java` for the orders object CRUD API and obtaining the EntityManager.

See `src/main/java/dao/ProductsDao.java` for the products object CRUD API and obtaining the EntityManager.


**JPA Configuration**

See `src/main/webapp/WEB-INF/web.xml` & `src/main/resources/META-INF/persistence.xml` for JPA configuration.

**UI**

See `src/main/webapp/WEB-INF/index.html`, `src/main/webapp/WEB-INF/index.js` and `src/main/webapp/WEB-INF/util.js` for the front-end UI calling the back-end API of the main page.

See `src/main/webapp/WEB-INF/donate.html`, `src/main/webapp/WEB-INF/donate.js` and `src/main/webapp/WEB-INF/util.js` for the front-end UI calling the back-end API of the food donating page.

See `src/main/webapp/WEB-INF/request.html`, `src/main/webapp/WEB-INF/request.js` and `src/main/webapp/WEB-INF/util.js` for the front-end UI calling the back-end API of the food requesting page.
