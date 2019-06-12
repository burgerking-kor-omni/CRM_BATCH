package com.ubone.batch.job.member.dao;

import com.ubone.framework.data.DataList;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : GradeJobDAO.java
 *  설    명   : 회원등급 배치잡 DAO 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-11-05
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
public interface GradeJobDAO {
	
	DataList selectMemberInfo(Parameter parameter);
	
	DataList selectMemberGrade(Parameter parameter);
	
	DataList selectGradeInfo(Parameter parameter);
	
	DataList selectDownStand(Parameter parameter);
	
	DataList selectGradeDownTarget(Parameter parameter);
	
	int updateMemberGrade(Parameter parameter);
	
}
