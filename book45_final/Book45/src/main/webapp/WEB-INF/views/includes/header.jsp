<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
<link rel="stylesheet" href="/resources/css/common.css">
<link rel="icon" href="/resources/img/book-solid.png">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
   <script>
      $("#logoutBtn").click(function() {
         alert("정상적으로 로그아웃되었습니다.");
      });
   </script>
</head>
<body>

<header>
      <div id="header">
         <div id="log">
            <ul>
               <c:choose>
					<c:when test="${empty member && empty kakaoMember}">
						<li><a href="/member/login">로그인</a></li>
						<li>&#124;</li>
						<li><a href="/member/terms">회원가입</a></li>
						<li>&#124;</li>
						<li><a id="loginBtn2" href="#">장바구니</a></li>
						<li>&#124;</li>
						<li><a id="loginBtn3" href="#">마이페이지</a></li>
					</c:when>
					<%-- <c:when test="${kakaoMember != null}">
						<li id="hello">안녕하세요, ${kakaoMember.name}님</li>
						<li>&#124;</li>
						<li><a href="/member/logout" id="logoutBtn">로그아웃</a></li>
						<li>&#124;</li>
						<li><a href="/cart/cartList/${kakaoMember.id}">장바구니</a></li>
						<li>&#124;</li>
						<c:if test="${kakaoMember.lev == 'A'}">
							<li><a href="/member/myPage/${kakaoMember.id}">마이페이지</a>
							</li>
							<li>&#124;</li>
							<li><a href="/admin/main" onclick="buttonClick()">관리자 페이지</a></li>
						</c:if>
						<c:if test="${kakaoMember.lev == 'B'}">
							<li><a href="/member/myPage/${kakaoMember.id}">마이페이지</a>
							</li>
						</c:if>
					</c:when> --%>
					<%-- <c:when test="${kakaoMember.id == member.id}">
						<li id="hello">안녕하세요, ${kakaoMember.nickname}님</li>'<li>&#124;</li>
						<li><a href="/member/logout" id="logoutBtn">로그아웃</a></li>
						<li>&#124;</li>
						<li><a href="/cart/cartList/${member.id}">장바구니</a></li>
						<li>&#124;</li>
						<c:if test="${member.lev == 'A'}">
							<li><a href="/member/myPage">마이페이지</a>
							</li>
							<li>&#124;</li>
							<li><a href="/admin/main" onclick="buttonClick()">관리자 페이지</a></li>
						</c:if>
						<c:if test="${member.lev == 'B'}">
							<li><a href="/member/myPage">마이페이지</a>
							</li>
						</c:if>
					</c:when> --%>
					<c:otherwise>
						<li id="hello">안녕하세요, ${member.name}님</li>
						<li>&#124;</li>
						<li><a href="/member/logout" id="logoutBtn">로그아웃</a></li>
						<li>&#124;</li>
						<li><a href="/cart/cartList/${member.id}">장바구니</a></li>
						<li>&#124;</li>
						<c:if test="${member.lev == 'A'}">
							<li><a href="/member/myPage/${member.id}">마이페이지</a>
							</li>
							<li>&#124;</li>
							<li><a href="/admin/memberList">관리자 페이지</a></li>
						</c:if>
						<c:if test="${member.lev == 'B'}">
							<li><a href="/member/myPage/${member.id}">마이페이지</a>
							</li>
						</c:if>
					</c:otherwise>
				</c:choose>
            </ul>
         </div>
         <div id="headnav">
            <h1 id="hlogo">
               <a href="/main"> <img
                  src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FCdrYl%2FbtrUTKBvWql%2FneUhG0VH9Jro8xRV8EGl61%2Fimg.png"
                  alt="LOGO" width="230px" height="70px">
               </a>
            </h1>
            <div class="search">
               <!-- <select name="" id="hoption">
                        <option value="제목검색">제목검색</option>
                        <option value="작가검색">작가검색</option>
                        <option value="카테고리검색">카테고리검색</option>
                    </select>
                    &#124;
                    <input type="text" id="hsearch" value="검색할 내용을 입력해주세요">
                    <i class="fa-solid fa-magnifying-glass" id="searchbu"
                    onclick="return searchCheck()"></i> -->

               <form method="get" id="search_form" name="frm2">
                  <!-- <input type="hidden" name="command" value="book_search"> -->
                  <select name="type" id="hoption">
                     <option value="title">제목</option>
                     <option value="author">작가</option>
                     <option value="pub">출판사</option>
                  </select> &#124; <input type="text" name="keyword" id="hsearch"
                     placeholder="검색할 내용을 입력해 주세요">
                  <button onclick="return searchCheck()">
                     <i class="fa-solid fa-magnifying-glass" id="searchbu"></i>
                  </button>
               </form>
            </div>
            <!-- <div class="mypage">
                    <a href="#">
                       <i class="fa-solid fa-cart-shopping"></i>
                    </a>
                    <a href="MemberServlet?command=member_mypage">
                       <i class="fa-solid fa-circle-user"></i>
                    </a>
                </div> -->
         </div>
         <nav>
            <ul class="hlist1">
				<li class="hlist1_1">
					<a href="/book/list">도서</a>
					<ul style="margin-left: -33px;">
						<li><a name="IT 모바일" value="IT 모바일" href="/book/categoryList/1">IT 모바일</a></li>
	                    <li><a name="가정 살림" value="가정 살림" href="/book/categoryList/2">가정 살림</a></li>
	                    <li><a name="경제 경영" value="경제 경영" href="/book/categoryList/3">경제 경영</a></li>
	                    <li><a name="국어 외국어 사전" value="국어 외국어 사전" href="/book/categoryList/4">국어 외국어 사전</a></li>
	                    <li><a name="만화/라이트노벨" value="만화/라이트노벨" href="/book/categoryList/5">만화/라이트노벨</a></li>
	                    <li><a name="사회 정치" value="사회 정치" href="/book/categoryList/6">사회 정치</a></li>
	                    <li><a name="소설/시/희곡" value="소설/시/희곡" href="/book/categoryList/7">소설/시/희곡</a></li>
	                    <li><a name="수험서 자격증" value="수험서 자격증" href="/book/categoryList/8">수험서 자격증</a></li>
	                    <li><a name="어린이" value="어린이" href="/book/categoryList/9">어린이</a></li>
	                    <li><a name="에세이" value="에세이" href="/book/categoryList/10">에세이</a></li>
	                    <li><a name="예술" value="예술" href="/book/categoryList/11">예술</a></li>
	                    <li><a name="유아" value="유아" href="/book/categoryList/12">유아</a></li>
	                    <li><a name="인문" value="인문" href="/book/categoryList/13">인문</a></li>
	                    <li><a name="자기계발" value="자기계발" href="/book/categoryList/14">자기계발</a></li>
	                    <li><a name="자연과학" value="자연과학" href="/book/categoryList/15">자연과학</a></li>
	                    <li><a name="종교" value="종교" href="/book/categoryList/16">종교</a></li>
	                    <li><a name="청소년" value="청소년" href="/book/categoryList/17">청소년</a></li>
					</ul>
				</li>
				<li>&middot;</li>
				<li class="hlist1_1">
					<a href="/album/list">앨범</a>
					<ul style="margin-left: -35px;">
						<li><a name="Blu-ray" value="Blu-ray" href="/album/categoryList/1">Blu-ray</a></li>
	                    <li><a name="J-POP" value="J-POP" href="/album/categoryList/2">J-POP</a></li>
	                    <li><a name="LP(Vinyl) 전문관" value="LP(Vinyl) 전문관" href="/album/categoryList/3">LP(Vinyl) 전문관</a></li>
	                    <li><a name="OST" value="OST" href="/album/categoryList/4">OST</a></li>
	                    <li><a name="POP" value="POP" href="/album/categoryList/5">POP</a></li>
	                    <li><a name="가요" value="가요" href="/album/categoryList/6">가요</a></li>
	                    <li><a name="뮤직 DVD" value="뮤직 DVD" href="/album/categoryList/7">뮤직 DVD</a></li>
	                    <li><a name="스타샵" value="스타샵" href="/album/categoryList/8">스타샵</a></li>
	                    <li><a name="스페셜샵" value="스페셜샵" href="/album/categoryList/9">스페셜샵</a></li>
	                    <li><a name="예약CD/LP" value="예약CD/LP" href="/album/categoryList/10">예약CD/LP</a></li>
	                    <li><a name="재즈" value="재즈" href="/album/categoryList/11">재즈</a></li>
	                    <li><a name="클래식" value="클래식" href="/album/categoryList/12">클래식</a></li>
					</ul>
				</li>
				<li>&middot;</li>
				<li class="hlist1_1">
					<a style="cursor: pointer;">베스트</a>
					<ul style="margin-left: -5px;">
						<li>
							<a href="/book/best">도서</a>
						</li>
						<li>
							<a href="/album/best">음반</a>
						</li>
					</ul>
				</li>
				<li>&middot;</li>
				<li class="hlist1_1">
					<a style="cursor: pointer;">신간</a>
					<ul style="margin-left: -10px;">
						<li>
							<a href="/book/new">도서</a>
						</li>
						<li>
							<a href="/album/new">음반</a>
						</li>
					</ul>
				</li>
			</ul>
            <ul class="hlist2">
                <li><a href="/board/boardList">자유게시판</a></li>
                <li><a href="/qna/list">Q&A</a></li>
                <li><a href="/notice/list">공지사항</a></li>
            </ul>
         </nav>
      </div>
   </header>
<script>
$("#loginBtn2").on("click", function(){
    alert("로그인 후 이용가능합니다.");
    self.location = "/member/login";
 });
$("#loginBtn3").on("click", function(){
    alert("로그인 후 이용가능합니다.");
    self.location = "/member/login";
 });
</script>