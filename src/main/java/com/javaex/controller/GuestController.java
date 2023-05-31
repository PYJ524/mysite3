package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestController {
	@Autowired
	GuestbookService guestbookService;
	
	@RequestMapping(value="/guestbook/addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String guestbookList() {
		System.out.println("GuestController.addList()");
		
		return "redirect:/guestbook/guestbookList";
	}
	
	@RequestMapping(value="/guestbook/guestbookList", method= {RequestMethod.GET, RequestMethod.POST})
	public String guestbookList(Model model) {
		System.out.println("GuestController.addList()");
		
		List<GuestbookVo> guestbookList = guestbookService.getList();
		System.out.println(guestbookList);
		
		model.addAttribute("gbList", guestbookList);
		
		return "/WEB-INF/views/guestbook/addList.jsp";
	}
	
	@RequestMapping(value="/guestbook/insert", method= {RequestMethod.GET, RequestMethod.POST})
	public String insert(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("GuestController.insert()");
		guestbookService.insert(guestbookVo);
		
		return "redirect:/guestbook/guestbookList";
	}
	
	@RequestMapping(value="/guestbook/deleteForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@RequestParam("no") int no) {
		
		return "/WEB-INF/views/guestbook/deleteForm.jsp";
	}
	
	@RequestMapping(value="/guestbook/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		guestbookService.delete(guestbookVo);
		
		return "redirect:/guestbook/guestbookList";
	}
}