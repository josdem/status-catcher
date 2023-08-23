Status Catcher Service
----------------------------------------------

[![GitHub](https://github.com/josdem/status-catcher/actions/workflows/main.yml/badge.svg)](https://github.com/josdem/status-catcher/actions)
[![Quality Gate Status](https://sonar.josdem.io/api/project_badges/measure?project=com.josdem.catcher%3Astatus-catcher-service&metric=alert_status)](https://sonar.josdem.io/dashboard?id=com.josdem.catcher%3Astatus-catcher-service)

Is an API that keeps application status using [Spring Webflux Framework](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html) and [Junit5](https://junit.org/junit5/)
for testing.

#### To run tests

```bash
gradle test
```

#### To run the project

```bash
gradle bootRun
```

#### To enable interceptor filter
```bash
gradle bootRun -Dinterceptor.enabled=true
```

#### To run tests with Jacoco and Sonarqube

```bash
gradle jacocoTestReport sonar test
```

#### Swagger

http://localhost:8080/swagger-ui.html

#### Read this as reference

- https://josdem.io/techtalk/spring/spring_webflux_basics/
- https://josdem.io/techtalk/spring/spring_webflux_server/
- https://josdem.io/techtalk/java/junit5/