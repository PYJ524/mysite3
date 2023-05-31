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
		System.out.println("RBoardDao.list()");
		List<RBoardVo> list = sqlSession.selectList("rBoard.list"); 
		System.out.println("DAO 에 오니"+list);
		
		return list;
	}
}
