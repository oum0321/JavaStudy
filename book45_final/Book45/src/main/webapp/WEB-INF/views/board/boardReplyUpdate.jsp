<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js" integrity="sha512-STof4xm1wgkfm7heWqFJVn58Hm3EtS31XFaagaa8VMReCXAkQnJZ+jEy8PCC/iT18dFy95WcExNHFTqLyp72eQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<style type="text/css">
     /* 창 여분 없애기 */
     body{
        margin : 0;
     }
     /* 전체 배경화면 색상 */
     .wrapper_div{
      background-color: #f5f5f5;
       height: 100%;     
     }
    /* 팝업창 제목 */
     .subject_div{
       width: 100%;
       background-color: #7b8ed1;
       color: white;
       padding: 10px;
       font-weight: bold;
     }
     
     /* 컨텐츠, 버튼 영역 padding */
     .input_wrap{
        padding: 30px;
     }
     .btn_wrap{
        padding: 5px 30px 30px 30px;
        text-align: center;
     }
     
     /* 버튼 영역 */
     .cancel_btn{
        margin-right:5px;
         display: inline-block;
       width: 130px;
       background-color: #5e6b9f;
       padding-top: 10px;
       height: 27px;
       color: #fff;
       font-size: 14px;
       line-height: 18px;  
       cursor: pointer;   
     }
     .enroll_btn{
          display: inline-block;
       width: 130px;
       background-color: #7b8ed1;
       padding-top: 10px;
       height: 27px;
       color: #fff;
       font-size: 14px;
       line-height: 18px; 
       cursor: pointer;     
     }

   /* 책제목 영역 */
   .bookName_div h2{
      margin : 0;
   }
     /* 평점 영역 */
     .rating_div{
        padding-top: 10px;
     }
     .rating_div h4{
        margin : 0;
     }
     select{
     margin: 15px;
    width: 100px;
    height: 40px;
    text-align: center;
    font-size: 16px;
    font-weight: 600;     
     }
     /* 리뷰 작성 영역 */
     .content_div{
        padding-top: 10px;
     }
     .content_div h4{
        margin : 0;
     }
     textarea{
      width: 100%;
       height: 100px;
       border: 1px solid #dadada;
       padding: 12px 8px 12px 8px;
       font-size: 15px;
       color: #a9a9a9;
       resize: none;
       margin-top: 10px;     
     }
     .update_btn{
          display: inline-block;
       width: 130px;
       background-color: #7b8ed1;
       padding-top: 10px;
       height: 27px;
       color: #fff;
       font-size: 14px;
       line-height: 18px;      
     }
</style>
</head>
<body>
   <div class="wrapper_div">
      <div class="subject_div">
         댓글 수정
      </div>
      <div class="input_wrap">         
         <div class="bookName_div">
            <h2>작성자 : ${member.nickname}</h2>
         </div>
         <div class="secret_div">
            <h4>비밀댓글</h4>
            <c:choose>
               <c:when test="${replyInfo.secret eq true}">
                  <input type="checkbox" name="secret" id="secret" checked>
               </c:when>
                <c:otherwise>
                   <input type="checkbox" name="secret" id="secret">
                </c:otherwise>
             </c:choose>
         </div>
         <div class="content_div">
            <h4>댓글</h4>
            <textarea name="content">${replyInfo.content}</textarea>
         </div>
      </div>
      <div class="btn_wrap">
         <a class="cancel_btn">취소</a><a class="update_btn">수정</a>
      </div>
   </div>
   <script>
   
   $(document).ready(function(){
      
      /* 취소 버튼 */
      $(".cancel_btn").on("click", function(e){
         window.close();
      });
      
      /* 등록 버튼 */
      $(".update_btn").on("click", function(e){
         if ($("#secret").is(":checked") ) {
            var secret=true;
         } else {
         var secret = false;
         }
         const replyNum = '${replyInfo.replyNum}';
         const boardNum = '${replyInfo.boardNum}';
         const memberId = '${member.id}';
      
         const content = $("textarea").val();      
         
         console.log("secret: "+secret);
         const data = {
               replyNum : replyNum, 
               boardNum : boardNum,
               memberId : memberId,
               secret : secret,
               content : content
         }   
         
         $.ajax({
            data : data,
            type : 'POST',
            url : '/reply/update',
            success : function(result){
               
               $(opener.location).attr("href", "javascript:replyListInit();");
               alert("수정 되었습니다.");
               window.close();
            }         
         });      

      });
   });
   
   </script>
</body>
</html>