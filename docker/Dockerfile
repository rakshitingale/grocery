# Use the official OpenJDK base image
FROM openjdk:17-slim

# Set the working directory
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY demo-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the ports for Spring Boot and PostgreSQL
EXPOSE 8080
EXPOSE 5432

# Install PostgreSQL server
RUN apt-get update && \
    apt-get install -y postgresql

# Set environment variables for PostgreSQL
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/testdb
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

CMD ["sh", "-c", "service postgresql start && su - postgres -c \"psql --command \\\"ALTER USER postgres WITH PASSWORD 'postgres';\\\" && createdb -O postgres testdb\" && java -jar app.jar"]
#i] docker build -t grocery .
#ii] docker run -p 8082:8082 -p 5432:5434 grocery
