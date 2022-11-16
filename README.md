
### 게시판

### 전국 병원 조회 페이지

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