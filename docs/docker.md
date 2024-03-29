# Docker Guide

This guide provides step-by-step instructions for building and managing Docker containers in the `backend` directory of BoardMaster. It is intended for developers who are not familiar with basic Docker commands and BoardMaster's environment.

## Prerequisites

Docker installed on your machine.
Basic understanding of Docker commands.
Ensure you are in the `backend` directory of the project.

## Building and Running Docker Containers
### Building the Docker Image
To build the Docker image:
```bash
gradlew bootBuildImage --imageName=cogito/boardmaster
```
This command builds a Docker image based on the specifications in your Dockerfile.

### Running the Docker Image
To run the Docker image:
```bash
docker run -p 8080:8080 -t cogito/boardmaster 
```
This command starts the containers defined in your docker-compose.yml.

### Running in Production
In a production environment, it's best to run the Docker container in detached mode:
```bash
docker run --detach -p 8080:8080 -t cogito/boardmaster 
```
This runs your container in the background. If one does not do this the process will be removed when the terminal is closed or after a certain amount of time of inactivity.


### Stopping the Docker Container
To stop and remove containers, networks, and images created by up:
```bash
docker kill {container_id}
```

## Managing Docker Containers
### Viewing Running Containers
To list all running Docker containers:
```bash
docker ps
```
This displays a list of all active containers along with their status.
