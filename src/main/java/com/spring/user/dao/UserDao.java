package com.spring.user.dao;

import java.util.List;

import com.spring.board.vo.ComCodeVO;
import com.spring.user.vo.UserVo;

public interface UserDao {

	int userInsert(UserVo userVo);

	UserVo login(UserVo userVo);

	int userCheck(UserVo userVo);

	List<ComCodeVO> userPhone();

	int idCheck(String userId);


}
