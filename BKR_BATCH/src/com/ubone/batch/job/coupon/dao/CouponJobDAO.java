package com.ubone.batch.job.coupon.dao;

import com.ubone.framework.data.DataList;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : CouponJobDAO.java
 *  설    명   : 쿠폰관련 배치잡 DAO 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-11-05
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
public interface CouponJobDAO {
	
	DataList selectDauCouponFirst(Parameter parameter);
	
	DataList selectDauCouponConfig(Parameter parameter);
	
	DataList selectDauCouponTagget(Parameter parameter);
	
	DataList selectDauPinReq(Parameter parameter);
	
	int insertCouponPin(Parameter parameter);
	
	int updateDauReq(Parameter parameter);
	
	DataList selectCoupon(Parameter parameter);
	
	String insertCouponPinHistory(Parameter parameter);
	
	int deleteCouponPin(Parameter parameter);
	
}
