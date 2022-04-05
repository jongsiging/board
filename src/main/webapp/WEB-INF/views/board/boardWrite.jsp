<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/resources/js/board.js?ver=3" type="text/javascript"></script>
<title>boardWrite</title>
</head>
<script type="text/javascript">

/* var boardName = "${boardName}";
var boardNameArr = [];
boardName = boardName.substr(1,boardName.length-2);
boardNameArr = boardName.split(", "); */

	$j(document).ready(function(){
		
		$j.getJSON("/board/boardType.do", function(data) {
			for(var i=0; i<data.length; i++){
				var msg = "<option value="+data[i].codeId+">"+data[i].codeName+"</option>";
				$j(".boardType").append(msg);
			}
		});
		
		$j("#add").on("click",function(){
			var msg = add();
			$j("#tbody").append(msg);
			$j.getJSON("/board/boardType.do", function(data) {
				$j(".boardType").children().remove();
				for(var i=0; i<data.length; i++){
					var msg = "<option value="+data[i].codeId+">"+data[i].codeName+"</option>";
					$j(".boardType").append(msg);
				}
			});
		});
		
		
		
		$j("#submit").on("click",function(){
			
		/*	var boardTitle = $j('input[name=boardTitle]').val();
			var boardComment = $j('textarea[name=boardComment]').val();
			
			if(boardTitle == ""){
				alert("제목을 입력하세요");
				return false;
			}
			
			if(boardComment == ""){
				alert("내용을 입력하세요");
				return false;
			} */
			
/* 			var boardTitleList = new Array();
			var boardCommentList = new Array();
			var boardTypeList = new Array();
			
			$j('input[name=boardTitle]').each(function (i) {
				var boardTitle = $j("input[name='boardTitle']").eq(i).val();
				
				if(boardTitle == ""){
					alert("제목을 입력하세요");
					return false;
				}
				
				boardTitleList.push(boardTitle);
		    });
			
			$j('textarea[name=boardComment]').each(function (i) {
				var boardComment = $j("textarea[name='boardComment']").eq(i).val();
				
				if(boardComment == ""){
					alert("내용을 입력하세요");
					return false;
				}
				
				boardCommentList.push(boardComment);
		    });
			
			$j('.boardType').each(function (i) {
				var boardType = $j(".boardType").eq(i).val();
				boardTypeList.push(boardType);
		    });
			
			$j('#boardTitleList').val(boardTitleList);
			var boardTitleList = $j('#boardTitleList').val();
			
			$j('#boardCommentList').val(boardCommentList);
			var boardCommentList = $j('#boardCommentList').val();

			$j('#boardTypeList').val(boardTypeList);
			var boardTypeList = $j('#boardTypeList').val();
			
			$j.ajax({
				type : "POST",
				url : "/board/boardWriteAction.do",
				dataType : "text",
				data : {
					"boardTypeList" : boardTypeList,
					"boardTitleList" : boardTitleList,
					"boardCommentList" : boardCommentList
				},
				success: function(data, textStatus, jqXHR)
			    {
					alert("작성완료");
					
					//alert("메세지:"+data.success);
					
					location.href = "/board/boardList.do?pageNo=1";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			
			}); */
			var rtn = false;
			
			$j('input[name=boardTitle]').each(function (i) {
				var boardTitle = $j("input[name='boardTitle']").eq(i).val();
				
				if(boardTitle == ""){
					alert("제목을 입력하세요");
					rtn = false;
				}else{
					rtn = true;
				}
				
		    });
			if(rtn == false){
				return rtn;
			}
			$j('textarea[name=boardComment]').each(function (i) {
				var boardComment = $j("textarea[name='boardComment']").eq(i).val();
				
				if(boardComment == ""){
					alert("내용을 입력하세요");
					rtn = false;
				}else{
					rtn = true;
				}
				
		    });
			
			if(rtn == false){
				return rtn;
			}
			
			 /* for(var i=0; i<$j('.boardType').length; i++){
				$j('.boardType').eq(i).attr("name", "boardVoList["+i+"].boardType");
				$j('.boardTitle').eq(i).attr("name", "boardVoList["+i+"].boardTitle");
				$j('.boardComment').eq(i).attr("name", "boardVoList["+i+"].boardComment");
			}  */
			
			/* for(var i=0; i<boardNameArr.length; i++){
				var boardName = boardNameArr[i];
				for(var l=0; l<$j('.boardType').length; l++){
					$j("."+boardName).eq(l).attr("name", "boardVoList["+l+"]."+boardName);
				}
			} */
			
			var boardTypeLength = $j('.boardType').length;
			var boardInputLength = 0; 
			var k = 0;
			var l = 0;
			
			for(var i=0; i<$j("#boardTable :input").length; i++){
				if(typeof $j("#boardTable :input").eq(i).attr('name') != "undefined"){
					boardInputLength++;
				}
			}
			
			var boardLength = boardInputLength/boardTypeLength;
			
			for(var i=0; i<$j("#boardTable :input").length; i++){
				
				if(typeof $j("#boardTable :input").eq(i).attr('name') != "undefined"){
					
					l++;
					
					var name = $j("#boardTable :input").eq(i).attr("name");
					
					$j("#boardTable :input").eq(i).attr("name","boardVoList["+k+"]."+name);
					
					if(l%boardLength == 0){
						
						k++;
					}
				}
			}
			
			
			var $frm = $j('.boardWrite :input');
			var param = $frm.serialize();
			
			$j.ajax({
			    url : "/board/boardWriteAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    async: false,
			    success: function(data, textStatus, jqXHR)
			    {
					alert("작성완료");
					
					alert("메세지:"+data.success);
					
					rtn = true;
					
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    	rtn = false;
			    }
			});
			
			if(rtn == true){
				location.href = "/board/boardList.do?pageNo=1";
				return rtn;
			}
			
		});
	});
	

	$j(document).on("click",".remove",function(){
		
			$j(this).parent().parent().prev().remove();
			$j(this).parent().parent().next().remove();
			$j(this).parent().parent().remove();
			
	});
		
	
	
