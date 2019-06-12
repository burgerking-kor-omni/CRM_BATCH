package com.ubone.batch.job.dispatch.service;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;

import com.ubone.batch.job.dispatch.dao.DispatchJobDAO;
import com.ubone.batch.utils.ApplicationContextProvider;
import com.ubone.batch.utils.XssUtils;
import com.ubone.framework.ConfigHolder;
import com.ubone.framework.data.DataList;
import com.ubone.framework.data.DataUtil;
import com.ubone.framework.data.Parameter;
import com.ubone.interfaces.mail.MimeMailService;

/** 
 * <pre>
 *  파 일 명  : DispatchJobSO.java
 *  설    명   : 통보실행 배치잡 Service Object 클래스
 *  작 성 자  :  
 *  작 성 일  : 2019-01-10
 *  버    전   : 1.0
 *  수정이력 :
 *  기타사항 :
 * </pre>
 * @author Copyrights 2015 by ㈜ 유비원. All right reserved.
 */
@Service
public class DispatchJobSO {
	
	@Resource
	private DispatchJobDAO dispatchJobDAO;
	
	@Resource
	private MimeMailService mimeMailService;
	
	Log logger = LogFactory.getLog(DispatchJobSO.class);
	
	
	/**
	 * 신규 쿠폰 초기 핀 발행
	 * @param 
	 * @return 
	 */
	public int sendMail() {
		int nCnt = 0;
		Parameter parameter = DataUtil.makeParameter();
		
		// 0. 메일 발송 조회
		DataList dtDispatch = dispatchJobDAO.selectDispatch(parameter);
		
		if (0 < dtDispatch.getRowCount()) {
			for (int i=0; i<dtDispatch.getRowCount(); i++) {
				// 1. 메일 발송 대상 조회
				Parameter param = DataUtil.makeParameter();
				param.setParameter("ID_DISPATCH_RUN", dtDispatch.getString(i, "ID_DISPATCH_RUN"));
				DataList dtDispatchList = dispatchJobDAO.selectDispatchList(param);
				
				String status = "O0";
				String resultMsg = "";
				String toMail = "";
				String attachUrl = "";
				
				if (0 < dtDispatchList.getRowCount()) {
					for (int j=0; j<dtDispatchList.getRowCount(); j++) {
						// 운영이 아닐땐 테스트 메일로 발송
						toMail = dtDispatchList.getString(j, "DS_EMAIL_RECV");

						if (!"real".equals(ConfigHolder.APPLICATION_MODE)) {
							toMail = "woosukcc@ubqone.com";
							attachUrl = "https://an2-bkr-dev-omni.s3.ap-northeast-2.amazonaws.com/";
						} else {
							attachUrl = "https://an2-bkr-prd-omni.s3.ap-northeast-2.amazonaws.com/";
						}
						try {
							Parameter paramVoc = DataUtil.makeParameter();
							paramVoc.setParameter("ID_BIZ_KEY", dtDispatchList.getString(j, "ID_BIZ_KEY"));
							DataList dtVocFile = dispatchJobDAO.selectVocFile(paramVoc);
							
							ArrayList<String> fileNames = new ArrayList<String>();
							ArrayList<InputStreamSource> files = new ArrayList<InputStreamSource>();
							
							// 메일전송이 완료되기 전까지 리소스 객체를 유지해야 하기 때문에 로컬변수로 생성할 경우 Overwrite로 객체 유효성이 끊어짐.!!
							ArrayList<org.springframework.core.io.Resource> attachFiles = new ArrayList<org.springframework.core.io.Resource>();
							
							
							// 직원에게만 첨부파일 발송
							if ("02".equals(dtDispatchList.getString(j, "TP_DISPATCH_TARGET"))) {
								if (0 < dtVocFile.getRowCount()) {
									for (int k=0; k<dtVocFile.getRowCount(); k++) {
										String fileUrl = attachUrl + dtVocFile.getString(k, "FILE_KEY");
										attachFiles.add(ApplicationContextProvider.getApplicationContext().getResource(fileUrl));
										
										fileNames.add(dtVocFile.getString(k, "FILE_NM"));
										files.add(attachFiles.get(k));
									}
								}
							}

							mimeMailService.sendMail(
									  new InternetAddress("bkr-noreply@bkr.co.kr", "비케이알")
									, InternetAddress.parse(toMail, false)
									, dtDispatchList.getString(j, "DS_TITLE_DISPATCH")
									, XssUtils.reconvertXss(dtDispatchList.getString(j, "DS_DISPATCH"))
									, fileNames
									, files);
							
							Parameter paramTarget = DataUtil.makeParameter();
							paramTarget.setParameter("ID_DISPATCH_RUN_TARGET", dtDispatchList.getString(j, "ID_DISPATCH_RUN_TARGET"));
							paramTarget.setParameter("SEND_YN", "Y");
							dispatchJobDAO.updateDispatchTarget(paramTarget);
							nCnt++;
							
							logger.debug("EMail Sent Successfully!!");
						} catch (Exception e) {
							e.printStackTrace();
							status = "F0";
							resultMsg = e.getMessage();
						}
					}
				}
				param.setParameter("TP_STATUS_DISPATCH", status);
				param.setParameter("DS_DISPATCH_RSLT", resultMsg);
				
				dispatchJobDAO.updateDispatch(param);
			}
		}
		return nCnt;
	}
}
