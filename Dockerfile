# start with base image containing java runtime
FROM openjdk:17 as build

#information of owner or maintainer of image
MAINTAINER siengbank

#Add the application jar to the container
COPY target/account-0.0.1-SNAPSHOT.jar account-0.0.1-SNAPSHOT.jar

#Execute application
ENTRYPOINT [ "java","-jar","/account-0.0.1-SNAPSHOT.jar" ]