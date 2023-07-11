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
  
### How to run the project ###
* Clone the project using this link:
  * git clone https://github.com/bhargav0605/IdentityReconciliation.git
* Change the directory in the "IdentityReconciliation" directory.
* In this directory check if "docker-compose" file is present or not.
* Now run "docker-compose up -d" command to run this project, it will pull all necessary images and run the migration script and wait till mysql container is ready to accept connection after that Spring application will be up and running.
* To check if containers are up and running run below command:
  * `docker ps`
  * 