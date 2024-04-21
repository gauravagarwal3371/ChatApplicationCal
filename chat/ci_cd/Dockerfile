FROM debian:10 AS build

RUN apt-get update && apt-get -y install maven

WORKDIR /opt/chat

COPY pom.xml /opt/chat/
RUN mvn dependency:resolve
COPY src /opt/chat/src/
RUN mvn package

#
# Run
#
FROM openjdk:17-jdk

EXPOSE 8080

WORKDIR /opt/chat

COPY --from=build /opt/chat/target/chat-0.0.1-SNAPSHOT.jar chat.jar

CMD [ "java", "-Xmn256m", "-Xmx768m", "-jar", "chat.jar" ]