# microservicedemo
# please run command mvn spring-boot:run to run every service in this folder
# the order should be eureka, zuul, auth, product, store
# store is a service to demo how to connect 2 service in system thougth eureka
# for testing, please call api localhost:8762/auth with body {username:admin, password:12345} for logging with admin role or {username:huyvu, password:12345} with user role
# method GET, POST, PUT, DELETE for testing please call api localhost:8762/product which is service connected with heroku postgres database(free online database)
# As only for demo we will not create order or user service to organize order or user, we combine api localhost:8762/product/order in product service to test how customer can 
# order in system.