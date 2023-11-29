# OPS-Challenge
OPS IP Range Challenge 

Version: 1.0

Language: Java

## Introduction

This application serves as a IP information service that exposes one endpoint for users 
to access all the IP ranges for selectable regions from AWS ip ranges. It leverages a 
separate IP range datasource to provide accurate information.

## Endpoints

The application automatically makes a swagger-ui page available at http://server:port/swagger-ui.html 
(ex. http://localhost:8083/swagger-ui.html) with more detailed information for the endpoint. 

For basic endpoint information read the following:

1. **GET /api/v1/ip-management/range** - Get IP Range
- Returns the IP ranges for a specific region or all and requires the following paramter:
  - **region**: The region by which the data will be filtered (ex. EU, US, CA)

- Request URL

  ex: http://localhost:8080/api/v1/ip-management/range?region=US

- **Response Text**

  `data:{"ip_prefix":"13.34.66.0/27","region":"us-east-1","service":"AMAZON","network_border_group":"us-east-1"}`

## Instalation ⚙️
1. Clone/Download this repo
   `git clone https://github.com/jaelgoco/ops-challenge.git`

2. Enter the root directory

   `cd ops-challenge`

3. Execute Gradle clean build using the gradle wrapper

   `./gradlew clean build`

## Requisitos Previos

Before you can use `Docker-compose` or simply run the application, please ensure you 
have the following prerequisites met.

1. **Docker** installed - [link](https://docs.docker.com/get-docker/)

## Quick Start

This section will explain how you can quickly set up this application without Docker

Although this app is relatively simple to set up, if you have Docker already installed, 
it is possible to follow the Docker Quick Start below. If you choose to set it up yourself, 
these are the steps:

1. Execute **./src/main/java/com/jason/opschallenge/OspChallengeApplication.java** using your IDE of choice

## Docker Quick Start 🐳

This section will explain how you can quickly use this image with `Docker-compose`.

### Using `docker-compose`

You can use the `docker-compose.yml` file this single command:

```bash
docker-compose up
```

This command will create the one container needed to run this app without any further installation.
1. **osp (localhost:8080)** - Container running the OPS Challenge app

**Important**: Make sure to stop any processes running on these two ports before running your docker 
compose

## Downloading Docker Image 🐳

This section will explain how you can run this application locally without download the full code
by using an image stored in a DockerHub Repository.

If you choose to set up your Docker container by downloading the image, these are the steps:

1. Execute the following command to download the latest image
```bash
docker pull jasoneliasgomez/challenge-repo:main
```

2. Execute the following command to run the image in your docker (replace CONTAINER_NAME with the name of your choosing)
```bash
docker run --name <CONTAINER_NAME> -p 8080:8080 -d jasoneliasgomez/challenge-repo:main
```








