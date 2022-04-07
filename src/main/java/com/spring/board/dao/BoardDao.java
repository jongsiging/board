package com.spring.board.dao;

import java.util.List;
import java.util.Map;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVO;
import com.spring.board.vo.PageVo;

public interface BoardDao {

	public String selectTest() throws Exception;

	public List<BoardVo> selectBoardList(PageVo pageVo) throws Exception;

	public BoardVo selectBoard(BoardVo boardVo) throws Exception;

	public int selectBoardCnt() throws Exception;

	public int boardInsert(BoardVo boardVo) throws Exception;

	public void update(BoardVo boardVo);

	public void delete(BoardVo boardVo);

	public int deleteCheck(BoardVo boardVo);

	public List<ComCodeVO> boardType();

	public ComCodeVO getCodeName(String boardType);

	public List<BoardVo> SelectBoardListOfType(Map<String, Object> map);

	public int selectBoardCntOfType(String[] hiddenValue);

	public int boardInsert(List<BoardVo> param);

	public List<BoardVo> SelectBoardListOfKeyword(Map<String, Object> map);

	public int selectBoardCntOfKeyword(String searchInput);

//	public int selectBoardCntOfType(String boardType);

}
