
[![Pull Requests][pr-shield]][pr-url] [![issues][issue-shield]][issue-url]
# 🔥코딩 테스트 스터디🔥
- 죽을때까지 진행되는 **코딩 테스트 대비 문제 풀이** 스터디입니다.
- 매 주 **✔금요일 23:59** 까지 [5문제](https://github.com/TEAM-GDG/Algorithm/issues)를 풀고, **✔금요일 23:59** 까지 [코드 리뷰](https://github.com/TEAM-GDG/Algorithm/pulls)를 진행합니다.
  - 문제는 각자 원하는 문제 1개 [tony9402 오늘의 문제](https://github.com/tony9402/baekjoon/blob/main/picked.md)에서 보충합니다.
- 매 주 **✔토요일 10:00**에 **⭐모의 코딩 테스트**를 진행합니다.
  - **오프라인**으로 각자 문제를 풀이합니다. (단, 부득이한 이유로 오프라인에 미참여시 온라인으로 진행)
  - **3\~5시간 동안 2\~3문제**를 풀이하며, 주로 [Solved.ac](https://solved.ac/problems/level)기준 실버에서 골드난이도의  **코딩테스트 문제**나 기업**코딩테스트 문제**를 풀이합니다.
    - 문제는 미리 풀어보는 것을 방지하기 위해, 당일에 공개합니다.
  - 문제 풀이 이후 간단히 풀이와 느낌을 공유합니다.


<br>
<br>

## 🔸 참여 방법

### 🔹 다음 주에 풀고 싶은 문제 신청 방법 [(✈ 문제 등록하기)](https://github.com/TEAM-GDG/Algorithm/issues/new?assignees=&labels=%EA%B5%AC%ED%98%84&projects=&template=add-a-problem.md&title=%5B%ED%94%8C%EB%9E%AB%ED%8F%BC%5D%5B%23%EB%AC%B8%EC%A0%9C%EB%B2%88%ED%98%B8%5D+%EB%AC%B8%EC%A0%9C_%EC%9D%B4%EB%A6%84%2F%EB%82%9C%EC%9D%B4%EB%8F%84)
- Issues에 풀고 싶은 문제를 추가해 주세요.

<br>

### 🔹 소스 코드 업로드 및 리뷰 요청 방법
1. `fe/dev 또는 be/dev`에서 새 branch를 생성한다.
2. 브랜치명은 `[본인 이름]`/`[이슈 번호]`로 생성한다.
2. 본인이 해결한 문제의 소스 코드를 **본인의 branch**에 push한다.
3. `[본인 이름]`/`[이슈 번호]` 브랜치에서 -> **fe/dev 또는 be/dev**브랜치로 **Pull Request**를 통해 코드 리뷰를 요청한다.
4. 스터디원 모두에게 리뷰를 받은 후, Approve가 완료되면 Merge을 수정한다.

<br>

### 🔹 Code Review 규칙
- 자유롭게 의견을 제시한다.
  - 잘했다고 생각하는 부분 칭찬하기
    - 피드백 할 게 없으면 칭찬해 주세요👍
  - 다른 풀이 방법 공유하기
    - 코드 전체를 공유하는 것이 아닌, 키워드나 간단한 소개만 해 주세요.
  - 개선이 필요한 부분 설명하기
    - 단, 개선이 필요한 이유를 충분히 설명해 주세요.
    - 정답 코드를 알려주기 보다는, 스스로 고민하고 개선 방법을 선택할 수 있게 해 주세요.
    - 리뷰 과정이 숙제 검사가 아닌, 학습 과정으로 느낄 수 있게 해 주세요.
  - 궁굼한 부분 물어보기
- 오픈 커뮤니케이션 지향
  > ex) ~ 하는 게 어떨까요? / ~ 부분은 ~ 문제가 있는 것 같은데 괜찮을까요?
- 코드 작성자에게 피드백하는 것이 아닌, 코드 자체를 피드백한다는 생각으로 리뷰한다.

#### 📚 References
- [SW 개발을 위한 코드리뷰(우아한 테크 세미나)](https://www.youtube.com/watch?v=ssDMIcPBqUE&ab_channel=%EC%9A%B0%EC%95%84%ED%95%9CTech)
- [효과적인 코드리뷰를 위한 리뷰어의 자세(카카오 테크)](https://tech.kakao.com/2022/03/17/2022-newkrew-onboarding-codereview/)

<br>

### 🔹 Pull Request 규칙
- PR 템플릿에 맞게 작성한다.
  - 문제를 푸는 과정에서 본인이 생각한 내용을 작성한다.
  - 코드 설명을 작성한다. (단, 주석에 작성한 경우 생략한다.)
  - 특히 리뷰를 받고 싶은 부분을 작성한다.
    - Markdown Codeblock을 이용하여 코드 일부를 발췌해서 작성한다.
    - 특히 리뷰를 받고 싶은 부분은, 리뷰어의 시간을 아낄 수 있게 본인 코드를 충분히 설명해 주세요.
- Reviewer는 본인을 제외한 소속인명을 추가한다.
- Label은 `Review Request`로 추가한다.
- 모든 스터디원에게 리뷰를 받은 후, 코드 수정이 완료되었으면 Label을 `Merge Request`로 변경한다.
- dev branch에 병합되면, 병합된 branch는 삭제시킨다.

<br>

### 🔹 Commit Message 컨벤션
```
type : subject

body
```
#### ✔ Type
- **Add**: 소스 코드 파일(cpp) 추가
- **Refactor**: 소스 코드 수정
- **Style**: 소스 코드 형식(format) 수정, 변수 네이밍 수정, 주석 추가/삭제 등
  - (코드 동작에 영향이 없는 수정)
- **Chore**: 그 외 기타 작업

#### ✔ Subject
- 50자 이하의 간단한 제목을 사용한다.
  > ex) Add: #문제번호  <br>
  > ex) Refactor: 완전 탐색 -> 이분 탐색 <br>
  > ex) Style: 함수명 변경

#### ✔ Body(optional)
- 부연 설명을 작성한다.(주석으로 작성시 생략)
  > ex) input으로 주어진 배열의 원소들이 오름차순으로 정렬되어 있다는 특징을 이용하여, 탐색 알고리즘을 이분 탐색으로 수정하였습니다. <br>
  > 따라서 시간 복잡도가 O(n²) -> O(nlogn) 으로 최적화 되었습니다.
