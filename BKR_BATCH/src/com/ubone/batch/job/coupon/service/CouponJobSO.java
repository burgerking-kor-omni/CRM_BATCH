package com.ubone.batch.job.coupon.service;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ubone.batch.job.coupon.dao.CouponJobDAO;
import com.ubone.framework.ConfigHolder;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.DataUtil;
import com.ubone.framework.data.Parameter;

/** 
 * <pre>
 *  파 일 명  : GradeUpJobSO.java
 *  설    명   : [다우]쿠폰관련 배치잡 Service Object 클래스
 *  작 성 자  :  
 *  작 성 일  : 2018-11-05
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Service
public class CouponJobSO {
	
	@Resource
	private CouponJobDAO couponJobDAO;
	
	Log logger = LogFactory.getLog(CouponJobSO.class);
	
	private String CALL_CTN = ConfigHolder.get("dau.call.ctn");
	private String ISSUE_COUNT = ConfigHolder.get("dau.issue.cnt");
	private String RCV_CTN = ConfigHolder.get("dau.rcv.ctn");
	private String SEND_MSG = ConfigHolder.get("dau.send.msg");
	private String PAY_ID = ConfigHolder.get("dau.pay.id");
	private String BOOKING_NO = ConfigHolder.get("dau.booking.no");
	private String SITE_URL = ConfigHolder.get("dau.site.url");
	
	/**
	 * 신규 쿠폰 초기 핀 발행
	 * @param 
	 * @return 
	 */
	public DataList publishCouponFirst() {
		Parameter parameter = DataUtil.makeParameter();
		DataList dtReturn = DataUtil.makeDataList("getDauCouponInfo");
		
		// 1. 쿠폰핀 발행 대상 조회
		DataList dtData = couponJobDAO.selectDauCouponFirst(parameter);
		
		// 2. 다우 핀 발행 요청
		if (0 < dtData.getRowCount()) {
			
			// 1. 1시간 배치시 발생 조건 조회
			DataList dtPin = couponJobDAO.selectDauCouponConfig(parameter);
			
			int valFirstPin = Integer.parseInt(dtPin.getString(0, "VAL_FIRST_PIN"));	// 초기가져오기개수
			
			for (int i=0; i<dtData.getRowCount(); i++) {
				Parameter dauParam = DataUtil.makeParameter();
				
				// 서버 환경에 따른 값 분기 처리
				if ("real".equals(ConfigHolder.APPLICATION_MODE)) {
					dauParam.setParameter("DAU_DOMAIN", ConfigHolder.get("dau.real.domain"));
					dauParam.setParameter("COOPER_ID", ConfigHolder.get("dau.real.cooper.id"));
					dauParam.setParameter("COOPER_PW", ConfigHolder.get("dau.real.cooper.pw"));
					dauParam.setParameter("SITE_ID", ConfigHolder.get("dau.real.site.id"));
				} else {
					dauParam.setParameter("DAU_DOMAIN", ConfigHolder.get("dau.dev.domain"));
					dauParam.setParameter("COOPER_ID", ConfigHolder.get("dau.dev.cooper.id"));
					dauParam.setParameter("COOPER_PW", ConfigHolder.get("dau.dev.cooper.pw"));
					dauParam.setParameter("SITE_ID", ConfigHolder.get("dau.dev.site.id"));
				}
				dauParam.setParameter("NO_REQ", dtData.getString(i, "NO_REQ"));
				dauParam.setParameter("VALID_START", dtData.getString(i, "DT_PIN_START").replace("-", ""));
				dauParam.setParameter("VALID_END", dtData.getString(i, "DT_PIN_END").replace("-", ""));
				
				for (int j=0; j<valFirstPin; j++) {
					Document doc = getCouponPinXmlDocumentFromDoau(dauParam, "D");
					NodeList nl = doc.getElementsByTagName("CPN_LIST");
					Element docEle = doc.getDocumentElement();
					String rt = docEle.getElementsByTagName("RT").item(0).getTextContent();
				    
				    if (nl != null && nl.getLength() > 0 && "S000001".equals(rt)) {
			            if (nl.item(0).getNodeType() == Node.ELEMENT_NODE) {
			                Element el = (Element) nl.item(0);
			                
//			                Parameter insertParam = DataUtil.makeParameter();
//			                insertParam.setParameter("CD_COUPON", dtTarget.getString(i, "CD_COUPON"));
//			                insertParam.setParameter("PIN_NUM", el.getElementsByTagName("NO_CPN").item(0).getTextContent());
//			                insertParam.setParameter("DT_EXPIRY_START", dtTarget.getString(i, "DT_PIN_START"));
//			                insertParam.setParameter("DT_EXPIRY_END", dtTarget.getString(i, "DT_PIN_END"));
//			                
//			                couponJobDAO.insertCouponPin(insertParam);
			                
			                Map<String, Object> data = new HashMap<String, Object>();
			                data.put("CD_COUPON", dtData.getString(i, "CD_COUPON"));
			                data.put("PIN_NUM", el.getElementsByTagName("NO_CPN").item(0).getTextContent());
			                data.put("DT_EXPIRY_START", dtData.getString(i, "DT_PIN_START"));
			                data.put("DT_EXPIRY_END", dtData.getString(i, "DT_PIN_END"));
			                
			                dtReturn.addRow(data);
			            }
				    }
				    
				}
			}
		}
		return dtReturn;
	}
	
	/**
	 * 1시간 조건에 해당하는 핀 발행
	 * @param 
	 * @return 
	 */
	public DataList publishCouponHour() {
		Parameter parameter = DataUtil.makeParameter();
		DataList dtReturn = DataUtil.makeDataList("getDauCouponInfo");
		
		// 1. 1시간 배치시 발생 조건 조회
		DataList dtData = couponJobDAO.selectDauCouponConfig(parameter);
		
		if (0 < dtData.getRowCount()) {
			int valLimitPin = Integer.parseInt(dtData.getString(0, "VAL_LIMIT_PIN"));	// 남은 쿠폰 개수
			int valBringPin = Integer.parseInt(dtData.getString(0, "VAL_BRING_PIN"));	// 가져오기 개수
			
			// 2. 조건에 해당하는 쿠폰 대상 조회
			Parameter searchParam = DataUtil.makeParameter();
			searchParam.setParameter("VAL_LIMIT_PIN", valLimitPin);
			DataList dtTarget = couponJobDAO.selectDauCouponTagget(searchParam);
			
			if (0 < dtTarget.getRowCount()) {
				for (int i=0; i<dtTarget.getRowCount(); i++) {
					Parameter dauParam = DataUtil.makeParameter();
					
					// 서버 환경에 따른 값 분기 처리
					if ("real".equals(ConfigHolder.APPLICATION_MODE)) {
						dauParam.setParameter("DAU_DOMAIN", ConfigHolder.get("dau.real.domain"));
						dauParam.setParameter("COOPER_ID", ConfigHolder.get("dau.real.cooper.id"));
						dauParam.setParameter("COOPER_PW", ConfigHolder.get("dau.real.cooper.pw"));
						dauParam.setParameter("SITE_ID", ConfigHolder.get("dau.real.site.id"));
					} else {
						dauParam.setParameter("DAU_DOMAIN", ConfigHolder.get("dau.dev.domain"));
						dauParam.setParameter("COOPER_ID", ConfigHolder.get("dau.dev.cooper.id"));
						dauParam.setParameter("COOPER_PW", ConfigHolder.get("dau.dev.cooper.pw"));
						dauParam.setParameter("SITE_ID", ConfigHolder.get("dau.dev.site.id"));
					}
					dauParam.setParameter("NO_REQ", dtTarget.getString(i, "NO_REQ"));
					dauParam.setParameter("VALID_START", dtTarget.getString(i, "DT_PIN_START").replace("-", ""));
					dauParam.setParameter("VALID_END", dtTarget.getString(i, "DT_PIN_END").replace("-", ""));
					
					for (int j=0; j<valBringPin; j++) {
						Document doc = getCouponPinXmlDocumentFromDoau(dauParam, "D");
						NodeList nl = doc.getElementsByTagName("CPN_LIST");
						Element docEle = doc.getDocumentElement();
						String rt = docEle.getElementsByTagName("RT").item(0).getTextContent();

					    if (nl != null && nl.getLength() > 0 && "S000001".equals(rt)) {
				            if (nl.item(0).getNodeType() == Node.ELEMENT_NODE) {
				                Element el = (Element) nl.item(0);
				                
//				                Parameter insertParam = DataUtil.makeParameter();
//				                insertParam.setParameter("CD_COUPON", dtTarget.getString(i, "CD_COUPON"));
//				                insertParam.setParameter("PIN_NUM", el.getElementsByTagName("NO_CPN").item(0).getTextContent());
//				                insertParam.setParameter("DT_EXPIRY_START", dtTarget.getString(i, "DT_PIN_START"));
//				                insertParam.setParameter("DT_EXPIRY_END", dtTarget.getString(i, "DT_PIN_END"));
//				                
//				                couponJobDAO.insertCouponPin(insertParam);
				                
				                Map<String, Object> data = new HashMap<String, Object>();
				                data.put("CD_COUPON", dtTarget.getString(i, "CD_COUPON"));
				                data.put("PIN_NUM", el.getElementsByTagName("NO_CPN").item(0).getTextContent());
				                data.put("DT_EXPIRY_START", dtTarget.getString(i, "DT_PIN_START"));
				                data.put("DT_EXPIRY_END", dtTarget.getString(i, "DT_PIN_END"));
				                
				                dtReturn.addRow(data);
				            }
					    }
					}
				}
			}
		}
		return dtReturn;
	}
	
	/**
	 * 실시간 쿠폰핀 발행
	 * @param 
	 * @return LO
	 */
	public DataList publishCouponAlways() {
		Parameter parameter = DataUtil.makeParameter();
		DataList dtReturn = DataUtil.makeDataList("getDauCouponInfo");
		
		// 1. 실시간 발행 요청 대상 조회
		DataList dtData = couponJobDAO.selectDauPinReq(parameter);
		
		if (0 < dtData.getRowCount()) {
			// 2. 실시간 발행 요청 대상 핀 발행
			for (int i=0; i<dtData.getRowCount(); i++) {
				// 2-1. 대상 상태 변경 - 발행중
				Parameter dauReqParam = DataUtil.makeParameter();
				dauReqParam.setParameter("NO_DAUPIN_REQ", dtData.getString(i, "NO_DAUPIN_REQ"));	// 다우핀 발행요청 PK
				dauReqParam.setParameter("DAUPIN_STATUS", "02");	// 상태(01:발행 신청,02:발행중,99:완료)
				couponJobDAO.updateDauReq(dauReqParam);
				
				// 2-2. 핀발행
				int cntPin = Integer.parseInt(dtData.getString(i, "CNT_PIN"));	// 발행 요청 핀수
				
				Parameter dauParam = DataUtil.makeParameter();
				
				// 서버 환경에 따른 값 분기 처리
				if ("real".equals(ConfigHolder.APPLICATION_MODE)) {
					dauParam.setParameter("DAU_DOMAIN", ConfigHolder.get("dau.real.domain"));
					dauParam.setParameter("COOPER_ID", ConfigHolder.get("dau.real.cooper.id"));
					dauParam.setParameter("COOPER_PW", ConfigHolder.get("dau.real.cooper.pw"));
					dauParam.setParameter("SITE_ID", ConfigHolder.get("dau.real.site.id"));
				} else {
					dauParam.setParameter("DAU_DOMAIN", ConfigHolder.get("dau.dev.domain"));
					dauParam.setParameter("COOPER_ID", ConfigHolder.get("dau.dev.cooper.id"));
					dauParam.setParameter("COOPER_PW", ConfigHolder.get("dau.dev.cooper.pw"));
					dauParam.setParameter("SITE_ID", ConfigHolder.get("dau.dev.site.id"));
				}
				dauParam.setParameter("NO_REQ", dtData.getString(i, "NO_REQ"));
				dauParam.setParameter("VALID_START", dtData.getString(i, "VALID_START").replace("-", ""));
				dauParam.setParameter("VALID_END", dtData.getString(i, "VALID_END").replace("-", ""));
				dauParam.setParameter("COOPER_ORDER", dtData.getString(i, "COOPER_ORDER"));
				
				for (int j=0; j<cntPin; j++) {
					Document doc = getCouponPinXmlDocumentFromDoau(dauParam, "S");
					NodeList nl = doc.getElementsByTagName("CPN_LIST");
					Element docEle = doc.getDocumentElement();
					String rt = docEle.getElementsByTagName("RT").item(0).getTextContent();

				    if (nl != null && nl.getLength() > 0 && "S000001".equals(rt)) {
			            if (nl.item(0).getNodeType() == Node.ELEMENT_NODE) {
			                Element el = (Element) nl.item(0);
			                
//			                Parameter insertParam = DataUtil.makeParameter();
//			                insertParam.setParameter("CD_COUPON", dtData.getString(i, "CD_COUPON"));
//			                insertParam.setParameter("PIN_NUM", el.getElementsByTagName("NO_CPN").item(0).getTextContent());
//			                insertParam.setParameter("DT_EXPIRY_START", dtData.getString(i, "VALID_START"));
//			                insertParam.setParameter("DT_EXPIRY_END", dtData.getString(i, "VALID_END"));
//			                
//			                couponJobDAO.insertCouponPin(insertParam);
			                
			                Map<String, Object> data = new HashMap<String, Object>();
			                data.put("CD_COUPON", dtData.getString(i, "CD_COUPON"));
			                data.put("PIN_NUM", el.getElementsByTagName("NO_CPN").item(0).getTextContent());
			                data.put("DT_EXPIRY_START", dtData.getString(i, "VALID_START"));
			                data.put("DT_EXPIRY_END", dtData.getString(i, "VALID_END"));
			                
			                dtReturn.addRow(data);
			            }
				    }
				}
				// 2-3. 대상 상태 변경 - 완료
				dauReqParam.setParameter("DAUPIN_STATUS", "99");	// 상태(01:발행 신청,02:발행중,99:완료)
				couponJobDAO.updateDauReq(dauReqParam);
			}
			
		}
		return dtReturn;
	}
	
	
	/**
	 * 신규 쿠폰 초기 핀 발행
	 * @param 
	 * @return 
	 */
	private Document getCouponPinXmlDocumentFromDoau(Parameter parameter, String gubun) {
		Document doc = null;
		try {
			String cooperOrder = UUID.randomUUID().toString();
			if ("S".equals(gubun)) {	// 실시간 요청일경우 저장되어 있는 데이터 사용
				cooperOrder = parameter.getParameters("COOPER_ORDER").toString();
			}
			String daouProductListApiUrl = "/B2CCoupon/B2CService.aspx?ACTION=CI112_ONLY_ISSUECPN_WITHPAY";
			StringBuilder urlBuilder = new StringBuilder();
			urlBuilder.append(parameter.getParameter("DAU_DOMAIN").toString());
			urlBuilder.append(daouProductListApiUrl);
			urlBuilder.append("&COOPER_ID=" + parameter.getParameter("COOPER_ID").toString());
			urlBuilder.append("&COOPER_PW=" + parameter.getParameter("COOPER_PW").toString());
			urlBuilder.append("&SITE_ID=" + parameter.getParameter("SITE_ID").toString());
			urlBuilder.append("&NO_REQ=" + parameter.getParameter("NO_REQ").toString());
			urlBuilder.append("&COOPER_ORDER=" + cooperOrder);
			urlBuilder.append("&VALID_START=" + parameter.getParameter("VALID_START").toString());
			urlBuilder.append("&VALID_END=" + parameter.getParameter("VALID_END").toString());
			
			urlBuilder.append("&SEND_MSG=" + SEND_MSG);
			urlBuilder.append("&ISSUE_COUNT=" + ISSUE_COUNT);
			urlBuilder.append("&CALL_CTN=" + CALL_CTN);
			urlBuilder.append("&RCV_CTN=" + RCV_CTN);
			urlBuilder.append("&PAY_ID=" + PAY_ID);
			urlBuilder.append("&BOOKING_NO=" + BOOKING_NO);
			urlBuilder.append("&SITE_URL=" + SITE_URL);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			URL url = new URL(urlBuilder.toString());
			doc = dBuilder.parse(url.openStream());
			
		} catch(Exception e) {
			logger.debug(e.getMessage());
		}
		return doc;
	}

}
