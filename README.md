# 인프런 워밍업 클럽 - 미니 프로젝트
> 인프런 워밍업 클럽 0기 (BE)에서 진행하는 미니 프로젝트입니다.

<br>

## 요구사항
### 📌 기술 스택
- **JDK 17**
- **Spring Boot 3.x.x**
- **JPA**
- **MySQL**

<br>

### 📌 기능 구현
- **팀 등록**: 회사 팀을 등록할 수 있어야 한다. 필수 정보는 다음과 같다.
  - `팀 이름`
- **직원 등록**: 직원을 등록할 수 있어야 한다. 필수 정보는 다음과 같다.
  - `직원 이름`
  - `매니저 여부`
  - `입사 일자`
  - `생일`
- **팀 조회**: 모든 팀의 정보를 한 번에 조회할 수 있어야 한다.
  - `name`: 팀 이름
  - `manager`: 팀 매니저 (없으면 null)
  - `memberCount`: 팀 인원 수
    ```json
    [
      {
        "name": "경영지원",
        "manager": "민병관",
        "memberCount": 2
      }, ...
    ]
    ```
- **직원 조회**: 모든 직원의 정보를 한 눈에 조회할 수 있어야 한다.
  - `name`: 직원 이름
  - `teamName`: 소속 팀 이름
  - `role`: 매니저인지 직원인지
  - `birthday`: 생일
  - `workStartDate`: 입사일
    ```json
    [
      {
        "name": "강호식",
        "teamName": "경영지원",
        "role": "MEMBER"
        "birthday": "2000-01-01",
        "workStartDate": "2024-01-01"
      }, ...
    ]
    ```

<br>

