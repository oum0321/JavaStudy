<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ include file="includes/header.jsp" %> 
<%
	String id = (String)session.getAttribute("id");
	String kakaoNickname = (String)session.getAttribute("kakaoNickname");
%>
<title>BOOK45</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
<link rel="stylesheet" href="/resources/css/main.css">
<link rel="stylesheet" href="/resources/css/common.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

	<div id="container">
        <div id="ad">
			<!-- <div id="adlist">
				<p id="view">도서</p>
				<ul id="ullist">
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
			</div> -->
            <div id="adimg">
            	<div class="mySlides fade">
				  <!-- <div class="numbertext">1 / 3</div> -->
				  <img src="https://cdn.pixabay.com/photo/2017/08/01/00/40/books-2562331_960_720.jpg" style="width:100%; height: 100%;">
				  <!-- <div class="text">Caption Text</div> -->
				</div>
				
				<!-- <div class="mySlides fade">
				  <div class="numbertext">2 / 3</div>
				  <img src="https://cdn.pixabay.com/photo/2019/07/05/06/51/library-4317851_960_720.jpg" style="width:100%; height: 100%;">
				  <div class="text">Caption Two</div>
				</div>
				
				<div class="mySlides fade">
				  <div class="numbertext">3 / 3</div>
				  <img src="https://cdn.pixabay.com/photo/2019/10/06/18/32/books-4530944_960_720.jpg" style="width:100%; height: 100%;">
				  <div class="text">Caption Three</div>
				</div> -->
            </div>
        </div>
        <section>
			<article id="sec1">
				<h2>오늘의 책</h2>
				<div id="sec1-1">
					<div class="sec1-1book">
						<a class="move" href='<c:out value="9791162540640"/>'><img alt="" src="http://image.yes24.com/goods/69655504/XL" width="100%" height="70%"></a>
						<div class="sec1-1text">
							<p><a class="move" href='<c:out value="9791162540640"/>'>아주 작은 습관의 힘</a></p>
							<p>제임스 클리어 저/이한이 역</p>
							<p>12,900원</p>
						</div>
					</div>
					<div class="sec1-1book">
						<a class="move" href='<c:out value="9788937461033"/>'><img alt="" src="http://image.yes24.com/goods/1387488/XL" width="100%" height="70%"></a>
						<div class="sec1-1text">
							<p><a class="move" href='<c:out value="9788937461033"/>'>인간실격</a></p>
							<p>다자이 오사무 저</p>
							<p>8,100원</p>
						</div>
					</div>
					<div class="sec1-1book">
						<a class="move" href='<c:out value="9791185402642"/>'><img alt="" src="http://image.yes24.com/goods/112046565/XL" width="100%" height="70%"></a>
						<div class="sec1-1text">
							<p><a class="move" href='<c:out value="9791185402642"/>'>부모의 말</a></p>
							<p>김종원 저</p>
							<p>13,620원</p>
						</div>
					</div>
					<div class="sec1-1book">
						<a class="move" href='<c:out value="9788954681179"/>'><img alt="" src="http://image.yes24.com/goods/102687133/XL" width="100%" height="70%"></a>
						<div class="sec1-1text">
							<p><a class="move" href='<c:out value="9788954681179"/>'>밝은 밤</a></p>
							<p>최은영 저</p>
							<p>11,550원</p>
						</div>
					</div>
					<form id="actionForm" action="/book/get" method="get">
						<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
						<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
						<input type="hidden" name="type" value="${pageMaker.cri.type}">
						<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
					</form>
				</div>
			</article>
			<article id="sec2">
				<h2>SAO CHANNEL</h2>
				<div id="youtube">
					<iframe width="100%" height="95%" src="https://www.youtube.com/embed/C7CR0Sruusw" title="YouTube video player" 
            		frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
               		allowfullscreen></iframe>
					
				</div>
				<div id="sec2text">
					<h3>2023 소설가 50인이 뽑은<br>올해의 소설 1위</h3>
					<br>
					<img src="https://contents.kyobobook.co.kr/display/bn_273_200_91fdcf4881fb4663b8c1780ef8d8c26f.jpg" width="287px">
					<p>
						'2023 소설가 50인이 뽑은 올해의 소설' 리스트는 소설가 약 90여 명에게 추천을 의뢰해 그 중 답변을 준 50명의 추천 도서를 모아 정리하였다. 
						
					</p>
				</div>
			</article>
        </section>
    </div>
    
<script>
let slideIndex = 0;
showSlides();

function showSlides() {
	let i;
	let slides = document.getElementsByClassName("mySlides");
	for (i = 0; i < slides.length; i++) {
		slides[i].style.display = "none";  
	}
	slideIndex++;
	if (slideIndex > slides.length) {slideIndex = 1}    
	slides[slideIndex-1].style.display = "block";  
	setTimeout(showSlides, 3000); // Change image every 3 seconds
}

$("#logoutBtn").click(function() {
	alert("정상적으로 로그아웃되었습니다.");
});

let actionForm = $("#actionForm");

$(".move").on("click",function(e){
	e.preventDefault();
	
	actionForm.append("<input type='hidden' name='isbn' value='" + $(this).attr("href") + "'>");
	actionForm.submit();
});
</script>
<%@ include file="includes/footer.jsp" %>