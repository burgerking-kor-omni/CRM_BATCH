package com.ubone.batch.job.analytic.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ubone.batch.job.analytic.dao.AnalyticStampJobDAO;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.DataUtil;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : AnalyticStampJobSO.java
 *  설    명   : 기간별 스탬프 적립현황 데이터 적재 배치잡 Service Object 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-12-20
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Service
public class AnalyticStampJobSO {
	
	@Resource
	private AnalyticStampJobDAO analyticStampJobDAO;
	
	/**
	 * 스탬프 개수 별 회원 수 적재
	 * @param DataList
	 * @return 
	 */
	public int changeSaveStamp() {
		int updateCnt = 0;
		Parameter param = DataUtil.makeParameter();
		DataList dt = analyticStampJobDAO.selectSaveStamp(param);
		
		for (int i=0; i<dt.getRowCount(); i++) {
			// 1. 데이터 존재여부 확인
			Parameter parameter = DataUtil.makeParameter();
			parameter.setParameter("YYYY", dt.getString(i, "YYYY"));			// 년도
			parameter.setParameter("MM", dt.getString(i, "MM"));				// 월
			parameter.setParameter("DD", dt.getString(i, "DD"));				// 일
			parameter.setParameter("CD_GENDER", dt.getString(i, "CD_GENDER"));	// 성병
			parameter.setParameter("CD_AGE", dt.getString(i, "CD_AGE"));		// 연령대
			parameter.setParameter("CNT_ONE", dt.getString(i, "CNT_ONE"));		// 1개
			parameter.setParameter("CNT_TWO", dt.getString(i, "CNT_TWO"));		// 2개
			parameter.setParameter("CNT_THREE", dt.getString(i, "CNT_THREE"));	// 3개
			parameter.setParameter("CNT_FOUR", dt.getString(i, "CNT_FOUR"));	// 4개
			parameter.setParameter("CNT_FIVE", dt.getString(i, "CNT_FIVE"));	// 5개
			parameter.setParameter("CNT_SIX", dt.getString(i, "CNT_SIX"));		// 6개
			parameter.setParameter("CNT_SEVEN", dt.getString(i, "CNT_SEVEN"));	// 7개
			parameter.setParameter("CNT_EIGHT", dt.getString(i, "CNT_EIGHT"));	// 8개
			parameter.setParameter("CNT_NINE", dt.getString(i, "CNT_NINE"));	// 9개
			parameter.setParameter("CNT_TEN", dt.getString(i, "CNT_TEN"));		// 10개
			parameter.setParameter("CNT_ELEVEN_MORE", dt.getString(i, "CNT_ELEVEN_MORE"));	// 11개 이상
			
			// 2. 기존 데이터 존재여부 확인
			DataList dtSearch = analyticStampJobDAO.selectStampPeriod(parameter);

			if (0 < dtSearch.getRowCount()) {
				analyticStampJobDAO.updateStampPeriod(parameter);
			} else {
				analyticStampJobDAO.insertStampPeriod(parameter);
			}
			updateCnt++;
		}
		return updateCnt;
	}
	
	/**
	 * 리워드 적재
	 * @param DataList
	 * @return 
	 */
	public int changeStampReward() {
		int updateCnt = 0;
		Parameter param = DataUtil.makeParameter();
		DataList dt = analyticStampJobDAO.selectStampReward(param);
		
		for (int i=0; i<dt.getRowCount(); i++) {
			// 1. 데이터 존재여부 확인
			Parameter parameter = DataUtil.makeParameter();
			parameter.setParameter("YYYY", dt.getString(i, "YYYY"));			// 년도
			parameter.setParameter("MM", dt.getString(i, "MM"));				// 월
			parameter.setParameter("DD", dt.getString(i, "DD"));				// 일
			parameter.setParameter("CD_GENDER", dt.getString(i, "CD_GENDER"));	// 성병
			parameter.setParameter("CD_AGE", dt.getString(i, "CD_AGE"));		// 연령대
			parameter.setParameter("CNT_REWARD_FIVE", dt.getString(i, "CNT_REWARD_FIVE"));	// 리워드 5개
			parameter.setParameter("CNT_REWARD_TEN", dt.getString(i, "CNT_REWARD_TEN"));	// 리워드 10개
			
			// 2. 기존 데이터 존재여부 확인
			DataList dtSearch = analyticStampJobDAO.selectStampPeriod(parameter);

			if (0 < dtSearch.getRowCount()) {
				analyticStampJobDAO.updateStampPeriod(parameter);
			} else {
				analyticStampJobDAO.insertStampPeriod(parameter);
			}
			updateCnt++;
		}
		return updateCnt;
	}
	
	/**
	 * 리워드 사용 적재
	 * @param DataList
	 * @return 
	 */
	public int changeStampUseReward() {
		int updateCnt = 0;
		Parameter param = DataUtil.makeParameter();
		DataList dt = analyticStampJobDAO.selectStampUseReward(param);
		
		for (int i=0; i<dt.getRowCount(); i++) {
			// 1. 데이터 존재여부 확인
			Parameter parameter = DataUtil.makeParameter();
			parameter.setParameter("YYYY", dt.getString(i, "YYYY"));			// 년도
			parameter.setParameter("MM", dt.getString(i, "MM"));				// 월
			parameter.setParameter("DD", dt.getString(i, "DD"));				// 일
			parameter.setParameter("CD_GENDER", dt.getString(i, "CD_GENDER"));	// 성병
			parameter.setParameter("CD_AGE", dt.getString(i, "CD_AGE"));		// 연령대
			parameter.setParameter("CNT_REWARD_USE_FIVE", dt.getString(i, "CNT_REWARD_USE_FIVE"));	// 리워드사용 5개
			parameter.setParameter("CNT_REWARD_USE_TEN", dt.getString(i, "CNT_REWARD_USE_TEN"));	// 리워드사용 10개
			
			// 2. 기존 데이터 존재여부 확인
			DataList dtSearch = analyticStampJobDAO.selectStampPeriod(parameter);

			if (0 < dtSearch.getRowCount()) {
				analyticStampJobDAO.updateStampPeriod(parameter);
			} else {
				analyticStampJobDAO.insertStampPeriod(parameter);
			}
			updateCnt++;
		}
		return updateCnt;
	}

}
