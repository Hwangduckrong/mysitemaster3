package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
public class GalleryController {

	@Autowired
	private GalleryService galleryService;

	// 사진등록
	@RequestMapping(value = "/gallery/uploading", method = { RequestMethod.GET, RequestMethod.POST })
	public String pload(@RequestParam(value = "file") MultipartFile file, Model model) {
		System.out.println("AttachController.upload()");
		System.out.println(file.getOriginalFilename());
		String saveName = galleryService.pictureUpload(file);
		System.out.println(saveName);

		model.addAttribute("saveName", saveName);
		return "/gallery/list";
	}
	//사진 리스트 
	@RequestMapping(value = "/gallery/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String upload(Model model) {
		System.out.println("GalleryController.upload");
		List<GalleryVo> galleryList = galleryService.exeupload();
		model.addAttribute(" galleryList",  galleryList);
		return "/gallery/list";// 포워드

	}
}
