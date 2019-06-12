package com.ubone.batch.job.coupon.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.ubone.batch.job.coupon.dao.CouponJobDAO;
import com.ubone.framework.dao.UbSqlSessionDaoSupport;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : CouponJobDAOImpl.java
 *  설    명   : 쿠폰관련 배치잡 DAO 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-11-05
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Repository
public class CouponJobDAOImpl extends UbSqlSessionDaoSupport implements CouponJobDAO{

	private String queryPrefix = "CouponJob.";

	@Override
	public DataList selectDauCouponFirst(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectDauCouponFirst", parameter);
	}
	
	@Override
	public DataList selectDauCouponConfig(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectDauCouponConfig", parameter);
	}
	
	@Override
	public DataList selectDauCouponTagget(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectDauCouponTagget", parameter);
	}
	
	@Override
	public DataList selectDauPinReq(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectDauPinReq", parameter);
	}
	
	@Override
	public int insertCouponPin(Parameter parameter) {
		SqlSessionFactory factory = getSqlSessionTemplate().getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("CD_COUPON", parameter.getParameter("CD_COUPON"));
			data.put("DT_EXPIRY_START", parameter.getParameter("DT_EXPIRY_START"));
			data.put("DT_EXPIRY_END", parameter.getParameter("DT_EXPIRY_END"));
			data.put("PIN_NUM", parameter.getParameter("PIN_NUM"));
			
			session.insert(this.queryPrefix + "insertCouponPin", data);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.close();
		}
		
		return 1;
	}
	
	@Override
	public int updateDauReq(Parameter parameter) {
		SqlSessionFactory factory = getSqlSessionTemplate().getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("NO_DAUPIN_REQ", parameter.getParameter("NO_DAUPIN_REQ"));
			data.put("DAUPIN_STATUS", parameter.getParameter("DAUPIN_STATUS"));
			
			session.update(this.queryPrefix + "updateDauReq", data);
			session.commit();
		} catch (Exception e) {
			session.rollback();
		} finally {
			session.close();
		}
		return 1;
	}
	
	@Override
	public DataList selectCoupon(Parameter parameter) {
		return queryForDataList(this.queryPrefix + "selectCoupon", parameter);
	}
	
	@Override
	public String insertCouponPinHistory(Parameter parameter) {
		return insert(this.queryPrefix + "insertCouponPinHistory", parameter);
	}
	
	@Override
	public int deleteCouponPin(Parameter parameter) {
		return delete(this.queryPrefix + "deleteCouponPin", parameter);
	}

}
