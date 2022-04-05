package com.spring.user.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.vo.ComCodeVO;
import com.spring.user.dao.UserDao;
import com.spring.user.service.UserService;
import com.spring.user.vo.UserVo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;

	@Override
	public int userInsert(UserVo userVo) {
		// TODO Auto-generated method stub
		return userDao.userInsert(userVo);
	}

	@Override
	public UserVo login(UserVo userVo) {
		// TODO Auto-generated method stub
		return userDao.login(userVo);
	}

	@Override
	public int userCheck(UserVo userVo) {
		// TODO Auto-generated method stub
		return userDao.userCheck(userVo);
	}

	@Override
	public List<ComCodeVO> userPhone() {
		// TODO Auto-generated method stub
		return userDao.userPhone();
	}

	@Override
	public int idCheck(String userId) {
		// TODO Auto-generated method stub
		return userDao.idCheck(userId);
	}
	
}
