package com.ubone.batch.job.analytic.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ubone.batch.job.analytic.dao.AnalyticGradeJobDAO;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.DataUtil;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : AnalyticGradeJobSO.java
 *  설    명   : 월별 멤버십 변동 현황 데이터 적재 배치잡 Service Object 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-12-20
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Service
public class AnalyticGradeJobSO {
	
	@Resource
	private AnalyticGradeJobDAO analyticGradeJobDAO;
	
	/**
	 * 멤버십 변동 적재
	 * @param DataList
	 * @return 
	 */
	public int changeGrade() {
		int updateCnt = 0;
		Parameter param = DataUtil.makeParameter();
		DataList dt = analyticGradeJobDAO.selectGrade(param);
		
		for (int i=0; i<dt.getRowCount(); i++) {
			// 1. 데이터 존재여부 확인
			Parameter parameter = DataUtil.makeParameter();
			parameter.setParameter("YYYY", dt.getString(i, "YYYY"));				// 년도
			parameter.setParameter("MM", dt.getString(i, "MM"));					// 월
			parameter.setParameter("CNT_J_DOWN", dt.getString(i, "CNT_J_DOWN"));	// 적립회원수
			parameter.setParameter("CNT_W_UP", dt.getString(i, "CNT_W_UP"));		// 적립스탬프
			parameter.setParameter("CNT_W_DOWN", dt.getString(i, "CNT_W_DOWN"));	// 적립회원수 - 주니어
			parameter.setParameter("CNT_K_UP", dt.getString(i, "CNT_K_UP"));		// 적립스탬프수 - 주니어
			
			// 2. 기존 데이터 존재여부 확인
			DataList dtSearch = analyticGradeJobDAO.selectGradePeriod(parameter);

			if (0 < dtSearch.getRowCount()) {
				analyticGradeJobDAO.updateGradePeriod(parameter);
			} else {
				analyticGradeJobDAO.insertGradePeriod(parameter);
			}
			updateCnt++;
		}
		return updateCnt;
	}
	
}
