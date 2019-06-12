package com.ubone.batch.job.analytic.impl;

import org.springframework.stereotype.Repository;

import com.ubone.batch.job.analytic.dao.AnalyticStampJobDAO;
import com.ubone.framework.dao.UbSqlSessionDaoSupport;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : AnalyticStampJobDAOImpl.java
 *  설    명   : 기간별 스탬프 적립현황 데이터 적재  배치잡 DAO 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-12-20
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Repository
public class AnalyticStampJobDAOImpl extends UbSqlSessionDaoSupport implements AnalyticStampJobDAO{

	private String queryPrefix = "AnalyticStampJob.";

	@Override
	public DataList selectStampPeriod(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectStampPeriod", parameter);
	}
	
	@Override
	public String insertStampPeriod(Parameter parameter) {
		return insert(this.queryPrefix + "insertStampPeriod", parameter);
	}
	
	@Override
	public int updateStampPeriod(Parameter parameter) {
		return update(this.queryPrefix + "updateStampPeriod", parameter);
	}
	
	@Override
	public DataList selectSaveStamp(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectSaveStamp", parameter);
	}
	
	@Override
	public DataList selectStampReward(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectStampReward", parameter);
	}
	
	@Override
	public DataList selectStampUseReward(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectStampUseReward", parameter);
	}
	
}
