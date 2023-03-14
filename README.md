# StoreEmployee
This web application allows the user to store employees and stores in a database, and updates or deletes them. It also shows a dashboard which provides info such as number of active stores and number of active employees. The applications also provides a RESTful API for the store and employee entites.

http://localhost:8080/ for homepage

## Endpoints
- GET - /stores
- GET - /stores/{id}
- POST - /stores
- PUT - /stores/{id}
- DELETE - /stores/{id}
- GET - /employees
- GET - /employees/{id}
- POST - /employees
- PUT - /employees/{id}
- DELETE - /employees/{id}
### Example
  GET http://localhost:8080/storeapplication/stores

## Web pages
- http://localhost:8080/custore (create and update web page for stores)
- http://localhost:8080/cuemployee (create and update web page for employees)
- http://localhost:8080/dashboard (dashboard web page)
- http://localhost:8080/demployee (delete employee web page)
- http://localhost:8080/dstore (delete store web page)

## Languages
- JAVA (Spring Boot framework)
- HTML for web pages 

## Dependencies
- spring-boot-starter-data-jpa
- spring-boot-starter-web
- spring-boot-starter-thymeleaf

## How to run 
run (gradlew.bat bootRun) in cmd

## Resources
- Installing and building (https://spring.io/quickstart)
- Accessing Database (https://spring.io/guides/gs/accessing-data-mysql/)
- Creating web pages (https://spring.io/guides/gs/serving-web-content/)
