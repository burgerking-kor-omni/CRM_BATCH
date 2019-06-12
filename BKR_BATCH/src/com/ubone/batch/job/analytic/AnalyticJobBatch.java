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
import com.ubone.batch.job.analytic.service.AnalyticJobSO;

/** 
 * <pre>
 *  파 일 명  : AnalyticJobBatch.java
 *  설    명   : 기간별 회원현황 데이터 적재 배치 인스턴스 클래스
 *  작 성 자  : 
 *  작 성 일  : 2018-12-20
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Component
public class AnalyticJobBatch extends BaseBatch  {

	@Resource
	AnalyticJobSO  analyticJobSO;
	
	/**
	 * <pre>
	 * 프로세스를 실행
	 * </pre>
	 * @throws Exception
	 */
	public int execute(String[] args) throws Exception{
		int nCount = 0;
		
		// 1. 가입회원 데이터 적재
		nCount = analyticJobSO.changeJoinMember();
		
		// 2. 가입채널 데이터 적재
		nCount =+ analyticJobSO.changeChnnMember();
		
		// 3. 휴면회원 데이터 적재
		nCount =+ analyticJobSO.changeDormancyMember();
		
		// 4. 탈퇴회원 데이터 적재
		nCount =+ analyticJobSO.changeDelMember();
		
		// 5. 로그인회원 데이터 적재
		nCount =+ analyticJobSO.changeLoginMember();
		
		// 6. 멥버십 리워드 회원 데이터 적재
		nCount =+ analyticJobSO.changeRewardMember();
		
		// 7. 누적 멤버십 리워드 쿠폰수 데이터 적재
		nCount =+ analyticJobSO.changeRewardCoupon();
		
		// 8. 주문 테이블 데이터 저장
		analyticJobSO.insertOmniOrder();
		
		// 9. 구매관련 데이터 적재
		nCount =+ analyticJobSO.changeOrder();
		
        return nCount;

	}

	/**
	 * Shell 단독 실행 테스트용
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:resource/config/spring/applicationContext-*.xml");		
		BaseBatch batch = context.getBean(AnalyticJobBatch.class);
		batch.run();
	}
}
