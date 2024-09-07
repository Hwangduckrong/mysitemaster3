package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	/* 회원가입 */
	public int insertUser(UserVo userVo) {
		System.out.println("UserDao.insertUser()");
		
		int count = sqlSession.insert("user.insert", userVo);
		return count;
	}
	
	/* 로그인 */
	public UserVo selectUser(UserVo userVo) {
		System.out.println("UserDao.selectUser()");
		
		UserVo authUser = sqlSession.selectOne("user.selectByIdPw", userVo);
		return authUser;
	}
	//id로 데이터 가져오기-id 사용여부체크할때 사용
	
	public int SelectUserById(String id) {
		System.out.println("UserDao.SelectUserById");
		int count=sqlSession.selectOne("user.selectById",id );
		System.out.println(count);
		return count;
	}
	
}
