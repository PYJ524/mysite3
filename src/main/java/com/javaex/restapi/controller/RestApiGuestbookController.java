package com.javaex.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;
import com.javaex.vo.JsonResult;

@RestController
@RequestMapping(value="/restapi")
public class RestApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	// 전체리스트 가져오기
	@GetMapping(value="/guestbooks")
	public JsonResult list() {
		System.out.println("RestApiGuestbookController.list()");
		
		List<GuestbookVo> getGuestbookList = guestbookService.getList();
		System.out.println(getGuestbookList);
		
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(getGuestbookList);
		return jsonResult;
	}
	
	// 게스트북 등록
	@PostMapping(value="/guestbooks")
	public JsonResult writeGuest(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("RestApiGuestbookController.writeGuest()");
		System.err.println(guestbookVo);
		GuestbookVo guestVo = guestbookService.addGuest(guestbookVo);
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(guestVo); 
		
		return jsonResult;
	}
	
	@DeleteMapping(value="/guestbooks/{no}")
	public JsonResult delete(@PathVariable("no") int no, @RequestBody GuestbookVo guestbookVo) {
		System.out.println("RestApiGuestbookController.deleteGuest()");
		System.out.println(no);
		System.out.println(guestbookVo);
		
		int count = guestbookService.delete(guestbookVo);
		System.out.println(count);
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(count); 
		
		
		return jsonResult;
	}
}
