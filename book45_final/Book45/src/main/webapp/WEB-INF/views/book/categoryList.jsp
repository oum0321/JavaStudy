<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp" %>
<%
	session.getAttribute("category");
%>

<title>BOOK45 도서</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/book/category.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

	<div id="wrap" align="center">
		<h2><%= session.getAttribute("category") %> 검색 결과</h2>
		<c:if test="${member.lev == 'A'}">
			<input type="button" value="도서 등록" id="regibtn" onclick="location.href='/album/register'" style="float: right;">
		</c:if>
		<table class="list">
			<c:forEach var="book" items="${category}">
				<tr class="record">
					<td name="num">${book.num}<input type="hidden" name="num" value="${book.num}"></td>
					<td><a class="move" href='<c:out value="${book.isbn}"/>'><img alt="" src="${book.pictureUrl}" id="cover"></a><input type="hidden" name="pictureurl" value="${book.pictureUrl}"></td>
					<td>
					<b>카테고리</b> &nbsp; ${book.category}<input type="hidden" name="category" value="${book.category}">
					<br> <b>제목</b>
						&nbsp; <!-- <a href="/book/bookDetail"> --><a class="move" href='<c:out value="${book.isbn}"/>'>
							${book.title} </a> <%-- <input type="hidden" name="isbn" value="${book.isbn}"> --%>
							<br> <b>작가</b> &nbsp; ${book.author} <input type="hidden" name="author" value="${book.author}"><br>
						<b>출판사</b> &nbsp; ${book.pub} <input type="hidden" name="pub" value="${book.pub}"><br> <b>가격</b> &nbsp;
						<fmt:formatNumber value="${book.price}" pattern="#,### 원"></fmt:formatNumber> &nbsp;<input type="hidden" name="price" value="${book.price}"><br> <b>줄거리</b> <br>${book.summary}
					</td>
					<!-- <td>
						<a class="cartBtn">장바구니</a>
					</td> -->
					<!-- <th width="100px;">
					<b>수량</b><input type="text" value="1" id="amountBox">
					<a id="cart"> <img alt=""
							width="100%" height="100%"
							src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fb0cUga%2FbtrURTstBS4%2FvfCL8IU8BoC2CH9ogwjuT1%2Fimg.png">
							<br> <em>장바구니</em>
					</a></th> -->
				</tr>
			</c:forEach>
		</table>
		<form id="actionForm" action="/album/list" method="get">
              <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
              <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
              <input type="hidden" name="type" value="${pageMaker.cri.type}">
              <input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
        </form>
        
	<!-- 검색 시작 -->
	<div class='row' align="center">
		<div class="col-lg-12">
			<form id="searchForm" action="/book/list" method="get">
				<select name="type" id="hoption">
					<option value="" <c:out value="${pageMaker.cri.type==null? 'selected': ''}"/>>--</option>
					<option value="T" <c:out value="${pageMaker.cri.type eq 'T'? 'selected': ''}"/>>제목</option>
					<option value="C" <c:out value="${pageMaker.cri.type eq 'C'? 'selected': ''}"/>>내용</option>
					<option value="W" <c:out value="${pageMaker.cri.type eq 'W'? 'selected': ''}"/>>작성자</option>
					<option value="TC" <c:out value="${pageMaker.cri.type eq 'TC'? 'selected': ''}"/>>제목/내용</option>
					<option value="TW" <c:out value="${pageMaker.cri.type eq 'TW'? 'selected': ''}"/>>제목/작성자</option>
					<option value="TCW" <c:out value="${pageMaker.cri.type eq 'TWC'? 'selected': ''}"/>>제목/내용/작가</option>
				</select>
				<input type="text" name="keyword" value='${pageMaker.cri.keyword}' id="hsearch" placeholder="검색할 내용을 입력해 주세요"/>
				<input type="hidden" name="pageNum" value='${pageMaker.cri.pageNum}'/>
				<input type="hidden" name="amount" value='${pageMaker.cri.amount}'/>
				<button class="btn btn-default" id="searchBtn">검색</button>
			</form>	
		</div>
	</div>          
	<!-- 검색 끝 -->
      
      <!-- 페이징처리 시작 -->
	<%-- <div class="pull-right">
		<ul class="pagination">
		
		<c:if test="${pageMaker.prev}">
			<li class="paginate_button previous"><a href="${pageMaker.startPage -1}">Previous</a></li>
		</c:if>
		
		<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<li class="paginate_button ${pageMaker.cri.pageNum == num ? "active" : ""}">
			<a href="${num}">${num}</a></li>
		</c:forEach>
		
		<c:if test="${pageMaker.next}">
			<li class="paginate_button next"><a href="${pageMaker.endPage +1}">Next</a></li>
		</c:if>
		
		</ul>
		
	</div> --%>
	<!-- 페이징처리 끝 -->
</div>
	
<script>
$(document).ready(function() {
	$("#regBtn").on("click", function() {

		self.location = "/album/register";
	});
	
	var actionForm = $("#actionForm");
	
	$(".paginate_button a").on("click", function(e){
		e.preventDefault();
		
		//추가
		actionForm.attr("action", "/album/list"); // 수정삭제메뉴에서 뒤로가기 한다음 page번호 클릭하면 상세페이지 빠지는 것 방지
		//
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	});
	
	$(".move").on("click",function(e){
		
		e.preventDefault();
		//추가
		actionForm.find("input[name='isbn']").remove();  //뒤로 간 후 리스트 클릭하면 bno가 계속 쌓이는 문제
		//
		actionForm.append("<input type='hidden' name='isbn' value='" + $(this).attr("href") + "' >");
		actionForm.attr("action", "/book/get");
		actionForm.submit();
	});
	
	var searchForm = $("#searchForm");
	$("#searchForm button").on("click", function(e){
		
		if(!searchForm.find("option:selected").val()){
			alert("검색종류를 선택하세요");
			return false;
		}
		if(!searchForm.find("input[name='keyword']").val()){
			alert("키워드를 입력하세요");
			return false;
		}
		searchForm.find("input[name='pageNum']").val("1");
		e.preventDefault();
		searchForm.submit();
		
	});
	
});
</script>
<%@ include file="../includes/footer.jsp" %>