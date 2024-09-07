package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.AttachVo;

@Repository
public class AttachDao {
	
	@Autowired
	private SqlSession sqlSession;

	public int Attachinsert(AttachVo attachVo) {
		System.out.println("AttachVo.insert");
		
		int count=sqlSession.insert("gallery.insertImage");
		
		return count;
		
	}
}
