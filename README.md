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
- I assumed that per each 
