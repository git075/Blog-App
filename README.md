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
spring.datasource.username=usernam
spring.datasource.password=pass
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

API Endpoints
1. User Registration & Login
POST /api/auth/register - Register a new user.
POST /api/auth/login - Log in with credentials to receive a JWT token.
2. Blog Post Management
GET /api/posts - Get all blog posts.
GET /api/posts/{id} - Get a specific blog post by ID.
POST /api/posts - Create a new blog post.
PUT /api/posts/{id} - Update an existing blog post by ID.
DELETE /api/posts/{id} - Delete a blog post by ID.

Environment Variables
SPRING_DATASOURCE_URL: MySQL database URL.
SPRING_DATASOURCE_USERNAME: MySQL username.
SPRING_DATASOURCE_PASSWORD: MySQL password.
