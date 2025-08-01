# Golf Tournament System REST API
This is a Spring Boot REST API for managing Golf Club Members and Tournaments. The API allows CRUD operations on members and tournaments, linking members to tournaments, and supports search functionalities.

## Features
- Add / View / Update / Delete Members

- Add / View / Update / Delete Tournaments

- Add Members to Tournaments

- Search Members by:
  - Name
  - Phone Number
  - Membership Start Date
    
- Search Tournaments by:
  - Start Date
  - Location
  - View all Members participating in a Tournament


## Technologies
- Java 22 (or compatible Java 17+)

- Spring Boot

- Spring Data JPA

- MySQL

- Postman (for API testing)

- Docker (optional for DB deployment)

## API Endpoints
### Members
| Method | Endpoint                                          | Description                    |
| ------ | ------------------------------------------------- | ------------------------------ |
| GET    | `/members`                                        | Get all members                |
| GET    | `/members/{id}`                                   | Get member by ID               |
| POST   | `/member`                                         | Create a new member            |
| PUT    | `/member/{id}`                                    | Update a member                |
| DELETE | `/member/{id}`                                    | Delete a member                |
| GET    | `/members/searchByName?name={name}`               | Search members by name         |
| GET    | `/members/searchByPhoneNumber?phone={phone}`      | Search members by phone number |
| GET    | `/members/searchByStartDate?startDate=yyyy-MM-dd` | Search members by start date   |

### Tournaments
| Method | Endpoint                                             | Description                      |
| ------ | ---------------------------------------------------- | -------------------------------- |
| GET    | `/tournaments`                                       | Get all tournaments              |
| GET    | `/tournaments/{id}`                                  | Get tournament by ID             |
| POST   | `/tournament`                                        | Create a new tournament          |
| PUT    | `/tournament/{id}`                                   | Update a tournament              |
| DELETE | `/tournament/{id}`                                   | Delete a tournament              |
| GET    | `/tournament/searchByLocation?location={location}`   | Search tournaments by location   |
| GET    | `/tournament/searchByStartDate?startDate=yyyy-MM-dd` | Search tournaments by start date |
| GET    | `/tournament/{id}/members`                           | Get all members in a tournament  |
| POST   | `/tournament/{tournamentId}/addMember/{memberId}`    | Add a member to a tournament     |

## Running the Project
1. Setup MySQL Database:
- Create a schema: golf_tournament_db
- Update application.properties:
### application.properties:
```
spring.datasource.url=jdbc:mysql://localhost:3306/golf_tournament_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
## Run the Application:

- In IntelliJ/Eclipse: Run RestServiceApplication.java

- Or via terminal:
```
mvn spring-boot:run
```

## Test in Postman:

- Use the endpoints above.
- For POST/PUT requests, use application/json body.

## Running with Docker:

### 1. Build the Docker Image
From the root of your project, build the Docker image:
```
docker build -t golf_tournament_system .
```
### 2. Run MySQL Container
Run a MySQL container that the API will connect to:
```
docker run --name golf_db \
    -e MYSQL_ROOT_PASSWORD=root \
    -e MYSQL_DATABASE=golf_db \
    -p 3307:3306 \
    -d mysql:8
```
### 3. Configure Spring Boot to Connect to MySQL
Ensure your application.properties (or application.yml) has:
```
spring.datasource.url=jdbc:mysql://host.docker.internal:3307/golf_db
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
### 4. Run the API Container
Once MySQL is running, start your API container and link it to the MySQL container:
```
docker run --name golf_api \
    --link golf_db:mysql \
    -p 8080:8080 \
    golf_tournament_system
```
### 5. Test in Postman
- Create Member: POST http://localhost:8080/member

- Get Members: GET http://localhost:8080/members

- Create Tournament: POST http://localhost:8080/tournament

- Add Member to Tournament: POST http://localhost:8080/tournament/{tournamentId}/addMember/{memberId}





