package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping(value="/list", method= {RequestMethod.POST, RequestMethod.GET})
	public String list(Model model) {
		System.out.println("Gallerycontroller.list()");
		List<GalleryVo> list = galleryService.list();
		model.addAttribute("galleryList", list);
		
		return "/WEB-INF/views/gallery/list.jsp";
	}
	
	@RequestMapping(value="/write", method = {RequestMethod.GET, RequestMethod.POST })
	public String write(@RequestParam("file") MultipartFile file, HttpSession session, @ModelAttribute GalleryVo galleryVo) {
		System.out.println("Gallerycontroller.write()");
		System.out.println(galleryVo);
		UserVo vo = (UserVo) session.getAttribute("uInfo");
		galleryVo.setUserNo(vo.getNo());
		galleryService.write(file, galleryVo);
		
		return "redirect:/gallery/list";
	}
	
}
