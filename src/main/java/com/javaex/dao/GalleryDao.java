package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.AttachVo;
import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public int pictureinsert(GalleryVo galleryVo) {
		int count=sqlSession.insert("gallery.insertpicture");
		
		return count;
	}
	
	
	//사진 정보 등록
	
	
	
	//사진 정보들을 리스트로 가져오기
	public List<GalleryVo> galleryload() {
		List<GalleryVo> galleryList = sqlSession.selectList("gallery.selectList");
		
		
		
		return galleryList;
	}
}