## 풀이
### ⚙ 프로젝트 생성
[스프링 이니셜라이저](https://start.spring.io/)에서 스프링 프로젝트를 생성한다.

- **Project**: Gradle - Groovy
- **Language**: Java
- **Spring Boot**: 3.1.9
- **Project Metadata**
  - **Group**: club.warmingup
  - **Artifact**: emp-app
  - **Name**: emp-app
  - **Description**: practice
  - **Package-name**: club.warmingup.emp-app
- **Packaging**: Jar
- **Java**: 17
- **Dependencies**: Spring Web / Lombok / Spring Data JPA / MySQL Driver

<br>

### ⚙ DB 설계
```sql
create table team (
    team_id bigint auto_increment,
    name varchar(20) not null unique,
    primary key (team_id)
);
```

```sql
create table employee (
    emp_id bigint auto_increment,
    name varchar(50) not null,
    team_id bigint not null,
    role varchar(50) not null check (role in ('MANAGER', 'MEMBER')),
    work_start_date datetime not null default (current_time),
    birthday datetime not null default '2000-01-01',
    primary key (emp_id),
    FOREIGN KEY (team_id) REFERENCES team (team_id)
);
```

<p align="center">
	<img width="100%" src="https://github.com/areyouhun/emp-app/assets/97642395/8f3dbbaf-0b45-46ab-b0b1-12c91d199fe9">
</p>

<br>

### ⚙ 도메인 개발
#### \[팀 엔티티\]
https://github.com/areyouhun/emp-app/blob/d3eee45b71aaf88b5332863e6013f481c64b75e2/src/main/java/club/warmingup/empapp/domain/Team.java#L18-L43

<br>

#### \[직원 엔티티\]
https://github.com/areyouhun/emp-app/blob/d3eee45b71aaf88b5332863e6013f481c64b75e2/src/main/java/club/warmingup/empapp/domain/Employee.java#L17-L49
팀과 직원은 DB에서 1:N의 연관관계를 맺고 있기에, JPA 기술을 이용해 반영했다.

<br>

#### \[ENUM - 직책\]
https://github.com/areyouhun/emp-app/blob/d3eee45b71aaf88b5332863e6013f481c64b75e2/src/main/java/club/warmingup/empapp/domain/Role.java#L3-L6

https://github.com/areyouhun/emp-app/blob/d3eee45b71aaf88b5332863e6013f481c64b75e2/src/main/resources/application.yml#L16-L18
요청 데이터를 ENUM 데이터로 바인딩하는 과정에서 대소문자를 구분하지 않도록 `application.yml` 파일에 관련 옵션을 설정한다.

<br>

### 🚀 기능 | 팀 등록 & 조회
#### \[팀 컨트롤러\]
https://github.com/areyouhun/emp-app/blob/d3eee45b71aaf88b5332863e6013f481c64b75e2/src/main/java/club/warmingup/empapp/controller/team/TeamController.java#L12-L26
입력값 유효성 검사는 제외했다.

<br>

#### \[팀 서비스\]
https://github.com/areyouhun/emp-app/blob/d3eee45b71aaf88b5332863e6013f481c64b75e2/src/main/java/club/warmingup/empapp/service/team/TeamService.java#L12-L32
- **팀 등록**: 팀 이름의 중복 여부 검사
- **팀 조회**: 엔티티 안에 담긴 데이터를 응답용 DTO로 전달

<br>

#### \[팀 레포지토리\]
https://github.com/areyouhun/emp-app/blob/d3eee45b71aaf88b5332863e6013f481c64b75e2/src/main/java/club/warmingup/empapp/repository/team/TeamRepository.java#L7-L12

<br>

#### \[팀 응답용 DTO\]
https://github.com/areyouhun/emp-app/blob/d3eee45b71aaf88b5332863e6013f481c64b75e2/src/main/java/club/warmingup/empapp/dto/team/TeamResponse.java#L8-L28

<br>

<table>
  <tr>
    <td width="50%">
      <p align="center">
        <img width="100%" src="https://github.com/areyouhun/emp-app/assets/97642395/82dc1deb-4de3-4c6a-8705-39c2c7219469" />
      </p>
        <h4 align="center">팀 등록 결과 (성공)</h4>
    </td>
    <td width="50%">
      <p align="center">
        <img width="100%" src="https://github.com/areyouhun/emp-app/assets/97642395/395e9f9d-55ef-4329-936d-e2c3d6afe28c" />
      </p>
        <h4 align="center">팀 등록 결과 (실패)</h4>
    </td>
  </tr>
  <tr>
    <td colspan="2">
      <p align="center">
        <img width="60%" src="https://github.com/areyouhun/emp-app/assets/97642395/aeeaf315-6e92-4224-9289-be0b402b80dd" />
      </p>
        <h4 align="center">팀 조회 결과</h4>
    </td>
  </tr>
</table>

<br>

### 🚀 기능 | 직원 등록 & 조회
#### \[직원 컨트롤러\]
https://github.com/areyouhun/emp-app/blob/d3eee45b71aaf88b5332863e6013f481c64b75e2/src/main/java/club/warmingup/empapp/controller/employee/EmployeeController.java#L13-L27
입력값 유효성 검사는 제외했다.

<br>

#### \[직원 서비스\]
https://github.com/areyouhun/emp-app/blob/d3eee45b71aaf88b5332863e6013f481c64b75e2/src/main/java/club/warmingup/empapp/service/employee/EmployeeService.java#L15-L40
- **직원 등록**: 존재하는 팀인지 검사한다. 검사를 통과하면 요청용 DTO 안에 담긴 데이터를 갖고 직원 엔티티를 생성해 직원 레포지토리에 추가한다.
- **직원 조회**: 엔티티 안에 담긴 데이터를 응답용 DTO로 전달

<br>

#### \[직원 레포지토리\]
https://github.com/areyouhun/emp-app/blob/d3eee45b71aaf88b5332863e6013f481c64b75e2/src/main/java/club/warmingup/empapp/repository/employee/EmployeeRepository.java#L6-L7

<br>

#### \[직원 요청용 DTO\]
https://github.com/areyouhun/emp-app/blob/d3eee45b71aaf88b5332863e6013f481c64b75e2/src/main/java/club/warmingup/empapp/dto/employee/EmployeeCreateRequest.java#L9-L18

<br>

<table>
  <tr>
    <td width="50%">
      <p align="center">
        <img width="100%" src="https://github.com/areyouhun/emp-app/assets/97642395/898c6552-26c9-43d6-b16f-95688ceb1eed" />
      </p>
        <h4 align="center">직원 등록 결과 (실패)</h4>
    </td>
    <td width="50%">
      <p align="center">
        <img width="100%" src="https://github.com/areyouhun/emp-app/assets/97642395/66268e5b-0c88-4f87-9d43-a9cb6d2072a3" />
      </p>
        <h4 align="center">직원 등록 결과 (성공)</h4>
    </td>
  </tr>
  <tr>
    <td colspan="2">
      <p align="center">
        <img width="60%" src="https://github.com/areyouhun/emp-app/assets/97642395/f838248d-c158-4546-a161-18f9f6ce0da9" />
      </p>
        <h4 align="center">직원 조회 결과</h4>
    </td>
  </tr>
</table>

<br>

#### \[직원 응답용 DTO\]
https://github.com/areyouhun/emp-app/blob/d3eee45b71aaf88b5332863e6013f481c64b75e2/src/main/java/club/warmingup/empapp/service/employee/EmployeeResponse.java#L8-L23