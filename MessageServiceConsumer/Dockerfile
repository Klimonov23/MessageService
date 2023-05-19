FROM maven AS build
# FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk
COPY --from=build /home/app/target/MessageServiceConsumer-0.0.1-SNAPSHOT.jar /usr/local/lib/MessageServiceConsumer.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","/usr/local/lib/MessageServiceConsumer.jar"]