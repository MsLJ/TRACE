# TRACE
멤버/뉴스/1대1문의/admin/채팅
과정을 네이버 블로그에 정리

<a href="https://blog.naver.com/llife_/223166439338">프로젝트 기간 매일 쓴 개발일지

<a href="https://blog.naver.com/llife_/223209123943"> 팀 프로젝트 실제 환경 + 페이지 스크린샷</a>

#주요 업무 정리

#멤버

회원가입이메일인증(ajax+Spring boot email)&닉네임 중복확인(ajax)

비밀번호는 Hash값로그인&로그아웃,비밀번호 찾기(ajax+Spring boot email),비밀번호 찾기,멤버 세션, 회원 탈퇴수정

#고려한점

비밀번호를 Hash값으로 저장함으로써 보안강화,그래서 비밀번호 찾기시 기존 비밀번호를 찾는게 아닌 업데이트 해주는

​

#글작성자

Bean지정,멤버 업데이트시 자동업데이트

#고려한점

이 테이블을 따로 만든이유는 Member라는 테이블을 자주부르는데 기존 Member 테이블같은경우에는 칼럼수가 너무많기에 그게 추후에 많아지면 계속해서 그 테이블을 불러왔을때 일어나는 성능저하가 있을것이라고 생각이 들었고 글 작성때 필요한 칼럼만 따로 정리해두면 그 칼럼 2~3개만 불러서 글이 작성이 될테니 속도+성능적으로 좋아질꺼라고 생각해서 테이블을 나눴습니다.

​

#Exception처리&에러처리

에러가나면 에러창을 띄우지않고 index로 보낸다.

#고려한점

원래 기존 Exception처리 같은경우에는 각 에러코드별 나누는게 맞지만 시간상 그냥 index로 보내는 처리를 했습니다.

​

#멤버 로그

멤버 테이블설게,로그인시 로그기록남게

#고려한점

멤버 로그에 어떠한점을 추가적으로 넣을지 고려해봤는데 게시판 글작성시에도 로그를 남겨서 그 회원을 눌렀을때 그 회원의 활동 로그가 나오도록 쓰고싶었지만 시간이 생각보다 지체돼서 추가적으로 칼럼을 못넣었습니다.


#admin

관리자 세션 구축 ,관리자 홈페이지구축,관리자 기능 구현(글 작성자+회원탈퇴)

#고려한점

admin이라는 테이블을 따로 만들지 아니면 칼럼에 추가할지 고민을했는데 그냥 @가 들어갔는지 안들어갔는지 여부에 따라 Java 내에서 admin session생성 지정해주는

​
#NEWS 

SPA (Single Page Application) 방식 구현

매시간 News데이터 수집 Python+Naver News API+MongoDB 

Linux서버에 crontab활용해서 매 00시에 자동으로 Python파일 실행

node.js+express활용 데이터를 JSON형태 페이징

Spring Boot 측 ajax활용 HTML출력,jQeury를 활용한 이벤트,

Hadoop 활용 트렌드 핫 키워드추출

#고려한점

그리고 추후에 데이터가 많아졌을때 생길 웹 사이트 과부화 + 서버 과부화 를 대비한 JSON 페이징 데이터를 한번에 불러오는게 아닌 10개씩 불러오게,jQeury를 활용한 이벤트,트렌드 핫 키워드(Hadoop활용 뉴스 데이터 핫키워드 추출)

#CHAT

실시간 소켓통신 서버 구축 Linux ubuntu+node.js io.socket 활용

기존 뉴스 app.js파일 통합 실시간 소켓통신 포트번호: 1234,뉴스 포트번호:3000

​#고려한점

xss라는 공격을 할수있다는것을 추후에 알고 node.js의 라이브러리 xss라이브러리 + dom객체를 만들수 없게 따로 node.js내에서 임의로 코드를 작성해서 태그들도 처리했습니다.

#1:1문의

1대1문의 가능한 게시판(수정x삭제o)

#고려한점

1대1문의의 답변은 사용자에게만 보여야하고 그리고 그 모든 1대1문의는 admin에게만 보여야한다. 

​

