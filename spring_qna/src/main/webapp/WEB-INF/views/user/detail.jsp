<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../common/header.jsp"/>  
<jsp:include page="../common/nav.jsp"/>  
	<div class="container mt-3 ">
  <h3 class="mb-3">User Detail</h3>          
  <table class="table">
      <tr>
        <th>id</th>
        <td id="idVal">${uvo.id }</td>
      </tr>
      <tr>
        <th>Nick</th>
        <td>${uvo.nickName }</td>
      </tr>
      <tr>
        <th>Grade</th>
        <td>${uvo.grade }</td>
      </tr>
      <tr>
        <th>Reg_at</th>
        <td>${uvo.regAt}</td>
      </tr>
      <tr>
        <th>Last_login</th>
        <td>${uvo.lastLogin}</td>
      </tr>
  </table>
      <div class="row">
        <a href="/user/modify?id=${uvo.id }" class="btn btn-primary col-sm"> modify</a>
        <div class="col-sm"></div>
        <div class="col-sm"></div>
        <div class="col-sm"></div>
        <a  id="userRemove" class="btn btn-danger col-sm"> remove</a>
        <form action="" id="userRmForm" style="display:none;" method="post">
        <input type="hidden" id="id" value="" name="id">
        </form>
      </div>
</div>
<script src="/resources/js/user.detail.js"></script>
<jsp:include page="../common/footer.jsp"/> 
