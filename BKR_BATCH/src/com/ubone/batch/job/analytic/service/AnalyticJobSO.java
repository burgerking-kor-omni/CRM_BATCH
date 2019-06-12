package com.ubone.batch.job.analytic.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ubone.batch.job.analytic.dao.AnalyticJobDAO;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.DataUtil;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : AnalyticJobSO.java
 *  설    명   : 기간별 회원현황 데이터 적재 배치잡 Service Object 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-12-20
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Service
public class AnalyticJobSO {
	
	@Resource
	private AnalyticJobDAO analyticJobDAO;
	
	/**
	 * 가입회원 데이터 적재
	 * @param DataList
	 * @return 
	 */
	public int changeJoinMember() {
		int updateCnt = 0;
		Parameter param = DataUtil.makeParameter();
		DataList dt = analyticJobDAO.selectJoinMamber(param);
		
		for (int i=0; i<dt.getRowCount(); i++) {
			// 1. 데이터 존재여부 확인
			Parameter parameter = DataUtil.makeParameter();
			parameter.setParameter("YYYY", dt.getString(i, "YYYY"));			// 년도
			parameter.setParameter("MM", dt.getString(i, "MM"));				// 월
			parameter.setParameter("DD", dt.getString(i, "DD"));				// 일
			parameter.setParameter("CD_GENDER", dt.getString(i, "CD_GENDER"));	// 성병
			parameter.setParameter("CD_AGE", dt.getString(i, "CD_AGE"));		// 연령대
			parameter.setParameter("CD_GRADE", dt.getString(i, "CD_GRADE"));	// 등급
			parameter.setParameter("CNT_JOIN", dt.getString(i, "CNT_JOIN"));	// 가입 회원수
			// 2. 기존 데이터 존재여부 확인
			DataList dtSearch = analyticJobDAO.selectMamberPeriod(parameter);

			if (0 < dtSearch.getRowCount()) {
				analyticJobDAO.updateMemberPeriod(parameter);
			} else {
				analyticJobDAO.insertMemberPeriod(parameter);
			}
			updateCnt++;
		}
		return updateCnt;
	}
	
	/**
	 * 가입회원 데이터 적재
	 * @param DataList
	 * @return 
	 */
	public int changeChnnMember() {
		int updateCnt = 0;
		Parameter param = DataUtil.makeParameter();
		DataList dt = analyticJobDAO.selectChnnMamber(param);
		
		for (int i=0; i<dt.getRowCount(); i++) {
			// 1. 데이터 존재여부 확인
			Parameter parameter = DataUtil.makeParameter();
			parameter.setParameter("YYYY", dt.getString(i, "YYYY"));						// 년도
			parameter.setParameter("MM", dt.getString(i, "MM"));							// 월
			parameter.setParameter("DD", dt.getString(i, "DD"));							// 일
			parameter.setParameter("CD_GENDER", dt.getString(i, "CD_GENDER"));				// 성병
			parameter.setParameter("CD_AGE", dt.getString(i, "CD_AGE"));					// 연령대
			parameter.setParameter("CD_GRADE", dt.getString(i, "CD_GRADE"));				// 등급
			parameter.setParameter("CNT_CHNN_BKR", dt.getString(i, "CNT_CHNN_BKR"));		// 가입채널(BKR)
			parameter.setParameter("CNT_CHNN_NAVER", dt.getString(i, "CNT_CHNN_NAVER"));	// 가입채널(네이버)
			parameter.setParameter("CNT_CHNN_KAKAO", dt.getString(i, "CNT_CHNN_KAKAO"));	// 가입채널(카카오톡)
			parameter.setParameter("CNT_CHNN_SAMSUNG", dt.getString(i, "CNT_CHNN_SAMSUNG"));// 가입채널(삼성앱카드)
			// 2. 기존 데이터 존재여부 확인
			DataList dtSearch = analyticJobDAO.selectMamberPeriod(parameter);

			if (0 < dtSearch.getRowCount()) {
				analyticJobDAO.updateMemberPeriod(parameter);
			} else {
				analyticJobDAO.insertMemberPeriod(parameter);
			}
			updateCnt++;
		}
		return updateCnt;
	}
	
