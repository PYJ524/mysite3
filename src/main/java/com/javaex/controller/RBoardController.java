package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.RBoardService;
import com.javaex.vo.RBoardVo;

@Controller
public class RBoardController {

	@Autowired
	private RBoardService rBoardService;
	
	@RequestMapping(value = "/rBoard/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("RBoardController.list()");
		List<RBoardVo> list = rBoardService.list();
		model.addAttribute("rbList", list);

		return "/WEB-INF/views/board/rList.jsp";
	}
	
	@RequestMapping(value = "/rBoard/write")
	public String write(@RequestAttribute RBoardVo rBoardVo) {
		
		
		return "";
	}
}