</script>
<body>
<form class="boardWrite">
	<table align="center">
		<tr>
			<td align="right">
			<input id="submit" type="button" value="작성">
			<input id="add" type="button" value="행추가">
			</td>
		</tr>
		<tr>
			<td>
				<table border ="1" id="boardTable"> 
					<tr>
						<td width="120" align="center">
						Type
						</td>
						<td width="400">
<!-- 						<select class="boardType" name="boardVoList[0].boardType"> -->
						<select class="boardType" name="boardType">
						</select>
						<!-- <input id="boardTypeList" type="hidden" value=""> -->
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
						Title
						</td>
						<td width="400">
<%-- 						<input class="boardTitle" name="boardVoList[0].boardTitle" type="text" size="50" value="${board.boardTitle}">  --%>
						<input class="boardTitle" name="boardTitle" type="text" size="50" value="${board.boardTitle}"> 
						<!-- <input id="boardTitleList" type="hidden" value=""> -->
						</td>
					</tr>
					<tr>
						<td height="300" align="center">
						Comment
						</td>
						<td valign="top">
<%-- 						<textarea class="boardComment" name="boardVoList[0].boardComment"  rows="20" cols="55">${board.boardComment}</textarea> --%>
						<textarea class="boardComment" name="boardComment"  rows="20" cols="55">${board.boardComment}</textarea>
						<!-- <input id="boardCommentList" type="hidden" value=""> -->
						</td>
					</tr>
					
					<tbody id="tbody">
						
					</tbody>
					
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
				<a href="/board/boardList.do?pageNo=1">List</a>
			</td>
		</tr>
	</table>
</form>	
</body>
</html>