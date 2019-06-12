package com.ubone.batch.job.analytic.impl;

import org.springframework.stereotype.Repository;

import com.ubone.batch.job.analytic.dao.AnalyticJobDAO;
import com.ubone.framework.dao.UbSqlSessionDaoSupport;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : AnalyticJobDAOImpl.java
 *  설    명   : 기간별 회원현황 데이터 적재  배치잡 DAO 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-12-20
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Repository
public class AnalyticJobDAOImpl extends UbSqlSessionDaoSupport implements AnalyticJobDAO{

	private String queryPrefix = "AnalyticJob.";

	@Override
	public DataList selectMamberPeriod(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectMamberPeriod", parameter);
	}
	
	@Override
	public String insertMemberPeriod(Parameter parameter) {
		return insert(this.queryPrefix + "insertMemberPeriod", parameter);
	}
	
	@Override
	public int updateMemberPeriod(Parameter parameter) {
		return update(this.queryPrefix + "updateMemberPeriod", parameter);
	}
	
	@Override
	public DataList selectJoinMamber(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectJoinMamber", parameter);
	}
	
	@Override
	public DataList selectChnnMamber(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectChnnMamber", parameter);
	}
	
	@Override
	public DataList selectDormancyMamber(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectDormancyMamber", parameter);
	}
	
	@Override
	public DataList selectDelMamber(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectDelMamber", parameter);
	}
	
	@Override
	public DataList selectLoginMamber(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectLoginMamber", parameter);
	}
	
	@Override
	public DataList selectRewardMamber(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectRewardMamber", parameter);
	}
	
	@Override
	public DataList selectRewardCoupon(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectRewardCoupon", parameter);
	}
	
	@Override
	public String insertOmniOrder(Parameter parameter) {
		return insert(this.queryPrefix + "insertOmniOrder", parameter);
	}
	
	@Override
	public DataList selectOrder(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectOrder", parameter);
	}

}
