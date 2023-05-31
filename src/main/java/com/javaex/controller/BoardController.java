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

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/board/list", method = {RequestMethod.GET, RequestMethod.POST })
	public String boardList(@RequestParam(value = "keyword", required = false , defaultValue = "") String keyword ,Model model) {
		System.out.println("BoardController.boardList()");
		List<BoardVo> boardList = boardService.list(keyword);
		model.addAttribute("bList", boardList);
		return "/WEB-INF/views/board/list.jsp";
	}

	@RequestMapping(value = "/board/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("BoardController.writeForm()");
		return "/WEB-INF/views/board/writeForm.jsp";
	}

	@RequestMapping(value = "/board/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		System.out.println("BoardControllder.write()");
		UserVo vo = (UserVo) session.getAttribute("uInfo");
		boardVo.setUserNo(vo.getNo());
		boardService.write(boardVo);
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/board/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {
		boardService.delete(no);
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/board/read", method= {RequestMethod.GET, RequestMethod.POST})
	public String read(@RequestParam("no") int no, HttpSession session) {
		session.setAttribute("oneList", boardService.read(no));
		return "/WEB-INF/views/board/read.jsp";
	}
	
	@RequestMapping(value="/board/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(HttpSession session) {
		BoardVo vo = (BoardVo)session.getAttribute("oneList");
		System.out.println(vo);
		return "/WEB-INF/views/board/modifyForm.jsp"; 
	}
	
	@RequestMapping(value="/board/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo, HttpSession session) {
		boardService.modify(boardVo);
		boardVo.setRegDate(boardVo.getRegDate()+"(수정됨)");
		System.out.println(boardVo.getRegDate());
		return "redirect:/board/read?no="+boardVo.getNo();
	}
}
