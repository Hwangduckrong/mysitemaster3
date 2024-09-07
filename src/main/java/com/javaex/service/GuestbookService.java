package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao; //no비어있음;

	// 등록폼+리스트
	public List<GuestbookVo> exeAddList() {
		System.out.println("GuestbookService.exeAddList()");

		List<GuestbookVo> guestbookList = guestbookDao.guestbookSelectList();

		return guestbookList;
	}

	// 등록
	public int exeAdd(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService.exeAdd()");

		int count = guestbookDao.guestbookInsert(guestbookVo);
		return count;
	}
	//ajax 등록 이후 저장(비즈니스 로직)
	public GuestbookVo exeAddandGuest(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService.exeAddandGuest");
		/////////비즈니스 로직//////
		//저장
		int count =guestbookDao.insertSelectKey(guestbookVo);
		//1명의 데이터만 가져오기
		GuestbookVo gVo=guestbookDao.guestbookSelectOne(guestbookVo.getNo());
		
		
		return guestbookVo;
	}
	// 삭제
		public int exeRemove(GuestbookVo guestbookVo) {
			System.out.println("GuestbookService.exeRemove()");

			int count = guestbookDao.guestbookDelete(guestbookVo);
			return count;
		}

}