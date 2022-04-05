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
<table align="center">
	<tr>
		<td>
			<table border ="1">
				<tr>
					<td width="120" align="center">
					Title
					</td>
					<td width="400">
					${board.boardTitle}
					</td>
				</tr>
				<tr>
					<td height="300" align="center">
					Comment
					</td>
					<td>
					${board.boardComment}
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
			<a href = "/board/${boardType}/${boardNum}/boardUpdateUI.do?pageNo=${pageNo}">update</a>
			<a id="delete" href="/board/${boardType}/${boardNum}/boardDelete.do" >Delete</a>
		</td>
	</tr>
</table>	
<script type="text/javascript">

	$j(document).ready(function(){
		
		$j("#delete").on("click",function(){
			var boardType = "${boardType}";
			var boardNum = ${boardNum};
			$j.getJSON("/board/"+boardType+"/"+boardNum+"/deleteCheck.do", function(data) {
				if(data >= 1){
					alert("삭제되었습니다.");
				}else{
					alert("이미 삭제되었습니다.");
				}
 			});
		});
	});
	

</script>
</body>
</html>