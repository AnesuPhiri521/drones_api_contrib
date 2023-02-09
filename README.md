# drones_api
Assignment from Musala Soft

Task : Develop a service via REST API that allows clients to communicate with the drones

Implementation:
- I used springboot and H2 in memory database for all my REST API services
- I opened separate exeption classes for validation purposes e.g. Trying to load a drone whose battery percent is below 25
- I prepared separate packages for related code or classes, e.g. controller package fro application controller anotated classes, dto package 
  for Request model versus validation rules classes
- I used Autowiring feature of spring framework enables you to inject the object dependency implicitly
- I used the controller advice @RestControllerAdvice anotated class to give full reposnse to front end app especially when exception occur

Assumptions:
- I assumed that drone battrey percent must not exceed 100
- Another assumption was that medication name must be 6 to 50 characters
- I assumed that per each entity there must be no any field that must be blank or null in the database tables
- Medication name should be 6 to 10 characters on length
- Each time a drone goes to deliver its battery percent is reduced by 10 %
- Each time the drone return from delivery, its battery percent is reduced by 10% again
- The drone battery can be recharged via updating the drone details via REST api path
  http://localhost:8080/drones/update
- No next drone state is going to be initiated without the previous state completed first
- Once the drone start loading, the drone state will automatically changes to LOADING state
- Once the drone state changes to DELIVERED all the delivered loads will change their delivered state from false to true

Process Flow
1.  Save drones in system first http://localhost:8080/drones/save
    {
      "serialNumber" : "DR1002",
      "model" : "Lightweight",
      "weight" : 500,
      "battreyPercent" : 80,
      "state" : "IDLE"
    }
 2. 
    
