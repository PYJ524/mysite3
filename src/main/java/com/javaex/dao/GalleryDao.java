package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;

	public List<GalleryVo> list(){
		
		System.out.println("GalleryDao.list()");
		List<GalleryVo> list = sqlSession.selectList("gallery.list");
		
		return list;
	}
	
	public void insert(GalleryVo galleryVo) {
		sqlSession.insert("gallery.insert", galleryVo);
	}
}
