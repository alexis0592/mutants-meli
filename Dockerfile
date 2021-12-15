FROM openjdk:11-jre-slim
EXPOSE 8080
COPY build/libs/*.jar mutants.jar
ENV AWS_ACCESS_KEY_ID="AKIA4BLTCFZGMH6G5LGP"
ENV AWS_SECRET_ACCESS_KEY = "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"
ENV JAVA_OPTS="-XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar mutants.jar"]