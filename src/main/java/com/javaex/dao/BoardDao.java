package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> selectList3(int startRnum, int endRnum, String keyword){
		System.out.println("BoardDao.selectList3()");
		System.out.println(startRnum+ " " + endRnum);
		
		Map<String, Object> bMap = new HashMap<String, Object>();
		bMap.put("startRnum", startRnum);
		bMap.put("endRnum", endRnum);
		bMap.put("keyword", keyword);
		System.out.println(bMap);
			
		List<BoardVo> boardList = sqlSession.selectList("board.selectList3", bMap);
		System.out.println(boardList);

		return boardList;
	}

	/* 게시판 리스트 출력용 */
	public List<BoardVo> list(String keyword) {
		System.out.println("RBoardController.list()");
		List<BoardVo> boardList = sqlSession.selectList("board.list", keyword);
		System.out.println(boardList);
		return boardList;
	}

	/* 게시판 작성글 추가용 */
	public void insert(BoardVo vo) {
		sqlSession.insert("board.insert", vo);
	}

	/* 게시판 작성글 삭제용 */
	public void delete(int no) {
		sqlSession.delete("board.delete", no);
	}

	/* 게시판 작성글 내용 읽기용 */
	public BoardVo oneSelect(int no) {
		return sqlSession.selectOne("board.selectOne", no);
	}
	
	public void update(BoardVo boardVo) {
		System.out.println("BoardDao.update()");
		sqlSession.update("board.update", boardVo);
	}
	
	public int totalCount(String keyword) {
		System.out.println("boardDao.totalCount()");
		int total = sqlSession.selectOne("board.totalCount", keyword);
		System.out.println(total);
		return total;
	}
}
