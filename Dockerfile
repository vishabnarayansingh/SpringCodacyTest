FROM openjdk:8-jdk-alpine as artifact
WORKDIR /artifact
COPY target/springcodacytest-*.jar /artifact/
RUN jar -xf *.jar

COPY --from=artifact /artifact/BOOT-INF/lib /app/lib
COPY --from=artifact /artifact/META-INF /app/META-INF
COPY --from=artifact /artifact/BOOT-INF/classes /app

ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -cp app:app/lib/* com..SpringCodacyCheck" ]