- 한 줄에 72자를 넘기지 않는다.

<br>

### 🔹 Branch Naming 컨벤션
- `본인_이름(영어_이니셜⭕, 한글❌)`/`이슈_번호(문제_번호❌)`
  > ex) rohgibong/1 <br>
  - branch 이름에 한글이 들어가면 문제가 생겨서 반드시 ⭐본인 이름을 영어 이니셜⭐로 branch를 생성해 주세요!
  - 문제 번호가 아닌, ⭐이슈 번호⭐로 branch를 생성해 주세요!
- 각 **문제마다 branch를 새롭게 생성**해서, 소스 코드를 push 후 리뷰 요청하는 방식

<br>

### 🔹 File Naming 컨벤션
- `문제번호.java`
  > ex) 문제번호_문제이름.java

<br>

### 🔹 Directory 구조
```
└── 📂알고리즘_주제
       ├── 📂 Bronze
       │      ├── 💾문제번호_문제이름.java
       │      ├── 💾...
       │      └── 💾문제번호_문제이름.java
       ├── 📂...
       └── 📂Gold
└── 📂알고리즘_주제
       ├── 📂Bronze
       │      ├── 💾문제번호_문제이름.java
       │      ├── 💾...
       │      └── 💾문제번호_문제이름.java
       ├── 📂...
       └── 📂Gold
```

<br>
<br>

## 🔸 참여자
|  이름   |[용준](https://github.com/55yong)|[가영](https://github.com/kaouo)|[상희](https://github.com/tkdgml822)|[도훈](https://github.com/dohun1109)|[희수](https://github.com/kingxeesu)|[기봉](https://github.com/rohgibong) |
|:-:|:-:|:-:|:-:|:-:|:-:|:-:|
|  프로필  |![용준](https://avatars.githubusercontent.com/u/132319467?v=4)|![가영](https://avatars.githubusercontent.com/u/144293040?v=4)|![상희](https://avatars.githubusercontent.com/u/77792853?v=4)|![도훈](https://avatars.githubusercontent.com/u/108252423?v=4)|![희수](https://avatars.githubusercontent.com/u/112453560?v=4)|![기봉](https://avatars.githubusercontent.com/u/119557561?v=4)|
| 기술 스택 | <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/react/react-original-wordmark.svg" alt="react" width="40" height="40"/> | <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/react/react-original-wordmark.svg" alt="react" width="40" height="40"/> | <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> | <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> | <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> | <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> |
|  분류   |프론트엔드|프론트엔드|백엔드|백엔드|백엔드|백엔드 |
|  이름   |[승헌](https://github.com/12seungheon)|[정원](https://github.com/Seo-garden)|[현동](https://github.com/lhdmir)| | | |
| 프로필   |![승헌](https://avatars.githubusercontent.com/u/164005659?v=4)|![정원](https://avatars.githubusercontent.com/u/125368624?v=4)|![현동](https://avatars.githubusercontent.com/u/42959665?v=4)| | | |
|  분류   |프론트엔드|프론트엔드|백엔드||| |


[pr-shield]: https://img.shields.io/github/issues-pr/TEAM-GDG/Algorithm?style=for-the-badge
[pr-url]: https://github.com/TEAM-GDG/Algorithm/pulls

[issue-shield]: https://img.shields.io/github/issues/TEAM-GDG/Algorithm?style=for-the-badge
[issue-url]: https://github.com/TEAM-GDG/Algorithm/issues


## 주차별 알고리즘 주제
- 1주차 : 구현 (재귀함수) + 시간 복잡도 + 빅오표기법 + 공간복잡도
- 2주차 : DP
- 3주차 : 누적합 + ( 순열 + 조합)
- 4주차 : 트리순회, 후위순회, 전위순회, 중위순회 + 트리 + 이진트리 + 이진탐색트리
- 5주차 : 완전탐색, 백트래킹, DFS, BFS
- 방학 주
- 6주차 : 라인스위핑
- 7주차 : 비트마스킹
- 8주차 : 그리디 + 브루트포스
- 9주차 : 투포인터
- 10주차 :이분탐색과 LIS
- 11주차 : 펜윅트리 최단거리

## 주차별 정리

### [1주차 - 구현]() : 9월 22일
- `풀어올 문제`
  - [백준 ](https://www.acmicpc.net/problem/1475)
  - [백준 ](https://www.acmicpc.net/problem/1748)
  - [백준 ](https://www.acmicpc.net/problem/12871)
  - [백준 ](https://www.acmicpc.net/problem/20006)
  - [백준 ](https://www.acmicpc.net/problem/3107)
- `스터디 당일 푼 문제(9월 28일 공개)`
  - [백준 ]()
  - [백준 ]()
  - [백준 ]()


