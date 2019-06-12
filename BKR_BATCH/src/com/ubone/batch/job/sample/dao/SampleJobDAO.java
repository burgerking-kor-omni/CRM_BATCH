package com.ubone.batch.job.sample.dao;

import com.ubone.framework.data.DataList;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명 : SampleBatchJobDAO.java
 *  설    명 : 샘플 배치잡 DAO 클래스
 *  작 성 자 : 강영운 
 *  작 성 일 : 2015-08-11
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
public interface SampleJobDAO {
	
	DataList getList(Parameter parameter);
	
	int deleteCompanyInfo();

	int insertCompanyInfo(DataList dataList);
}
