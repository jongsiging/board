<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>boardView</title>
</head>
<body>
<form action="/board/calendar.do">
<div align="center">
	<select name="year">
		<c:forEach begin="2000" end="2022" var="i">
			<option value="${i}">${i}</option>
		</c:forEach>
	</select>
	<select name="month">
		<c:forEach begin="1" end="12" var="i">
			<option value="${i}">${i}월</option>
		</c:forEach> 
	</select>
	<input type="submit" value="달력 엑셀 출력">
</div>
</form>
</body>
</html>