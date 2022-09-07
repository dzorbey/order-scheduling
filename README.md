# Order-Scheduling Api Project
# Author: Delikan Zorbey Gokyildiz

This is a Spring Boot Api implementation project of an order-scheduling process that is by default running on port 2700. [the default port can be updated via the configuration file]

The project creates allocations for a received set of delivery orders for a predefined container group. Later on those allocations are transformed into a set of journeys.
The main algorithmic approach used for allocations is a variation of the knapsack algorithm.


Initial setup 

-The project relies on postgres, and the actual db name required to be created before mvn install is called "scheduling_bl".
      This db should have user owner/user defined as "postgres" with an empty password. A sample creation script would look like this;

  CREATE DATABASE scheduling_bl
    WITH OWNER = postgres
         ENCODING = 'UTF8'
         TABLESPACE = pg_default
         LC_COLLATE = 'en_US.UTF-8'
         LC_CTYPE = 'en_US.UTF-8'
         CONNECTION LIMIT = -1;

  but should be easily doable via the pgAdmin application UI as well.



-The java version used in development is Java 11, and the local mvn config is:

  Apache Maven 3.5.0
  Java version: 11.0.15, vendor: Red Hat, Inc.
  Java home: /usr/lib/jvm/java-11-openjdk-11.0.15.0.9-2.el7_9.x86_64
  Default locale: en_US, platform encoding: UTF-8
  OS name: "linux", version: "4.20.13-1.el7.elrepo.x86_64", arch: "amd64", family: "unix"


  BUT, the java version in pom.xml for delivery is reduced to 1.8 to be compatible across many multiple mvn environments, you can change it as you like.



-After the project db is created, we need to run:
      
      "mvn liquibase:update clean install -U"

  This command will execute the existing liquibase script and the db tables will be created, once those are created the jooq mvn plugin will try to auto generate the jooq generated db access classes,
  since those are already a part of the codebase, the diff will be empty space, so nothing will change.

  While running the command, the sample test scenario will be executed and you can see the outcome. I have three test cases as of now of which the two are commented out and you will be able to test each scenario later on as you like
  via "SchedulingServiceTest" class.





Application Run and API access

-There is a rest api implementation with two endpoints via which you can initiate the main scenario and retrieve the outcomes. 
 You can start up the Spring Boot in IDE via "OrderSchedulingApplication" class. Local Eclipse development env: Version: 2022-03 (4.23.0)



  The swagger api documentation is available via URL: "http://localhost:2700/swagger-ui.html"

  Endpoint for order scheduling : "http://localhost:2700/schedule/orders" [POST : expecting JSON input]

  sample input for the endpoint: 

  {
	"orders": [{
		"orderLines": [{
			"source": "shell",
			"destination": "pointA",
			"weight": 3.0,
			"fuelType": "gas"
		}, {
			"source": "shell",
			"destination": "pointA",
			"weight": 3.0,
			"fuelType": "gas"
		}, {
			"source": "shell",
			"destination": "pointB",
			"weight": 4.0,
			"fuelType": "diesel"
		}]
	}, {
		"orderLines": [{
			"source": "shell",
			"destination": "pointA",
			"weight": 4.0,
			"fuelType": "gas"
		}, {
			"source": "exxon",
			"destination": "pointB",
			"weight": 3.0,
			"fuelType": "diesel"
		}]
	}]
  } 

  Endpoint for retrieving allocations and journey results : "http://localhost:2700/schedule/orders" [GET] 
  
  This endpoint will return the allocated set of journeys with their order unit allocations per rigid compartment per journey. Meanwhile the DB records can be accessed via "scheduling_bl" tables during the operations.


