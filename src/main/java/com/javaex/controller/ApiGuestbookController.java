package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	//전체리스트 가져오기
	@ResponseBody //리턴에 있는데이터를 json으로 바꿔서 응답문서의 body에 넣어줘
	@RequestMapping(value="/api/guestbook/list", method = {RequestMethod.GET, RequestMethod.POST})
	public List<GuestbookVo> list() {
		System.out.println("ApiGuestbookController.list()");
		
		List<GuestbookVo> guestbookList = guestbookService.exeAddList();
		System.out.println(guestbookList);
		return guestbookList;
	}
	
	
	//방명록 등록
	@ResponseBody
	@RequestMapping(value="/api/guestbook/write", method = {RequestMethod.GET, RequestMethod.POST})
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.write()");
		
		//int count = guestbookService.exeAdd(guestbookVo);
		
		//데이터 3개짜리 vo
		System.out.println(guestbookVo);
		
		//가져오기
		GuestbookVo gVo = guestbookService.exeAddandGuest(guestbookVo);
		System.out.println(gVo);  //데이터5개짜리 vo
		
		return gVo;
	}
	
	//방명록 삭제
	
	@RequestMapping(value="/api/guestbook/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String remove(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("Apicontroller.Remove");
		System.out.println(guestbookVo);
		int count =guestbookService.exeRemove(guestbookVo);
		System.out.println(count);
		return "";
	}
	
	
}
