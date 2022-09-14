<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container mt-3">
	<nav class="navbar navbar-expand-sm navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="/">NAVER</a>
		</div>


		<c:choose>
			<c:when test="${ses eq null }">
				<ul class="nav nav-pills">
					<li class="nav-item"><a class="nav-link" href="/user/register">회원가입</a>
					</li>
				</ul>
				<a href="/user/login" class="btn btn-primary">Login</a>
			</c:when>
			<c:otherwise>
				<a href="/user/logout" class="btn btn-primary">Logout</a>
			</c:otherwise>
		</c:choose>
	</nav>
	<ul class="nav nav-pills">
		<c:if test="${ses ne null }">
			<li class="nav-item"><a class="nav-link"
				href="/user/detail?id=${ses.id }">${ses.id }</a></li>
		</c:if>
		<li class="nav-item"><a class="nav-link"
			href="/board/list">지식인</a></li>

	</ul>
</div>