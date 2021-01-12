# Conference Demo Application

This application is a playground for learning more about the major Java frameworks and tools.

## Concepts Covered

- Spring Boot
- Spring MVC
- Lombok Annotations
- Spring Data (Postgres)
- Docker Compose

## Required Tools/Downloads

- [Java 11](https://jdk.java.net/archive/)
- [Docker](https://www.docker.com/get-started)

## Optional Tools/Downloads

- [IntelliJ IDE](https://www.jetbrains.com/idea/download/)
- [Visual Studio Code](https://code.visualstudio.com/)

## Environment Variables

The `env/development.env` file contains all the environment variables that will need to be set before running the application. For any "production like" environments, use the hosting provider's environment variable "service" to set the values. **Production environment variables should never be checked into the repository.**

For any new terminal window you open, you'll need to run the following command to set the environment variables.

```shell
source ./env/development.env
```

## Build

### Clean and Build

```shell
./mvnw clean compile
```

### Build Without Cleaning

```shell
./mvnw compile
```

## Run

### Start Database and Control Panel

```shell
docker-compose up -d
```

To start up the database and control panel with a `.env` file, run the following command.

```shell
docker-compose --env-file ./.env up -d
```

### Start API

```shell
./mvnw spring-boot:run
```

To start the API in production mode, run the following command.

```shell
./mvnw spring-boot:run -Dspring.profiles.active=prod
```

## Cleanup

### Stop and Remove Database and Control Panel

```shell
docker-compose down -v
```
