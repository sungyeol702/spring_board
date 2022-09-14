<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../common/header.jsp"/>  
<jsp:include page="../common/nav.jsp"/>  

<c:set var="bvo" value="${bdto.bvo }"/>
<div class="container mt-3">
  <div class="card">
    <div class="card-header"><h1>Q.${bvo.title }</h1></div>
    <div class="card-header" id="bnoVal" style="display:none;">${bvo.bno }</div>
    <div class="card-body">${bvo.content }</div> 
  		<c:choose>
  			<c:when test="${not empty bdto.imageList }">
    			<c:forEach items="${bdto.imageList }" var="ivo">
    			<div class="card-body">
				<img class="imgeModal" 
					src="/upload/${fn:replace(ivo.saveDir,'\\','/')}/${ivo.uuid }_${ivo.fileName }" 
					style="cursor: pointer; height: 404.267px; width: 400px;">
		    	</div>
    			</c:forEach>
  			</c:when>
  			<c:otherwise></c:otherwise>
  		</c:choose>
    <div class="card-footer">작성자:${bvo.writer } 작성일:${bvo.modAt } 조회수:${bvo.readCount }</div>
  </div>
  <div class="row">
      <a href="/board/list?pageNo=${pgvo.pageNo }&qty=${pgvo.qty}&scope=${pgvo.scope}&kwd=${pgvo.kwd}" class="mb-3">List</a>
      <c:if test="${ses.id eq bvo.writer}">
        <a href="/board/modify?bno=${bvo.bno }&pageNo=${pgvo.pageNo }&qty=${pgvo.qty}&scope=${pgvo.scope}&kwd=${pgvo.kwd}" class="btn btn-primary col-sm"> modify</a>
        <div class="col-sm"></div>
        <div class="col-sm"></div>
        <div class="col-sm"></div>
        <a id="boardRemove" class="btn btn-danger col-sm"> remove</a>
        <form action="" id="boardRmForm" style="display:none;" method="post">
        <input type="hidden" id="bno" value="" name="bno">
        <input type="hidden" id="pno" value="${pgvo.pageNo }" name="pageNo">
        <input type="hidden" id="pno" value="${pgvo.qty }" name="qty">
        <input type="hidden" id="pno" value="${pgvo.scope }" name="scope">
        <input type="hidden" id="pno" value="${pgvo.kwd }" name="kwd">
        </form>
      </c:if>
      </div>
</div>
<div class="container mt-3">
     <h2>Comment <span id="total"class="badge bg-primary"></span></h2>
    <div class="input-group mb-3" id ="cmt">
     	<span class="input-group-text" id="cmtWriter" style="display:none;">${ses.id }</span>
     	<input type="text" class="form-control" placeholder="답변하시오" id="cmtText">
    	<button type="button" class="btn btn-primary" id="cmtSbmBtn">ADD</button>
    </div>
</div>
<div class="container mt-3" id="cmtZone"></div>
<div class="text-center" style="Visibility:hidden;">
  <button type="button" id="moreBtn" data-page="1" class="btn btn-outline-secondary">MORE +</button>
  </div>
<script src="/resources/js/board.detail.js"></script>
<script src="/resources/js/board.cmt.js"></script>
<script>
document.addEventListener('DOMContentLoaded', function(){
	getCommentList(document.getElementById('bnoVal').innerText);
	let isUp = '<c:out value="${isUp}"/>';
	if(parseInt(isUp)>0){
		alert("board수정완료")
	}
	if(document.getElementById('cmtWriter').innerText == ''){
		document.getElementById('cmtText').disabled= true;
		document.getElementById('cmtSbmBtn').classList.add("disabled");
	}
	});
</script>
<jsp:include page="../common/footer.jsp"/> 
