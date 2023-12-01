# 💻 코리(Co:RE) : 개발자를 위한 GPT 활용 도우미 `[개발중]`
> 2023 동양미래대학교 졸업작품
<br>

<img src="https://github.com/Team-Dasom/co-re-server/assets/84304802/e52d7cfe-05fa-4000-8028-10645bf95223" width="500" height="160"/>

## 서비스 설명
팀 **‘다솜’** 은 개발자 리서칭을 통해 초보 개발자들이 GPT의 도움을 받아 효율적인 개발을 하고싶어도, <br>
무엇을 어떻게 물어봐야하는 지에 대해 어려워한다는 점을 발견하였고, 이에 집중하여 <br>
GPT API를 활용하여 개발자들이 필요로 하는 기능을 간단한 입력만으로 손쉽게 제공하는 개발자를 위한 GPT 간편 활용 서비스 **‘코리’** 를 개발하게 되었습니다. <br>
<br>
코리는 `FineTuning`된 GPT모델을 활용하여 **‘코드 리팩토링’**, **‘변수명 추천’**, **‘주석 설명 추가’** 등 개발자들 위한 편의 서비스를 제공합니다.

<br>

## 👥 팀원 소개
<table>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/Largopie"><img src="https://avatars.githubusercontent.com/u/106071687?v=4" width="100px;" alt=""/><br /><sub><b>FE 팀원 : 송재석</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/hijiyun"><img src="https://avatars.githubusercontent.com/u/114905530?v=4" width="100px;" alt=""/><br /><sub><b>FE 팀원 : 박지윤</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/imzzok"><img src="https://avatars.githubusercontent.com/u/118805355?v=4" width="100px;" alt=""/><br /><sub><b>FE 팀원 : 임서정</b></sub></a><br /></td>
     <tr/>
      <td align="center"><a href=""><img src="https://avatars.githubusercontent.com/u/84304802?v=4" width="100px;" alt=""/><br /><sub><b>BE 팀원(팀장) : 최승준</b></sub></a><br /></td>
    </tr>
  </tbody>
</table>

<br>

---

## 🗺️ 아키텍쳐.

![백엔드](https://github.com/Team-Dasom/co-re-server/assets/84304802/e88b0e37-2c98-40a9-b23f-723c466272bb)


---

## 📋 API Docs.
http://core.pe.kr/swagger-ui/index.html

<br>

---

## 🛠️ 사용 기술.
### Server
- SpringBoot 3.0.6
- Java 17
- Nginx
- Spring Data JPA
- QueryDSL
- JWT
- Springdoc Swagger
- Bucket4j

### AI Model
- GPT 3.5 Turbo
  
### DB
- MySQL 8.0
- Redis

### Infra
- AWS EC2
- AWS ELB
- AWS RDS
- AWS ElastiCache
- AWS IAM
- AWS Route53

### Local
- Docker
- H2 DB
