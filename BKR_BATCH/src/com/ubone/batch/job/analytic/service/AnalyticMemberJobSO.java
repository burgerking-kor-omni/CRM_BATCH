package com.ubone.batch.job.analytic.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ubone.batch.job.analytic.dao.AnalyticMemberJobDAO;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.DataUtil;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : AnalyticMemberJobSO.java
 *  설    명   : 전체회원 적재 배치잡 Service Object 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-12-20
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Service
public class AnalyticMemberJobSO {
	
	@Resource
	private AnalyticMemberJobDAO analyticMemberJobDAO;
	
	/**
	 * 전체회원 데이터 적재
	 * @param DataList
	 * @return 
	 */
	public int changeAllMember() {
		int updateCnt = 0;
		Parameter param = DataUtil.makeParameter();
		DataList dt = analyticMemberJobDAO.selectAllMember(param);
		
		for (int i=0; i<dt.getRowCount(); i++) {
			// 1. 데이터 존재여부 확인
			Parameter parameter = DataUtil.makeParameter();
			parameter.setParameter("YYYY", dt.getString(i, "YYYY"));				// 년도
			parameter.setParameter("MM", dt.getString(i, "MM"));					// 월
			parameter.setParameter("DD", dt.getString(i, "DD"));					// 일
			parameter.setParameter("CD_GENDER", dt.getString(i, "CD_GENDER"));		// 성병
			parameter.setParameter("CD_AGE", dt.getString(i, "CD_AGE"));			// 연령대
			parameter.setParameter("CD_GRADE", dt.getString(i, "CD_GRADE"));		// 등급
			parameter.setParameter("CNT_MEMBER", dt.getString(i, "CNT_MEMBER"));	// 전체 회원수
			
			// 2. 데이터 적재
			analyticMemberJobDAO.insertAllMemberPeriod(parameter);
			
			updateCnt++;
		}
		return updateCnt;
	}

}
