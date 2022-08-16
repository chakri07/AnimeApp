# quarkus Project

backend for final project

//to use mongo:
docker run -ti --rm -p 27017:27017 mongo:latest

Endpoints to run the api

1. GET /quote - gives list of all the quotes
2. POST /quote - add a quote to the list
   valid JSON example: (should be in the request body)
   {
   "quote": "Rottweiler",
   "anime": "male",
   "character": "pixel",
3. "userEmail": "test@gmail.com"
   }
4. GET /quote/{email} - returns a List of quotes of the user
5. DELETE /quote/{id} - deletes the quote entry with the given ID
6. PATCH /quote/{id} - update anything in the quote entry
    1. valid JSON example: (a complete new quote JSON should be given to it)
       {
       "quote": "Rottweiler",
       "anime": "male",
       "character": "pixel",
    2. "userEmail": "test2@gmail.com"
       }
7. GET /quote/paged/{page number} - return data in a pagable manner according to the page number

Language: java
Developer: Chakradhar@uchicago.edu

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
