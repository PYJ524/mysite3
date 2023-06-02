package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public int join(UserVo userVo) {
		System.out.println("UserService.join()");
		int count = userDao.join(userVo);
		
		return count;
	}
	
	public UserVo login(@ModelAttribute UserVo userVo) {
		System.out.println("UserService.login()");
		UserVo userInfo = userDao.SelectUser(userVo);
			return userInfo;
	}
	
	public UserVo oneUser(UserVo noVo) {
		return userDao.oneUser(noVo);
	}
	
	public void modify(UserVo userVo) {
		if(userDao.update(userVo)!= 0) {
			System.out.println("수정에 성공했습니다.");
		}else {
			System.err.println("수정에 실패했습니다.");
		}
	}
	public boolean checkId(String id) {
		boolean result = false;
		UserVo vo = userDao.checkId(id);
		System.out.println(vo);
		if(vo == null) {result = true;}
		return result;
	}
}
