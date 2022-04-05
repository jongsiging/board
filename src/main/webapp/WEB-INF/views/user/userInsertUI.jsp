<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user Insert</title>
</head>
<script type="text/javascript">

//특수문자 정규식 변수(공백 미포함) 
var replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>0-9\/.\`:\"\\,\[\]?|{}]/gi;
var replaceChar1 = /[~!@\#$%^&*\()\-=+_'\;<>\/.\`:\"\\,\[\]?|{}]/gi;

// 완성형 아닌 한글 정규식 
var replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;
var replaceFullKorean = /[가-힣]/;

var replaceEng = /[a-zA-Z0-9]/gi;
var replaceEng1 = /[a-zA-Z]/gi;

var regPostNo = /^\d{3}-?\d{3}$/u;
var hyphen = /-/;

	$j(document).ready(function(){
		
		$j("#userName").on("focusout", function() { 
			var x = $j(this).val();
			if (x.length > 0) {
				if (x.match(replaceChar) || x.match(replaceNotFullKorean)) { 
					x = x.replace(replaceChar, "").replace(replaceNotFullKorean, ""); 
				} 
				$j(this).val(x); 
			} 
		}).on("keyup", function() { 
			$j(this).val($j(this).val().replace(replaceChar, "")); 
			$j(this).val($j(this).val().replace(replaceEng, "")); 
		});
		
		$j("#userId").on("keyup", function() { 
			$j(this).val($j(this).val().replace(replaceChar1, "")); 
			$j(this).val($j(this).val().replace(replaceNotFullKorean, ""));
		});
		
		$j("#userPw").on("keyup", function() { 
			$j(this).val($j(this).val().replace(replaceNotFullKorean, ""));
		});
		
		$j("#userPw1").on("keyup", function() { 
			$j(this).val($j(this).val().replace(replaceNotFullKorean, ""));
		});
		
		$j("#userAddr1").on("keyup", function() { 
			$j(this).val($j(this).val().replace(replaceEng1, "")); 
			$j(this).val($j(this).val().replace(replaceChar1, "")); 
			$j(this).val($j(this).val().replace(replaceNotFullKorean, ""));
			$j(this).val($j(this).val().replace(/^(\d{3})(\d{3})$/, `$1-$2`));
		}).on("focusout", function() {
			if(!hyphen.test($j(this).val()) && $j(this).val().length > 6){
				var a = $j(this).val().substr(0,3);
				var b = "-";
				var c = $j(this).val().substr(3,3);
				$j(this).val(a+b+c);
			}
		});

		$j("#userAddr2").on("focusout", function() { 
			var x = $j(this).val();
			if (x.length > 0) {
				if (x.match(replaceChar1) || x.match(replaceNotFullKorean)) { 
					x = x.replace(replaceChar1, "").replace(replaceNotFullKorean, ""); 
				} 
				$j(this).val(x); 
			} 
		}).on("keyup", function() { 
			$j(this).val($j(this).val().replace(replaceChar1, "")); 
			$j(this).val($j(this).val().replace(replaceEng1, "")); 
		});
		
		$j.getJSON("/user/userPhone.do", function(data) {
			for(var i=0; i<data.length; i++){
				var msg = "<option value="+data[i].codeName+">"+data[i].codeName+"</option>";
				$j(".phone").append(msg);
			}
		});
		
		var idCheckResult = "";
		
		var userId = "";
		
		$j("#idCheck").on("click",function(){
			
			userId = $j("input[name='userId']").val();
			
			if(userId == ""){
				alert("id를 입력하세요.");
				$j("input[name='userId']").focus();
				return false;
			};
			
			$j.getJSON("/user/"+userId+"/idCheck.do", function(data) {
				
				if(data == 0){
					$j("#idCheckResult").val(userId);
				}
				idCheckResult = $j("#idCheckResult").val();
				if(data > 0){
					alert("중복된 아이디 입니다.");
				}else{
					alert("사용 가능한 아이디 입니다.");
				}
			});
		});
		
		$j("#submit").on("click",function(){
			var rtn = false;
			userId = $j("input[name='userId']").val();
			var userPw = $j("#userPw").val();
			var userPwLength = $j("#userPw").val().length;
			var userPw1 = $j("#userPw1").val();
			var userPw1Length = $j("#userPw1").val().length;
			var userName = $j("input[name='userName']").val();
			var userPhone2 = $j("input[name='userPhone2']").val();
			var userPhone2Length = $j("input[name='userPhone2']").val().length;
			var userPhone3 = $j("input[name='userPhone3']").val();
			var userPhone3Length = $j("input[name='userPhone3']").val().length;
			var userAddr1 = $j("input[name='userAddr1']").val();
			var userAddr2 = $j("input[name='userAddr2']").val();
			//var userCompany = $j("input[name='userCompany']").val();
			
			if(userId == ""){
				alert("id를 입력하세요.");
				$j("input[name='userId']").focus();
				return false;
			};
			
			if(idCheckResult == ""){
				alert("idCheck를 하세요.");
				return false;
			}
			
			if(userId != idCheckResult){
				alert("idCheck를 하세요.");
				return false;
			}
			
			if(userPw == ""){
				alert("pw를 입력하세요.");
				$j("#userPw").focus();
				return false;
			};
			
			if(userPwLength < 6){
				alert("pw를 6글자 이상 입력하세요.");
				$j("#userPw").focus();
				return false;
			};
			
			if(userPw1 == ""){
				alert("pw check를 입력하세요.");
				$j("#userPw1").focus();
				return false;
			};
			
			if(userPw1Length < 6){
				alert("pw를 6글자 이상 입력하세요.");
				$j("#userPw1").focus();
				return false;
			};
			
			if(userName == ""){
				alert("name를 입력하세요.");
				$j("input[name='userName']").focus();
				return false;
			};
			
			if(userPhone2 == ""){
				alert("Phone을 입력하세요.");
				$j("input[name='userPhone2']").focus();
				return false;
			};
			
			if(userPhone2Length != 4){
				alert("Phone을 네자리 입력하세요.");
				$j("input[name='userPhone2']").focus();
				return false;
			};
			
			if(userPhone3 == ""){
				alert("Phone을 입력하세요.");
				$j("input[name='userPhone3']").focus();
				return false;
			};
			
			if(userPhone3Length != 4){
				alert("Phone을 네자리 입력하세요.");
				$j("input[name='userPhone3']").focus();
				return false;
			};
			
			if(!regPostNo.test(userAddr1)){
				if(userAddr1 == ""){
					rtn = true;
				}else{
					alert("postNo을 형식에 맞게 입력하세요(xxx-xxx).");
					$j("input[name='userAddr1']").focus();
					rtn = false;
				}
				
			}else{
				rtn = true;
			};
			
			if(rtn == false){
				return rtn;
			}
			
			if(!replaceFullKorean.test(userAddr2)){
				if(userAddr2 == ""){
					rtn = true;
				}else{
					alert("address를 제대로 입력하세요.");
					$j("input[name='userAddr2']").focus();
					rtn = false;
				}
			}else{
				rtn = true;
			};
			
			if(rtn == false){
				return rtn;
			};
			
			if(userPw != userPw1){
				alert("비밀번호가 같지 읺습니다.");
				$j("#userPw1").focus();
				return false;
			};
			
			/* if(userAddr1 == ""){
				alert("postNo를 입력하세요.");
				$j("input[name='userAddr1']").focus();
				return false;
			};
			
			if(userAddr2 == ""){
				alert("address를 입력하세요.");
				$j("input[name='userAddr2']").focus();
				return false;
			};
			
			if(userCompany == ""){
				alert("userCompany를 입력하세요.");
				$j("input[name='userCompany']").focus();
				return false;
			}; */
			
			var $frm = $j('.userInsert :input');
			var param = $frm.serialize();
			
			$j.ajax({
			    url : "/user/userInsert.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    async: false,
			    success: function(data, textStatus, jqXHR)
			    {
					alert("작성완료");
					
					alert("메세지:"+data.success);
					
					rtn = true;
					console.log(rtn);
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    	rtn = false;
			    }
			});
			
			console.log(rtn);
			if(rtn == true){
				console.log(rtn);
				location.href = "/board/boardList.do?pageNo=1";
				return rtn;
			};
		});
	});
	

</script>
<body>
<form class="userInsert">
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
						<td width="400">
							<input id="userId" name="userId" type="text" size="12" maxlength="10">
							<input id="idCheck" name="idCheck" type="button" value="idCheck"> 
							<input id="idCheckResult" type="hidden">
						</td>
					</tr>
					<tr>
						<td align="center">
						pw
						</td>
						<td>
							<input id="userPw" name="userPw" type="password" size="12" minlength="6" maxlength="12">
						</td>
					</tr>
					<tr>
						<td align="center">
						pw check
						</td>
						<td>
							<input id="userPw1" name="userPw1" type="password" size="12" minlength="6" maxlength="12">
						</td>
					</tr>
					<tr>
						<td align="center">
						name
						</td>
						<td>
							<input id="userName" name="userName" type="text" size="12" maxlength="4">
						</td>
					</tr>
					<tr>
						<td align="center">
						phone
						</td>
						<td>
							<select class="phone" name="userPhone1">
							</select>-
							<input name="userPhone2" type="text" size="4" maxlength="4" oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');">-
							<input name="userPhone3" type="text" size="4" maxlength="4" oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');">
						</td>
					</tr>
					<tr>
						<td align="center">
						postNo
						</td>
						<td>
							<input id="userAddr1" name="userAddr1" type="text" size="10" maxlength="7">
						</td>
					</tr>
					<tr>
						<td align="center">
						address
						</td>
						<td>
							<input id="userAddr2" name="userAddr2" type="text" size="20" maxlength="20">
						</td>
					</tr>
					<tr>
						<td align="center">
						company
						</td>
						<td>
							<input name="userCompany" type="text" size="10" maxlength="10">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="right">
				<input id="submit" type="button" value="join">
				
			</td>
		</tr>
	</table>
</form>	
</body>
</html>