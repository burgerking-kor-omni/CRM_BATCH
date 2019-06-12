package com.ubone.batch.job.analytic.dao;

import com.ubone.framework.data.DataList;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : AnalyticJobDAO.java
 *  설    명   : 기간별 회원현황 데이터 적재 배치잡 DAO 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-12-20
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
public interface AnalyticJobDAO {
	
	DataList selectMamberPeriod(Parameter parameter);

	String insertMemberPeriod(Parameter parameter);
	
	int updateMemberPeriod(Parameter parameter);

	DataList selectJoinMamber(Parameter parameter);
	
	DataList selectChnnMamber(Parameter parameter);
	
	DataList selectDormancyMamber(Parameter parameter);
	
	DataList selectDelMamber(Parameter parameter);
	
	DataList selectLoginMamber(Parameter parameter);
	
	DataList selectRewardMamber(Parameter parameter);
	
	DataList selectRewardCoupon(Parameter parameter);
	
	String insertOmniOrder(Parameter parameter);
	
	DataList selectOrder(Parameter parameter);
	
}
