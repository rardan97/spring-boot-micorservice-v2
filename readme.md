# Microservices Architecture: (Spring Boot + PostgreSQL + API Gateway + Eureka Server + JWT Authenticaton + Docker + Swagger)

This project implements a microservices architecture using:

- Spring Boot (Web & WebFlux)
- Spring Security
- PostgreSQL
- API Gateway (Spring Cloud Gateway)
- Eureka Server (Service Registry & Discovery)
- Docker


## Architecture Diagram
--



## Services Overview
- api-gateway – Routes requests to the appropriate service
- eureka-server – Service registry (for registration and lookup of services)
- auth-service - Service for Authentication (Register, login, refresh token, Logout)
- user-service – Manages user data
- department-service – Manages department data
- address-service – Manages address data


## Dependencies

### Api-Gateway        
- `spring-cloud-starter-gateway-server-webflux`  
- `spring-boot-starter-webflux`  
- `spring-cloud-starter-netflix-eureka-client`  
- `springdoc-openapi-starter-webflux-ui`  
- `spring-cloud-starter-loadbalancer`
- `spring-boot-starter-security`
- `jjwt-api`
- `jjwt-impl`
- `jjwt-jackson`

### Eureka Server
- `spring-boot-starter-web`  
- `spring-cloud-starter-netflix-eureka-server`  
- `spring-boot-starter-actuator`

### Service-User
- `spring-boot-starter-web`  
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-security`
- `spring-cloud-starter-netflix-eureka-client`
- `springdoc-openapi-starter-webmvc-ui`
- `postgresql`  
- `lombok`  
- `jjwt-api`
- `jjwt-impl`
- `jjwt-jackson`


### Service-User
- `spring-boot-starter-web`  
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-webflux`  
- `spring-cloud-starter-netflix-eureka-client`
- `spring-cloud-starter-loadbalancer`
- `springdoc-openapi-starter-webmvc-ui`
- `postgresql`  
- `lombok`  

### Service-Task
- `spring-boot-starter-web`  
- `spring-boot-starter-data-jpa`  
- `spring-cloud-starter-netflix-eureka-client`
- `springdoc-openapi-starter-webmvc-ui`
- `postgresql`  
- `lombok` 

## Database
Each service has its own separate PostgreSQL database:
- authdb
- userdb
- taskdb


## Configuration


### api-gateway (application.yml)
```yaml
server:
  port: 8080

spring:
  application:
    name: api-gateway

  cloud:
    gateway:
      server:
        webflux:
          routes:
            - id: auth-service
              uri: lb://auth-service
              predicates:
                - Path=/api/auth/**
              filters:
                - RewritePath=/api/auth/(?<segment>.*), /api/auth/${segment}
            - id: user-service
              uri: lb://user-service
              predicates:
                - Path=/api/user/**
              filters:
                - RewritePath=/api/user/(?<segment>.*), /api/user/${segment}
            - id: task-service
              uri: lb://task-service
              predicates:
                - Path=/api/task/**
              filters:
                - RewritePath=/api/task/(?<segment>.*), /api/task/${segment}

springdoc:
  swagger-ui:
    urls:
      - url: /api/auth/v3/api-docs
        name: Auth Service
      - url: /api/user/v3/api-docs
        name: User Service
      - url: /api/task/v3/api-docs
        name: Task Service

logging:
  level:
    org.springframework.cloud.gateway: DEBUG

eureka:
  client:
    service-url:
      defaultZone: ${EUREKA_CLIENT_SERVICEURL_DEFAULTZONE:http://eurekaserver:8761/eureka/}
    register-with-eureka: true
    fetch-registry: true
```

### eureka-server (application.yml)

```yaml
server:
  port: 8761

spring:
  application:
    name: eureka-server

management:
  endpoints:
    web:
      exposure:
        include: health, info
  endpoint:
    health:
      show-details: always

logging:
  level:
    com.netflix.discovery: DEBUG

eureka:
  client:
    register-with-eureka: false
    fetch-registry: true

```

### auth-service (application.yml)

```yaml
server:
  port: 8081

spring:
  application:
    name: auth-service
  datasource:
    url: ${SPRING_DATASOURCE_URL}
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect

blackcode:
  app:
    jwtSecret: ${blackcode.app.jwtSecret}
    jwtExpirationMs: ${blackcode.app.jwtExpirationMs}
    jwtRefreshExpirationMs: ${blackcode.app.jwtRefreshExpirationMs}


springdoc:
  api-docs:
    path: /api/auth/v3/api-docs
  swagger-ui:
    path: /swagger-ui.html
    enabled: true

eureka:
  client:
    service-url:
      defaultZone: ${EUREKA_CLIENT_SERVICEURL_DEFAULTZONE:http://eurekaserver:8761/eureka/}
    register-with-eureka: true
    fetch-registry: true
```


### user-service (application.yml)

```yaml
server:
  port: 8082

spring:
  application:
    name: user-service
  datasource:
    url: ${SPRING_DATASOURCE_URL}
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect

springdoc:
  api-docs:
    path: /api/user/v3/api-docs
  swagger-ui:
    path: /swagger-ui.html
    enabled: true

eureka:
  client:
    service-url:
      defaultZone: ${EUREKA_CLIENT_SERVICEURL_DEFAULTZONE:http://eurekaserver:8761/eureka/}
    register-with-eureka: true
    fetch-registry: true

```

### task-service (application.yml)
```yaml

```

## Docker & Deployment

This project is fully integrated with:

- Docker for containerization
- Automated health checks and restart policies
- To run all services together:


## Run Project
```
docker-compose up --build
```

## Stop Project
```
docker-compose down
```



## API Documentation (Swagger UI)

The API documentation using Swagger UI can be accessed at the following base URL:
```
http://localhost:8080/swagger-ui/index.html
```

## API Endpoints

All requests are sent through the API Gateway at:
```
http://localhost:8080.
```

