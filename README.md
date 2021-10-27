## Description

This's backend smart city project in final course of Programming Using Java and Oracle DBMS by Ms. Mithona Kim at IT Academy Step Cambodia .
### `Key Features`
The key features of this Smart City web-based software are:
- Using this project, the details of city can be accessed from anywhere at any time.
- The implementation of this city project promotes tourism and business effectively.
- Hotels can be searched more easily from anywhere.With the help of this online software, students can look for the academic institutes located in the city.

### `run in development` enviroment
```
java -Dspring.profiles.active=dev -jar /software/<your_jar_file>
```
Runs the app in the development mode.
Open http://localhost:2770 to view it in the browser.

### `run in production` enviroment
```
java -Dspring.profiles.active=prod -Dserver.port=$PORT $JAVA_OPTS -jar /software/<your_jar_file>
```
## Contianer deployment in heroku

1. docker buildx build --platform linux/amd64 -t docker_image_name .
2. docker tag docker_image_name registry.heroku.com/appName/web
3. docker push registry.heroku.com/appName/>/web
4. heroku container:release web -a appName

## Postman
  
Get postman collection [here](./postman/SmartCity.postman_collection.json)
