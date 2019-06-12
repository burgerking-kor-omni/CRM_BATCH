package com.ubone.batch.job.sample.impl;

import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.ubone.batch.job.sample.dao.SampleJobDAO;
import com.ubone.framework.dao.UbSqlSessionDaoSupport;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.DataUtil;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명 : SampleJobDAOImpl.java
 *  설    명 : 샘플 배치잡 DAO 클래스
 *  작 성 자 : 강영운 
 *  작 성 일 : 2015-08-11
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Repository
public class SampleJobDAOImpl extends UbSqlSessionDaoSupport implements SampleJobDAO{

	private String queryPrefix = "SampleBatchJob.";

	@Override
	public DataList getList(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "getList", parameter);
	}

	/**
	 * 회사정보를 초기화 하는 함수
	 */
	@Override
	public int deleteCompanyInfo() {
		Parameter parameter = DataUtil.makeParameter();
		return update(this.queryPrefix + "deleteCompanyInfo", parameter);
	}
	
	/**
	 * 회사정보를 배치 형태로 등록하는 함수 
	 */
	@Override
	public int insertCompanyInfo(DataList dataList) {
		for(Map<String, Object> row : dataList){
			insert(this.queryPrefix+"insertCompanyInfo", row);
		}
		return dataList.getRowCount();
	}
}
