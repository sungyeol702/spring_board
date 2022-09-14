<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../common/header.jsp"/>  
<jsp:include page="../common/nav.jsp"/>  

<div class="container mt-3">
<form action="/user/register" method="post">
    <div class="mb-3 mt-3">
      <label for="id">id:</label>
      <div class="input-group">
      <input type="text" class="form-control" id="id" placeholder="Enter id" name="id">
      <button type="button" class="btn btn-success" id="dupleCheck">중복확인</button>
      </div>
    </div>
    <div class="mb-3">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
    </div>
    <div class="mb-3">
      <label for="nick">nickName:</label>
      <input type="text" class="form-control" id="nick" placeholder="Enter nickName" name="nickName">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
  </form>
</div>
<script src="/resources/js/user.register.js"></script>
<jsp:include page="../common/footer.jsp"/>  