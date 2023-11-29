code->demo/src/main/java/com/example/demo
Technologies used:

-Spring Boot
-Postgres
-Spring Security for role based authorization("admin","user" roles)
-JPA for database interactions

How to Start:

1]The easy way to start the application is to run it in the docker container.
Run the following commands inside the "docker" folder I have added
You don't need to do anything.Just Build and start the container.The database is installed within the container.
Go inside the "docker" folder and open command prompt

i] docker build -t grocery .
ii] docker run -p 8082:8082 -p 5432:5434 grocery

Application will be started on 8082

2]Clone the project,do the DB conf in application.properties inside src/main/resources and then run as a spring boot Application


USERS

Created 3 in-memory users to save the time

i] username:admin , password:admin ,role="admin"
ii] username:user , password:user , role="user"
iii] username:user2 , password:user2 ,role="user"

Admin APIs

1]Get all grocery items
method=get
url="/api/admin/items"
e.g.http://localhost:8082/api/admin/items

2]delete grocery items
method=delete
url="/api/admin/items/{id}"
e.g.http://localhost:8082/api/admin/items/2

3]Add grocery items
method=POST
url="/api/admin/items"
e.g.http://localhost:8082/api/admin/items
request body: {
    "name":"Apple",
    "price":24,
    "quantityInStock":10
}

4]Update grocery items
method=PUT
url="/api/admin/items/{id}"
e.g.http://localhost:8082/api/admin/items/2

5]get order items for order id
method=PUT
url="/api/admin/order/{order-id}"
e.g.http://localhost:8082/api/admin/order/1

6]get orders for users
method=get
url="/api/admin/order/user/{username}"
e.g.http://localhost:8082/api/admin/order/user/user2

User APIs
1]get items 
method=get
url="/api/user/groceries"
e.g.http://localhost:8082/api/user/groceries

2]order multiple items with quantities 
method=POST
url="/api/user/order"
e.g.http://localhost:8082/api/user/order
http://localhost:8082/api/users/order?itemIds=1,2,3&quantities=2,2,3
