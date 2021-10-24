FROM openjdk:11
RUN mkdir -p /software
ADD target/smart_city-0.0.1.jar /software/smart_city-0.0.1.jar
CMD java -Dspring.profiles.active=prod -Dserver.port=$PORT $JAVA_OPTS -jar /software/smart_city-0.0.1.jar
EXPOSE $PORT