	/**
	 * 휴면회원 데이터 적재
	 * @param DataList
	 * @return 
	 */
	public int changeDormancyMember() {
		int updateCnt = 0;
		Parameter param = DataUtil.makeParameter();
		DataList dt = analyticJobDAO.selectDormancyMamber(param);
		
		for (int i=0; i<dt.getRowCount(); i++) {
			// 1. 데이터 존재여부 확인
			Parameter parameter = DataUtil.makeParameter();
			parameter.setParameter("YYYY", dt.getString(i, "YYYY"));					// 년도
			parameter.setParameter("MM", dt.getString(i, "MM"));						// 월
			parameter.setParameter("DD", dt.getString(i, "DD"));						// 일
			parameter.setParameter("CD_GENDER", dt.getString(i, "CD_GENDER"));			// 성병
			parameter.setParameter("CD_AGE", dt.getString(i, "CD_AGE"));				// 연령대
			parameter.setParameter("CD_GRADE", dt.getString(i, "CD_GRADE"));			// 등급
			parameter.setParameter("CNT_DORMANCY", dt.getString(i, "CNT_DORMANCY"));	// 휴면 회원수
			// 2. 기존 데이터 존재여부 확인
			DataList dtSearch = analyticJobDAO.selectMamberPeriod(parameter);

			if (0 < dtSearch.getRowCount()) {
				analyticJobDAO.updateMemberPeriod(parameter);
			} else {
				analyticJobDAO.insertMemberPeriod(parameter);
			}
			updateCnt++;
		}
		return updateCnt;
	}
	
	/**
	 * 탈퇴회원 데이터 적재
	 * @param DataList
	 * @return 
	 */
	public int changeDelMember() {
		int updateCnt = 0;
		Parameter param = DataUtil.makeParameter();
		DataList dt = analyticJobDAO.selectDelMamber(param);
		
		for (int i=0; i<dt.getRowCount(); i++) {
			// 1. 데이터 존재여부 확인
			Parameter parameter = DataUtil.makeParameter();
			parameter.setParameter("YYYY", dt.getString(i, "YYYY"));				// 년도
			parameter.setParameter("MM", dt.getString(i, "MM"));					// 월
			parameter.setParameter("DD", dt.getString(i, "DD"));					// 일
			parameter.setParameter("CD_GENDER", dt.getString(i, "CD_GENDER"));		// 성병
			parameter.setParameter("CD_AGE", dt.getString(i, "CD_AGE"));			// 연령대
			parameter.setParameter("CD_GRADE", dt.getString(i, "CD_GRADE"));		// 등급
			parameter.setParameter("CNT_DEL", dt.getString(i, "CNT_DEL"));			// 탈퇴 회원수
			// 2. 기존 데이터 존재여부 확인
			DataList dtSearch = analyticJobDAO.selectMamberPeriod(parameter);

			if (0 < dtSearch.getRowCount()) {
				analyticJobDAO.updateMemberPeriod(parameter);
			} else {
				analyticJobDAO.insertMemberPeriod(parameter);
			}
			updateCnt++;
		}
		return updateCnt;
	}
	
	/**
	 * 로그인회원 데이터 적재
	 * @param DataList
	 * @return 
	 */
	public int changeLoginMember() {
		int updateCnt = 0;
		Parameter param = DataUtil.makeParameter();
		DataList dt = analyticJobDAO.selectLoginMamber(param);
		
		for (int i=0; i<dt.getRowCount(); i++) {
			// 1. 데이터 존재여부 확인
			Parameter parameter = DataUtil.makeParameter();
			parameter.setParameter("YYYY", dt.getString(i, "YYYY"));				// 년도
			parameter.setParameter("MM", dt.getString(i, "MM"));					// 월
			parameter.setParameter("DD", dt.getString(i, "DD"));					// 일
			parameter.setParameter("CD_GENDER", dt.getString(i, "CD_GENDER"));		// 성병
			parameter.setParameter("CD_AGE", dt.getString(i, "CD_AGE"));			// 연령대
			parameter.setParameter("CD_GRADE", dt.getString(i, "CD_GRADE"));		// 등급
			parameter.setParameter("CNT_LOGIN", dt.getString(i, "CNT_LOGIN"));		// 로그인 회원수
			// 2. 기존 데이터 존재여부 확인
			DataList dtSearch = analyticJobDAO.selectMamberPeriod(parameter);

			if (0 < dtSearch.getRowCount()) {
				analyticJobDAO.updateMemberPeriod(parameter);
			} else {
				analyticJobDAO.insertMemberPeriod(parameter);
			}
			updateCnt++;
		}
		return updateCnt;
	}
	
