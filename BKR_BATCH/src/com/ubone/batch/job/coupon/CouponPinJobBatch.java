/**
 * <pre>
 *  Batch Id    : NEP_USER_SYNC
 *  File Name   : UserSyncBatch.java
 *  Description : Loader the user information of the Nahq B2E Portal System from the GHR System.
 *  Author      : YW.kang  
 *  Create Date : 01.25.2013.
 * </pre>
 */
package com.ubone.batch.job.coupon;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.ubone.batch.core.base.BaseBatch;
import com.ubone.batch.job.coupon.service.CouponPinSO;
import com.ubone.framework.data.DataList;

/** 
 * <pre>
 *  파 일 명  : CouponPinJobBatch.java
 *  설    명   : 기간이 지난 쿠폰핀 백업 및 삭제 배치 인스턴스 클래스
 *  작 성 자  : 
 *  작 성 일  : 2019-05-21
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Component
public class CouponPinJobBatch extends BaseBatch  {

	@Resource
	CouponPinSO couponPinSO;
	
	/**
	 * <pre>
	 * 프로세스를 실행
	 * </pre>
	 * @throws Exception
	 */
	public int execute(String[] args) throws Exception{
		int nCount = 0;
		
		// 1. 쿠폰 삭제대상 조회
		DataList dt = couponPinSO.selectCoupon();
		
		if (0 < dt.getRowCount()) {
			// 2. 쿠폰핀 백업
			couponPinSO.insertCouponPinHistory(dt);
			
			// 3. 쿠폰핀 삭제
			couponPinSO.deleteCouponPin(dt);
		}

		return nCount;
	}

	/**
	 * Shell 단독 실행 테스트용
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:resource/config/spring/applicationContext-*.xml");		
		BaseBatch batch = context.getBean(CouponPinJobBatch.class);
		batch.run();
	}
}
