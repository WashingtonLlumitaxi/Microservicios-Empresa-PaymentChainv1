# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

### Use docker
docker  build -t customer_image  --no-cache --build-arg JAR_FILE=target/*.jar  this is the short way
docker  build -t customer_image  --no-cache --build-arg JAR_FILE=target/customer-0.0.1-SNAPSHOT.jar .


#Create images a partir de la imagen
docker build -t customer_image --no-cache --build-arg JAR_FILE=target/*.jar .

#Ejecutar contenedor a partir de la imagen
docker run -d -p 7080:8080 --name customer_container customer_image:latest


#Ejecutar orquestacion 
docker compose up -d