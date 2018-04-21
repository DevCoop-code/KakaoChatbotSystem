<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>짤 폴더</title>
</head>
<body>
	<h1>Folder List</h1>
	<c:if test="${folderList != null}">
		<c:forEach var="foldername" items="${folderList}" begin="0" end="${folderList.size()}">
			<a href="">${foldername}</a><br>
		</c:forEach>
	</c:if>
</body>
</html>