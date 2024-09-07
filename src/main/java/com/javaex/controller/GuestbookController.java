package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	// 등록폼+리스트
	@RequestMapping(value = "/addlist", method = { RequestMethod.GET, RequestMethod.POST })
	public String addList(Model model) {
		System.out.println("GuestbookController.addList()");

		List<GuestbookVo> guestbookList = guestbookService.exeAddList();
		System.out.println(guestbookList);
		model.addAttribute("guestbookList", guestbookList);

		return "guestbook/addList";
	}

	// 등록
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(@ModelAttribute GuestbookVo guestVo) {
		System.out.println("GuestController.add()");

		int count = guestbookService.exeAdd(guestVo);
		System.out.println(count);
		return "redirect:/guestbook/addlist";
	}

	// 삭제폼
	@RequestMapping(value = "/deleteform", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm() {
		System.out.println("GuestbookController.deleteForm()");

		return "guestbook/deleteForm";
	}

	// 삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@ModelAttribute GuestbookVo guestVo) {
		System.out.println("GuestbookController.delete()");

		guestbookService.exeRemove(guestVo);

		return "redirect:/guestbook/addlist";
	}

	//////////////////////////////////////////////////////////////
	// ajax방명록 메인
	@RequestMapping(value = "/ajaxindex", method = { RequestMethod.GET, RequestMethod.POST })
	public String ajaxIndex() {
		System.out.println("GuestbookController.ajaxIndex()");

		return "guestbook/ajaxindex";
	}
	
	

}