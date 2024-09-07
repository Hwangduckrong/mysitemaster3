package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	//필드
	@Autowired
	private UserService userService;
	//생성자
	//메소드 gs
	//메소드 일반
	
	/* 회원가입폼 */
	@RequestMapping(value="/user/joinform", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController.joinForm()");
		
		return "user/joinForm";
	}
	
	/* 회원가입 */
	@RequestMapping(value="/user/join", method = {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController.join()");
		
		userService.exeJoin(userVo);
		
		return "user/joinOk";
	}
	
	
	/* 로그인폼 */
	@RequestMapping(value="/user/loginform", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm(){
		System.out.println("UserController.loginForm()");
		
		return "user/loginForm";
	}
	
	
	/* 로그인 */
	@RequestMapping(value="/user/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController.login()");
		
		UserVo authUser = userService.exeLogin(userVo);
		
		System.out.println("여기는 컨트롤러------------------");
		System.out.println(authUser);
		
		//로그인(세션영역에 저장)
		session.setAttribute("authUser", authUser);
		
		//메인페이지로 리다이렉트
		return "redirect:/main";
	}
	
	/* 로그아웃 */
	@RequestMapping(value="/user/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("UserController.logout()");
		
		//session.removeAttribute("authUser");
		session.invalidate();
		
		//메인페이지로 리다이렉트
		return "redirect:/main";
	}
	/*아이디체크*/
	@ResponseBody //기존방식으로 보내지 말고 Json으로 바디에 담아서 보내기
	@RequestMapping(value="api/user/idcheck", method = {RequestMethod.GET, RequestMethod.POST})
	public boolean idcheck(@RequestParam(value="id")String id) {
		System.out.println("UserController.idCheck()");
		
		boolean can =userService.exeIdCheck(id);
		
		return can;
	}
	
	
}
