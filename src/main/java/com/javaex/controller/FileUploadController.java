package com.javaex.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.reflect.MethodDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileUploadService;

@Controller
@RequestMapping(value="/fileUpload")
public class FileUploadController {
	
	@Autowired
	private FileUploadService fileUploadService;

	
	@RequestMapping(value = "/form", method = {RequestMethod.GET, RequestMethod.POST})
	public String form() {
		System.out.println("FileUploadController.form()");
		
		
		return "/WEB-INF/views/fileupload/form.jsp";
	}
	
	@RequestMapping(value ="/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("FileUploadController.upload()");
		
		String saveName = fileUploadService.restore(file);
		model.addAttribute("saveName",saveName);
		
		return "/WEB-INF/views/fileupload/result.jsp";
	}
}
