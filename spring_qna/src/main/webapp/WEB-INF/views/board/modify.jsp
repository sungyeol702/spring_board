<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../common/header.jsp"/>  
<jsp:include page="../common/nav.jsp"/>  
<c:set var="bvo" value="${bdto.bvo }"/> 
<div class="container mt-3">
<form action="/board/modify" method="post" enctype="multipart/form-data">
    <div class="mb-3">
      <label for="title">Title:</label>
      <input type="text" class="form-control" id="title" 
      placeholder="Enter Title" name="title" value="${bvo.title}">
      <input type="hidden" class="form-control" id="bno" 
      placeholder="Enter Title" name="bno" value="${bvo.bno}">
    </div>
   <div class="mb-3 mt-3">
      <label for="category">Category:</label><br>
    <div class="form-check">
      <input type="radio" class="form-check-input" id="radio1" name="category" value="html" checked>
      <label class="form-check-label" for="radio1">html</label>
    </div>
    <div class="form-check">
      <input type="radio" class="form-check-input" id="radio2" name="category" value="css">
      <label class="form-check-label" for="radio2">css</label>
    </div>
    <div class="form-check">
      <input type="radio" class="form-check-input" id="radio3" name="category" value="js">
      <label class="form-check-label" for="radio3">js</label>
    </div>
    <div class="form-check">
      <input type="radio" class="form-check-input" id="radio4" name="category" value="java">
      <label class="form-check-label" for="radio4">java</label>
    </div>
    <div class="form-check">
      <input type="radio" class="form-check-input" id="radio5" name="category" value="spring">
      <label class="form-check-label" for="radio5">spring</label>
    </div>
    </div>
    <div class="mb-3">
    <label for="content">Content</label>
    <textarea rows="3" cols="" class="form-control" name="content">${bvo.content}</textarea>
    </div>
     <div class="mb-3">
			<label for="attach">Image Attach:</label> <input type="file"
				class="form-control" style="display: none;" id="images"
				name="fileAttached" multiple>
			<div class="d-grid">
				<button type="button" id="attachTrigger"
					class="btn btn-outline-primary btn-block d-block">Attach Files</button>
			</div>
			<div class="my-3" id="imageZone"></div>
		</div>
    
    <button type="submit" class="btn btn-primary" id ="regBtn">Submit</button>
  </form>
</div>
<br>
  <div class="container mt-3">
<c:if test="${not empty bdto.imageList}">
 <h1>image List</h1>
</c:if>
  <c:forEach items="${bdto.imageList }" var="ivo">
  <div class="d-flex justify-content-around mb-3">
    <div class="p-2">${ivo.fileName } <span class="badge bg-primary rounded-pill"> ${ivo.fileSize }</span></div>
    <div class="p-2"></div>
    <button data-uuid="${ivo.uuid }"
    class="btn btn-outline-danger btn-sm p-2 imageDelBtn">X</button>
</div>
  </c:forEach>
    
  </div>

<script>
	document.getElementById('attachTrigger').addEventListener('click',()=>{
		document.getElementById('images').click();
	});
</script>
<script src="/resources/js/board.register.js"></script>
<script src="/resources/js/board.modify.js"></script>
<jsp:include page="../common/footer.jsp"/>  