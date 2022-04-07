<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/resources/js/board.js?ver=1" type="text/javascript"></script>
<title>list</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
		
		//체크 박스 불러오기
		$j.getJSON("/board/boardType.do", function(data) {
			for(var i=0; i<data.length; i++){
				var msg = "<input class='normal' type='checkbox' name='boardType' value="+data[i].codeId+">"+data[i].codeName;
				$j(".checkbox").append(msg);
			}
		});
		
		//엑셀 출력
		$j("#excel").on("click",function(){
			var chkArray = new Array();
			$j('input:checkbox[name=boardType]:checked').each(function() {
	            chkArray.push(this.value);
	        });
			
	        $j('#hiddenValue').val(chkArray);
	        var hiddenValue = $j('#hiddenValue').val();
	        
	       	location.href = "/board/excel.do?pageNo=1&hiddenValue="+hiddenValue;
		});
		
		//키워드 검색
		$j("#searchButton").on("click",function(){
			
			var searchInput = $j('#searchInput').val();
			
			if(searchInput == "" || searchInput == null){
				alert("키워드를 입력하세요.")
				return false;
			};
			
			$j.ajax({
				type : "get",
				url : "/board/boardListOfKeyword.do",
				dataType : "text",
				data : {
					"pageNo" : 1,
					"searchInput" : searchInput
				},
				success : function(data){
					$j("#boardTable").html("");
					data = JSON.parse(data);
					
					var msg1 = a();
					$j("#boardTable").append(msg1);
					for(var i=0;i<data.boardList.length; i++){
						var codeName = data.boardList[i].codeName;
						var boardNum = data.boardList[i].boardNum;
						var boardType = data.boardList[i].boardType;
						var pageNo = data.pageNo;
						var boardTitle = data.boardList[i].boardTitle;
						var msg2 = b(codeName,boardNum,boardType,pageNo,boardTitle);
						$j("#boardTable").append(msg2);
					}
					
					$j("#totalpage").html("");
					var totalpage = data.totalpage;
					for(var i=1; i<totalpage+1; i++){
						var msg1 = "<a class='pageOfKeyword' href='#'>"+i+"</a> "
						$j("#totalpage").append(msg1);
					}
					
					
					$j("#totalCnt").html("");
					var totalCnt = data.totalCnt;
					var msg2 = "total : "+totalCnt;
					$j("#totalCnt").append(msg2);
				}
				
			});
			
		});
		
		// 체크박스 조회
		$j("#search").on("click",function(){
			var chkArray = new Array();
			$j('input:checkbox[name=boardType]:checked').each(function() {
	            chkArray.push(this.value);
	        });
	        $j('#hiddenValue').val(chkArray);
	        var hiddenValue = $j('#hiddenValue').val()
	        var length = chkArray.length
	        
			var boardType =$j("input:checkbox[name=boardType]:checked").val();
	        
			if($j("input:checkbox[name=boardType]").is(":checked") == true){
				if(boardType == "" || boardType == null){
					location.href = "/board/boardList.do?pageNo=1";
				}else{
					$j.ajax({
						type : "get",
						url : "/board/boardListOfType.do",
						dataType : "text",
						data : {
							"pageNo" : 1,
							"hiddenValue" : hiddenValue
						},
						success : function(data){
							$j("#boardTable").html("");
							data = JSON.parse(data);
							
							var msg1 = a();
							$j("#boardTable").append(msg1);
							for(var i=0;i<data.boardList.length; i++){
								var codeName = data.boardList[i].codeName;
								var boardNum = data.boardList[i].boardNum;
								var boardType = data.boardList[i].boardType;
								var pageNo = data.pageNo;
								var boardTitle = data.boardList[i].boardTitle;
								var msg2 = b(codeName,boardNum,boardType,pageNo,boardTitle);
								$j("#boardTable").append(msg2);
							}
							
							$j("#totalpage").html("");
							var totalpage = data.totalpage;
							for(var i=1; i<totalpage+1; i++){
								var msg1 = "<a class='pageOfType' href='#'>"+i+"</a> "
								$j("#totalpage").append(msg1);
							}
							
							
							$j("#totalCnt").html("");
							var totalCnt = data.totalCnt;
							var msg2 = "total : "+totalCnt;
							$j("#totalCnt").append(msg2);
						}
						
					});
				}
			}else {
				alert("옵션을 선택하세요.")
			}
			
		});
		
		//체크박스 전체 선택시
		$j(".checkbox").on("click", "#check_all", function () {
			  var checked = $j(this).is(":checked");
				
			  if(checked){
			  	$j(this).parents(".checkbox").find('input').prop("checked", true);
			  } else {
			  	$j(this).parents(".checkbox").find('input').prop("checked", false);
			  }
			});
		
		//일반 체크박스 선택시
		$j(".checkbox").on("click", ".normal", function() {
			  var checked = $j(this).is(":checked");

			  if (!checked) {
			  	$j("#check_all").prop("checked", false);
			  }
		});
		
		// 체크박스 전부 선택시
		$j(".checkbox").on("click", function() { 
			   if($j('input:checkbox[name=boardType]:checked').length > 3){ 
			       $j('#check_all').prop("checked",true); 
			    }else{ 
			       $j('#check_all').prop("checked",false); 
			    } 
		});
	});
	
	//조회된 리스트에 페이지 선택 시 
	$j(document).on("click",".pageOfType",function(){
		
			var pageNo = $j(this).text();
			var chkArray = new Array();
			$j('input:checkbox[name=boardType]:checked').each(function() {
	            chkArray.push(this.value);
	        });
	        $j('#hiddenValue').val(chkArray);
	        var hiddenValue = $j('#hiddenValue').val()
	        var length = chkArray.length
	        
			var boardType =$j("input:checkbox[name=boardType]:checked").val();
	        
			if($j("input:checkbox[name=boardType]").is(":checked") == true){
				if(boardType == "" || boardType == null){
					location.href = "/board/boardList.do?pageNo=1";
				}else{
					
					$j.ajax({
						type : "get",
						url : "/board/boardListOfType.do",
						dataType : "text",
						data : {
							"pageNo" : pageNo,
							"hiddenValue" : hiddenValue
						},
						success : function(data){
							$j("#boardTable").html("");
							data = JSON.parse(data);
							
							var msg1 = a();
							$j("#boardTable").append(msg1);
							for(var i=0;i<data.boardList.length; i++){
								var codeName = data.boardList[i].codeName;
								var boardNum = data.boardList[i].boardNum;
								var boardType = data.boardList[i].boardType;
								var pageNo = data.pageNo;
								var boardTitle = data.boardList[i].boardTitle;
								var msg2 = b(codeName,boardNum,boardType,pageNo,boardTitle);
								$j("#boardTable").append(msg2);
							}
							
							$j("#totalpage").html("");
							var totalpage = data.totalpage;
							for(var i=1; i<totalpage+1; i++){
								var msg1 = "<a class='pageOfType' href='#'>"+i+"</a> "
								$j("#totalpage").append(msg1);
							}
							
							
							$j("#totalCnt").html("");
							var totalCnt = data.totalCnt;
							var msg2 = "total : "+totalCnt;
							$j("#totalCnt").append(msg2);
						}
					
					});
				}
			}
		
	});
	
	$j(document).on("click",".pageOfKeyword",function(){
		var pageNo = $j(this).text();
		console.log(pageNo)
		var searchInput = $j('#searchInput').val();
		console.log(searchInput)
		
		if(searchInput == "" || searchInput == null){
			alert("키워드를 입력하세요.")
			return false;
		};
		
		$j.ajax({
			type : "get",
			url : "/board/boardListOfKeyword.do",
			dataType : "text",
			data : {
				"pageNo" : pageNo,
				"searchInput" : searchInput
			},
			success : function(data){
				$j("#boardTable").html("");
				data = JSON.parse(data);
				
				var msg1 = a();
				$j("#boardTable").append(msg1);
				for(var i=0;i<data.boardList.length; i++){
					var codeName = data.boardList[i].codeName;
					var boardNum = data.boardList[i].boardNum;
					var boardType = data.boardList[i].boardType;
					var pageNo = data.pageNo;
					var boardTitle = data.boardList[i].boardTitle;
					var msg2 = b(codeName,boardNum,boardType,pageNo,boardTitle);
					$j("#boardTable").append(msg2);
				}
				
				$j("#totalpage").html("");
				var totalpage = data.totalpage;
				for(var i=1; i<totalpage+1; i++){
					var msg1 = "<a class='pageOfKeyword' href='#'>"+i+"</a> "
					$j("#totalpage").append(msg1);
				}
				
				
				$j("#totalCnt").html("");
				var totalCnt = data.totalCnt;
				var msg2 = "total : "+totalCnt;
				$j("#totalCnt").append(msg2);
			}
			
		});
		
	});
	
</script>
<body>

<table  align="center">
	<tr>
		<td align="left">
			<c:if test="${not empty login}">
				${login.userId}
			</c:if>
			<c:if test="${empty login}">
				<a href="/user/loginUI.do">login</a>
				<a href ="/user/userInsertUI.do">join</a>
			</c:if>
		</td>
		<td id="totalCnt" align="right">
			total : ${totalCnt}
		</td>
	</tr>
	<tr>
		<td align="right">
			<input type="text" name="searchInput" id='searchInput'>
			<input type='button' id='searchButton' value='조회'>
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
		<td align="left">
			<input id="excel" type="button" value="excel"> 
		</td>
		<td align="right">
			<a href ="/board/boardWrite.do">글쓰기</a>
			<c:if test="${not empty login}">
				<a href="/user/logout.do">로그아웃</a>
			</c:if>
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
		<td id="totalpage" align="right">
			<c:forEach begin="1" end="${totalpage}" var="i">
				<a href="/board/boardList.do?pageNo=${i}">${i}</a>
			</c:forEach>
		</td>
	</tr>
</table>

</body>
</html>