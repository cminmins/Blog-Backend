프로젝트 : 블로그 서비스
--------------
Blog Backend project with realworld.io

<br>

설명 : 
    
    정해진 API spec에 맞게 백엔드 서버 구현

기능 : 
<br>

    회원가입, 로그인(인증), 회원정보 수정 
    게시글 - 작성, 수정, 삭제
    댓글 - 작성 수정, 삭제
    팔로우, 언팔로우 
<br>


**기본구성 화면**

------------

![img_5.png](img_5.png)

<br>

![img_4.png](img_4.png)

<br>

![img_6.png](img_6.png)

<br>
<br>


**인증 관련 : JWT**

------------

Header에 Token을 포함하여 전달

![img.png](img.png)
<br>

Token 생성

![img_2.png](img_2.png)

Token 유효성 확인

![img_3.png](img_3.png)


<br>
<br>

**Request / Response**

---------

![img_7.png](img_7.png)

![img_13.png](img_13.png)



<br>

java/com/example/blog/api/article/ArticleApi.java

![img_11.png](img_11.png)

![img_9.png](img_9.png)


<br>

java/com/example/blog/service/requestDTO

![img_12.png](img_12.png)


java/com/example/blog/service/responseDTO

![img_10.png](img_10.png)

<br>




<br>
<br>
<br>

**Reference**

--------
https://github.com/gothinkster/realworld

https://realworld-docs.netlify.app/docs/specs/backend-specs/endpoints/#add-comments-to-an-article


