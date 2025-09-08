FROM openjdk:21

COPY target/Oauth2AuthorizationServer-0.0.1-SNAPSHOT.jar docker_auth.jar

ENTRYPOINT ["java", "-jar", "/docker_auth.jar"]