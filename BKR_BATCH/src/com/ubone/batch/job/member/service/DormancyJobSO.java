package com.ubone.batch.job.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ubone.batch.job.member.dao.DormancyJobDAO;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.DataUtil;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : DormancyJobSO.java
 *  설    명   : 휴면처리 배치잡 Service Object 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-11-05
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Service
public class DormancyJobSO {
	
	@Resource
	private DormancyJobDAO dormancyJobDAO;
	
	/**
	 * 휴면 처리 대상자 조회
	 * @param 
	 * @return 
	 */
	public DataList selectDormancyMamber() {
		Parameter parameter = DataUtil.makeParameter();
		
		// 1. 전일자 기준 스템프 적립이 있는 회원 목록 조회
		DataList dtMember = dormancyJobDAO.selectDormancyMamber(parameter);
		
		return dtMember;
	}
	
	/**
	 * 휴면 처리
	 * @param DataList
	 * @return 
	 */
	public int updateDormancyMamber(DataList dt) {
		int updateCnt = 0;
		
		// 1. 스템프 변경이 있던 회원의 기존등급 확인
		for (int i=0; i<dt.getRowCount(); i++) {
			Parameter parameter = DataUtil.makeParameter();
			parameter.setParameter("ID_MEMBER", dt.getString(i, "ID_MEMBER"));	// 회원PK	
			dormancyJobDAO.updateDormancyMamber(parameter);
			updateCnt++;
		}
		
		return updateCnt;
	}

}
