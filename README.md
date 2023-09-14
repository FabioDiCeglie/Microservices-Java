# Microservices project 

This project is a Java microservice that integrates various technologies to demonstrate a robust and scalable microservices architecture. It leverages Sequelize as an ORM for relational databases, MongoDB as a NoSQL database, Kafka for event-driven communication, Grafana and Prometheus for monitoring, Eureka for service discovery, Zipkin for distributed tracing, and Resilience4j for fault tolerance. Additionally, Docker is used for containerization.


## Getting Started

To get started with this microservice, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/your-project.git
   ```

2. Install the required dependencies.

## Docker

You can containerize this microservice using Docker for easier deployment and scaling. Follow these steps:

1. Build a Docker image:

   ```bash
   docker build -t your-image-name .
   ```

2. Run a Docker container from the image:

   ```bash
   docker run -p 8080:8080 your-image-name
   ```

## Docker Compose

You can use Docker Compose to containerize this microservice along with its dependencies (e.g., databases, Kafka, Grafana, Prometheus, Zipkin). Follow these steps:

Ensure you have Docker Compose installed on your system.

Run the following command in the project directory:

   ```bash
    docker-compose up
   ```

This will start all the required containers and set up the entire environment.