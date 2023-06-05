package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.JsonResult;
import com.javaex.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/joinForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController.joinForm()");
		
		return "/WEB-INF/views/user/joinForm.jsp";
	}
	
	@RequestMapping(value="user/join", method= {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController.join()");
		System.out.println(userVo);
		
		int count = userService.join(userVo);
		
		if(count>0) {
			return "/WEB-INF/views/user/joinOk.jsp";
		}else {
			return "redirect:/user/joinForm";
		}
	}
	
	
	@RequestMapping(value="/user/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController.loginForm()");
		
		return "/WEB-INF/views/user/loginForm.jsp";
	}
	
	@RequestMapping(value="/user/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController.login()");
		System.out.println(userVo);
		
		UserVo userInfo = userService.login(userVo);
		System.out.println(userInfo);
		if(userInfo !=null) {
			System.out.println("로그인 성공!!");
			//세션에 저장
			session.setAttribute("uInfo", userInfo);
			//메인으로 리다이렉트
			return "redirect:/main";
		}else {
			System.err.println("로그인 실패!!");
			// 로그인폼으로 리다이렉트
			return "redirect:/user/loginForm?result=fail";
		}
		
	}
	@RequestMapping(value="/user/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		
		session.removeAttribute("uInfo");
		session.invalidate();
		
		return "redirect:/main";
	}
	
	@RequestMapping(value="/user/modifyForm",method= {RequestMethod.GET, RequestMethod.POST})
	public String oneSelect(HttpSession session) {
		UserVo noVo = (UserVo)session.getAttribute("uInfo");
		UserVo oneUser = userService.oneUser(noVo);
		System.out.println(oneUser);
		session.setAttribute("oneUser", oneUser);
		
		return "/WEB-INF/views/user/modifyForm.jsp";
	}
	
	@RequestMapping(value="/user/modify")
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		userService.modify(userVo);
		session.setAttribute("uInfo", userVo);
		
		return "redirect:/main";
	}
	
	@ResponseBody
	@RequestMapping(value="/user/checkId")
	public JsonResult checkId(@RequestParam("id") String id) {
		System.out.println(id);
		System.out.println("UserController.checkId()");
		boolean data = userService.checkId(id);
		System.out.println(data);
		
		JsonResult jsonResult = new JsonResult();
		jsonResult.success(data);

		
//		jsonResult.fail("통신오류");
		
		/*
		 * getter, setter 사용할 경우 --> 잘못 사용할 가능성이 높다
		jsonResult.setResult("success"); 
		jsonResult.setData(data);
		
		
		jsonResult.setResult("fail");
		jsonResult.setFailMsg("통신 오류");
		*/
		System.out.println(jsonResult);
		
		return jsonResult;
	}
		
	
}