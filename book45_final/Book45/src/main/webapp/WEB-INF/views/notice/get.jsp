<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../includes/header.jsp" %>

<title>BOOK45 공지사항</title>

<link rel="stylesheet" href="/resources/css/notice/noticeDetail.css">
<%
   request.setCharacterEncoding("UTF-8");
   String id = request.getParameter("id");
   session.setAttribute("id", id);
%>

<title>BOOK45 공지사항</title>

<div id="wrap">
   	<h1>공지사항</h1>
   	<div id="noticeBox">
   		<div id="noticeHead">
   			<h1>${notice.title}</h1>
   			<div id="headerBox">
   				<strong>${notice.id}</strong>
   				<p>
   					<fmt:formatDate value="${notice.writeDate}" pattern="yyyy/MM/dd"/>
                  	조회수 ${notice.readCount}
   				</p>
   			</div>
   		</div>
   		<div id="noticeBody">
   			<div id="noticeContent">
   				${notice.content}
   			</div>
		    <form id="operForm" action="/notice/modify" method="get">
		    	<input type="hidden" id="num" name="num" value="<c:out value='${notice.num}'/>">
		    	<input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum}'/>">
		    	<input type="hidden" name="amount" value="<c:out value='${cri.amount}'/>">
		    	<input type="hidden" name="type" value="<c:out value='${cri.type}'/>">
		    	<input type="hidden" name="keyword" value="<c:out value='${cri.keyword}'/>">
		    </form>
   		</div>
   		<c:if test="${member.lev == 'A'}"> 
			<button data-oper="modify" class="btn btn-default" id="updateBtn">수정</button>
		</c:if>
		<button data-oper="list" class="btn" id="listBtn">목록으로</button>
   	</div>
      <%-- <table>
         <tr>
            <th>글번호</th>
            <td><c:out value="${notice.num}"/></td>
         </tr>
         <tr>
         	<th>작성자</th>
            <td><c:out value ="${notice.id}"/></td>
         </tr>
         <tr>
            <th>작성일</th>
            <td><fmt:formatDate pattern="yyyy/MM/dd" value ="${notice.updateDate}"/></td>
            <th>조회수</th>
            <td><c:out value ="${notice.readCount}"/></td>
         </tr>
         <tr>
            <th>제목</th>
            <td colspan="3">
               <input class="form-control" name="title"value='<c:out value="${notice.title}"/>' readonly>
               </td>
         </tr>
         <tr>
            <th>내용</th>
            <td colspan="3">
               <textarea class="form-control" rows="10" cols="70" name="content" readonly><c:out value="${notice.content}"/></textarea>
               </td>
      </tr>
   </table> --%>
</div>
   
<script>
$(document).ready(function() {
   var operForm = $("#operForm");
   
   $("button[data-oper='modify']").on("click", function(e) {
      operForm.attr("action", "/notice/modify").submit();
   });
   
   $("button[data-oper='list']").on("click", function(e) {
      operForm.find("#num").remove();
      operForm.attr("action", "/notice/list");
      operForm.submit();
   });
});
</script>
<%@ include file="../includes/footer.jsp" %>