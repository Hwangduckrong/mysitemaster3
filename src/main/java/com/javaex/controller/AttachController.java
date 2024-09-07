package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;
import com.javaex.vo.AttachVo;

@Controller
public class AttachController {
   
   @Autowired
   private AttachService attachService;

   /* 업로드 폼 */
   @RequestMapping(value = "/attach/form", method = { RequestMethod.GET, RequestMethod.POST })
   public String form() {
      System.out.println("AttachController.form()");
      
      return "/attach/form";
   }
   
   /* 업로드 */
   
   @RequestMapping(value = "/attach/upload", method = { RequestMethod.GET, RequestMethod.POST })
   public String upload(@RequestParam(value="file") MultipartFile file, Model model) {
      System.out.println("AttachController.upload()");
      System.out.println(file.getOriginalFilename());
      String saveName=attachService.upload(file);
      System.out.println(saveName);
      
      model.addAttribute("saveName",saveName);
      return "/attach/result";
   }
  
}
