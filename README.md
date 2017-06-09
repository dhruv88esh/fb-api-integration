fb-api-integration Web Application:
===================================

Software or library version used:
--------------------------------------
Java Version: 1.8.0_131
Maven Version: 3.5.0
Tomcat Version: Apache Tomcat 7.0

Libraries: Will be downloaded automatically by Maven while running 'mvn clean install' on project
M2_REPO/junit/junit/4.11/junit-4.11.jar
M2_REPO/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar
M2_REPO/commons-codec/commons-codec/1.9/commons-codec-1.9.jar
M2_REPO/commons-logging/commons-logging/1.2/commons-logging-1.2.jar
M2_REPO/org/apache/httpcomponents/httpclient/4.5/httpclient-4.5.jar
M2_REPO/org/apache/httpcomponents/httpcore/4.4.1/httpcore-4.4.1.jar
M2_REPO/com/googlecode/json-simple/json-simple/1.1.1/json-simple-1.1.1.jar
M2_REPO/org/json/json/20090211/json-20090211.jar
M2_REPO/log4j/log4j/1.2.17/log4j-1.2.17.jar
M2_REPO/com/restfb/restfb/2.0.0-rc.2/restfb-2.0.0-rc.2.jar
----------------------------------------


EntryPoint servlet and jsp:
---------------------------------
fb-api-integration\src\main\java\com\inm\fb\graph\api\MainServlet.java
fb-api-integration\WebContent\WEB-INF\pages\index.jsp


Setup to run the application:
----------------------------------------
- Create facebook account and a facebook page.
- Create some posts on facebook page also.
- Create an app on developers.facebook.com/apps to connect to facebook developer api
- Generate access token for your app on https://developers.facebook.com/tools/explorer

- Create postgresDB on machine and run the schemas defined in the file
	fb-api-integration\src\main\resources\com\inm\fb\graph\db\schema_tables.sql
		- Contains all the schemas to create the tables in postgresdb
		- contains schemas to create the indexes for tables for fast data retrieval
	
	fb-api-integration\WebContent\WEB-INF\config\application.properties 
	  - add details about PostgresDB connection.
	  - add details for facebook page like secret key, access token for fb connection
	  - Also facebook page id and app id to get the latest post from fb page

----------------------------------------


Implementation Strategy and points:
----------------------------------------
- RestFB API is used to authenticate to Facebook and make connection.
- PostgresDB is used to store the data for the facebook posts
- index.jsp will show the data for latest post from facebook page
- AJAX call method is used for refresh mechanism to refresh the data on index.jsp file
- Every 60 seconds the page will be refreshed with latest post and data.
- User can also click on Refresh button to refresh the data on the page.
- PageId and other settings are configurable in application.properties file so no need to make change in the code. Just update in application.properties
- Log4j is used to store the logs for the application.

Technical details:
------------------------
fb-api-integration\src\main\java\com\inm\fb\graph\api\ContextListener.java
	- Will be loaded while startup of the web application.
	- Will load all the properties from application.properties file and initialise AppProperties
	- Will load lo4j configs from log4j.properties file and intialise logger
	
fb-api-integration\src\main\java\com\inm\fb\graph\api\MainServlet.java
	- Main servlet which will be called whenever client will request for latest fb page data
	- Will also provide the response to AJAX request.

fb-api-integration\src\main\java\com\inm\fb\graph\api\post\model\MetaData.java
and 
fb-api-integration\src\main\java\com\inm\fb\graph\api\post\model\PerformanceMetrics.java
	- Models which are used to contains the fb post data
	- Are using builder pattern to preserve immutability

fb-api-integration\src\main\java\com\inm\fb\graph\api\connection\FBConnection.java
	- Making connection to facebook 
	- also getting the latest data for facebook page

fb-api-integration\src\main\java\com\inm\fb\graph\api\db
	- package contains class to make db connection and persist data to db.
	
fb-api-integration\src\main\resources\com\inm\fb\graph\db\queries.sql
	- Contains the queries which will be needed by external component to query the postresDB

fb-api-integration\WebContent\WEB-INF\config\log4j.properties
	- Contains properties to setup log4j logger

fb-api-integration\WebContent\WEB-INF\pages\index.jsp
	- Welcome file which will show latest fb data to user.

fb-api-integration\WebContent\WEB-INF\scripts\refresh.js
	- contains refresh mechanism
	- AJAX call methods

fb-api-integration\WebContent\WEB-INF\web.xml
 - Web deployement descriptor which has all the entries for servlets and config files.

----------------------------------------


Further Improvements
----------------------------------------
- Add Integrations tests for db connection and facebook connection.
- Explore RestFB API documentation to get other data for Facebook post. I have only used few data for now just to show how to get data using restfb.
----------------------------------------