# user-location-api
This api returns people who are listed as either living in a city, 
or whose current coordinates are within 50 miles of the city.

### Technologies
    - Spring Rest APIs
    - Maven
    
### Build/Starting the Service
Specific environment running instructions:

    mvn clean
    mvn test
    mvn build
    mvn clean build #runs all the above
    
### Command line
Go to root directory of your project and run following command
`java -jar target\userLocationApi-0.0.1-SNAPSHOT.jar`

### Sample Request/Response
####Request
http://localhost:8082/city/london/users

####Response
```
[{
  "id": 998,
  "first_name": "Thelma",
  "last_name": "McSweeney",
  "email": "tmcsweeneyrp@deliciousdays.com",
  "ip_address": "214.81.12.163",
  "latitude": 14.6099284,
  "longitude": 121.0348405
}]
```