package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> list() {
		List<BoardVo> boardList = boardDao.list();
		return boardList;
	}

	public void write(BoardVo vo) {
		boardDao.insert(vo);
	}

	public void delete(int no) {
		boardDao.delete(no);
	}

	public BoardVo read(int no) {
		return boardDao.oneSelect(no);
	}
	
	public void modify(BoardVo boardVo) {
		boardDao.update(boardVo);
		System.out.println(boardVo);
	}
}
