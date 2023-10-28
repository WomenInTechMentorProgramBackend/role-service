# README #

"Role service is a one of medical center microservices. It handles requests about user roles and permissions.

## Application Launch ##

### Prerequisites ###
Before executing the commands, make sure you have Maven and Docker installed.

* Run Docker
* To download the repository from github, you need to use the terminal command
  `git clone git@github.com:WomenInTechMentorProgramBackend/role-service.git`
* Go to the project folder and open a terminal in it
* After that, you need to type the following commands in the terminal:

  `mvn clean package`

  `mkdir target/dependency`

  `cd target/dependency; jar -xf ../*.jar`

  `docker-compose build`

  `docker-compose up`
* Once the application is running, you can access PgAdmin to view the database. Open a web browser and go to http://localhost:5050/
* Enter the following login credentials in PgAdmin:
  Username: wit@wit.com

  Password: password
* On the main page of PgAdmin, click on the "Add New Server" icon.
* In the "General" tab, enter the following information:
  Name: medcenter
* In the "Connection" tab, fill in the following fields:

  Host name/address: db

  Port: 5432

  Maintenance database: roleservice_db

  Username: postgres

  Password: 123
* Click "Save".
* Now, you will be able to see the database.

Congratulations! Our microservice is running in a container


## Application API: ##

* role-service swagger:
  ```http://localhost:8090/swagger-ui/index.html```