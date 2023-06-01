package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RBoardVo;

@Repository
public class RBoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<RBoardVo> list(){
		List<RBoardVo> list = sqlSession.selectList("rBoard.list"); 
		System.out.println(list);
		return list;
	}
		
	/* 게시판 작성글 추가용 */
	public void insert(RBoardVo vo) {
		System.out.println("RBoardDao.insert()");
		sqlSession.insert("rBoard.insert", vo);
	}
	
	public RBoardVo oneSelect(int no) {
		return sqlSession.selectOne("rBoard.selectOne", no);
	}
}
