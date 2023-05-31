package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;
import com.javaex.vo.UserVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public List<GuestbookVo> getList(){
		System.out.println("GuestbookDao.getList()");
		List<GuestbookVo> guestbookList = sqlSession.selectList("user.list");
		System.out.println(guestbookList);
		
		return guestbookList;
	}
	
	public void insert(GuestbookVo vo) {
		System.out.println("GusetbookDao.insert()");
		System.out.println(vo);
		sqlSession.insert("guestbook.insert", vo);
	}
	public void delete(GuestbookVo vo) {
		sqlSession.delete("guestbook.delete", vo);
	}
}
