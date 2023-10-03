## Travel TodoList ✈️

---

### 🌎 프로젝트 환경
```
spring: spring Boot v3.1.4
java: jdk 17
build-tool: maven
sql-mapper: JPA
```

### ⚙️ MariaDB 로컬 셋팅
docker-compose
```
version: '3.3'
services:
  db:
    image: mariadb:10
    container_name: mariadb
    restart: always
    platform: linux/amd64
    environment:
      MARIADB_DATABASE: 'db'
      MARIADB_USER: 'test'
      MARIADB_PASSWORD: 'travel'
      MARIADB_ROOT_PASSWORD: 'travel'
    ports:
      - '3306:3306'
    expose:
      - '3306'
    volumes:
      - my-db:/Users/jeong/docker-repository
# Names our volume
volumes:
  my-db:
```

```
# 도커 접속 
docker exec -it mariadb mariadb -u root -p

# database 생성
create database travel;

# 접근 권한 주기
grant all privileges on *.* to 'test'@localhost identified by 'travel';
```