# Bitespeed Backend Test: Identity Reconciliation #

**Backend Framework:** Java, Spring-boot
**Database:** MySQL

## Project Structure ##
* :open_file_folder: src
  * :open_file_folder: main (Contains all the code and applications.properties)
* :open_file_folder: target (Created after packaging.)
* :open_file_folder: secrets (Contains a mysql root password file.)
* :page_facing_up: schema.sql (Data migration script.)
* :whale: Dockerfile (Contains instruction to create an image.)
* :whale: Docker Compose (Docker compose file which contains instruction of MySQL container and Spring application.)
* :page_facing_up: README.md
* :page_facing_up: Resume
  
### How to run the project ###
* Clone the project using this link:
  * `git clone https://github.com/bhargav0605/IdentityReconciliation.git`
* Change the directory in the "IdentityReconciliation" directory.
* In this directory check if "docker-compose" file is present or not.
* Now run `docker-compose up -d` command to run this project, it will pull all necessary images and run the migration script and wait till mysql container is ready to accept connection after that Spring application will be up and running.
* To check if containers are up and running run below command:
  * `docker ps`
  * Both containers should be up and running.

### How to use the API ###
* **API:**
  * Use postman to access this api.
  * API is accessible at `/identify` at port 8080 of localhost, Please use below url.
    * `http://localhost:8080/identify` 
    * JSON Payload: 
      * `{ "email": "abc@mail.com", "phoneNumber": 123456 }`
    * `POST` API
    * Please add `Content-Type: application/json`
  * To use this as a curl command instead from postman, use below command.
    * curl --location 'http://localhost:8080/identify' \
        --header 'Content-Type: application/json' \
        --data-raw '{
            "email": "abc@mail.com",
            "phoneNumber": 123456
        }'
  * Change your payload according to the test.
  * To change the log level or enable the hibernate query you can make changes in `docker-compose` file which includes the environment variables, current log-level is `INFO`.

* **Database:**
  * Database port will be `4406` on your local machine.
  * To connect to database and check the data use below command:
    * `mysql -h localhost -P 4406 -u root -p`
    * `SHOW DATABASES;`
    * `USE identity_reconciliation;`
    * `SELECT * FROM contact;`