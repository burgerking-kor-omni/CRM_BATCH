package com.ubone.batch.job.coupon.service;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ubone.batch.job.coupon.dao.CouponJobDAO;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.DataUtil;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : CouponPinSO.java
 *  설    명   : 기간이 지난 쿠폰핀 백업 및 삭제 배치잡 Service Object 클래스
 *  작 성 자  :  
 *  작 성 일  : 2019-05-21
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Service
public class CouponPinSO {
	
	@Resource
	private CouponJobDAO couponJobDAO;
	
	Log logger = LogFactory.getLog(CouponPinSO.class);
	
	/**
	 * 쿠폰 삭제대상 조회
	 * @param 
	 * @return 
	 */
	public DataList selectCoupon() {
		Parameter parameter = DataUtil.makeParameter();
		
		DataList dtReturn = couponJobDAO.selectCoupon(parameter);
		
		return dtReturn;
	}
	
	/**
	 * 쿠폰핀 백업
	 * @param 
	 * @return 
	 */
	public int insertCouponPinHistory(DataList dt) {
		int succCnt = 0;
		
		if (0 < dt.getRowCount()) {
			for (int i=0; i<dt.getRowCount(); i++) {
				Parameter parameter = DataUtil.makeParameter();
				parameter.setParameter("CD_COUPON", dt.getString(i, "CD_COUPON"));
				couponJobDAO.insertCouponPinHistory(parameter);
				succCnt++;
			}
		}
		
		return succCnt;
	}
	
	/**
	 * 쿠폰핀 삭제
	 * @param 
	 * @return 
	 */
	public int deleteCouponPin(DataList dt) {
		int succCnt = 0;
		
		if (0 < dt.getRowCount()) {
			for (int i=0; i<dt.getRowCount(); i++) {
				Parameter parameter = DataUtil.makeParameter();
				parameter.setParameter("CD_COUPON", dt.getString(i, "CD_COUPON"));
				couponJobDAO.deleteCouponPin(parameter);
				succCnt++;
			}
		}
		
		return succCnt;
	}

}
