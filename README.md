## ProductReviewInfoMicroservices_WithoutServiceRegistry
Microservice for Product Info with Review 


### Build Micro-Services
* Both micro-services are used _**maven**_ as build tool. 
* Please use **_mvn clean install_** for building the projects
* Jars will generate in target directory

### Running from command line 
* Please reach into target directory of the projects
* run following commands
* #### java -jar review-service-0.0.1.jar 
* #### java -jar product-service-0.0.1.jar 

### Accessing swagger-ui
* Product Service configured at 8080 : http://localhost:8080/swagger-ui.html
* Review Service configured at 8081 : http://localhost:8081/swagger-ui.html

### Running by docker
* build docker image for review service : _**docker build -t microservicetest/review-service .**_
* build docker image for product service : _**docker build -t microservicetest/product-service .**_
* **docker run -p 8081:8081 --name REVIEW_SERVICE microservicetest/review-service**
* **docker run -p 8080:8080 --add-host host.docker.internal:host-gateway --name PRODUCT_SERVICE microservicetest/product-service**
