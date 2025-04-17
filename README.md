# 📎 감성을 담은 꽃 선물, 플로라이 </br>(AI 기반 개인 맞춤형 꽃 추천 서비스)
![image](https://github.com/user-attachments/assets/e74c33d4-bb97-480d-bf98-8b0538b9632a)

## 👀 서비스 소개
- **서비스명**: FlorAI  
- **서비스 설명**:  
  사용자 기념일, 감정 키워드, 선물 대상자 정보 등을 바탕으로  
  AI가 적절한 꽃을 추천하고, 기념일 정보 제공, 꽃말 도감까지 제공하는  
  **AI 기반 맞춤형 꽃 추천 서비스 플랫폼**입니다.  
  초보자도 쉽게 꽃을 고를 수 있도록 감성적인 UI/UX를 제공합니다.

---

## 📅 프로젝트 기간
2025.02.27 ~ 2025.04.15 (약 7주)

---

## ⭐ 주요 기능
- 사용자의 선물 대상자, 감정, 상황 정보를 기반으로 AI 꽃 추천
- 꽃말 의미에 기반한 감성적 큐레이션
- 모바일/웹 기반 직관적 UI/UX 설계

---
## 동작 구조
1. 프론트 → 백엔드(Spring)로 질문 키워드 전송 (JSON)
2. 백엔드 → Python FastAPI 추천 서버에 전달
3. Python 서버 → LangChain으로 문장 3개 확장(desc/emotion/meaning)
4. 각 문장을 text-embedding-3-small로 임베딩 → Qdrant에서 유사도 검색
5. Top 10 추출 → 유사 그룹화 + 랜덤 추출 → 3개 선택 → GPT로 추천 이유 생성
6. 추천 결과 (FLW_IDX, 이유) → 백엔드로 전송
7. 백엔드는 꽃 메타데이터와 매칭해서 프론트로 전달

--- 

## ⛏ 기술스택

| 구분 | 내용 |
|------|------|
| **사용언어** | ![](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=OpenJDK&logoColor=white) ![](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=white) ![](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3&logoColor=white) ![](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white) ![](https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=React&logoColor=black) |
| **라이브러리** | ![](https://img.shields.io/badge/Swiper-6332F6?style=for-the-badge&logo=Swiper&logoColor=white) ![](https://img.shields.io/badge/axios-5A29E4?style=for-the-badge&logo=axios&logoColor=white) ![](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
| **개발도구** | ![](https://img.shields.io/badge/VScode-007ACC?style=for-the-badge&logo=VisualStudioCode&logoColor=white) ![](https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=Figma&logoColor=white) ![](https://img.shields.io/badge/IntelliJ-0071A9?style=for-the-badge&logo=intellij-idea&logoColor=white)
| **서버환경** | ![](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white) |
| **데이터베이스** | ![](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white) |
| **AI 및 기타** | ![](https://img.shields.io/badge/OpenAI-412991?style=for-the-badge&logo=openai&logoColor=white) ![](https://img.shields.io/badge/LangChain-000000?style=for-the-badge) ![](https://img.shields.io/badge/Qdrant-1A1A1A?style=for-the-badge) ![](https://img.shields.io/badge/JPA-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
| **협업도구** | ![](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white) ![](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white) |

---

## ⚙ 시스템 아키텍처(구조)
![image](https://github.com/user-attachments/assets/43de95b0-198f-40ba-aff9-0973f5e87ee3)
---
## 📌 SW유스케이스

---
## 📌 서비스 흐름도
![image](https://github.com/user-attachments/assets/dbb404b6-08bd-4216-88ad-62cf8815747a)

---
## 📌 ER다이어그램
![image](https://github.com/user-attachments/assets/74cb9c17-1bd0-423f-a08f-d99c35e416cc)
---

## 🖥 화면 구성


## 👨‍👩‍👦‍👦 팀원 역할

| 이름 | 역할 | GitHub |
|------|------|--------|
| 전호원 | 팀장, 기획, Front-end, DB 설계 및 구축 | [GitHub](https://github.com/사용자ID) |
| 이석현 | 데이터 수집, 크롤링, AI 추천 모델링 | [GitHub](https://github.com/사용자ID) |
| 김성하 | Back-end 개발, DB 설계 및 구축 | [GitHub](https://github.com/julle0123/Florai) |

---

## 🤾‍♂️ 트러블슈팅

- **문제1: 추천 정확도 부족**  
  - 원인: 사용자의 입력값이 추상적일 경우, 의미 매칭이 부정확했음  
  - 해결: GPT 기반 LangChain으로 감성 키워드를 꽃말 벡터로 연결하는 매핑 로직 개선

- **문제2: 로그인 세션 유지 문제**  
  - 원인: React 세션 토큰이 새로고침 시 삭제됨  
  - 해결: `sessionStorage` 활용 및 Redux로 사용자 로그인 상태 전역 관리

- **문제3: Swiper.js 반응형 오류**  
  - 원인: 카테고리 배너에 `grab-cursor`가 작동하지 않음  
  - 해결: Swiper 옵션에서 `grabCursor: true` 설정 후 CSS 병행 수정

- **문제4: 벡터 DB 연동 실패**  
  - 원인: Qdrant API 접근 권한 설정 누락  
  - 해결: API 키 활성화 및 IP 화이트리스트 적용으로 정상 연동
 
- **문제5: Spring Boot, Render서버 간 통신 실패**
  - 원인: Spring Boot에서 Render로보낸 JSON 형식이 예상과 달라 AI 서버가 파싱 실패
  - 해결: Render 서버는 query 형식의 JSON을 받아야 했는데 해당 부분 코드 수정으로 정상 작동
