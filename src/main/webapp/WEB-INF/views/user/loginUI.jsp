<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<script type="text/javascript">
	$j(document).ready(function() {
		$j('#submit').click(function(event) {
			//event.preventDefault();
			var rtn = false;
			var $frm = $j('.login :input');
			var param = $frm.serialize();
			
			var userId = $j("input[name='userId']").val();
			var userPw = $j("input[name='userPw']").val();
			
			if(userId == null || userId == ""){
				alert("id를 입력하세요.");
				$j("input[name='userId']").focus();
				return false;
			};
			if(userPw == null || userPw == ""){
				alert("pw를 입력하세요.");
				$j("input[name='userPw']").focus();
				return false;
			};
			$j.ajax({
				type : "GET",
				url : "/user/userCheck.do",
				dataType : "text",
				data : param,
				async: false,
				success: function(data){
					if(data != 0){
						alert("로그인 되었습니다.");
						rtn= true;
					}else{
						alert("id나 pw가 틀렸습니다.");
						rtn= false;
					}
						
				}
			});
			
			return rtn;
			
		});
	});

</script>
<body>
<form class="login" action="/user/login.do" id="login" style="margin-top: 100px;">
	<table align="center">
		<tr>
			<td align="left">
				<a href="/board/boardList.do">List</a>
			</td>
		</tr>
		<tr>
			<td>
				<table border ="1"> 
					<tr>
						<td width="120" align="center">
						id
						</td>
						<td width="200">
						<input name="userId" type="text" size="20"> 
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
						pw
						</td>
						<td width="200">
						<input name="userPw" type="password" size="20"> 
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="right">
				<button id="submit" type="submit">login</button>
				<!-- <input type="submit" id="submit" value="login"> -->
			</td>
		</tr>
	</table>
</form>	
</body>
</html>