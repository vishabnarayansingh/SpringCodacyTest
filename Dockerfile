FROM openjdk:8-jre-alpine
VOLUME /tmp
COPY target/springcodacytest-*.jar app.jar
ENTRYPOINT ["sh","-c","java -Djava.security.efgd=file:/dev/./urandom -jar /app.jar"]