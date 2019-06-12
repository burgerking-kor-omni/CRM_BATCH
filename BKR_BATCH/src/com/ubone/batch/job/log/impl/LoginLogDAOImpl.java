package com.ubone.batch.job.log.impl;

import org.springframework.stereotype.Repository;

import com.ubone.batch.job.log.dao.LoginLogDAO;
import com.ubone.framework.dao.UbSqlSessionDaoSupport;
import com.ubone.framework.data.DataUtil;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : LoginLogDAOImpl.java
 *  설    명   : 회원 로그인 로그 삭제  배치잡 DAO 클래스
 *  작 성 자  :  
 *  작 성 일  : 2019-05-17
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Repository
public class LoginLogDAOImpl extends UbSqlSessionDaoSupport implements LoginLogDAO{

	private String queryPrefix = "LoginLog.";

	@Override
	public int deleteLoginLog() {
		Parameter parameter = DataUtil.makeParameter();
		return delete(this.queryPrefix + "deleteLoginLog", parameter);
	}

}
