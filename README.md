<h2>Microserviced project cloning main BlaBlaCar logic</h2>
Encompasses m/s:
<ul>
<li>eureka-server</li>
<li>config-service</li>
<li>user-service</li>
<li>rating-service</li>
<li>notification-service</li>
<li>trip-service</li>
<li>booking-service</li>
<li>telegram-service</li>
</ul>

<h3>Stack:</h3>
<ul>
  <li>Spring Boot 3.5.0, Spring Cloud, Spring Security, Spring Stream, Spring Function, RESTTemplate, Docker container, Mapstruct for DTOs</li>
  <li>JUnit and Mockito for testing</li>
  <li>Jnanoid API for generating randomise IDs</li>
  <li>Dedicated config-server for microservices centralised config repository</li>
  <li>Netflix Eureka Discovery service for pinging services </li>
  <li>RabbitMQ for messaging
  <li>OpenFeign Api for HTTP req interconnect
  <li>Resilience4j (circuit-Breaker and Fallback logic)
  <li>Keycloak oauth2 server 22.0.5</li>
  - FeignClientInterceptor for sharing tokens between services
  <li>Trip-service utilises:</li>
  <ol type="1">
    <li>GraphHopper Api for finding optimal route between cities</li>
    <li>Haversine direct distance calculator berween geocoordinates</li>
    <li>MySQL DBase</li>
  </ol><br>
<li>Notification-service utilises:</li> 
  <ol type="1">
    <li>Twillio Api for sending SMS</li>
    <li>Gmail API for emails</li>
    <li>Telegram Bot Api service</li>
    <li>Mongo DB</li><br>
  </ol>
<li>User-service utilises:</li> 
  <ol type = "1">
    <li>Users division onto Drivers and Passengers</li>
    <li>PostGres DB</li><br>
  </ol>
<li>booking-service:</li> 
  <ol type="1">
    <li>saves information on trip booking by passengers</li>
    <li>MongoDB</li>
  </ol><br>
<li>rating-service:</li> 
    <ol type="1">
      <li>saves information on trip's drivers rating and review by passengers</li>
      <li>MySQL DBase</li>
  </ol>
</ul>

