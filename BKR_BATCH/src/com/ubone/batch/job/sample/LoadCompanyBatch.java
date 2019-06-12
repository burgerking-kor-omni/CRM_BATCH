/**
 * <pre>
 *  Batch Id    : NEP_USER_SYNC
 *  File Name   : UserSyncBatch.java
 *  Description : Loader the user information of the Nahq B2E Portal System from the GHR System.
 *  Author      : YW.kang  
 *  Create Date : 01.25.2013.
 * </pre>
 */
package com.ubone.batch.job.sample;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.ubone.batch.core.base.BaseBatch;
import com.ubone.batch.job.sample.service.SampleJobSO;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.DataUtil;

/** 
 * <pre>
 *  파 일 명 : LoadCompanyBatch.java
 *  설    명 : 회사정보 로드 배치 클래스
 *             --> MyBatis에서 Batch Type으로 데이터를 Insert 하는 예제
 *  작 성 자 : 강영운 
 *  작 성 일 : 2015-08-24
 *  버    전 : 1.0
 *  수정이력 :
 *  기타사항 : 배치 클래스는 트랜젝션에 따른 예외 처리시에 유용함.
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Component
public class LoadCompanyBatch extends BaseBatch  {

	Log logger = LogFactory.getLog(LoadCompanyBatch.class);
	
	@Resource
	SampleJobSO sampleJobSO;
	
	/**
	 * <pre>
	 * 프로세스를 실행
	 * </pre>
	 * @throws Exception
	 */
	public int execute(String[] args) throws Exception{
		
		logger.info("1. 데이터 조회");

		// 데이터를 조회했다는 전제하에 DataList 생성함.
		DataList dataList = DataUtil.makeDataList("test");

		sampleJobSO.deleteComapanyInfo();
		
		
		logger.info("3. 데이터 저장");
        return sampleJobSO.loadCompanyInfo(dataList);
	}

	/**
	 * Shell 단독 실행 테스트용
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:resource/config/spring/applicationContext-*.xml");		
		BaseBatch batch = context.getBean(LoadCompanyBatch.class);
		batch.run();
	}
}
