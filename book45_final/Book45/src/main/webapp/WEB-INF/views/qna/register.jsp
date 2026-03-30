<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="../includes/header.jsp"%>


<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	session.setAttribute("id", id);
%>
<title>BOOK45 Q&A</title>

<link rel="stylesheet" type="text/css" href="/resources/css/qna/qnaRegister.css">
<div id="container">
	<div class="headline">
		<h2 style="text-align: center;">문의글 작성</h2><br><br><br>
	</div>
	
	<div id="register">
		<form method="post" action="/qna/register" name="frm">
			<table width="100%">
				<tr height="50px">
					<th width="40%">작성자</th>
					<td>
						${member.id}
						<input type='hidden' name='id' value='${member.id}'>
					</td>
				</tr>
				<tr height="50px">
					<th width="40%">제목</th>
					<td>
						<input type="text" name="title" id="title">
					</td>
				</tr>
				<tr height="50px">
					<th width="40%">공개 여부</th>
					<td>
					    <input type="radio" name="secret" id="secret" value="N" class="secret" /><span class="ml_10">공개</span>&nbsp;
					    <input type="radio" name="secret" id="secret" value="Y" class="secret"/><span class="ml_10">비공개</span>
					</td>
				</tr>
				<tr height="50px">
					<th width="40%">내용</th>
					<td>
						<textarea id="content" name="content" style="resize: none; outline: none;" cols="70" rows="10"></textarea>
						<p class="textCount">0 byte</p>
					</td>
				</tr>
			</table>
			<div class="qnaBtn">
				<button id="regBtn" type="submit" class="btn">문의 등록</button>
				<button id="resetBtn" class="btn" type="reset">다시 작성</button>
				<button type="button" class="btn" id="listBtn" onclick="location.href='/qna/list'">목록으로</button>
			</div>
		</form>
	</div>
</div>

	<%-- <div id="wrap" align="center">
		<h2>문의하기</h2>
		<form name="frm" role="form" action="/qna/register" method="post" onSubmit="return Checkform()">
			
			<table>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" size="107"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="id" size="10" value='<c:out value="${member.id}"/>' readOnly></td>
				</tr>
				<tr>
					<th>비밀글</th>
					<td>
					    <input type="radio" name="secret" id="secret" value="N" class="secret" /><span class="ml_10">공개</span>
					    <input type="radio" name="secret" id="secret" value="Y" class="secret"/><span class="ml_10">비공개</span>
					</td>
				</tr>
		
				<tr>
					<th>내용</th>
					<td><textarea cols="110" rows="10" name="content"></textarea></td>
				</tr>
			</table>
			<br><br>
			<button type="submit" class="btn btn-default">등록</button>
			<button type="reset" class="btn">다시 작성</button>
			<button type="submit" data-oper="list" class="btn">목록으로</button>
		</form>
	</div> --%>

<script>
$("#regBtn").click(function() {
	let container = $("#container");
	
	if (!container.find("#title").val()) {
		alert("제목을 입력해 주세요.");
		return false;
 	} else if ((frm.secret[0].checked == false) && (frm.secret[1].checked == false)) {
		alert("비밀글 여부를 선택해 주세요.");
		return false;
	} else if (!container.find("#content").val()) {
		alert("문의 내용을 입력해 주세요.");
		return false;
	}
	alert("문의글이 정상적으로 등록되었습니다.");
});

$("#content").keyup(function(e){
	
	let textBox = $(this).val();
	
	if (textBox.length == 0 || content == '') {
    	$('.textCount').text('0 byte');
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
	
	<!-- <script>
	function Checkform() {

	    if( frm.title.value == "" ) {
	    
	        frm.title.focus();
	        alert("제목을 입력해 주십시오.");
	        
	        return false;
	        
	    }
	    else if( (frm.secret[0].checked == false) && (frm.secret[1].checked == false) ) {

	        alert("비밀글 여부를 선택해 주십시오.");
	        return false;

	    }
	    else if( frm.content.value == "" ) {
	    
	        frm.content.focus();
	        alert("내용을 입력해 주십시오.");
	        
	        return false;
	        
	    }

	    alert("문의글이 등록되었습니다.");
	}
	</script> -->
<%@ include file="../includes/footer.jsp" %>