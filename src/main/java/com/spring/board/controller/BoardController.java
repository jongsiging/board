package com.spring.board.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVO;
import com.spring.board.vo.PageVo;
import com.spring.common.CommonUtil;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	public String boardList(Locale locale, Model model,PageVo pageVo) throws Exception{
		
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		
		int page = 1;
		int totalCnt = 0;
		int totalpage = 0;
		
		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);
		}
		
		boardList = boardService.SelectBoardList(pageVo);
		totalCnt = boardService.selectBoardCnt();
		
		if(totalCnt%10 == 0) {
			totalpage = totalCnt/10;
		}else {
			totalpage = (totalCnt/10)+1;
		}
		
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageNo", page);
		model.addAttribute("totalpage", totalpage);
		
		return "board/boardList";
	}
	
//	@RequestMapping(value = "/board/boardListOfType.do", method = RequestMethod.GET)
//	public String boardListOfType(Locale locale
//			,Model model,int pageNo,String[] hiddenValue) throws Exception{
//		
//		List<BoardVo> boardList = new ArrayList<BoardVo>();
//		
//		int page = 1;
//		int totalCntOfType = 0;
//		int totalpageOfType = 0;
//		String type = "";
//		
//		if(pageNo == 0){
//			pageNo = page;
//		}
//		for(int i=0; i<hiddenValue.length; i++) {
//			List<BoardVo> list = boardService.SelectBoardListOfType(pageNo,hiddenValue[i]);
//			for(int j=0; j<list.size(); j++) {
//				boardList.add(list.get(j));
//			}
//			totalCntOfType += boardService.selectBoardCntOfType(hiddenValue[i]);
//			type += hiddenValue[i];
//			if(i < hiddenValue.length-1) {
//				type += ",";
//			}
//		}
//		
//		if(totalCntOfType%10 == 0) {
//			totalpageOfType = totalCntOfType/10;
//		}else {
//			totalpageOfType = (totalCntOfType/10)+1;
//		}
//		
//		
//		model.addAttribute("boardList", boardList);
//		model.addAttribute("totalCntOfType", totalCntOfType);
//		model.addAttribute("pageNo", page);
//		model.addAttribute("totalpageOfType", totalpageOfType);
//		model.addAttribute("hiddenValue", type);
	
