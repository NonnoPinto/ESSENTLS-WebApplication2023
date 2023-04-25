# ESN Padova Web Application

This repository contains the source code of the essentls project, due to implement a better user and event
management for ESN Padova.

*Web Applications* is a course of the

* [Master Degree in Computer Engineering](https://lauree.dei.unipd.it/lauree-magistrali/computer-engineering/)
* [Master Degree in ICT for Internet and Multimedia](https://lauree.dei.unipd.it/lauree-magistrali/ict-for-internet-multimedia-mime/)
* [Master Degree in Cybersecurity](https://cybersecurity.math.unipd.it/)

of the  [Department of Information Engineering](https://www.dei.unipd.it/en/), [University of Padua](https://www.unipd.it/en/), Italy. *Web Applications* is part of the teaching activities of the [Intelligent Interactive Information Access (IIIA) Hub](http://iiia.dei.unipd.it/).

Description on the project: this project is intended to be a Web App that will be used by [ESN Padova](https://padova.esn.it/).
It would help them to streamline and facilitate tasks of registering members and maintaining a membership list different every year (currently made by volunteers).  
Overall goal is to facilitate the process and build a stronger database.

The project is developed in PostgreSQL, Java, HTML, JavaScript and CSS.

Developers of the project are:

Borsato Alessandro 2089108  
Campagnol Andrea 2091178  
Cardillo Vittorio 2091429  
Lenartavicius Vaidas 2092135  
Maglie Mattia 2095330  
Marcato Francesco 2082155  
Pallante Laura 2092566  
Talukder Md Imran Faruck 2041440  
Villani Matteo 2090299  
Zago Giovanni 2087645


# Getting Started

Below a list of steps to follow in order to run the ESN Padova Web Application on your local machine.

## Prerequisites:

On your machine you must have installed:

* Apache Tomcat
* Maven
* PostgreSQL/pgAdmin 4

## Configuring the Database

1. Clone the repo

    ```sh
    git clone https://your_username@bitbucket.org/upd-dei-stud-prj/essentls.git
    ```

2. Open pgAdmin 4 and create a new database named: `essentls`

   &nbsp;

   <img src="readme_assets/createDB.png" alt="createDB" style="display: block; margin-left: auto; margin-right: auto; width: 50%;">

   &nbsp;

   <img src="readme_assets/nameDB.png" alt="createDB" style="display: block; margin-left: auto; margin-right: auto; width: 50%;">

   &nbsp;

3. Restore the database from the `ESSENTLSDB.sql` file. You can find this file inside the cloned repo following this path: `essentls/proj/src/main/db/ESSENTLSDB.sql`

   &nbsp;

   <img src="readme_assets/restoreDB.png" alt="createDB" style="display: block; margin-left: auto; margin-right: auto; width: 50%;">

   &nbsp;

   <img src="readme_assets/fileDB.png" alt="createDB" style="display: block; margin-left: auto; margin-right: auto; width: 50%;">

   &nbsp;

4. Create a new user in pgAdmin 4 called `essentls` with password `essentls`. (See the [alternative](#markdown-header-alternative) if you don't want to create a new user)

   &nbsp;

   <img src="readme_assets/createUserDB.png" alt="createDB" style="display: block; margin-left: auto; margin-right: auto; width: 50%;">

   &nbsp;

   <img src="readme_assets/nameUserDB.png" alt="createDB" style="display: block; margin-left: auto; margin-right: auto; width: 50%;">

   &nbsp;

   <img src="readme_assets/passwordUserDB.png" alt="createDB" style="display: block; margin-left: auto; margin-right: auto; width: 50%;">

   &nbsp;

   <img src="readme_assets/privilegesUserDB.png" alt="createDB" style="display: block; margin-left: auto; margin-right: auto; width: 50%;">

   &nbsp;

5. Set the ownership of the `essentls` database to `essentls` user.

   &nbsp;

   <img src="readme_assets/proprietiesDB.png" alt="createDB" style="display: block; margin-left: auto; margin-right: auto; width: 50%;">

   &nbsp;

   <img src="readme_assets/ownerDB.png" alt="createDB" style="display: block; margin-left: auto; margin-right: auto; width: 50%;">

   &nbsp;

### Alternative:

4. Follow the previous steps (from 1 to 3)

5. Modify the `context.xml` file inside `essentls/proj/src/main/webapp/META-INF/` by putting your postgres `username`, `password` and `port` in the corresponding field.

   &nbsp;

   <img src="readme_assets/changeContextDB.png" alt="createDB" style="display: block; margin-left: auto; margin-right: auto; width: 50%;">


## Build the application

1. Open terminal and change directory to `essentls/proj`

2. Compile with `Maven`

    ```sh
    mvn clean package
    ```
You can find the compiled `.war` file in `essentls/proj/target`

## Running the application

1. Startup your local version of Apache Tomcat

2. Open Tomcat manager at: `http://localhost:8080/manager/html`. (change the port number to match your configuration)

3. Deploy the `.war` file in `essentls/proj/target` generated in the building phase

4. Start the app by clicking on the link


