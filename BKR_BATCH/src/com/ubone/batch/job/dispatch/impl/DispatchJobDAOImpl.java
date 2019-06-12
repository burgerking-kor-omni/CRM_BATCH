package com.ubone.batch.job.dispatch.impl;

import org.springframework.stereotype.Repository;

import com.ubone.batch.job.dispatch.dao.DispatchJobDAO;
import com.ubone.framework.dao.UbSqlSessionDaoSupport;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : DispatchJobDAOImpl.java
 *  설    명   : 통보실행 배치잡 DAO 클래스
 *  작 성 자  :  
 *  작 성 일  : 2019-01-10
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Repository
public class DispatchJobDAOImpl extends UbSqlSessionDaoSupport implements DispatchJobDAO{

	private String queryPrefix = "DispatchJob.";

	@Override
	public DataList selectDispatch(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectDispatch", parameter);
	}
	
	@Override
	public DataList selectDispatchList(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectDispatchList", parameter);
	}
	
	@Override
	public DataList selectVocFile(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectVocFile", parameter);
	}
	
	@Override
	public int updateDispatch(Parameter parameter) {
		return update(this.queryPrefix + "updateDispatch", parameter);
	}
	
	@Override
	public int updateDispatchTarget(Parameter parameter) {
		return update(this.queryPrefix + "updateDispatchTarget", parameter);
	}

}
