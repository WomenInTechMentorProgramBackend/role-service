# README #

"Role service is a one of medical center microservices. It handles requests about user roles and permissions.

### Application launch ###

* To download the repository from github, you need to use the terminal command
`git clone git@github.com:WomenInTechMentorProgramBackend/role-service.git`
* Go to the project folder and open a terminal in it
* Create the database using the following command:
  `createdb -U postgres -h localhost -p 5433 roleservice_db`
* After that, you need to type the following commands in the terminal: 
`mvn clean`
`mvn package`
`mkdir target/dependency`
`cd target/dependency; jar -xf ../*.jar`
`docker-compose build`
`docker-compose up`
* Congratulations! Our microservice is running in a container



### Application API: ###