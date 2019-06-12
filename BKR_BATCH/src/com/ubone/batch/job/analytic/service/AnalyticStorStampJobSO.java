package com.ubone.batch.job.analytic.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ubone.batch.job.analytic.dao.AnalyticStorStampJobDAO;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.DataUtil;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : AnalyticStorStampJobSO.java
 *  설    명   : 매장별 스탬프 적립현황 데이터 적재 배치잡 Service Object 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-12-20
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Service
public class AnalyticStorStampJobSO {
	
	@Resource
	private AnalyticStorStampJobDAO analyticStorStampJobDAO;
	
	/**
	 * 매장별 스탬프 적재
	 * @param DataList
	 * @return 
	 */
	public int changeStorStamp() {
		int updateCnt = 0;
		Parameter param = DataUtil.makeParameter();
		DataList dt = analyticStorStampJobDAO.selectStorStamp(param);
		
		for (int i=0; i<dt.getRowCount(); i++) {
			// 1. 데이터 존재여부 확인
			Parameter parameter = DataUtil.makeParameter();
			parameter.setParameter("YYYY", dt.getString(i, "YYYY"));					// 년도
			parameter.setParameter("MM", dt.getString(i, "MM"));						// 월
			parameter.setParameter("STOR_CD", dt.getString(i, "STOR_CD"));				// 매장코드
			parameter.setParameter("CNT_ALL_MEMBER", dt.getString(i, "CNT_ALL_MEMBER"));// 적립회원수
			parameter.setParameter("CNT_ALL_STAMP", dt.getString(i, "CNT_ALL_STAMP"));	// 적립스탬프
			parameter.setParameter("CNT_J_MEMBER", dt.getString(i, "CNT_J_MEMBER"));	// 적립회원수 - 주니어
			parameter.setParameter("CNT_J_STAMP", dt.getString(i, "CNT_J_STAMP"));		// 적립스탬프수 - 주니어
			parameter.setParameter("CNT_W_MEMBER", dt.getString(i, "CNT_W_MEMBER"));	// 적립회원수 - 와퍼
			parameter.setParameter("CNT_W_STAMP", dt.getString(i, "CNT_W_STAMP"));		// 적립스탬프수 - 와퍼
			parameter.setParameter("CNT_K_MEMBER", dt.getString(i, "CNT_K_MEMBER"));	// 적립회원수 - 킹
			parameter.setParameter("CNT_K_STAMP", dt.getString(i, "CNT_K_STAMP"));		// 적립스탬프수 - 킹
			
			// 2. 기존 데이터 존재여부 확인
			DataList dtSearch = analyticStorStampJobDAO.selectStorStampPeriod(parameter);

			if (0 < dtSearch.getRowCount()) {
				analyticStorStampJobDAO.updateStorStampPeriod(parameter);
			} else {
				analyticStorStampJobDAO.insertStorStampPeriod(parameter);
			}
			updateCnt++;
		}
		return updateCnt;
	}
	
}