//		return "board/boardListOfType";
//	}
	
	@RequestMapping(value = "/board/boardListOfType.do", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> boardListOfType(Locale locale
			,Model model,int pageNo,String[] hiddenValue) throws Exception{
		ResponseEntity<Map<String, Object>> entity = null;

		try {
			List<BoardVo> boardList = new ArrayList<BoardVo>();
			Map<String, Object> map = new HashMap<String, Object>();
			
			int page = 1;
			int totalCntOfType = 0;
			int totalpageOfType = 0;
			String type = "";
			
			if(pageNo == 0){
				pageNo = page;
			}
			map.put("pageNo", pageNo);
			map.put("hiddenValue", hiddenValue);
			
			boardList = boardService.SelectBoardListOfType(map);
			
			totalCntOfType += boardService.selectBoardCntOfType(hiddenValue);
			
//			for(int i=0; i<hiddenValue.length; i++) {
//				
//				List<BoardVo> list = boardService.SelectBoardListOfType(pageNo,hiddenValue[i]);
//				
//				for(int j=0; j<list.size(); j++) {
//					boardList.add(list.get(j));
//				}
//				
//				totalCntOfType += boardService.selectBoardCntOfType(hiddenValue[i]);
//				
//				type += hiddenValue[i];
//				if(i < hiddenValue.length-1) {
//					type += ",";
//				}
//			}
			if(totalCntOfType%10 == 0) {
				totalpageOfType = totalCntOfType/10;
			}else {
				totalpageOfType = (totalCntOfType/10)+1;
			}
			
			model.addAttribute("boardList", boardList);
			model.addAttribute("totalCnt", totalCntOfType);
			model.addAttribute("pageNo", page);
			model.addAttribute("totalpage", totalpageOfType);
			model.addAttribute("hiddenValue", type);

			map.put("boardList", boardList);
			map.put("totalCnt", totalCntOfType);
			map.put("pageNo", page);
			map.put("totalpage", totalpageOfType);
			map.put("hiddenValue", type);
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum, int pageNo) throws Exception{
		
		BoardVo boardVo = new BoardVo();
		
		
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardView";
	}
	
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model) throws Exception{
		
		BoardVo vo = new BoardVo();
		
		List<String> boardName = new ArrayList<String>();
		
		for(Field field :vo.getClass().getDeclaredFields()){
            field.setAccessible(true);
            String name = field.getName();
            boardName.add(name);
        };
        
        model.addAttribute("boardName", boardName);
		
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale,
			BoardVo boardVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		int resultCnt = 0;
		
		resultCnt = boardService.boardInsert(boardVo);
		
//		String[] boardTypeArr = boardVo.getBoardType().split(",");
//		String[] boardTitleArr = boardVo.getBoardTitle().split(",");
//		String[] boardCommentArr = boardVo.getBoardComment().split(",");
//
//		for(int i=0; i<boardTypeArr.length; i++) {
//			boardVo.setBoardType(boardTypeArr[i]);
//			boardVo.setBoardTitle(boardTitleArr[i]);
//			boardVo.setBoardComment(boardCommentArr[i]);
//			
//			resultCnt += boardService.boardInsert(boardVo);
//		}
		
//		for(int i=0;i<param.();i++) {
//			resultCnt += boardService.boardInsert(param.get(i));
//		}
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		
		return callbackMsg;
	}
	
//	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
//	@ResponseBody
//	public String boardWriteAction(Locale locale,String[] boardTypeList,
//			String[] boardTitleList,
//			String[] boardCommentList ) throws Exception{
//		
//		HashMap<String, String> result = new HashMap<String, String>();
//		CommonUtil commonUtil = new CommonUtil();
//		BoardVo boardVo = new BoardVo();
//		int resultCnt = 0;
//		
//		if(boardTitleList.length == boardCommentList.length) {
//			for(int i=0; i<boardTitleList.length; i++) {
//				boardVo.setBoardType(boardTypeList[i]);
//				boardVo.setBoardTitle(boardTitleList[i]);
//				boardVo.setBoardComment(boardCommentList[i]);
//				resultCnt += boardService.boardInsert(boardVo);
//			}
//		}
//		
//		result.put("success", (resultCnt > boardTitleList.length)?"Y":"N");
//		
//		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
//		
//		System.out.println("callbackMsg::"+callbackMsg);
//		
//		
//		return callbackMsg;
//	}
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardUpdateUI.do", method = RequestMethod.GET)
	public String updateui(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum, int pageNo) throws Exception{
		
		BoardVo boardVo = new BoardVo();
		
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardUpdateUI";
	}
	
	@RequestMapping(value = "/board/boardUpdate.do", method = RequestMethod.POST)
	public String update(BoardVo boardVo) throws Exception{

		
		boardService.update(boardVo);
		
		
		return "redirect:/board/boardList.do";
	}
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardDelete.do", method = RequestMethod.GET)
	public String delete(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{

		
		boardService.delete(boardType,boardNum);
		
		
		return "redirect:/board/boardList.do";
	}
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/deleteCheck.do", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Integer> deleteCheck(Model model, 
			@PathVariable("boardType") String boardType,
			@PathVariable("boardNum") int boardNum) throws Exception{
		ResponseEntity<Integer> entity = null;

		try {
			int data = boardService.deleteCheck(boardType,boardNum);
			model.addAttribute("data", data);

			entity = new ResponseEntity<Integer>(data, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	@RequestMapping(value = "/board/boardType.do", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ComCodeVO>> boardType(Model model) throws Exception{
		ResponseEntity<List<ComCodeVO>> entity = null;

		try {
			List<ComCodeVO> data = boardService.boardType();

			entity = new ResponseEntity<List<ComCodeVO>>(data, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<ComCodeVO>>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	@RequestMapping(value = "/board/excel.do", method = RequestMethod.GET)
	public void excel(HttpServletResponse response,
			int pageNo,
			String[] hiddenValue) throws Exception{
		
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("게시판");
		int rowNo = 0;
		
		Row headerRow = sheet.createRow(rowNo++);
	    headerRow.createCell(0).setCellValue("TYPE");
	    headerRow.createCell(1).setCellValue("NUM");
	    headerRow.createCell(2).setCellValue("TITLE");
	    headerRow.createCell(3).setCellValue("COMMENT");
	    headerRow.createCell(4).setCellValue("CNT");
		
	    List<BoardVo> boardList = new ArrayList<BoardVo>();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("pageNo", null);
		
		if(hiddenValue.length != 0) {
			map.put("hiddenValue", hiddenValue);
		}
		
		boardList = boardService.SelectBoardListOfType(map);
		
		for(BoardVo board : boardList) {
			Row row = sheet.createRow(rowNo++);
            row.createCell(0).setCellValue(board.getBoardType());
            row.createCell(1).setCellValue(board.getBoardNum());
            row.createCell(2).setCellValue(board.getBoardTitle());
            row.createCell(3).setCellValue(board.getBoardComment());
            row.createCell(4).setCellValue(board.getTotalCnt());
		}
		
		response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=boardlist.xls");
 
        workbook.write(response.getOutputStream());
        workbook.close();
	}
	
	@RequestMapping(value = "/board/calendarUI.do", method = RequestMethod.GET)
	public String calendarUI(Locale locale, Model model) throws Exception{
		
		
		return "board/calendarUI";
	}
	
	@RequestMapping(value="/board/calendar.do")
	public void calendar (HttpServletResponse response, HttpServletRequest request) throws Exception{
		String req_month = request.getParameter("month");
		String req_year = request.getParameter("year");

		Calendar calendar = Calendar.getInstance();
		
		//월 구하기 (-1)
		System.out.println("선택 월 : " + req_month);

		int month=0;
		month= Integer.parseInt(req_month)-1;
		

		
		//년도 구하기
		String year = "";
		
		year=req_year;
		
		
		System.out.println("선택 년도 : " + year);

		//1일의 요일 구하기
		calendar.set(2020,month,1);
		int day1=calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println("1일의 요일 : " + day1);
		
		//일 수 구하기
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("일 수 : " + days);
		
		//주 수 구하기
		int weeks = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
		System.out.println("주 수 : " + weeks);
		
	
		//표시용 월
		month =  month+1;
		System.out.println("월 : " + month);
		

		
		//파일 읽기
		FileInputStream fis = new FileInputStream("C:\\sample.xlsx");
		//워크북 생성
		Workbook wb = new XSSFWorkbook(fis);
		//시트 읽기
		Sheet sheet = wb.getSheetAt(0);
		
		//행의 수 읽기
		int rows =sheet.getPhysicalNumberOfRows();
		
		//열의 수 읽기
		Row row = sheet.getRow(0);
		int cells = row.getPhysicalNumberOfCells();
		Cell cell=null;
		
		//시트 생성 
		Sheet sheet1 = wb.createSheet("calender");

		//숫자 쓸 폰트 지정
		Font font = wb.createFont();
		Font fontRed = wb.createFont();
		Font fontBlue = wb.createFont();
		font.setColor(HSSFColorPredefined.BLACK.getIndex());
		fontRed.setColor(HSSFColorPredefined.RED.getIndex());
		fontBlue.setColor(HSSFColorPredefined.BLUE.getIndex());
		
		//요일 헤더 스타일 지정
		CellStyle dayStyle = wb.createCellStyle();
			//데이터 가운데 정렬
		dayStyle.setAlignment(HorizontalAlignment.CENTER);
			//경계선
		dayStyle.setBorderTop(BorderStyle.THIN);
		dayStyle.setBorderBottom(BorderStyle.THIN);
			// 배경색은 노란색
		dayStyle.setFillForegroundColor(HSSFColorPredefined.LIGHT_YELLOW.getIndex());
		dayStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		Row calendarRow=null;
		Cell calendarCell=null;
		
		int rowindex=0;
		int columnindex=0;
		
		
		
		//월 표시
		calendarRow = sheet1.createRow(rowindex);
		calendarCell  = calendarRow.createCell(0);
		System.out.println("표시 직전 월 : " + month);
		calendarCell.setCellValue(month);
		calendarCell  = calendarRow.createCell(1);
		calendarCell.setCellValue("월");
		rowindex++;//rowindex=1
		
		
		//요일 행 만들기
		calendarRow = sheet1.createRow(rowindex);
		String[] daystring= {"일","월","화","수","목","금","토"};
		int dayorder=0;
		
		for(int i=0;i<7*cells;i++) {
		calendarCell = calendarRow.createCell(i);
		calendarCell.setCellStyle(dayStyle);
		
		if(i%cells==1) {
			calendarCell.setCellValue(daystring[dayorder]);
			dayorder++;
		}
		
		} rowindex++; //rowindex==2;
					
		
		//셀 스타일 읽어와서 담기
		CellStyle[][] bodystyle = new CellStyle[rows][cells];
		for(int rownum=0;rownum<rows;rownum++) {
			row = sheet.getRow(rownum);
			for(int cellnum=0;cellnum<cells;cellnum++) {
				cell=row.getCell(cellnum);
				bodystyle[rownum][cellnum]=cell.getCellStyle();
				if(rownum==0) {
					bodystyle[rownum][cellnum].setFont(font);
				}
			}
		}
		CellStyle[][] Rbodystyle = new CellStyle[rows][cells];
		for(int rownum=0;rownum<rows;rownum++) {
			row = sheet.getRow(rownum);
			for(int cellnum=0;cellnum<cells;cellnum++) {
				cell=row.getCell(cellnum);
				Rbodystyle[rownum][cellnum]=cell.getCellStyle();
				if(rownum==0) {
					Rbodystyle[rownum][cellnum].setFont(fontRed);
				}
			}
		}
		CellStyle[][] Bbodystyle = new CellStyle[rows][cells];
		for(int rownum=0;rownum<rows;rownum++) {
			row = sheet.getRow(rownum);
			for(int cellnum=0;cellnum<cells;cellnum++) {
				cell=row.getCell(cellnum);
				Bbodystyle[rownum][cellnum]=cell.getCellStyle();
				if(rownum==0) {
					bodystyle[rownum][cellnum].setFont(fontBlue);
				}
			}
		}
		
		//월앞에 0붙이기
		String Month=Integer.toString(month);
		if(month>=1 && month<=9) {
			Month = "0"+Integer.toString(month);
		}
		
		//년도 두자리 자르기
		String Year = year.substring(2,4);		
		
		String create_time="";		
		String Dayofmonth="";
		
		//달력 표 만들기
		int AbsoluteRowNum = 2;
		int dayofmonth=1;
		int createDay=1;
	
		for(int weekrow=0;weekrow<weeks;weekrow++) { //n개 주를 만들때까지 반복 , weeks = 그달의 주 수 

			for(int rownum=0;rownum<rows;rownum++) {
				calendarRow = sheet1.createRow(AbsoluteRowNum++);//행 만들기 
				
				
				for(int daynum=0;daynum<7;daynum++) {
					for(int cellindex=0;cellindex<cells;cellindex++) {
						//셀 만들기
						calendarCell = calendarRow.createCell(cells*daynum+cellindex);
						calendarCell.setCellStyle(bodystyle[rownum][cellindex]);
						
						//셀값설정 : 숫자 2~막날
						if(rownum==0 && cellindex==1 && dayofmonth<=days && dayofmonth>=2) {
							calendarCell.setCellValue(dayofmonth);
							dayofmonth++;
						}
						//셀값설정 : 숫자 1		
						if(weekrow==0 && rownum ==0 && day1 == daynum+1 && cellindex==1) {
							calendarCell.setCellValue(dayofmonth);
							dayofmonth++;
							
						}

						if(daynum == 0) {
							Rbodystyle[rownum][cellindex].setFont(fontRed);
							calendarCell.setCellStyle(Rbodystyle[rownum][cellindex]);
						}
						if(daynum == 6) {
							Bbodystyle[rownum][cellindex].setFont(fontBlue);
							calendarCell.setCellStyle(Bbodystyle[rownum][cellindex]);
						}
						if(daynum>0 && daynum<6) {
							bodystyle[rownum][cellindex].setFont(font);
							calendarCell.setCellStyle(bodystyle[rownum][cellindex]);
						}
						
						//셀값 설정 : 글 갯수 (2일~ 막날)
						if(rownum==2 && cellindex==1 && createDay>=2 & createDay <=days) {
							if(createDay>=1&&createDay<=9) {Dayofmonth="0"+Integer.toString(createDay);}
							else {Dayofmonth = Integer.toString(createDay);}
							create_time=Year+"/"+Month + "/"+Dayofmonth;
							//calendarCell.setCellValue("글" + boardService.countByDate(create_time) + "개");	
							createDay++;
						}
		
						
						//셀값설정 : 글개수 입력 (1일)
						if(weekrow == 0 && rownum==2 && day1 == daynum+1 && cellindex==1 ) {
							if(createDay>=1&&createDay<=9) {Dayofmonth="0"+Integer.toString(createDay);}
							else {Dayofmonth = Integer.toString(createDay);}
							create_time= Year+"/"+Month + "/" +Dayofmonth;
							//calendarCell.setCellValue("글" + boardService.countByDate(create_time) + "개");	
							createDay++;
						}				
										
					}
				}				
			}
		}
			
		//컨텐츠 타입과 파일명 지정
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=calentdar_test.xlsx");

		//엑셀 출력
		wb.write(response.getOutputStream());
		wb.close();
		
			
		}
	
}
