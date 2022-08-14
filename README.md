# OCPP Server

## About this application
The OCPP server application is an simplified counterpart to the H2B backend infrastructure and acts as centralized
server among all clients in the network. Via the OCPP client library, the server can establish a communication to all
clients in the network (a guide how to install the OCPP client libary can be found at https://github.com/NLCProject/OcppClient).

The server provides the entire server functionality from the installed library and can be controlled via the provided
frontend. Additionally, the frontend provides all data from the OCPP protocol of all connected clients (e.g. connection
status, connector information, health status, current activities, ...).

## Docker
Dockerfiles can be found for backend in directory <b>docker-backend</b>, for frontend at
<b>src\main\kotlin\org\ocpp\server\frontend</b>.

Docker compose file can be found in directory <b>docker-backend</b>.

### Run in Docker
In order to run the database, backend and frontend via the compose file run the following commands
<ol>
    <li>Go to folder <b>docker-backend</b></li>
    <li>Run the file <b>publish.bat</b> in the terminal. This builds the required jar file for docker</li>
    <li>Run command <b>docker build -t org.ocpp.server:1.0.0 .</b></li>
    <li>Run command <b>docker-compose up</b></li>
    <li>Wait until all services has been loaded. Frontend service takes usually the longest (1 or 2 minutes).</li>
    <li>Open the browser and access the application via <b>http://localhost:4200 </b></li>
</ol>

<b>Warning</b>: The database image exposes its port on 3306. Make sure you don't run any other database instance on your machine
or just change the port in the compose file (take care that the app uses then the different port too!).

## Installation
<b>Warning</b>: An active internet connection is required for the installation.

### Database
<ol>
    <li>Install a SQL database (MySQL recommended)</li>
    <li>Add a database user with username <b>user</b> and password <b>password</b></li>
    <li>Start the database on your local machine on port 3306 (default port)</li>
    <li>Create the database <b>ocppServer</b> manually. Tables will be created automatically by the application.</li>
    <li>If you use MySQL, search and open the <b>MySQL Command Line Client</b>. Login with password <b>root</b> and
    enter <b>create database ocppServer</b>. Click <b>Enter</b>.</li>
</ol>

### Backend Pre-Requisites
<ol>
    <li>Install Java 8 or higher</li>
    <li>Open the project in an IDE (Intellij IDEA recommended). It is recommended to check it out via git.</li>
    <li>Build the Gradle project</li>
    <li>Make sure all dependencies has been loaded via Gradle</li>
</ol>

### Frontend Pre-Requisites
<ol>
    <li>Install NodeJs and NPM via <b>https://nodejs.org/en/download/</b></li>
    <li>Install Angular CLI. Run the command <b>npm install -g @angular/cli</b> in your console</li>
    <li>Go to <b>src\main\kotlin\org\ocpp\server\frontend</b></li>
    <li>Open the terminal</li>
    <li>Run command <b>npm i --save</b> to load all dependencies</li>
</ol>

## Run Backend Application
Open <b>Application.kt</b> file and run it either in run or debug mode. Make sure the database is running and the database
has been created. The tables will be created by the Spring Application at startup.

## Run Frontend Application
<ol>
    <li>Go to <b>src\main\kotlin\org\ocpp\server\frontend</b></li>
    <li>Open the terminal</li>
    <li>Run command <b>ng serve</b></li>
</ol>

## Usage
When backend and frontend is running, open <b>http://localhost:4200 </b> in your browser

## Tests
Tests can be found in the test package. The ISC library framework generates all tests for the
repository, model (DTO) and entity services.

## Credits
<ul>
    <li>Markus Graf</li>
    <li>Alex Gill</li>
    <li>Ivan Krylov</li>
</ul>

## License
The ISC jar library which is used in this project was written by Markus Graf.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
documentation files (the “Software”), to deal in the Software without restriction, including without limitation the
rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## Literature
<ul>
    <li>https://github.com/NLCProject/OcppClient</li>
    <li>https://github.com/NLCProject/BatteryController</li>
    <li>https://github.com/ChargeTimeEU/Java-OCA-OCPP/wiki</li>
    <li>https://github.com/ChargeTimeEU/Java-OCA-OCPP/wiki/Getting-started</li>
    <li>https://github.com/ChargeTimeEU/Java-OCA-OCPP/wiki/Setting-up-v1.6-OCPP-J-server</li>
    <li>https://github.com/ChargeTimeEU/Java-OCA-OCPP/wiki/Setting-up-v1.6-OCPP-J-client</li>
    <li>https://github.com/ChargeTimeEU/Java-OCA-OCPP/tree/master/ocpp-v1_6/src/main/java/eu/chargetime/ocpp</li>
</ul>
