package com.ubone.batch.job.coupon.service;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ubone.batch.job.coupon.dao.CouponJobDAO;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : CouponDauJobSO.java
 *  설    명   : [다우]쿠폰핀 등록 배치잡 Service Object 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-11-05
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Service
public class CouponDauJobSO {
	
	@Resource
	private CouponJobDAO couponJobDAO;
	
	Log logger = LogFactory.getLog(CouponDauJobSO.class);
	
	/**
	 * 다우 쿠폰핀 등록
	 * @param 
	 * @return 
	 */
	public void insertCouponPin(Parameter parameter) {
        couponJobDAO.insertCouponPin(parameter);
	}

}
