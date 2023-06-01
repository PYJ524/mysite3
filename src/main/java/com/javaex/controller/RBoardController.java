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

import com.javaex.service.RBoardService;
import com.javaex.vo.RBoardVo;
import com.javaex.vo.UserVo;

@Controller
public class RBoardController {

	@Autowired
	private RBoardService rBoardService;
	
	@RequestMapping(value = "/rBoard/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("RBoardController.list()");
		List<RBoardVo> list = rBoardService.list();
		System.out.println(list);
		model.addAttribute("rbList", list);

		return "/WEB-INF/views/board/rList.jsp";
	}
	
	@RequestMapping(value = "/rBoard/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute RBoardVo rBoardVo, HttpSession session) {
		System.out.println("RBoardControllder.write()");
		UserVo vo = (UserVo) session.getAttribute("uInfo");
		rBoardVo.setUserNo(vo.getNo());
		rBoardService.write(rBoardVo);
		return "redirect:/rBoard/list";
	}
	
	@RequestMapping(value = "/rBoard/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("RBoardController.writeForm()");
		return "/WEB-INF/views/board/rWriteForm.jsp";
	}
	
	@RequestMapping(value = "/rBoard/read", method= {RequestMethod.GET, RequestMethod.POST})
	public String read(@RequestParam("no") int no, HttpSession session) {
		session.setAttribute("oneList", rBoardService.read(no));
		return "/WEB-INF/views/board/read.jsp";
	}
}
