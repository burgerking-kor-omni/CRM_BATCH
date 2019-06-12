package com.ubone.batch.job.log.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ubone.batch.job.log.dao.LoginLogDAO;

/** 
 * <pre>
 *  파 일 명  : LoginLogSO.java
 *  설    명   : 회원 로그인 로그 삭제 배치잡 Service Object 클래스
 *  작 성 자  :  
 *  작 성 일  : 2019-05-17
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Service
public class LoginLogSO {
	
	@Resource
	private LoginLogDAO loginLogDAO;
	
	/**
	 * 로그인 로그 삭제
	 * @param 
	 * @return 
	 */
	public int deleteLoginLog() {
		int updateCnt = loginLogDAO.deleteLoginLog();
		
		return updateCnt;
	}

}
