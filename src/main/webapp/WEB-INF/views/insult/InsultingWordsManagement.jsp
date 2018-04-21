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
	<title>욕설관리 페이지</title>
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
<div class="jumbotron">
	<div class="text-center">
		<h1>욕설관리 페이지</h1>
	</div>
	<form action="/insult/InsultingWordsManagement" method="post">
		<label for="registerWord"></label>
		<input type="text" id="registerWord" name="registeredWord" required autofocus/>
		<button type="submit">등록</button>
	</form>
	<div class="text-center">
		<table class="table">
			<thead>
				<th scope="col">번호</th>
				<th scope="col">등록된 단어</th>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="insultWordVO">
					<tr>
						<td>${insultWordVO.bno}</td>
						<td>${insultWordVO.insult_word}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<a href="/">목록 이동</a>
</div>
</main>
</body>
</html>