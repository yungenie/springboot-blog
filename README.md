# springboot-blog
- `개인 프로젝트 ing~`
- 나만의 블로그(메모장) 컨셉

## 개요
- Spring Boot 3.x 학습을 위해 개발환경을 구축하고 블로그, 회원가입, 로그인 등 기능을 구현하여 AWS 배포 및 CI/CD까지 전반적인 프로젝트 동작방식을 익히기 위한 저장소 입니다.
- 추후 아이디어 및 기능을 보태서 애플리케이션으로 업그레이드 할 예정 입니다.

## 기술스택
- Java : 17
- Spring-Boot : 3.0.2
- Gradle : 7.6.2
- DB : H2 인메모리, AWS RDS (MySQL)
- dependencies
  - spring-boot-starter-web
  - spring-boot-starter-data-jpa
  - spring-boot-starter-thymeleaf
  - spring-boot-starter-security
  - thymeleaf-extras-springsecurity6
  - com.h2database:h2
  - mysql:mysql-connector-java
  - org.projectlombok:lombok
  - spring-boot-starter-test
  - spring-security-test
  - ... 추가 예정


## 기능(예정)
 - [X] JPA/Hibernate + H2 사용한 블로그 기능 구현
 - [X] spring security를 사용한 로그인/로그아웃, 회원가입 구현
 - [ ] JWT Refresh Token 사용한 로그인/로그아웃 구현
 - [ ] OAuth2 + JWT를 활용한 소셜 로그인(Google) 구현
 - [X] AWS Elastic Beanstalk에 EC2, RDS 인스턴스 생성 및 배포하기
 - [ ] 깃허브 액션 CI/CD 구축하기

