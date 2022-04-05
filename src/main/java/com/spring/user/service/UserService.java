package com.spring.user.service;

import java.util.List;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVO;
import com.spring.board.vo.PageVo;
import com.spring.user.vo.UserVo;

public interface UserService {

	int userInsert(UserVo userVo);

	UserVo login(UserVo userVo);

	int userCheck(UserVo userVo);

	List<ComCodeVO> userPhone();

	int idCheck(String userId);

}
