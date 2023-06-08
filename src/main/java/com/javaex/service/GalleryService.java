package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;

	public List<GalleryVo> list(){
		System.out.println("GalleryService.list()");
		List<GalleryVo> list = galleryDao.list();
		
		return list;
	}
	
	// 파일 처리
	public String write(MultipartFile file, GalleryVo galleryVo) {
		System.out.println("FileUploadService.resore()");
		// 원 파일 이름
		String orgName = file.getOriginalFilename();
		System.out.println("orgName: " + orgName);
		
		// 확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName: " + exName);
		
		// 저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString()+ exName;
		System.out.println("saveName: "+saveName);
		
		// 파일패스
		String filePath = saveDir + "/"+ saveName;
		System.out.println("filePath: "+ filePath);
		
		// 파일 사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize: "+fileSize);
		
		// 파일 업로드(하드디스크에 저장)
		
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout =  new BufferedOutputStream(out);
			bout.write(fileData);
			bout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		GalleryVo vo = new GalleryVo(galleryVo.getUserNo(), galleryVo.getContent(), filePath, orgName, saveName, fileSize);
		galleryDao.insert(vo);
		
		return saveName;
	}
	
	String saveDir = "C:/Yoojun_Spring/upload";

	
}
