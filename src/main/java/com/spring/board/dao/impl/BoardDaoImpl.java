package com.spring.board.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.BoardDao;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVO;
import com.spring.board.vo.PageVo;

@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		
		String a = sqlSession.selectOne("board.boardList");
		
		return a;
	}
	/**
	 * 
	 * */
	@Override
	public List<BoardVo> selectBoardList(PageVo pageVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.boardList",pageVo);
	}
	
	@Override
	public int selectBoardCnt() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.boardTotal");
	}
	
	@Override
	public BoardVo selectBoard(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.boardView", boardVo);
	}
	
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.boardInsert", boardVo);
	}
	@Override
	public void update(BoardVo boardVo) {
		// TODO Auto-generated method stub
		sqlSession.update("board.boardUpdate",boardVo);
	}
	@Override
	public void delete(BoardVo boardVo) {
		// TODO Auto-generated method stub
		sqlSession.update("board.boardDelete",boardVo);
	}
	@Override
	public int deleteCheck(BoardVo boardVo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.deleteCheck",boardVo);
	}
	@Override
	public List<ComCodeVO> boardType() {
		
		return sqlSession.selectList("board.boardType");
	}
	@Override
	public ComCodeVO getCodeName(String codeId) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.getCodeName", codeId);
	}
	@Override
	public List<BoardVo> SelectBoardListOfType(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.boardList",map);
	}
//	@Override
//	public int selectBoardCntOfType(String boardType) {
//		// TODO Auto-generated method stub
//		return sqlSession.selectOne("board.boardTotalOfType",boardType);
//	}
	@Override
	public int selectBoardCntOfType(String[] hiddenValue) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.boardTotalOfType",hiddenValue);
	}
	@Override
	public int boardInsert(List<BoardVo> param) {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.boardInsert", param);
	}
	@Override
	public List<BoardVo> SelectBoardListOfKeyword(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.boardList",map);
	}
	@Override
	public int selectBoardCntOfKeyword(String searchInput) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("board.boardTotalOfKeyword",searchInput);
	}
	
	
}
