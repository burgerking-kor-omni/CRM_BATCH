package com.ubone.batch.job.member.impl;

import org.springframework.stereotype.Repository;

import com.ubone.batch.job.member.dao.GradeJobDAO;
import com.ubone.framework.dao.UbSqlSessionDaoSupport;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : GradeJobDAOImpl.java
 *  설    명   : 회원등급 배치잡 DAO 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-11-05
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Repository
public class GradeJobDAOImpl extends UbSqlSessionDaoSupport implements GradeJobDAO{

	private String queryPrefix = "GradeJob.";

	@Override
	public DataList selectMemberInfo(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectMemberInfo", parameter);
	}
	
	@Override
	public DataList selectMemberGrade(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectMemberGrade", parameter);
	}
	
	@Override
	public DataList selectGradeInfo(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectGradeInfo", parameter);
	}
	
	@Override
	public DataList selectGradeDownTarget(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectGradeDownTarget", parameter);
	}
	
	@Override
	public DataList selectDownStand(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectDownStand", parameter);
	}
	
	@Override
	public DataList selectAfterStamp(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectAfterStamp", parameter);
	}
	
	@Override
	public DataList selectChkGrade(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectChkGrade", parameter);
	}
	
	@Override
	public int updateMemberGrade(Parameter parameter) {
		return update(this.queryPrefix + "updateMemberGrade", parameter);
	}

}
