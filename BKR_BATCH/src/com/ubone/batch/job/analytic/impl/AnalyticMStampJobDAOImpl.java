package com.ubone.batch.job.analytic.impl;

import org.springframework.stereotype.Repository;

import com.ubone.batch.job.analytic.dao.AnalyticMStampJobDAO;
import com.ubone.framework.dao.UbSqlSessionDaoSupport;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : AnalyticJobDAOImpl.java
 *  설    명   : 멤버십 스탬프 적립현황 데이터 적재  배치잡 DAO 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-12-20
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Repository
public class AnalyticMStampJobDAOImpl extends UbSqlSessionDaoSupport implements AnalyticMStampJobDAO{

	private String queryPrefix = "AnalyticMStampJob.";

	@Override
	public DataList selectMShipStampPeriod(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectMShipStampPeriod", parameter);
	}
	
	@Override
	public String insertMShipStampPeriod(Parameter parameter) {
		return insert(this.queryPrefix + "insertMShipStampPeriod", parameter);
	}
	
	@Override
	public int updateMShipStampPeriod(Parameter parameter) {
		return update(this.queryPrefix + "updateMShipStampPeriod", parameter);
	}
	
	@Override
	public DataList selectMShipStamp(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectMShipStamp", parameter);
	}

}
