package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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

	// 방명록 첫페이지(ajax로 리스트 출력)
	@RequestMapping(value="api/guestbook/addList2", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList2() {
		System.out.println("ApiGuestbookController.addList2()");
		
		return "/WEB-INF/views/guestbook/ajaxList2.jsp";
	}
	
	//ajax 전체리스트 가져오깅
	@ResponseBody
	@RequestMapping(value="api/guestbook/list", method= {RequestMethod.POST, RequestMethod.GET})
	public JsonResult list() {
		System.out.println("ApiGuestbookController.list()");
		List<GuestbookVo> guestbooklist = guestbookService.getList();
		System.out.println(guestbooklist);
		
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(guestbooklist);
		
		System.out.println(jsonResult);
		return jsonResult;
	}
	
	
	///////////////////////////////////////////////////////////////////
	// ajax 방명록 첫페이지 (기본방식으로 구현)
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

	// json으로 데이터 전송 후 등록
	@ResponseBody
	@RequestMapping(value="api/guestbook/add2", method={RequestMethod.GET, RequestMethod.POST})
	public JsonResult add2(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.add2()");
		System.out.println(guestbookVo);
		GuestbookVo guestVo = guestbookService.addGuest(guestbookVo);
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(guestVo);   
		
		return jsonResult;
	}
	
	//ajax 삭제
	@ResponseBody
	@RequestMapping(value="api/guestbook/remove", method={RequestMethod.GET, RequestMethod.POST})
	public int remove(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.remove()");
		System.out.println(guestbookVo);
		int result = guestbookService.delete(guestbookVo);
			JsonResult jsonResult = new JsonResult();
			jsonResult.success(guestbookVo); 
		
		return result;
	}
	
}
