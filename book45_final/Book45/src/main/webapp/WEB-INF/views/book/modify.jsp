<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp" %>

<link rel="stylesheet" type="text/css" href="/resources/css/book/bookModify.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

<title>BOOK45 도서</title>

<div id="wrap" align="center">
	<h2>도서 수정</h2>	
	<form id="modifyForm" role="form" action="/book/modify" method="post">
		<input type="hidden" name="pageNum" value="${cri.pageNum}">
		<input type="hidden" name="amount" value="${cri.amount}">
		<input type="hidden" name="type" value="${cri.type}">
		<input type="hidden" name="keyword" value="${cri.keyword}">
		<table>
			<tr>
				<th>일련번호</th>
				<td>
					<input type="text" name="isbn" id="isbn" value="${book.isbn}">
					<span class="finalIsbnCheck">도서 일련번호를 입력해 주세요.</span>
				</td>
			</tr>
			<tr>
				<th>카테고리</th>
				<td>
					<select id="category" name="category" size="1">
						<option value="none" disabled selected>카테고리</option>
						<option value="IT 모바일">IT 모바일</option>
						<option value="가정 살림">가정 살림</option>
						<option value="경제 경영">경제 경영</option>
						<option value="국어 외국어 사전">국어 외국어 사전</option>
						<option value="만화/라이트노벨">만화/라이트노벨</option>
						<option value="사회 정치">사회 정치</option>
						<option value="소설/시/희곡">소설/시/희곡</option>
						<option value="수험서 자격증">수험서 자격증</option>
						<option value="어린이">어린이</option>
						<option value="에세이">에세이</option>
						<option value="예술">예술</option>
						<option value="유아">유아</option>
						<option value="인문">인문</option>
						<option value="자기계발">자기계발</option>
						<option value="자연과학">자연과학</option>
						<option value="종교">종교</option>
						<option value="청소년">청소년</option>
					</select>
					<span class="finalCategoryCheck">카테고리를 선택해 주세요.</span>
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" id="title" name="title" value="${book.title}">
					<span class="finalTitleCheck">도서 제목을 입력해 주세요.</span>
				</td>
			</tr>
			<tr>
				<th>저자</th>
				<td>
					<input type="text" id="author" name="author" value="${book.author}">
					<span class="finalAuthorCheck">작가명을 입력해 주세요.</span>
				</td>
			</tr>
			<tr>
				<th>출판사</th>
				<td>
					<input type="text" id="pub" name="pub" value="${book.pub}">
					<span class="finalPubCheck">출판사명을 입력해 주세요.</span>
				</td>
			</tr>
			<tr>
				<th>가격</th>
				<td>
					<input type="text" id="price" name="price" value="${book.price}">
					<span class="finalPriceCheck">도서 가격을 입력해 주세요.</span>
				</td>
			</tr>
			<tr>
				<th>줄거리</th>
				<td>
					<textarea cols="110" rows="10" id="summary" name="summary">${book.summary}</textarea><br>
					<span class="finalSummaryCheck">줄거리를 입력해 주세요.</span>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<label for="file"><div id="upload">파일 업로드</div></label>
					<input type="file" id="file" name="pictureUrl">
					<div id="uploadResult">
					
					</div>
				</td>
			</tr>
			<tr>
				<th>재고</th>
				<td>
					<input type="text" id="stock" name="stock" value="${book.stock}">
					<span class="finalStockCheck">재고를 입력해 주세요.</span>
				</td>
			</tr>
		</table>
		<button type="submit" id="modBtn" class="Btn">도서 수정</button>
		<button type="submit" data-oper="remove" class="Btn" id="button">도서 삭제</button>
		<button type="button" class="Btn" id="button" onclick="location.href='/book/list'">도서 목록</button>
	</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	let formObj = $("form");
	
	$('#button').on("click", function(e) {
		e.preventDefault();
		let operation = $(this).data("oper");
		
		console.log(operation);
		
		if(operation === 'remove'){
			formObj.attr("action", "/book/remove");
		} else if(operation === 'list'){
			formObj.attr("action", "/book/list").attr("method","get");
			
			let pageNumTag = $("input[name='pageNum']").clone();
			let amountTag = $("input[name='amount']").clone();
			let keywordTag = $("input[name='keyword']").clone();
			let typeTag = $("input[name='type']").clone();
			
			formObj.empty();
			formObj.append(pageNumTag);
			formObj.append(amountTag);
			formObj.append(keywordTag);
			formObj.append(typeTag);
		}
		
		formObj.submit();
	});
});

