<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../includes/header.jsp" %>

<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	session.setAttribute("id", id);
%>
<title>BOOK34 Q&A</title>	

<link rel="stylesheet" type="text/css" href="/resources/css/qna/qnaModify.css">

<div id="container">
	<div class="headline">
		<h2>Q&A 수정</h2>
	</div>
	
	<form action="/qna/modify" method="post" id="form">
		<input type="hidden" name="qnum" value="${qna.qnum}">
		<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
		<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
		<input type="hidden" name="type" value='<c:out value="${cri.type}"/>'>
		<input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'>
		
		<div id="textModify">
			<c:if test="${member.id eq qna.id}">
				<table id="table1">
					<tr height="50px;">
						<th width="20%">작성자</th>
						<td>
							<c:out value="${qna.id}"/>
						</td>
					</tr>
					<tr height="50px;">
						<th width="20%">제목</th>
						<td>
							<input type="text" name="title" id="title" value='<c:out value="${qna.title}"/>'>
						</td>
					</tr>
					<tr height="200px;">
						<th width="20%">내용</th>
						<td>
							<textarea rows="10" cols="70" name="content" id="content"
							style="resize: none; outline: none;">${qna.content}</textarea>
							<p class="textCount"> byte</p>
						</td>
					</tr>
					<tr height="50px;">
						<th>공개 여부</th>
						<td>
						    <c:if test="${qna.secret=='Y'}">
		                         <input type="radio" name="secret" id="secret" value="N" class="secret" ><span class="ml_10">공개</span>&nbsp;
		                         <input type="radio" name="secret" id="secret" value="Y" class="secret" checked><span class="ml_10">비공개</span>
		                     </c:if>
		                     <c:if test="${qna.secret=='N'}">
		                        <input type="radio" name="secret" id="secret" value="N" class="secret" checked><span class="ml_10">공개</span>&nbsp;
		                        <input type="radio" name="secret" id="secret" value="Y" class="secret" ><span class="ml_10">비공개</span>
		                     </c:if>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${member.lev == 'A'}">
				<table id="table2">
					<tr height="50px;">
						<th width="20%">작성자</th>
						<td>
							<c:out value="${qna.id}"/>
						</td>
					</tr>
					<tr height="50px;">
						<th width="20%">제목</th>
						<td>
							<c:out value="${qna.title}"/>
						</td>
					</tr>
					<tr height="200px;">
						<th width="20%">내용</th>
						<td>
							${qna.content}
						</td>
					</tr>
					<tr height="50px;">
						<th>공개 여부</th>
						<td>
							<c:if test="${qna.secret == 'Y'}">비공개</c:if>
						    <c:if test="${qna.secret == 'N'}">공개</c:if>
						</td>
					</tr>
				</table>
			</c:if>
			
				<!-- <tr height="50px;">
					<th colspan="2">
						
					</th>
				</tr> -->
			
			<div class="btn">
				<c:if test="${member.id eq qna.id}">
					<button type="submit" data-oper="modify" id="modifyBtn" class="modiBtn">
						수정
					</button>
				</c:if>
				<button type="submit" data-oper="remove" id="removeBtn" class="modiBtn">
					삭제하기
				</button>
				<button type="button" data-oper="list" id="listBtn" class="modiBtn">
					목록으로
				</button>
			</div>
		</div>
	</form>
</div>
	<%-- <div id="wrap" align="center">
		<h2>Q&A 수정</h2>
		<form name="frm" role="form" action="/qna/modify" method="post">
		<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
		<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
		<input type="hidden" name="type" value='<c:out value="${cri.type}"/>'>
		<input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'>
	<br>
		<table>
		
				<tr>
				<th>번호</th>
					<td><input type="text" name="qnum" value='<c:out value="${qna.qnum}"/>' readonly></td>
				
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" size="50" value='<c:out value="${qna.title}"/>'></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea cols="110" rows="10" name="content"><c:out value="${qna.content}"/></textarea></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input class="form-control" name="id" value='<c:out value="${qna.id}"/>' readonly></td>
				</tr>
				<tr>
					<th>비밀글</th>
					<td>
					    <input type="radio" name="secret" id="secret" value="N" class="secret" /><span class="ml_10">공개</span>
					    <input type="radio" name="secret" id="secret" value="Y" class="secret" checked/><span class="ml_10">비공개</span>
					</td>
				</tr>
			</table>
			<br><br>

			<button type="submit" data-oper="modify" class="btn btn-default">수정</button>
			<button type="button" data-oper="remove" class="btn btn-danger">삭제</button>
			<button type="button" data-oper="list" class="btn">목록으로</button>
	</form>

	</div>
	
	 --%>
	

<script>
$(document).ready(function() {
	
	var formObj = $("form");
	
	$('button').on("click", function(e) {
		e.preventDefault();
		
		var operation = $(this).data("oper");
		
		console.log(operation);
		
		if (operation === 'remove') {
			formObj.attr("action", "/qna/remove");
			alert("해당 문의글이 삭제되었습니다.");
		} 
		else if (operation === 'list') {
			formObj.attr("action", "/qna/list").attr("method", "get");
			var pageNumTag = $("input[name='pageNum']").clone();
			var amountTag = $("input[name='amount']").clone();
			var typeTag = $("input[name='type']").clone();
			var keywordTag = $("input[name='keyword']").clone();
			
			formObj.empty();
			formObj.append(pageNumTag);
			formObj.append(amountTag);
			formObj.append(typeTag);
			formObj.append(keywordTag);
		}
		else {
			alert("문의글이 수정되었습니다.");
		}
		
		formObj.submit();
		
		
	});
});

$("#content").keyup(function(e){
	
	let textBox = $(this).val();
	
	if (textBox.length == 0 || content == '') {
    	$('.textCount').text(' byte');
    } else {
    	$('.textCount').text(textBox.length + ' byte');
    }
	
	if (textBox.length > 500) {
		//500자 이상은 타이핑이 안되게 설정함
		$(this).val($(this).val().substring(0, 500));
		//500자가 넝어가면 알림창이 나옴
		alert("내용은 최대 500자까지만 작성 가능합니다.");
	}
});
</script>

<%@ include file="../includes/footer.jsp" %>
