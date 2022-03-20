# The Demo of implement cache in Spring Boot by Redis

## Overview

This project demo redis cache how to work in Spring Boot.

## Run Applications

### 

1. Startup the Database, Redis

```shell
docker-compose up -d ./docker/docker-compose.yml
```

2. Start main program

## How to test

- Send one product:

```shell
curl --location --request POST 'http://localhost:8080/products' --header 'Content-Type: application/json' --data-raw '{
  "number": 100,
  "name": "product1"
}'
```

- Search by id:

```shell
curl --location --request GET 'http://localhost:8080/products/1'
```

- See data at redis.
- Or just run test at [RedisCacheServiceTest](src/test/java/org/vipcube/spring/redis/RedisCacheServiceTest.java), it's will test cache.