	/**
	 * 멥버십 리워드 회원 데이터 적재
	 * @param DataList
	 * @return 
	 */
	public int changeRewardMember() {
		int updateCnt = 0;
		Parameter param = DataUtil.makeParameter();
		DataList dt = analyticJobDAO.selectRewardMamber(param);
		
		for (int i=0; i<dt.getRowCount(); i++) {
			// 1. 데이터 존재여부 확인
			Parameter parameter = DataUtil.makeParameter();
			parameter.setParameter("YYYY", dt.getString(i, "YYYY"));				// 년도
			parameter.setParameter("MM", dt.getString(i, "MM"));					// 월
			parameter.setParameter("DD", dt.getString(i, "DD"));					// 일
			parameter.setParameter("CD_GENDER", dt.getString(i, "CD_GENDER"));		// 성병
			parameter.setParameter("CD_AGE", dt.getString(i, "CD_AGE"));			// 연령대
			parameter.setParameter("CD_GRADE", dt.getString(i, "CD_GRADE"));		// 등급
			parameter.setParameter("CNT_REWARD", dt.getString(i, "CNT_REWARD"));	// 멥버십 리워드 회원수
			// 2. 기존 데이터 존재여부 확인
			DataList dtSearch = analyticJobDAO.selectMamberPeriod(parameter);

			if (0 < dtSearch.getRowCount()) {
				analyticJobDAO.updateMemberPeriod(parameter);
			} else {
				analyticJobDAO.insertMemberPeriod(parameter);
			}
			updateCnt++;
		}
		return updateCnt;
	}
	
	/**
	 * 누적 멤버십 리워드 쿠폰수 데이터 적재
	 * @param DataList
	 * @return 
	 */
	public int changeRewardCoupon() {
		int updateCnt = 0;
		Parameter param = DataUtil.makeParameter();
		DataList dt = analyticJobDAO.selectRewardCoupon(param);
		
		for (int i=0; i<dt.getRowCount(); i++) {
			// 1. 데이터 존재여부 확인
			Parameter parameter = DataUtil.makeParameter();
			parameter.setParameter("YYYY", dt.getString(i, "YYYY"));				// 년도
			parameter.setParameter("MM", dt.getString(i, "MM"));					// 월
			parameter.setParameter("DD", dt.getString(i, "DD"));					// 일
			parameter.setParameter("CD_GENDER", dt.getString(i, "CD_GENDER"));		// 성병
			parameter.setParameter("CD_AGE", dt.getString(i, "CD_AGE"));			// 연령대
			parameter.setParameter("CD_GRADE", dt.getString(i, "CD_GRADE"));		// 등급
			parameter.setParameter("CNT_REWARD_COUPON", dt.getString(i, "CNT_REWARD_COUPON"));	// 누적 멤버십 리워드 쿠폰수
			// 2. 기존 데이터 존재여부 확인
			DataList dtSearch = analyticJobDAO.selectMamberPeriod(parameter);

			if (0 < dtSearch.getRowCount()) {
				analyticJobDAO.updateMemberPeriod(parameter);
			} else {
				analyticJobDAO.insertMemberPeriod(parameter);
			}
			updateCnt++;
		}
		return updateCnt;
	}
	
	/**
	 * 주문 테이블 데이터 저장
	 * @param 
	 * @return 
	 */
	public void insertOmniOrder() {
		Parameter param = DataUtil.makeParameter();
		analyticJobDAO.insertOmniOrder(param);
	}
	
	/**
	 * 구매관련 데이터 적재
	 * @param DataList
	 * @return 
	 */
	public int changeOrder() {
		int updateCnt = 0;
		Parameter param = DataUtil.makeParameter();
		DataList dt = analyticJobDAO.selectOrder(param);
		
		for (int i=0; i<dt.getRowCount(); i++) {
			// 1. 데이터 존재여부 확인
			Parameter parameter = DataUtil.makeParameter();
			parameter.setParameter("YYYY", dt.getString(i, "YYYY"));				// 년도
			parameter.setParameter("MM", dt.getString(i, "MM"));					// 월
			parameter.setParameter("DD", dt.getString(i, "DD"));					// 일
			parameter.setParameter("CD_GENDER", dt.getString(i, "CD_GENDER"));		// 성병
			parameter.setParameter("CD_AGE", dt.getString(i, "CD_AGE"));			// 연령대
			parameter.setParameter("CD_GRADE", dt.getString(i, "CD_GRADE"));		// 등급
			parameter.setParameter("CNT_BUY", dt.getString(i, "CNT_BUY"));			// 구매 회원수
			parameter.setParameter("CNT_COUPON", dt.getString(i, "CNT_COUPON"));	// 쿠폰사용 회원수
			// 2. 기존 데이터 존재여부 확인
			DataList dtSearch = analyticJobDAO.selectMamberPeriod(parameter);

			if (0 < dtSearch.getRowCount()) {
				analyticJobDAO.updateMemberPeriod(parameter);
			} else {
				analyticJobDAO.insertMemberPeriod(parameter);
			}
			updateCnt++;
		}
		return updateCnt;
	}

}
