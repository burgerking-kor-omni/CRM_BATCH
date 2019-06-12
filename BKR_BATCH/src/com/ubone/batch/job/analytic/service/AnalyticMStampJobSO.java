package com.ubone.batch.job.analytic.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ubone.batch.job.analytic.dao.AnalyticMStampJobDAO;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.DataUtil;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : AnalyticMStampJobSO.java
 *  설    명   : 멤버십 스탬프 적립현황 데이터 적재 배치잡 Service Object 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-12-20
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Service
public class AnalyticMStampJobSO {
	
	@Resource
	private AnalyticMStampJobDAO analyticMStampJobDAO;
	
	/**
	 * 가입회원 데이터 적재
	 * @param DataList
	 * @return 
	 */
	public int changeMShipStamp() {
		int updateCnt = 0;
		Parameter param = DataUtil.makeParameter();
		DataList dt = analyticMStampJobDAO.selectMShipStamp(param);
		
		for (int i=0; i<dt.getRowCount(); i++) {
			// 1. 데이터 존재여부 확인
			Parameter parameter = DataUtil.makeParameter();
			parameter.setParameter("YYYY", dt.getString(i, "YYYY"));				// 년도
			parameter.setParameter("MM", dt.getString(i, "MM"));					// 월
			parameter.setParameter("CD_GRADE", dt.getString(i, "CD_GRADE"));		// 등급
			parameter.setParameter("CNT_SAVE_STAMP", dt.getString(i, "CNT_SAVE_STAMP"));	// 적립 스템프수

			// 2. 기존 데이터 존재여부 확인
			DataList dtSearch = analyticMStampJobDAO.selectMShipStampPeriod(parameter);

			if (0 < dtSearch.getRowCount()) {
				analyticMStampJobDAO.updateMShipStampPeriod(parameter);
			} else {
				analyticMStampJobDAO.insertMShipStampPeriod(parameter);
			}
			updateCnt++;
		}
		return updateCnt;
	}
	
}
