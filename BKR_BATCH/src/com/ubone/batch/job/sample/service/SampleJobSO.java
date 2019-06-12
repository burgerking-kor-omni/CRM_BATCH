package com.ubone.batch.job.sample.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ubone.batch.job.sample.dao.SampleJobDAO;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.DataUtil;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명 : SampleJobSO.java
 *  설    명 : 샘플 배치잡 Service Object 클래스
 *  작 성 자 : 강영운 
 *  작 성 일 : 2015-08-11
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Service
public class SampleJobSO {
	
	@Resource
	private SampleJobDAO sampleJobDAO;
	
	public DataList getList(){
		Parameter parameter = DataUtil.makeParameter();
		return sampleJobDAO.getList(parameter);
	}

	public int deleteComapanyInfo() {
		return sampleJobDAO.deleteCompanyInfo();
	}
	
	/**
	 * 회사 정보 로드 함수
	 * @param dataList
	 * @return
	 */
	public int loadCompanyInfo(DataList dataList) {
		return sampleJobDAO.insertCompanyInfo(dataList);
	}
}
