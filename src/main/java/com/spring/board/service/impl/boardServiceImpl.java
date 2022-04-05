package com.spring.board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.ComCodeVO;
import com.spring.board.vo.PageVo;

@Service
public class boardServiceImpl implements boardService{
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectTest();
	}
	
	@Transactional
	@Override
	public List<BoardVo> SelectBoardList(PageVo pageVo) throws Exception {
		// TODO Auto-generated method stub
		List<BoardVo> list = boardDao.selectBoardList(pageVo);
		
		for(int i=0;i<list.size(); i++) {
			String boardType = list.get(i).getBoardType();
			ComCodeVO comCodeVO = boardDao.getCodeName(boardType);
			list.get(i).setCodeName(comCodeVO.getCodeName());
		}
		
		return list;
	}
	
	@Override
	public int selectBoardCnt() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectBoardCnt();
	}
	
	@Override
	public BoardVo selectBoard(String boardType, int boardNum) throws Exception {
		// TODO Auto-generated method stub
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		
		return boardDao.selectBoard(boardVo);
	}
	
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		
		int[] results= new int[boardVo.getBoardVoList().size()];
		int result=1;
		
		for(int i=0; i<boardVo.getBoardVoList().size();i++) {
			results[i]=boardDao.boardInsert(boardVo.getBoardVoList().get(i));

			result *= results[i];
		}
		
		return result;
	}

	@Override
	public void update(BoardVo boardVo) {
		
		boardDao.update(boardVo);
	}

	@Override
	public void delete(String boardType, int boardNum) {
		// TODO Auto-generated method stub
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		
		boardDao.delete(boardVo);
	}

	@Override
	public int deleteCheck(String boardType, int boardNum) {
		// TODO Auto-generated method stub
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		
		return boardDao.deleteCheck(boardVo);
	}

	@Override
	public List<ComCodeVO> boardType() {
		// TODO Auto-generated method stub
		return boardDao.boardType();
	}
	@Transactional
	@Override
	public List<BoardVo> SelectBoardListOfType(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<BoardVo> list = boardDao.SelectBoardListOfType(map);
		
		for(int i=0;i<list.size(); i++) {
			String codeType = list.get(i).getBoardType();
			ComCodeVO comCodeVO = boardDao.getCodeName(codeType);
			list.get(i).setCodeName(comCodeVO.getCodeName());
		}
		
		return list;
	}

	@Override
	public int selectBoardCntOfType(String[] hiddenValue) {
		// TODO Auto-generated method stub
		return boardDao.selectBoardCntOfType(hiddenValue);
	}

//	@Override
//	public int boardInsert(List<BoardVo> param) {
//		// TODO Auto-generated method stub
//		return boardDao.boardInsert(param);
//	}


//	@Override
//	public int selectBoardCntOfType(String boardType) {
//		// TODO Auto-generated method stub
//		return boardDao.selectBoardCntOfType(boardType);
//	}
//	
//	@Transactional
//	@Override
//	public List<BoardVo> SelectBoardListOfType(int pageNo, String boardType) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("pageNo", pageNo);
//		map.put("boardType", boardType);
//		
//		List<BoardVo> list = boardDao.SelectBoardListOfType(map);
//		
//		for(int i=0;i<list.size(); i++) {
//			String codeType = list.get(i).getBoardType();
//			ComCodeVO comCodeVO = boardDao.getCodeName(codeType);
//			list.get(i).setCodeName(comCodeVO.getCodeName());
//		}
//		
//		return list;
//	}
	
}
