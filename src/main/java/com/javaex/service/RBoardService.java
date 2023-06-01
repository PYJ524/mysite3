package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RBoardDao;
import com.javaex.vo.RBoardVo;

@Service
public class RBoardService {

	@Autowired
	private RBoardDao rBoardDao;
	
	public List<RBoardVo> list(){
		System.out.println("RBoardService.list()");
		List<RBoardVo> list = rBoardDao.list(); 
		return list;
	}
	
	public void write(RBoardVo rBoardVo) {
		rBoardDao.insert(rBoardVo);
	}
		
}
