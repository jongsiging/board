package com.spring.board.service;

import java.util.List;
import java.util.Map;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVO;
import com.spring.board.vo.PageVo;

public interface boardService {

	public String selectTest() throws Exception;

	public List<BoardVo> SelectBoardList(PageVo pageVo) throws Exception;

	public BoardVo selectBoard(String boardType, int boardNum) throws Exception;

	public int selectBoardCnt() throws Exception;

	public int boardInsert(BoardVo boardVo) throws Exception;

	public void update(BoardVo boardVo);

	public void delete(String boardType, int boardNum);

	public int deleteCheck(String boardType, int boardNum);

	public List<ComCodeVO> boardType();

//	public List<BoardVo> SelectBoardListOfType(int pageNo, String boardType);
//
//	public int selectBoardCntOfType(String boardType);

	public List<BoardVo> SelectBoardListOfType(Map<String, Object> map);

	public int selectBoardCntOfType(String[] hiddenValue);

	public List<BoardVo> SelectBoardListOfKeyword(Map<String, Object> map);

	public int selectBoardCntOfKeyword(String searchInput);

//	public int boardInsert(List<BoardVo> param);

}
