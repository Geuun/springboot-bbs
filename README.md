# Springboot-JPA
> SpringBoot 를 활용한 BackEnd REST API 구축 프로젝트

### [Swagger-Ui](http://geun.me/swagger-ui/#)

<details>
<summary>Board</summary>
<div>

### 게시판 기능

- UI
  - 새 글 작성
  - 글 목록 조회
  - 글 상세 페이지 조회 및 수정 및 삭제
  - 댓글 작성

- REST API
  - (GET) getArticle : 게시글 조회
  - (POST) addArticle : 게시글 등록

</div>
</details>

<details>
<summary>Korea Nationwide Hospital List</summary>
<div>

### 전국 병원 조회 페이지

- UI를 통해 아래의 기능 지원
  - 전국 병원 이름 및 도로명 주소 조회
  - 병원명 및 주소로 검색
- REST API 를 통해 조회 지원
  - (GET) getHospital : 병원 조회 

</div>
</details>

---

### `deploy.sh` 사용법

![스크린샷 2022-11-16 오전 11 00 02](https://user-images.githubusercontent.com/89567475/202065074-969a1f25-c0e8-4de9-8260-c1f81bbe35c9.png)

- `$ bash deploy.sh`
  - deploy 작업 자동화 쉘스크립트
  
- 실행 조건
  - 프로젝트 명 == 이미지 태그 == 컨테이너 이름
  - url, username, password => 환경변수
  - root 권한 or docker 권한
  - \<ProjectName> \<url> \<username> \<password> \<port:port> \<DemonOpt: Y/N>
  - 입력 예시
  - springboot localhost:8080 root password 8080:8080 Y

---

### Commit Convention

|    제목    |          설명          |
|:--------:|:--------------------:|
|   feat   |      새로운 기능 추가       |
|   fix    |        버그 수정         |
|   docs   |        문서 수정         |
|  style   |        코드 포맷팅        |
| refactor |       코드 리펙토링        |
|   test   |   테스트 및 테스트 코드 관련    |
|  chore   | 빌드 및  패키지 매니저  수정 관련 |

---