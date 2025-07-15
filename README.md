<h2>Microserviced project cloning main BlaBlaCar logic</h2>
Encompasses m/s at ports:
<ul>
<li>eureka-server: 8761</li>
<li>config-service: 9090</li>
<li>user-service: 9091</li>
<li>rating-service: 9092</li>
<li>notification-service: 9093</li>
<li>trip-service: 9094</li>
<li>booking-service: 9095</li>
<li>telegram-service: 9096</li>
</ul>

<h3>Stack:</h3>
<ul>
<li>Spring Boot 3.5.0, Cloud, Security, Function, REST</li>
<li>RabbitMQ for messaging
<li>OpenFeign Api for HTTP req interconnect
<li>Resilience4j (circuit-Breaker and Fallback logic)
<li>Keycloak Auth server 22.0.5</li>
<li>Trip-service utilises:</li>  
  <ol>
  <li>GraphHopper Api for finding optimal route between cities</li>
  <li>Haversine direct distance calculator berween geocoordinates</li>
  <li>MySQL DBase</li>
</ol>
</ul>