/* 도서 수정 유효성 검사 */
let isbnCheck = false; // 일련번호 입력 여부
let categoryCheck = false; // 카테고리 입력 여부
let titleCheck = false; // 제목 입력 여부
let authorCheck = false; // 저자 입력 여부
let pubCheck = false; // 출판사 입력 여부
let priceCheck = false; // 가격 입력 여부
let summaryCheck = false; // 줄거리 입력 여부
let stockCheck = false; // 재고 입력 여부

$(document).ready(function() {
	$("#modBtn").click(function() {
		let isbn = $("#isbn").val();
		let category = $("select[name='category']").val();
	    let title = $("#title").val();
	    let price = $("#price").val();
	    let summary = $("#summary").val();
	    let author = $("#author").val();
	    let pub = $("#pub").val();
	    let stock = $("#stock").val();
		
	    console.log("카테고리: " + category);
	    /* 일련번호 유효성 검사 */
	 	if (isbn == "") {
	 		$(".finalIsbnCheck").css("display", "block");
			isbnCheck = false;
	 	} else {
	 		$(".finalIsbnCheck").css("display", "none");
			isbnCheck = true;	
	 	}
	 	
	 	/* 카테고리 유효성 검사 */
	 	if (category == null) {
	 		$(".finalCategoryCheck").css("display", "block");
	 		categoryCheck = false;
	 	} else {
	 		$(".finalCategoryCheck").css("display", "none");
	 		categoryCheck = true;
	 	}
	 	
	 	/* 도서 제목 유효성 검사 */
	 	if (title == "") {
	 		$(".finalTitleCheck").css("display", "block");
	 		titleCheck = false;
	 	} else {
	 		$(".finalTitleCheck").css("display", "none");
	 		titleCheck = true;
	 	}
	 	
	 	/* 작가명 유효성 검사 */
	 	if (author == "") {
	 		$(".finalAuthorCheck").css("display", "block");
	 		authorCheck = false;
	 	} else {
	 		$(".finalAuthorCheck").css("display", "none");
	 		authorCheck = true;
	 	}
	 	
	 	/* 출판사 유효성 검사 */
	 	if (pub == "") {
	 		$(".finalPubCheck").css("display", "block");
	 		pubCheck = false;
	 	} else {
	 		$(".finalPubCheck").css("display", "none");
	 		pubCheck = true;
	 	}
	 	
	 	/* 가격 유효성 검사 */
	 	if (price == "") {
	 		$(".finalPriceCheck").css("display", "block");
			priceCheck = false;
	 	} else {
	 		$(".finalPriceCheck").css("display", "none");
	 		priceCheck = true;	
	 	}
	 	
	 	/* 줄거리 유효성 검사 */
	 	if (summary == "") {
	 		$(".finalSummaryCheck").css("display", "block");
	 		summaryCheck = false;
	 	} else {
	 		$(".finalSummaryCheck").css("display", "none");
	 		summaryCheck = true;
	 	}
	 	
	 	/* 재고 유효성 검사 */
	 	if (stock == "") {
	 		$(".finalStockCheck").css("display", "block");
	 		stockCheck = false;
	 	} else {
	 		$(".finalStockCheck").css("display", "none");
	 		stockCheck = true;	
	 	}
	 	
	 	if (isbnCheck && categoryCheck && titleCheck && priceCheck && summaryCheck && authorCheck && pubCheck && stockCheck) {
	 		alert("도서가 정상적으로 수정되었습니다.");
			$("#modifyForm").attr("action", "/book/modify");
			$("#modifyForm").submit();
	 	}
	 	return false;
	});
});
	
/* 일련번호 유효성 해결 시 문구 삭제 */
$("#isbn").on("propertychange change keyup paste input",function(){
   $(".finalIsbnCheck").css("display", "none");
});

/* 카테고리 유효성 해결 시 문구 삭제 */
$("select[name='category']").on("propertychange change keyup paste input",function(){
   $(".finalCategoryCheck").css("display", "none");
});

/* 도서 제목 유효성 해결 시 문구 삭제 */
$("#title").on("propertychange change keyup paste input",function(){
   $(".finalTitleCheck").css("display", "none");
});

