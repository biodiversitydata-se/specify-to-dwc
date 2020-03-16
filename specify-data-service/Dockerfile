FROM openjdk:8-jdk-alpine

COPY target/specifyDataService-thorntail.jar /usr/src/myapp/
WORKDIR /usr/src/myapp


ENTRYPOINT [""]
EXPOSE 8080
CMD ["java", "-jar", "specifyDataService-thorntail.jar", "-Sinitdata"]
