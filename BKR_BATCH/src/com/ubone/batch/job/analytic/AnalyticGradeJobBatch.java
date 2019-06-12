/**
 * <pre>
 *  Batch Id    : NEP_USER_SYNC
 *  File Name   : UserSyncBatch.java
 *  Description : Loader the user information of the Nahq B2E Portal System from the GHR System.
 *  Author      : YW.kang  
 *  Create Date : 01.25.2013.
 * </pre>
 */
package com.ubone.batch.job.analytic;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.ubone.batch.core.base.BaseBatch;
import com.ubone.batch.job.analytic.service.AnalyticGradeJobSO;

/** 
 * <pre>
 *  파 일 명  : AnalyticGradeJobBatch.java
 *  설    명   : 월별 멤버십 변동 현황 데이터 적재 배치 인스턴스 클래스
 *  작 성 자  : 
 *  작 성 일  : 2018-12-20
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Component
public class AnalyticGradeJobBatch extends BaseBatch  {

	@Resource
	AnalyticGradeJobSO analyticGradeJobSO;
	
	/**
	 * <pre>
	 * 프로세스를 실행
	 * </pre>
	 * @throws Exception
	 */
	public int execute(String[] args) throws Exception{
		int nCount = 0;
		
		// 1. 멤버십 변동 적재
		nCount = analyticGradeJobSO.changeGrade();
		
        return nCount;

	}

	/**
	 * Shell 단독 실행 테스트용
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:resource/config/spring/applicationContext-*.xml");		
		BaseBatch batch = context.getBean(AnalyticGradeJobBatch.class);
		batch.run();
	}
}
