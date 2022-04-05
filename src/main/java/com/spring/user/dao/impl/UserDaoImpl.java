package com.spring.user.dao.impl;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.vo.ComCodeVO;
import com.spring.user.dao.UserDao;
import com.spring.user.vo.UserVo;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int userInsert(UserVo userVo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("user.userInsert", userVo);
	}

	@Override
	public UserVo login(UserVo userVo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.login", userVo);
	}

	@Override
	public int userCheck(UserVo userVo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.userCheck", userVo);
	}

	@Override
	public List<ComCodeVO> userPhone() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("user.userPhone");
	}

	@Override
	public int idCheck(String userId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.idCheck", userId);
	}
	
}
