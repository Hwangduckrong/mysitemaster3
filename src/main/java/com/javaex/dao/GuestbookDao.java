package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;

	// 전체 리스트
	public List<GuestbookVo> guestbookSelectList() {
		System.out.println("GuestbookDao.guestbookSelectList()");

		List<GuestbookVo> guestbookList = sqlSession.selectList("guestbook.selectList");

		return guestbookList;
	}

	// 등록
	public int guestbookInsert(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao.guestbookInsert()");

		int count = sqlSession.insert("guestbook.insert", guestbookVo);
		return count;
	}

	// 삭제
	public int guestbookDelete(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao.guestbookDelete()");

		int count = sqlSession.delete("guestbook.delete", guestbookVo);
		return count;
	}

	// ajax등록 저장
	public int insertSelectKey(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao.insertSelectKey()");
		
		//GuestbookVo guestbookVo = sqlSession.selectOne("guestbook.selectOne", no); 이렇게 다오에 로직 두개 주지 마라
		int count = sqlSession.insert("guestbook.insertSelectKey", guestbookVo);
		
		return count;
	
	}

	// 데이터 1개 가져오기 no 1명데이터 가져오기,다오는 무조건 쿼리문 하나, 즉 다른 다오로 서비스를 하나 더해야한다.
	public GuestbookVo guestbookSelectOne(int no) {
		System.out.println("GuestbookDao.guestbookSelectOne()");
		
		
		//1명데이터 데이터 가져오기
		GuestbookVo guestbookVo = sqlSession.selectOne("guestbook.selectOne", no);
		
		return guestbookVo;
	}

}