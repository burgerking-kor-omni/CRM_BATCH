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
import com.ubone.batch.job.coupon.service.CouponDauJobSO;
import com.ubone.batch.job.coupon.service.CouponJobEX;
import com.ubone.batch.job.coupon.service.CouponJobSO;

/** 
 * <pre>
 *  파 일 명  : CouponHourJobBatch.java
 *  설    명   : [다우] 1시간 조건에 해당하는 핀 발행 배치 인스턴스 클래스
 *  작 성 자  : 
 *  작 성 일  : 2018-11-19
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Component
public class CouponHourJobBatch extends BaseBatch  {

	@Resource
	CouponJobSO couponJobSO;
	
	@Resource
	CouponJobEX couponJobEX;
	
	@Resource
	CouponDauJobSO couponDauJobSO;
	
	/**
	 * <pre>
	 * 프로세스를 실행
	 * </pre>
	 * @throws Exception
	 */
	public int execute(String[] args) throws Exception{
		int nCount = 0;
		
		couponJobEX.publishCouponHour();
		
		// 1. [다우] 1시간 조건에 해당하는 핀 발행
//		DataList dt = couponJobSO.publishCouponHour();
//		
//		if (0 < dt.getRowCount()) {
//			for (int i=0; i<dt.getRowCount(); i++) {
//				Parameter insertParam = DataUtil.makeParameter();
//				insertParam.setParameter("CD_COUPON", dt.getString(i, "CD_COUPON"));
//		        insertParam.setParameter("PIN_NUM", dt.getString(i, "PIN_NUM"));
//		        insertParam.setParameter("DT_EXPIRY_START", dt.getString(i, "VALID_START"));
//		        insertParam.setParameter("DT_EXPIRY_END", dt.getString(i, "VALID_END"));
//		        
//		        couponDauJobSO.insertCouponPin(insertParam);
//			}
//		}
		
        return nCount;

	}

	/**
	 * Shell 단독 실행 테스트용
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:resource/config/spring/applicationContext-*.xml");		
		BaseBatch batch = context.getBean(CouponHourJobBatch.class);
		batch.run();
	}
}
