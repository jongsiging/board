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
<form action="/board/boardUpdate.do?pageNo=${pageNo}" method="post">
<table align="center">
	<tr>
		<td>
			<table border ="1">
				<tr>
					<td width="120" align="center">
					Title
					</td>
					<td width="400">
					<input name="boardTitle" type="text" size="50" value="${board.boardTitle}">
					</td>
				</tr>
				<tr>
					<td height="300" align="center">
					Comment
					</td>
					<td>
					<textarea name="boardComment"  rows="20" cols="55">${board.boardComment}</textarea>
					</td>
				</tr>
				<tr>
					<td align="center">
					Writer
					</td>
					<td>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="right">
			<a href="/board/boardList.do?pageNo=${pageNo}">List</a>
			<input id="update" type="submit" value="update">
		</td>
	</tr>
	<input name="boardType" type="hidden" size="50" value="${board.boardType}"> 
	<input name="boardNum" type="hidden" size="50" value="${board.boardNum}"> 
</table>	
<script type="text/javascript">

	$j(document).ready(function(){
		
		$j("#update").on("click",function(){
			alert("수정되었습니다.");
		});
	});
	

</script>
</form>
</body>
</html>