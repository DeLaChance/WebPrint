### General ###
Webprint is a web application. It is a hobby project of mine. The idea is that you should be able to run the app with a single command on any Linux-environment. The app hosts a website which can be used to upload files to the backend. A printer connected to the device via USB consumes these uploaded files and prints these at a user's request.

### Technology stack
Backend:
- [Vertx](vertx.io)
- Maven

Frontend:
- [Angular](https://angular.io/)
- [Angular Material](https://material.angular.io/)

Provisioning:
- [Docker](https://www.docker.com/)

### Development ###

For the backend do the following:

    cd webprint/rx-webprint-server/
    mvn clean install # build
    mvn vertx:run # to run (one way)
    java -jar target/rx-webprint-server.jar # to run (alternatively)

Then go to: [localhost:8181/](http://localhost:8181).

For the frontend do the following:

    cd webprint-frontend
    npm install
    ng serve

And go to: [localhost:4200/](http://localhost:4200).
