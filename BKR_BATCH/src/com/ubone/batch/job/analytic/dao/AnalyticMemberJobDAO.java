package com.ubone.batch.job.analytic.dao;

import com.ubone.framework.data.DataList;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : AnalyticMemberJobDAO.java
 *  설    명   : 전체회원 적재 배치잡 DAO 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-12-20
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
public interface AnalyticMemberJobDAO {
	
	DataList selectAllMember(Parameter parameter);
	
	String insertAllMemberPeriod(Parameter parameter);
	
}
