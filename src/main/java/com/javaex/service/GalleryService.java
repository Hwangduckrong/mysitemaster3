package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.AttachVo;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;

	// 사진 업로드
	public String pictureUpload(MultipartFile file) {
		System.out.println("GalleryService.upload");
		// 사진에 기본정보로 우리가 관리할 정보를 뽑아내야한다-->db저장

		// 파일 저장 폴더
		String saveDir = "C:\\javaStudy\\upload";

		// 오리지날 파일명
		String orgName = file.getOriginalFilename();
		System.out.println("orgName: " + orgName);

		// 확장자
		String exeName = orgName.substring(orgName.lastIndexOf("."));

		// 파일 사이즈
		long fileSize = file.getSize();

		// 저장파일명(겹치지않아야한다)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exeName;

		// 파일전체경로
		String filePath = saveDir + "\\" + saveName;

		// (1)DB저장
		// (1-1) 데이터 묶기
		GalleryVo galleryVo = new GalleryVo(filePath, orgName,saveName, fileSize);
		System.out.println(galleryVo);
		System.out.println("과제:DB저장 중");

		// (1-2) dao 통해서 db에 저장
		int count = galleryDao.pictureinsert(galleryVo);

		// (2)사진을 서버(강남)에 하드디스크에 복사해야한다
		// 파일 저장
		if (count == 1) {
			try {
				byte[] fileData = file.getBytes();

				OutputStream os = new FileOutputStream(filePath);
				BufferedOutputStream bos = new BufferedOutputStream(os);

				bos.write(fileData);
				bos.close();

			} catch (Exception e) {
				System.out.println();
			}
			return saveName;
		} else
			return null;

	}

	// 사진 목록
	public List<GalleryVo> exeupload() {
		System.out.println("Gallery.exeupload");

		List<GalleryVo>  galleryList = galleryDao.galleryload();

		return  galleryList;

	}
}
