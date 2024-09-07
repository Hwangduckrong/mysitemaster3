package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.TboardDao;
import com.javaex.vo.TboardVo;

@Service
public class TboardService {

	@Autowired
	private TboardDao tboardDao;
	
	public List<TboardVo> exeList() {
		System.out.println("TboardService.exeList()");
		
		List<TboardVo> tboardList = tboardDao.selectList();
		return tboardList;
	}
	
	public Map<String, Object> exeList2(int crtPage) {
		System.out.println("TboardService.exeList2()");
		
		//////////////////////////////////////////////////
		//리스트 가져오기
		//////////////////////////////////////////////////
		//한페이지의 출력갯수
		int listCnt = 10;  
		//현재페이지 음수일때 계산 3항연산자
		
		crtPage =(crtPage>0) ? crtPage : (crtPage=1);
		
		/*
		if(crtPage<0) {
			crtPage = 1;
		}else {
			crtPage=crtPage;
		}*/
		
		// startRowNo 구하기
		// 1->(1 10)  2->(11 10)  3->(21 10) 사람
		// 1->(0 10)  2->(10 10)  3->(20 10) mysql
		// (1-1)10  0
		// (2-1)10  10
		// (3-1)10  20
		// startRowNo = (crtPage-1)listCnt
		int startRowNo = (crtPage-1)*listCnt;
		
		
		// 두개의 데이타를 1개로 묶어준다
		Map<String, Integer> limitMap = new HashMap<String, Integer>();
		limitMap.put("startRowNo", startRowNo);
		limitMap.put("listCnt", listCnt);

		List<TboardVo> tboardList = tboardDao.selectList2(limitMap);
		
		////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////
		//페이징 계산(하단 버튼)
		//////////////////////////////////////////////////
		
		//페이지당 버튼 갯수
		int pgaeBtncount = 5;

		//전체글갯수
		int totalCnt = tboardDao.selectTotalCnt();  //187
		
		//endPageBtnNo 마지막 버튼 번호
		//  1  2  3  4  5  >
		// 1 --> (1, 5)
		// 2 --> (1, 5)
		// 3 --> (1, 5)
		// 4 --> (1, 5)
		// 5 --> (1, 5)
		// 6 --> (6, 10)
		// 7 --> (6, 10)
		//...
		// 10 --> (6, 10)
		// 11 --> (11, 15)
		
		//(1 5) => 올림(1/5)5                 0.2(1)*5 --> 5
		//(2 5) => 올림(2/5)5                 0.4(1)*5 --> 5
		//(3 5) => 올림(3/5)5                 0.6(1)*5 --> 5 
		//(4 5) => 올림(4/5)5                 0.8(1)*5 --> 5 
		//(5 5) => 올림(5/5)5                 1.0(1)*5 --> 5 
		//(5 10) => 올림(6/5)5                1.2(2)*5 --> 10 
		//(11 15) =>올림(11/5)5               2.2(3)*5 --> 15
		//(올림(crtPage/pgaeBtncount))*pgaeBtncount
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil((crtPage/(double)pgaeBtncount))*pgaeBtncount;	
		
		//시작 버튼 번호
		int startPageBtnNo = (endPageBtnNo - pgaeBtncount) + 1;
		
		System.out.println(crtPage + ", " + startPageBtnNo + ", " + endPageBtnNo);
		
		//다음 화살표 유무
		boolean next = false;
		if(listCnt * endPageBtnNo < totalCnt) {  //한페이지당 글갯수(10) * 마지막버튼번호(19) < 전체글개수(187)
			next = true;
		}else {
			//다음화살표가 false 일때 마지막 숫자버튼이 갯수를 정확히 계산
			//187->19page 187/10 = 18.7 올림 처리해서 19page로 계산중
			endPageBtnNo=(int)Math.ceil(totalCnt/(double)listCnt);
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		/* 화면에 표현할 모든 데이터를 묶는다 map */
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("tboardList", tboardList);
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		
		return pMap;
	}
	
}		