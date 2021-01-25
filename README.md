## Equipment Maintenance System
This app is a Springboot backend of a equipment maintenance system. You can create a service order, list all or opens, find, delete and update by id, 

### To start:

run as Spring Boot App the class com.gabrielcoutinho.equipmentmaintenance.EquipmentMaintenanceApplication

```shell
$ Runing on http://localhost:8080
```

## Technologies

```shell
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-parent</artifactId>
<version>2.4.0-SNAPSHOT</version>
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

### To acess the database:

Only when app is running <br />
```shell
URL http://localhost:8080/h2-console <br /> <br />
Fields on console: <br />
-Driver Class: org.h2.Driver <br />
-JDBC URL:	jdbc:h2:file:~/test <br />
-User Name: sa <br />
-Password:
```
