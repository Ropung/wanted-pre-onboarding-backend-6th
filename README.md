# wanted-pre-onboarding-backend

## 지원자의 성명
- 노기훈
<br/>

## docker-compose 실행 방법
Terminal<wanted-pre-onboarding-backend>: 
    `docker-compose up -d`

## 애플리케이션의 실행 방법 (엔드포인트 호출 방법 포함)
<br/>

### 1. docker 실행후 컨테이너 실행
![img.png](img.png)
<br/>

### 2. 개발환경 ?  VMoption 로컬로 실행 : 공백
![img_1.png](img_1.png)
`-Dspring.profiles.active=local`

### 3. 

### 데이터베이스 테이블 구조
```mermaid
erDiagram
    TB_MEMBER ||--o{ TB_BOARD: MEMBER-POST

    TB_MEMBER {
        member_idx BIGINT(10) PK "AUTO_INCREMENT"
        member_email VARCHAR(255) UK ""
        member_password VARCHAR(255) ""
        member_name VARCHAR(255) ""
        status VARCHAR(255) ""
        created_at datetime(6) "Not Null DEFAULT CURRENT_TIMESTAMP"
        updated_at datetime(6) "DEFAULT CURRENT_TIMESTAMP"
    }

    TB_BOARD {
        board_idx BIGINT(10) PK "AUTO_INCREMENT"
        member_idx BIGINT(10) PK "AUTO_INCREMENT"
        member_name VARCHAR(255) ""
        board_title VARCHAR(255) ""
        board_content VARCHAR(255) ""
        create_dt datetime(6) "DEFAULT CURRENT_TIMESTAMP"
        update_dt datetime(6) "DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP"
    }
```

### 구현한 API의 동작을 촬영한 데모 영상 링크

### 구현 방법 및 이유에 대한 간략한 설명

### API 명세(request/response 포함)


https://github.com/lordmyshepherd-edu/wanted-pre-onboardung-backend-selection-assignment