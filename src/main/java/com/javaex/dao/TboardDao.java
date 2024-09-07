package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.TboardVo;

@Repository
public class TboardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<TboardVo> selectList() {
		System.out.println("TboardDao.selectList()");
		
		List<TboardVo> tboardList = sqlSession.selectList("tboard.selectList");
		return tboardList;
	}
	
	public List<TboardVo> selectList2(Map<String, Integer> limitMap) {
		System.out.println("TboardDao.selectList2()");
		
		List<TboardVo> tboardList = sqlSession.selectList("tboard.selectList2", limitMap);
		return tboardList;
	}
	
	/* 전체 글갯수 */
	public int selectTotalCnt() {
		System.out.println("TboardDao.selectTotalCnt()");
		
		int totalCnt = sqlSession.selectOne("tboard.selectTotalCnt");
		System.out.println(totalCnt);
		return totalCnt;
	}
	
	
}