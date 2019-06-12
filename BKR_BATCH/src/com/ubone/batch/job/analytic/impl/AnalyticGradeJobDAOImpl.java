package com.ubone.batch.job.analytic.impl;

import org.springframework.stereotype.Repository;

import com.ubone.batch.job.analytic.dao.AnalyticGradeJobDAO;
import com.ubone.framework.dao.UbSqlSessionDaoSupport;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : AnalyticGradeJobDAOImpl.java
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
public class AnalyticGradeJobDAOImpl extends UbSqlSessionDaoSupport implements AnalyticGradeJobDAO{

	private String queryPrefix = "AnalyticGradeJob.";

	@Override
	public DataList selectGradePeriod(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectGradePeriod", parameter);
	}
	
	@Override
	public String insertGradePeriod(Parameter parameter) {
		return insert(this.queryPrefix + "insertGradePeriod", parameter);
	}
	
	@Override
	public int updateGradePeriod(Parameter parameter) {
		return update(this.queryPrefix + "updateGradePeriod", parameter);
	}
	
	@Override
	public DataList selectGrade(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectGrade", parameter);
	}
	
}
