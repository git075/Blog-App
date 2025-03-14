# Blog App

## Overview

This is a **Blog Application** built using **Spring Boot** for the backend and **MySQL** as the database. The application provides functionality for users to create, update, delete, and read blog posts.
## Technologies Used

- **Spring Boot** (Backend)
- **MySQL** (Database)
- **Docker** (Containerization)
- **AWS EC2** (Deployment)
- **Maven** (Build Tool)

## Prerequisites

- Java 11 or above
- Docker
- MySQL
- AWS EC2 Account

## Setup

### 1. Clone the Repository

git clone https://github.com/git075/blog-app.git
cd blog-app

2. Configure MySQL Database

Create a MySQL database named blogdb:
CREATE DATABASE blogdb;
Update the application.properties file with your MySQL credentials:

spring.application.name=BlogApp
spring.datasource.url=jdbc:mysql://localhost:3306/blogdb
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
server.port=8080
huggingface.api.url=${HuggingFaceApiUrl}
huggingface.api.key=${HuggingFaceApiKey}
openai.api.key=${OpenApiKey}

3. Run the Application
Build the application:
mvn clean install
Run the application:
mvn spring-boot:run
Your application will be running at http://localhost:8080.

Dockerization

Build the Docker image:
docker build -t blog-app .
Run the Docker container:
docker run -d -p 8080:8080 --name blog-container blog-app
Verify the container is running:
docker ps
Deployment on AWS EC2
1. Connect to AWS EC2 Instance
SSH into your EC2 instance:
ssh -i /path/to/your/key.pem ec2-user@your-ec2-public-ip
2. Install Docker on EC2
sudo yum update -y
sudo amazon-linux-extras install docker
sudo service docker start
sudo usermod -a -G docker ec2-user
exit
3. Build and Run the Application on EC2
Copy the Docker image to your EC2 instance or build it there.
Run the container:
docker run -d -p 8080:8080 --name blog-container blog-app
The application will now be accessible at http://your-ec2-public-ip:8080.

## API Endpoints

### 1. Blog Post Management

- **POST** `/api/blogs`  
  Create a new blog post. The request body should contain the blog's title and content. Upon successful creation, the blog post will be returned with a `201 Created` status.

- **GET** `/api/blogs`  
  Retrieve a list of all blog posts with pagination support. You can provide the `page` and `size` query parameters to control the number of results per page and which page of results to return.

- **GET** `/api/blogs/{id}`  
  Retrieve a specific blog post by its ID. If the blog post with the provided ID exists, it will be returned with a `200 OK` status. If not, a `204 No Content` status will be returned.

- **PUT** `/api/blogs/{id}`  
  Update an existing blog post by its ID. The request body should contain the updated title and content of the blog. If the blog post is successfully updated, the updated blog post is returned with a `200 OK` status.

- **DELETE** `/api/blogs/{id}`  
  Delete a blog post by its ID. A `204 No Content` status is returned upon successful deletion.

### 2. Blog Post Summary Generation

- **GET** `/api/blogs/{id}/summary`  
  Generate a summary of the blog post with the specified ID. The blog content will be processed by an AI service to generate a summary. If the blog content is empty or invalid, a `400 Bad Request` status will be returned. If there is an issue with the AI service, an error message with a `500 Internal Server Error` or a `429 Too Many Requests` status may be returned.


Environment Variables
SPRING_DATASOURCE_URL: MySQL database URL.
SPRING_DATASOURCE_USERNAME: MySQL username.
SPRING_DATASOURCE_PASSWORD: MySQL password.