/* 작가명 유효성 해결 시 문구 삭제 */
$("#author").on("propertychange change keyup paste input",function(){
   $(".finalAuthorCheck").css("display", "none");
});

/* 출판사 유효성 해결 시 문구 삭제 */
$("#pub").on("propertychange change keyup paste input",function(){
   $(".finalPubCheck").css("display", "none");
});

/* 가격 유효성 해결 시 문구 삭제 */
$("#price").on("propertychange change keyup paste input",function(){
   $(".finalPriceCheck").css("display", "none");
});

/* 줄거리 유효성 해결 시 문구 삭제 */
$("#summary").on("propertychange change keyup paste input",function(){
   $(".finalSummaryCheck").css("display", "none");
});

/* 재고 유효성 해결 시 문구 삭제 */
$("#stock").on("propertychange change keyup paste input",function(){
   $(".finalStockCheck").css("display", "none");
});

/* 업로드 이미지 유효성 */
/* let regex = new RegExp("(.*?)\.(exe|sh|zip|als)$");
let maxSize = 5242880;

function fileCheck(fileName, fileSize) {
	if (fileSize > maxSize) {
		alert("업로드 가능한 파일 용량을 초과하였습니다.");
		return false;
	}
	
	if (regex.test(fileName)) {
		alert("지원하지 않는 형식의 파일입니다.");
		return false;
	}
	return true;
} */

/* 이미지 업로드 */
/* $("input[type='file']").on("change", function(e) {
	let formData = new FormData();
	let fileInput = $('input[name="pictureUrl"]');
	let fileList = fileInput[0].files;
	let fileObj = fileList[0];
	
	if (!fileCheck(fileObj.name, fileObj.size)) {
		return false;
	}
	
	console.log("이름: " + fileObj.name);
	console.log("크기: " + fileObj.size);
	
	formData.append("pictureUrl", fileObj);
	
	console.log("formData: " + formData);
	
	$.ajax({
		url: "/book/uploadFile", // 서버로 요청을 보낼 URL
		processData: false, // 서버로 전송될 데이터를 queryString 형태로 변환할지 여부
		contentType: false, // 서버로 전송되는 데이터의 content-type
		data: formData, // 서버로 전송할 데이터
		type: 'POST', // 서버 요청 타입(GET, POST)
		dataType: 'json', // 서버로부터 반환받을 데이터 타입
		success: function(result) {
			console.log(result);
		},
		error: function(result) {
			alert("이미지 파일이 아닙니다.");
		}
	});
});

let isbn = '<c:out value="${book.isbn}"/>';
let uploadResult = $("#uploadResult");

$.getJSON("/book/getImageList", {isbn : isbn}, function(arr) {
	if (arr.length === 0) {
		let str = "";
		str += "<div id='resultCard'>";
		str += "<img src='/resources/img/noImage.png'></div>";
		
		uploadResult.html(str);
		
		return;
	}
	
	let str = "";
	let obj = arr[0];
	
	let fileCallPath = encodeURIComponent(obj.uploadPath.replace(/\\/g, '/') + "/s_" + obj.uuid + "_" + obj.fileName);
	str += "<div id='resultCard' data-path='" + obj.uploadPath + "' data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "'>";
	str += "<div class='imgDeleteBtn' data-file='" + fileCallPath + "'>X</div>";
	str += "<img src='/book/display?fileName=" + fileCallPath + "'>";
	str += "<input type='hidden' name='imageList[0].fileName' value='" + obj.fileName + "'>";
	str += "<input type='hidden' name='imageList[0].uuid' value='" + obj.uuid + "'>";
	str += "<input type='hidden' name='imageList[0].uploadPath' value='" + obj.uploadPath + "'></div>";
	
	uploadResult.html(str);
});

function deleteFile() {
	let targetFile = $(".imgDeleteBtn").data("file");
	let targetDiv = $("#resultCard");
	
	$.ajax({
		url: '/book/deleteFile',
		data: {fileName : targetFile},
		dataType: 'text',
		type: 'POST',
		success: function(result) {
			console.log(result);
			
			targetDiv.remove();
			$("input[type='file']").val("");
		},
		error: function(result) {
			console.log(result);
			alert("파일을 삭제하는 데 실패했습니다.");
		}
	});
}

$("#uploadResult").on("click", ".imgDeleteBtn", function(e) {
	deleteFile();
}); */
</script>
<%@ include file="../includes/footer.jsp" %>