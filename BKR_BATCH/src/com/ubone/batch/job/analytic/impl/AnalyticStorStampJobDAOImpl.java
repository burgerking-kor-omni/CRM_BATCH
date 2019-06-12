package com.ubone.batch.job.analytic.impl;

import org.springframework.stereotype.Repository;

import com.ubone.batch.job.analytic.dao.AnalyticStorStampJobDAO;
import com.ubone.framework.dao.UbSqlSessionDaoSupport;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : AnalyticStorStampJobDAOImpl.java
 *  설    명   : 매장별 스탬프 적립현황 데이터 적재  배치잡 DAO 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-12-20
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Repository
public class AnalyticStorStampJobDAOImpl extends UbSqlSessionDaoSupport implements AnalyticStorStampJobDAO{

	private String queryPrefix = "AnalyticStorStampJob.";

	@Override
	public DataList selectStorStampPeriod(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectStorStampPeriod", parameter);
	}
	
	@Override
	public String insertStorStampPeriod(Parameter parameter) {
		return insert(this.queryPrefix + "insertStorStampPeriod", parameter);
	}
	
	@Override
	public int updateStorStampPeriod(Parameter parameter) {
		return update(this.queryPrefix + "updateStorStampPeriod", parameter);
	}
	
	@Override
	public DataList selectStorStamp(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectStorStamp", parameter);
	}
	
}
