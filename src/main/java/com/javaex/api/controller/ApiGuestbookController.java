package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;
import com.javaex.vo.JsonResult;

@Controller
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;

	@RequestMapping(value="api/guestbook/addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList(@ModelAttribute GuestbookVo vo , Model model) {
		System.out.println("ApiGuestbookController.addList()");
		System.out.println(vo);
		List<GuestbookVo> guestbookVo = guestbookService.getList();
		System.out.println(guestbookVo);
		
		model.addAttribute("guestbookList", guestbookVo);
		
		
		return "/WEB-INF/views/guestbook/ajaxList.jsp";
	}
	
	@ResponseBody
	@RequestMapping(value="api/guestbook/add", method={RequestMethod.GET, RequestMethod.POST})
	public JsonResult add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.add()");
		GuestbookVo guestVo = guestbookService.addGuest(guestbookVo);
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(guestVo);   
		
		return jsonResult;
	}
	
}
