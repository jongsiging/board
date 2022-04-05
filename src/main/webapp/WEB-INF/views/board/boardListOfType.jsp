<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
		
		$j.getJSON("/board/boardType.do", function(data) {
			for(var i=0; i<data.length; i++){
				var msg = "<input class='normal' type='checkbox' name='boardType' value="+data[i].codeId+">"+data[i].codeName;
				$j(".checkbox").append(msg);
			}
		});

		$j("#search").on("click",function(){
			var chkArray = new Array();
			$j('input:checkbox[name=boardType]:checked').each(function() {
	            chkArray.push(this.value);
	        });
	        $j('#hiddenValue').val(chkArray);
	        var hiddenValue = $j('#hiddenValue').val()
	        var length = chkArray.length
	        
			var boardType =$j("input:checkbox[name=boardType]:checked").val();
			//return false;
			if($j("input:checkbox[name=boardType]").is(":checked") == true){
				if(boardType == "" || boardType == null){
					location.href = "/board/boardList.do?pageNo=1";
				}else{
					location.href = "/board/boardListOfType.do?pageNo=1&hiddenValue="+hiddenValue;
				}
			}else {
				alert("옵션을 선택하세요.")
			}
			
		});
		$j(".checkbox").on("click", "#check_all", function () {
			  var checked = $j(this).is(":checked");
				
			  if(checked){
			  	$j(this).parents(".checkbox").find('input').prop("checked", true);
			  } else {
			  	$j(this).parents(".checkbox").find('input').prop("checked", false);
			  }
			});
		
		$j(".checkbox").on("click", ".normal", function() {
			  var checked = $j(this).is(":checked");

			  if (!checked) {
			  	$j("#check_all").prop("checked", false);
			  }
			});
	});

</script>
<body>
<table  align="center">
	<tr>
		<td align="left">
			<a href="/user/loginUI.do">login</a>
			<a href ="/user/userInsertUI.do">join</a>
		</td>
		<td align="right">
			total : ${totalCntOfType}
		</td>
	</tr>
	<tr>
		<td>
			<table id="boardTable" border = "1">
				<tr>
					<td width="80" align="center">
						Type
					</td>
					<td width="40" align="center">
						No
					</td>
					<td width="300" align="center">
						Title
					</td>
				</tr>
				<c:forEach items="${boardList}" var="list">
					<tr>
						<td align="center">
							${list.codeName}
						</td>
						<td>
							${list.boardNum}
						</td>
						<td>
							<a href = "/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}">${list.boardTitle}</a>
						</td>
					</tr>	
				</c:forEach>
			</table>
		</td>
	</tr>
	<tr>
		<td align="right">
			<a href ="/board/boardWrite.do">글쓰기</a>
		</td>
	</tr>
	<tr>
		<td class="checkbox" align="left">
			<input type="checkbox" id="check_all" name="boardType" value="">전체
			<input type="hidden" id="hiddenValue" name="hiddenValue" value="">
		</td>
		<td>
		<input type='button' id='search' value='조회'>
		</td>
	</tr>
	<tr>
		<td align="right">
			<c:forEach begin="1" end="${totalpageOfType}" var="i">
				<a href="/board/boardListOfType.do?pageNo=${i}&hiddenValue=${hiddenValue}">${i}</a>
			</c:forEach>
		</td>
	</tr>
</table>	
</body>
</html>