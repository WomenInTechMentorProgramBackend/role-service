# README #

"Role service is a one of medical center microservices. It handles requests about user roles and permissions.

## Application Launch ##

### Running with Docker ###

#### Prerequisites ####
Before executing the commands, make sure you have Maven and Docker installed.

* To download the repository from github, you need to use the terminal command
`git clone git@github.com:WomenInTechMentorProgramBackend/role-service.git`
* Go to the project folder and open a terminal in it
* After that, you need to type the following commands in the terminal: 

    `mvn clean package -DskipTests`

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

### Running without Docker ###

#### Prerequisites ####
Before executing the commands, make sure you have the following:
- JDK installed locally.
- Maven installed locally.
- PostgreSQL installed locally.
- PgAdmin to create a database.

1. To download the repository from github, you need to use the terminal command
  `git clone git@github.com:WomenInTechMentorProgramBackend/role-service.git`
2. Go to the project folder and open a terminal in it
3. Create a PostgreSQL database named "roleservice_db" using PgAdmin.
4. After that, you need to type the following commands in the terminal:
  
    `mvn clean package`
  
    `mkdir target/dependency`
  
    `cd target/dependency; jar -xf ../*.jar`
  
    `docker-compose build`
  
    `docker-compose up`

Congratulations! Our microservice is running in a container

## Application API: ##