### General ###
WebPrint is a website that can be used to print files. A user can drag and drop a file in the UI and let it be printed. It assumes that the printer and the device on which the code is running are physically connected (e.g. USB).

The project should be deployable on any device (or at the very least any Linux environment) by invoking a single script.

Technologies:
1. Vertx toolkit
2. Angular 4
3. Docker
4. CUPS
5. Sqlite
6. Liquibase

### How to run ###
`mvn clean install`

Run the DatabaseVerticle
`java -jar target/database-connector-NEXT-SNAPSHOT-fat.jar -cluster -cluster-host 127.0.0.`

Run the ServerVerticle
`java -jar target/http-server-NEXT-SNAPSHOT-fat.jar -cluster -cluster-host 127.0.0.1`

### Architecture ###
The front-end running in a browser can do the following:

- It can list the printing jobs that are enqueued.
- It can display the status of the job currently being printed, given such job exists.
- Add a job to the queue
- Clear the job list

In the backend there are a few distinguishable components:

- HTTP server
- A light-weight database
- Printing job dispatcher
- Printing status poller

The HTTP server listens to the backend calls and connects to the database. The printing status poller polls the 'physical printer' and emits events whenever the printer is ready or finished printing or when it breaks down. Upon an event that the printer is ready, the printing job dispatcher queries the database for the most recent job and tries to print it. If the printer cannot print the job, the printing job is discarded.

### BACKEND ###
The [Vertex](http://vertx.io/) toolkit is used for coding the backend.

Sqlite is used as a database and liquibase is used for provisioning this database.

### FRONTEND ###
Use Angular 4.

REST API:

GET: /api/job
url parameter: isActive (boolean)

GET: /api/job/{id}
url parameter: id (string)

DELETE: /api/job/{id}
url parameter: id (string)

POST: /api/job
file: file contents

Definition:
Job {
  id: string;
  name: string;
  created: Date;
  started: Date;
  completed: Date;
}
