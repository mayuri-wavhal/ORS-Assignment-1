Introduction :

This assignment consists of RESTful services that implements a check out counter for an online retail store that scans products and generates itemized invoice/bill.

Sales tax varies based on of the product as per below : 

Category A - 10%
Category B - 20%
Category C- 0%
************************************************************************************************************************************
Technologies : 
1. Spring Boot
2. Java  
3. Maven 
4. Swagger2
5. H2 Database
6. JUnit
7. Spring Security
8. Spring JPA
***********************************************************************************************************************************
REST endpoints : 
1. Product has below endpoints

	a. GET /products - fetches list of all product data
	b. GET /products/{id} - fetch a specific product
	c. POST /products - Creates a new product

2. Invoice has below endpoints
	a. GET /invoices - Get all invoices
	b. GET /invoices/{id} - Get invoice of specific id
	c. POST /invoices - creates a invoice id.
***********************************************************************************************************************************
Implementation : 
Application is a SpringBoot project developed using Spring Security, Spring JPA and starters and tested using below technologies

1. Apache Maven 3.3.9
2. Java version: 1.8.0_181
3. STS: 3.9.4

Build & Run Commands:
1. Clone Repo  - git clone https://github.com/mayuri-wavhal/ORS-Assignment-1.git .
2. Build project - mvn clean package
3. Run Project using jar - 
    a. cd target
    b. java -jar OnlineRetailStore-0.0.1-SNAPSHOT.jar
    c. Default port - 8080
4. Accecc API using Swagger - http://localhost:8080/swagger-ui.html


