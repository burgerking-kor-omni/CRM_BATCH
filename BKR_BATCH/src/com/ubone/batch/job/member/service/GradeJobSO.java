package com.ubone.batch.job.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ubone.batch.job.member.dao.GradeJobDAO;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.DataUtil;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : GradeUpJobSO.java
 *  설    명   : 회원등급 배치잡 Service Object 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-11-05
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Service
public class GradeJobSO {
	
	@Resource
	private GradeJobDAO gradeJobDAO;
	
	/**
	 * 회원 스템프 조회 - 전일자 누적이 있는 회원
	 * @param 
	 * @return 
	 */
	public DataList selectMemberInfo() {
		Parameter parameter = DataUtil.makeParameter();
		
		// 1. 전일자 기준 스템프 적립이 있는 회원 목록 조회
		DataList dtMember = gradeJobDAO.selectMemberInfo(parameter);
		
		return dtMember;
	}
	
	/**
	 * 등급 조정
	 * @param DataList
	 * @return 
	 */
	public int updateGradeUp(DataList dt) {
		int updateCnt = 0;
		
		// 1. 스템프 변경이 있던 회원의 기존등급 확인
		for (int i=0; i<dt.getRowCount(); i++) {
			Parameter parameter = DataUtil.makeParameter();
			parameter.setParameter("ID_MEMBER", dt.getString(i, "ID_MEMBER"));	// 회원PK	
			DataList dtMemberGrade = gradeJobDAO.selectMemberGrade(parameter);
			
			// 2. 회원의 누적스템프 개수에 따른 등급 조정
			if (0 < dtMemberGrade.getRowCount()) {
				// 2-1. 등급기준조회
				Parameter changParam = DataUtil.makeParameter();
				changParam.setParameter("CNT_STAMP_STACK", dt.getString(i, "CNT_STAMP_STACK"));	// 누적스템프개수
				changParam.setParameter("CD_GRADE", dtMemberGrade.getString(0, "CD_GRADE"));	// 회원등급
				DataList dtGradeInfo = gradeJobDAO.selectGradeInfo(changParam);
				
				// 2-2. 회원등급변경
				if (0 < dtGradeInfo.getRowCount()) {
					if ("Y".equals(dtGradeInfo.getString(0, "TP_CHNG"))) {
						parameter.setParameter("CD_GRADE", dtGradeInfo.getString(0, "CD_GRADE"));	// 변경될 회원등급
						parameter.setParameter("TP_GRADE", "U");									// 등급구분
						gradeJobDAO.updateMemberGrade(parameter);
						updateCnt++;
					}
				}
			}
		}
		
		return updateCnt;
	}
	
	/**
	 * 하향대상 회원 조회
	 * @param 
	 * @return 
	 */
	public int updateGradeDownTarget() {
		int updateCnt = 0;
		
		Parameter parameter = DataUtil.makeParameter();
		
		// 1. 하향기준조회
		DataList dtDownStand = gradeJobDAO.selectDownStand(parameter);
		
		// 2. 하향대상 회원 조회
		if (0 < dtDownStand.getRowCount()) {
			parameter.setParameter("CD_DOWN_STAD", dtDownStand.getString(0, "CD_DOWN_STAD"));	// 하향기준 - 월
			DataList dtDownTarget = gradeJobDAO.selectGradeDownTarget(parameter);	
			
			if (0 < dtDownTarget.getRowCount()) {
				for (int i=0; i<dtDownTarget.getRowCount(); i++) {
					// 3. 등급변경일로 부터 적립된 스탬프 갯수에 따른 맴버십 등급 조정
					Parameter afterParam = DataUtil.makeParameter();
					afterParam.setParameter("ID_MEMBER", dtDownTarget.getString(i, "ID_MEMBER"));
					afterParam.setParameter("DT_CHNG_GRADE", dtDownTarget.getString(i, "DT_CHNG_GRADE"));
					DataList dtAfter = gradeJobDAO.selectAfterStamp(afterParam);
					
					
					// 4. 이후 적립이력이 있을때 등급별 적립개수 확인
					if (0 < dtAfter.getRowCount()) {						
						Parameter chkParam = DataUtil.makeParameter();
						chkParam.setParameter("CD_GRADE", dtDownTarget.getString(i, "CD_GRADE"));
						chkParam.setParameter("CNT_STAMP_INCRE", dtAfter.getString(0, "CNT_STAMP_INCRE"));
						DataList dtChk = gradeJobDAO.selectChkGrade(chkParam);
						
						// 5. 이후 적립개수가 해당 등급의 기준보다 작으면 등급 하향
						if (0 < dtChk.getRowCount()) {
							if ("N".equals(dtChk.getString(0, "FLAG_YN"))) {
								Parameter changParam = DataUtil.makeParameter();
								changParam.setParameter("ID_MEMBER", dtDownTarget.getString(i, "ID_MEMBER"));	// 회원PK
								changParam.setParameter("TP_GRADE", "D");										// 등급구분
								changParam.setParameter("CD_GRADE", dtDownTarget.getString(i, "CD_GRADE_CHNG"));// 하향등급
								gradeJobDAO.updateMemberGrade(changParam);
								updateCnt++;
							}
						}
					} else {
						// 6. 이후 적립이력이 없으면 등급하향
						Parameter changParam = DataUtil.makeParameter();
						changParam.setParameter("ID_MEMBER", dtDownTarget.getString(i, "ID_MEMBER"));	// 회원PK
						changParam.setParameter("TP_GRADE", "D");										// 등급구분
						changParam.setParameter("CD_GRADE", dtDownTarget.getString(i, "CD_GRADE_CHNG"));// 하향등급
						gradeJobDAO.updateMemberGrade(changParam);
						updateCnt++;
					}
				}
			}
		}
		
		return updateCnt;
	}

}
