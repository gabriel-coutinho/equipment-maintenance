## Equipment Maintenance System
This app is a Springboot backend of a equipment maintenance system. You can create a service order, list all or opens, find, delete and update by id, 

### To start:

run as Spring Boot App the class com.gabrielcoutinho.equipmentmaintenance.EquipmentMaintenanceApplication

```shell
$ Runing on http://localhost:8080
```

### Technologies:

```shell
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-parent</artifactId>
<version>2.4.2</version>
```
```shell
<java.version>11</java.version>
```
```shell
<groupId>org.hibernate</groupId>
<artifactId>hibernate-validator</artifactId>
<version>6.0.13.Final</version>
```
```shell
<groupId>com.h2database</groupId>
<artifactId>h2</artifactId>
<scope>runtime</scope>
```
```shell
<groupId>org.projectlombok</groupId>
<artifactId>lombok</artifactId>
<scope>provided</scope>
```
### To acess the database:

Only when app is running <br />
```shell
URL http://localhost:8080/h2-console
Fields on console: 
-Driver Class: org.h2.Driver 
-JDBC URL:	jdbc:h2:mem:equipmentmaintenance
-User Name: sa
-Password:
```
### Routes
```shell
GET - http://localhost:8080/orders
GET - http://localhost:8080/orders/opens
GET - http://localhost:8080/orders/{id}
POST - http://localhost:8080/orders
DELETE - http://localhost:8080/orders/{id}
PUT - http://localhost:8080/orders/{id}
POST - http://localhost:8080/orders/events/{id}
PUT - http://localhost:8080/orders/close/{id}
```

### Examples body:
Some examples JSON to facilitate test and execution <br />

```shell
POST - http://localhost:8080/orders
{
    "client": {
        "address": {
            "postalCode": "55555555",
            "streetName": "Nameless street",
            "number": "0",
            "complements": "House",
            "neighbourhood": "Gotham"
        },
        "email": "gabriel@test.com",
        "phone": "83988888888"
    },
    "equipment": {
        "model": "LG",
        "type": "TV"
    },
    "problem": "Screen problem"
}
```

```shell
PUT - http://localhost:8080/orders/1
{
    "client": {
        "id":"1",
        "address": {
            "id":"1",
            "postalCode": "50680340",
            "streetName": "Nameless street",
            "number": "0",
            "complements": "House",
            "neighbourhood": "Gotham"
        },
        "email": "gabriel_mudado@test.com",
        "phone": "83977777777"
    },
    "equipment": {
        "id":"1",
        "model": "Samsung",
        "type": "Phone"
    },
    "problem": "Changed problem"
}
```

```shell
POST - http://localhost:8080/orders/events/1
{
    "type": "PROBLEM",
    "message": "Missing screw"
}
```
