<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page="../common/header.jsp"/>  
<jsp:include page="../common/nav.jsp"/>  
<div class="container mt-3">
<h1>User modify</h1>
<form action="/user/modify" method="post">
    <div class="mb-3 mt-3">
      <label for="id">id:</label>
      <input type="text" class="form-control" id="email" placeholder="Enter id" name="id" value="${uvo.id }" readonly>
    </div>
    <div class="mb-3">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd" value="${uvo.pwd }">
    </div>
    <div class="mb-3">
      <label for="nick">nickName:</label>
      <input type="text" class="form-control" id="nickName" placeholder="Enter nickName" name="nickName" value=${uvo.nickName }>
    </div>
    <c:if test="${ses.grade == 99 }">
    <div class="mb-3">
      <label for="nick">Grade:</label>
      <input type="text" class="form-control" id="Grade" placeholder="Enter Grade" name="grade" value=${uvo.grade }>
    </div>
    </c:if>
    <c:if test="${ses.grade != 99 }">
    <div class="mb-3">
      <label for="nick">Grade:</label>
      <input type="text" class="form-control" id="Grade" placeholder="Enter Grade" name="grade" value=${uvo.grade } readonly>
    </div>
    </c:if>
    <button type="submit" class="btn btn-primary">Submit</button>
  </form>
</div>

<jsp:include page="../common/footer.jsp"/> 