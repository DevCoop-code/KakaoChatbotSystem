<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>대화로그</title>
	<!-- BootstrapCDN - CSS Only -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<!-- JS, Popper.js, jQuery -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<style>
	.layer
	{
		display: table;
		margin-left: auto;
		margin-right: auto;
	}
	</style>
</head>
<body>
	<main role="main">
		<div class="jumbotron text-center">
			<h1>A.I. 대화 로그</h1>
			<table class="table">
				<thead>
					<th scope="col">번호</th>
					<th scope="col">요청 메시지</th>
					<th scope="col">응답 메시지</th>
					<th scope="col">대화상대</th>
					<th scope="col">날짜</th>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="chatLogVO">
						<tr>
							<td>${chatLogVO.bno}</td>
							<td>${chatLogVO.request}</td>
							<td>${chatLogVO.response}</td>
							<td>${chatLogVO.user}</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${chatLogVO.regdate}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<a href="/">목록 이동</a>
		<div aria-label="Page navigation example" class="layer">
			<ul class="pagination">
				<c:if test="${pageMaker.prev}">
					<li class="page-item"><a class="page-link" href="listAll?page=${pageMaker.startPage - 1}">&laquo;</a></li>
				</c:if>
				
				<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
					<li class="page-item"
					<c:out value="${pageMaker.pageNum == idx?'class = active':''}"/>>
						<a class="page-link" href="listAll?page=${idx}">${idx}</a>
					</li>
				</c:forEach>
				
				<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
					<li class="page-item"><a class="page-link" href="listAll?page=${pageMaker.endPage + 1}">&raquo;</a></li>
				</c:if>
			</ul>
		</div>
	</main>
</body>
</html>