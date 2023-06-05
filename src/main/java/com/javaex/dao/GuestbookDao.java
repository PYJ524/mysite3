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

	public void insertSelectKey(GuestbookVo vo) {
		System.out.println("GusetbookDao.insert2()");
		System.out.println(vo);
		sqlSession.insert("guestbook.insertSelectKey", vo);
	}
	
	public void delete(GuestbookVo vo) {
		sqlSession.delete("guestbook.delete", vo);
	}
	
	public GuestbookVo selectGuest(int no) {
		
		return sqlSession.selectOne("guestbook.에이젝스리스트", no); 
	}
}
