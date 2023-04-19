# REST API for Online Cab Booking Application

* To Create an Application for Online Cab Booking 

* Customer should be able to view the list of available cabs.

* Customer should be able to book cab for desired location from a certain location. 

* Admin should be able to perform below operations:

      1. Cab Management 
      2. Driver Management
      3. Booking Management

## Tech Stack:

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Lombok
* Swagger

## Modules:

  * Login Module
	* Admin Module
	* Customer Module
	* Driver Management Module
	* Cab Management Module
	* Booking Management Module

## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties](https://github.com/Samrat-Sinha/Online-Cab-Booking-Application/blob/main/Online_Cab_Project/pom.xml) file. 
* Update the port number, username and password as per your local database config.

```
    server.port=1995

spring.datasource.url=jdbc:mysql://localhost:3306/online_cab
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root

```

## Swagger Deployed link
http://localhost:1995/swagger-ui/index.html#/

### Sample API Response for User Login

`POST   localhost:1995/login`

* Request Body

```
    {
        "email": "samrat@gmail.com",
        "password": "sam123",
        "role": "Customer"
    }
    
    {
        "email": "samrat@gmail.com",
        "password": "sam123",
        "role": "Admin"
    }
    
```


## Video Explainer of flow control
 <a href="https://drive.google.com/file/d/1CTeKQ8u9vowsjTv0TV98B15g8E4zcFCr/view">**Video Drive Link** </a>
 
 
### E-R Diagram Of Online Cab Booking Application
---

<img src="https://user-images.githubusercontent.com/101389007/233219411-57241aef-7c4a-40d7-8187-311463b7abe4.png">


---

## Swagger UI

---

<img src="https://user-images.githubusercontent.com/101389007/232260859-f6df5574-20dd-45ba-b343-0ae18a1fac96.png">

---

### Login Controller

---

<img src="https://user-images.githubusercontent.com/101389007/232260920-4d916958-50dd-4410-828e-427a4348ea70.png">

---

### Admin Controller

---

<img src="https://user-images.githubusercontent.com/101389007/232260962-0a4a970d-4e1f-4487-8de1-199212405115.png">

---

### Customer Controller

---

<img src="https://user-images.githubusercontent.com/101389007/232261022-1fcf7b10-854f-4f4b-b833-6f14eace4b8f.png">

---

### Driver Controller

---

<img src="https://user-images.githubusercontent.com/101389007/232261089-d8fd340c-9e7c-4d86-a746-a53c62d6ed2b.png">

---

### Cab Controller

---

<img src="https://user-images.githubusercontent.com/101389007/232261140-f4bb904e-e033-4798-a356-9dfd30c8a3fa.png">

---

### TripBooking Controller

---

<img src="https://user-images.githubusercontent.com/101389007/232261155-0c9521b4-6998-484c-9e31-270705c3b70d.png">

---

## Thank You for Visiting
 
