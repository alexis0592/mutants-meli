FROM openjdk:11-jre-slim
EXPOSE 8080
COPY build/libs/*.jar mutants.jar
ENV JAVA_OPTS="-XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar mutants.jar"]