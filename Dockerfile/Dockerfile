FROM adoptopenjdk/openjdk11
VOLUME /tmp
ADD lib/build/libs/*.jar app.jar
EXPOSE 8080
CMD["ls -la"]
ENTRYPOINT ["java","-jar","app.jar"]
