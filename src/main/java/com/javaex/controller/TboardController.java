package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.TboardService;
import com.javaex.vo.TboardVo;

@Controller
public class TboardController {

	@Autowired
	private TboardService tboardService;

	@RequestMapping(value = "tboard/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("TboardController.list");

		List<TboardVo> tboardList = tboardService.exeList();
		model.addAttribute("tboardList", tboardList);
		return "/tboard/list";
	}

	// 리스트2(페이징)
	@RequestMapping(value = "/tboard/list2", method = { RequestMethod.GET, RequestMethod.POST })
	public String list2(@RequestParam(value = "crtpage", required = false, defaultValue = "1") int crtPage,
			Model model) {
		System.out.println("TboardController.list()");

		Map<String, Object> pMap = tboardService.exeList2(crtPage);
		System.out.println(pMap);

		model.addAttribute("pMap", pMap);
		return "tboard/list2";
	}

}