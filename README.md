# wateralarm

## Running water alarm in PWS

1) Get the source 

```
git clone https://github.com/bertyno/wateralarm
```
2) Get into the project directory
```
cd wateralarm/wateralarm/
```
3) Create the war using package
```
mvn package
```
4) Push the application
```
cf push wateralarm -p target/wateralarm-0.0.1-SNAPSHOT.war
```
5) Bind an SQL and Rabbit service to the app
```
cf bind-service wateralarm clearDB

cf bind-service wateralarm myrabbi
```
6) Restage the app
```
cf restage wateralarm
```
7) Execute the sql schema in wateralarm/src/test/resources/testdb/schema-mysql.sql with your favorite mysql client

8) Visit the url http://wateralarm.cfapps.io/

9) Create a new user with a valid email

10)Login with the user you have just created

11) Go to the link "Edit User (to add plants)"

12) Create a plant with a name

13) Wait 5 minutes to get an email reminding you need to water your plant


