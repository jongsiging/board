package com.spring.user.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

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
import com.spring.user.service.UserService;
import com.spring.user.vo.UserVo;

@Controller
public class UserController {
	
	@Autowired 
	UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/user/loginUI.do", method = RequestMethod.GET)
	public String logintUI(Locale locale, Model model) throws Exception{
		
		
		return "user/loginUI";
	}
	
	@RequestMapping(value = "/user/login.do", method = RequestMethod.GET)
	public String logint(Locale locale, 
			Model model, 
			UserVo userVo,
			HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		
		Object loginchk = session.getAttribute("login");
		
		if(loginchk != null) {
			session.invalidate();
		}
		
		UserVo login = userService.login(userVo);
		
		session.setAttribute("login", login);	
		
		return "redirect:/board/boardList.do";
	}
	
	@RequestMapping(value = "/user/logout.do", method = RequestMethod.GET)
	public String logout(Locale locale, 
			Model model, 
			HttpServletRequest request) throws Exception{
		
		request.getSession().invalidate();	
		
		return "redirect:/board/boardList.do";
	}
	
	@RequestMapping(value = "/user/userInsertUI.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model) throws Exception{

		return "user/userInsertUI";
	}
	
	@RequestMapping(value = "/user/userInsert.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale,UserVo userVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = userService.userInsert(userVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		
		return callbackMsg;
	}
	
	@RequestMapping(value = "/user/userCheck.do", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Integer> userCheck(Model model, 
			UserVo userVo) throws Exception{
		ResponseEntity<Integer> entity = null;
//		UserVo userVo = new UserVo();
//		userVo.setUserId(userId);
//		userVo.setUserPw(userPw);
		try {
			int data = userService.userCheck(userVo);
			model.addAttribute("data", data);

			entity = new ResponseEntity<Integer>(data, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	@RequestMapping(value = "/user/userPhone.do", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ComCodeVO>> userPhone(Model model) throws Exception{
		ResponseEntity<List<ComCodeVO>> entity = null;

		try {
			List<ComCodeVO> data = userService.userPhone();
			
			entity = new ResponseEntity<List<ComCodeVO>>(data, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<ComCodeVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/user/{userId}/idCheck.do", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Integer> idCheck(Model model
			, @PathVariable("userId") String userId) throws Exception{
		ResponseEntity<Integer> entity = null;

		try {
			int data = userService.idCheck(userId);
			
			entity = new ResponseEntity<Integer>(data, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
