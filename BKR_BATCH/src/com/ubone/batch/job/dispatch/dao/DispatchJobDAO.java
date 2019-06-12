package com.ubone.batch.job.dispatch.dao;

import com.ubone.framework.data.DataList;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : DispatchJobDAO.java
 *  설    명   : 통보실행 배치잡 DAO 클래스
 *  작 성 자  :  
 *  작 성 일  : 2019-01-10
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
public interface DispatchJobDAO {
	
	DataList selectDispatch(Parameter parameter);
	DataList selectDispatchList(Parameter parameter);
	DataList selectVocFile(Parameter parameter);
	
	int updateDispatch(Parameter parameter);
	int updateDispatchTarget(Parameter parameter);
	
}
