<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<div class="container mt-3">

	<div class="card">
		<div class="card-header">
			<div class="d-flex justify-content-between mb-3">
				<div class="">Q&A</div>
				<div class="">질문수:<span class="badge bg-primary">${pgn.totalCount }</span></div>
			</div>
		</div>
		<div class="card-body">
			<ul class="nav">
				<li class="nav-item"><a class="nav-link" href="/board/list">전체보기</a></li>
				<li class="nav-item"><a class="nav-link" href="/board/list?scope=h&kwd=${pgvo.kwd}">HTML</a></li>
				<li class="nav-item"><a class="nav-link" href="/board/list?scope=c&kwd=${pgvo.kwd}">CSS</a></li>
				<li class="nav-item"><a class="nav-link" href="/board/list?scope=s&kwd=${pgvo.kwd}">JS</a></li>
				<li class="nav-item"><a class="nav-link" href="/board/list?scope=j&kwd=${pgvo.kwd}">JAVA</a></li>
				<li class="nav-item"><a class="nav-link" href="/board/list?scope=s&kwd=${pgvo.kwd}">SPRING</a></li>
			</ul>
		</div>
	</div>
	<c:if test="${ses ne null }">
		<a href="/board/register" class="btn btn-primary col-sm">register</a>
	</c:if>

	<table class="table table-hover">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>카테고리</th>
				<th>조회수</th>
				<th>댓글수</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<td>${bvo.bno }</td>
					<td><a
						href="/board/detail?bno=${bvo.bno }&pageNo=${pgn.pgvo.pageNo }&qty=${pgn.pgvo.qty}&scope=${pgn.pgvo.scope}&kwd=${pgn.pgvo.kwd}">${bvo.title }
						<c:if test="${bvo.fileCount > 0 }">
						 <i class='fas fa-file-image' style='font-size:24px'></i>
						</c:if>
						</a>
					</td>
					<td>${bvo.category }</td>
					<td>${bvo.readCount}</td>
					<td>${bvo.cmtQty}</td>
					<td>${bvo.regAt}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="pagination justify-content-center">
		<c:if test="${pgn.prev }">
			<li class="page-item"><a class="page-link"
				href="/board/list?pageNo=${pgn.startPage - 1 }&qty=${pgn.pgvo.qty}&scope=${pgn.pgvo.scope}&kwd=${pgn.pgvo.kwd}">Prev</a>
			</li>
		</c:if>
		<c:forEach begin="${pgn.startPage }" end="${pgn.endPage }" var="i">
			<li class="page-item ${pgn.pgvo.pageNo == i? 'active' :'' }"><a
				class="page-link"
				href="/board/list?pageNo=${i }&qty=${pgn.pgvo.qty}&scope=${pgn.pgvo.scope}&kwd=${pgn.pgvo.kwd}">${i }</a>
			</li>
		</c:forEach>
		<c:if test="${pgn.next }">
			<li class="page-item"><a class="page-link"
				href="/board/list?pageNo=${pgn.endPage + 1 }&qty=${pgn.pgvo.qty}&scope=${pgn.pgvo.scope}&kwd=${pgn.pgvo.kwd}">Next</a>
			</li>
		</c:if>
	</ul>
	<div class="row">
		<div class="col-sm-12 col-md-3"></div>
		<div class="col-sm-12 col-md-6">
			<c:set value="${pgn.pgvo.scope }" var="typed" />
			<form class="d-flex" action="/board/list" method="get">
				<div class="input-group">
					<input type="hidden" name="pageNo" value="${pgn.pgvo.pageNo }">
					<input type="hidden" name="qty" value="${pgn.pgvo.qty }"> <select
						class="form-select" name="scope">
						<option ${typed eq null ? 'selected':'' }>선택</option>
						<option value="hcsjr" ${typed eq 'hcsjr'? 'selected':'' }>전체검색</option>
						<option value="h" ${typed eq 'h'? 'selected':'' }>html</option>
						<option value="c" ${typed eq 'c'? 'selected':'' }>css</option>
						<option value="s" ${typed eq 's'? 'selected':'' }>js</option>
						<option value="j" ${typed eq 'j'? 'selected':'' }>java</option>
						<option value="r" ${typed eq 'r'? 'selected':'' }>spring</option>
					</select> <input class="form-control me-2" type="search"
						placeholder="Search" aria-label="Search" name="kwd"
						value="${pgn.pgvo.kwd }">
					<button class="btn btn-outline-success" type="submit">
						Search 
					</button>
				</div>
			</form>
		</div>
		<div class="col-sm-12 col-md-3"></div>
	</div>
	<script>
		let isUp = '<c:out value="${isUp}"/>';
		let isDel = '<c:out value="${isDel}"/>';
		if (parseInt(isUp)) {
			alert("상품등록성공")
		}
		if (parseInt(isDel)) {
			alert("상품삭제완료")
		}
	</script>
</div>
<jsp:include page="../common/footer.jsp" />
