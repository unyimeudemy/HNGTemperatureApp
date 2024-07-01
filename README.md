# TEMPERATURE_APP

The app is a simple Spring Boot application that takes in 
a user's name as a request parameter and returns a greeting that
tells the user their current location and the current temperature.
Example response:

```json
{
  "client_ip": "127.0.0.1", // The IP address of the requester
  "location": "New York" // The city of the requester
  "greeting": "Hello, Mark!, the temperature is 11 degrees Celcius in New York"
}
```
Location and temperature details are obtained from [WeatherApi](https://www.weatherapi.com/). The code is 
deployed on an AWS EC2 instance and can be accessed via the endpoint:
`http://ec2-3-80-223-233.compute-1.amazonaws.com:8080/api/hello?visitor_name="Mark"`