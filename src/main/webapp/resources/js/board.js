/**
 * 
 */
function a(){
	var msg =`
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
	`;
	return msg;
}
function b(codeName,boardNum,boardType,pageNo,boardTitle){
	var msg =`
					<tr>
						<td align="center">
							${codeName}
						</td>
						<td>
							${boardNum}
						</td>
						<td>
							<a href = "/board/${boardType}/${boardNum}/boardView.do?pageNo=${pageNo}">${boardTitle}</a>
						</td>
					</tr>	
	`;
	return msg;
}
function add(){
	var msg =`
					<tr>
						<td width="120" align="center">
						Type
						</td>
						<td width="400">
						<select class="boardType" name="boardType">
						</select>
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
						Title
						</td>
						<td width="300">
						<input class="boardTitle" name="boardTitle" type="text" size="30"> 
						<button class="remove">remove</button>
						</td>
					</tr>
					<tr>
						<td height="300" align="center">
						Comment
						</td>
						<td valign="top">
						<textarea class="boardComment" name="boardComment"  rows="20" cols="55"></textarea>
						</td>
					</tr>
	`;
	return msg;
}

function page(totalpage,hiddenValue){
	var msg =`
			<c:forEach begin="1" end="${totalpage}" var="i">
				<a href="/board/boardListOfType.do?pageNo=i&hiddenValue=${hiddenValue}">${i}</a>
			</c:forEach>
	`;
	return msg;
}
