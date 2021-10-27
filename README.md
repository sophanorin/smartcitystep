## Description

This's smart city project in final course of Programming Using Java and Oracle DBMS by Ms. Mithona Kim at IT Academy Step Cambodia .

### `run in development` enviroment
```
java -Dspring.profiles.active=dev -Dserver.port=$PORT $JAVA_OPTS -jar /software/<your_jar_file>
```
Runs the app in the development mode.
Open http://localhost:2770 to view it in the browser.

### `run in production` enviroment
```
java -Dspring.profiles.active=prod -Dserver.port=$PORT $JAVA_OPTS -jar /software/<your_jar_file>
```
## Contianer deployment in heroku

1. docker buildx build --platform linux/amd64 -t <docker_image_name> .
2. docker tag <docker_image_name> registry.heroku.com/<appName>/web
3. docker push  registry.heroku.com/<appName>/web
4. heroku container:release web -a <appName>

## Postman
  
Get postman collection [here](./postman/SmartCity.postman_collection.json)
