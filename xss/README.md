# XSS 공격

## 1. 시나리오

1. 해커가 XSS 공격을 위한 게시판에 악성 스크립트를 넣어서 게시글 작성
2. 로그인 사용자가 해당 게시글을 읽는다.
   1. 로그인 시 해당 작업은 쿠키 -> 세션 -> ? 로 이뤄지고 이에 대한 해결책을 알아보자
3. 해당 게시글에 숨어있던 악성 스크립트가 발동해서 cookie에 저장된 회원 정보를 탈취
4. 이를 이용해 회원 정보 노출등의 악질적인 행동 진행

## 2. 해결 방안
1. cookie를 사용했을 때 해결 방법
   1. 백엔드
      1. httpOnly true
      2. 템플릿엔진 사용
      3. 입력 데이터 검증, 필터링
   2. 프론트엔드
      1. 입력데이터 이스케이프
      2. CSP
2. session을 사용했을 때 해결 방법


