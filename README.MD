
[![Build Status](https://cloud.drone.io/api/badges/youssef-sherif/countrybook/status.svg)](https://cloud.drone.io/youssef-sherif/countrybook)

![Farmers Market Finder Demo](sample.gif)

## Countrybook

Countrybook is a free and open-source social media where you can follow countries and get all the posts related to the countries you are following.

## License

Countrybook is open-source software licensed under the GNU AGPLv3.
      
## Supported Features:

- Signup and login
- Following and unfollowing countries
- Creating new posts to countries you are following
- Comments and replies
- Favouriting and unfavouriting posts

## Technologies

Server:
- OpenJdk
- Spring Boot
- Mariadb
- JOOQ
- Flywaydb

Client:
- Create React App
- React
- Redux
- Webpack
- SCSS
- CSS Modules

Please update this list if necessary

## Installation

Server:
- Supports OpenJdk 9+ / Spring Boot 2+  / Mariadb 10.3+

- First create the database and create a new user and grant privileges
```
CREATE USER IF NOT EXISTS 'user'@'localhost' IDENTIFIED BY 'password';
CREATE DATABASE test;
GRANT ALL PRIVILEGES ON test.* TO 'user'@'localhost' ;
```
- After that you youd need to update application*.properties files which can be found in server/travelneer-spring-boot/src/main/resources  with the username, password and url

- Then build the project:
```
$ mvn clean install
```

- Flyway is a database migration tool that supports both raw SQL based migrations and Java based migrations
- SQL migrations can be found in server/travelneer-spring-boot/src/main/resources/db/migration/ while Java migrations can be         - found in server/travelneer-spring-boot/src/main/java/db/migration (this folder does not exist yet as I never needed to use Java          - based migrations until now).
- I have set up spring boot to automatically validate and run new migrations with each run using application*.properties files but you can still use this command to run migrations:
```
$ mvn flyway:migrate -Dflyway.url=... -Dflyway.user=... -Dflyway.password=...
```
- Alternatively you can create an external config file and use the following command:
```
$ mvn flyway:migrate -Dflyway.configFiles=path/to/myAlternativeConfig.conf
```

- JOOQ is a library that provides a DSL to construct queries from classes generated from a database schema
- Generated classes can be found in server/travelneer-spring-boot/src/generated/java/com/travelneer/jooq/
- You can use this command to generate JOOQ classes (see generate profile in server/travelneer-spring-boot/pom.xml for more details):
```
$ mvn install -Pgenerate
```
Note: You can use -DskipTests if you would like to skip tests


Client:
- Supports npm 6+ / nodejs 9+ / webpack 4+

- Install dependencies
```
$ npm install
```

- For development build
```
$ yarn start
```

- For production build
```
$ yarn build
$ serve -s build/
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## Acknowledgments

- This project would not be possible without all the opensource libraries used (see Technologies for more details)
