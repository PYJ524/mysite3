package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int join(UserVo userVo) {
		System.out.println("UserDao.join()");
		System.out.println(userVo);
		
		int count = sqlSession.insert("user.insert", userVo);
		System.out.println(count);
		return count;
	}
	
	public UserVo SelectUser(UserVo userVo) {
		System.out.println("UserDao.login()");
		return sqlSession.selectOne("user.selectUser", userVo);
		
	}
	
	public UserVo oneUser(UserVo vo) {
		return sqlSession.selectOne("user.oneUser", vo);
	}
	
	public int update(UserVo vo) {
		System.out.println("UserDao.update()");
		System.out.println(vo);
		return sqlSession.update("user.update",vo);
	}
	
	public UserVo checkId(String id) {
		return sqlSession.selectOne("checkId", id);
	}
}
