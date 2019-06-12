package com.ubone.batch.utils;

import com.ubone.batch.core.dbcp.EncryptionConfigtDataSource;
import com.ubone.framework.security.util.AESUtils;

public class EncryptTest {
	public static void main(String[] args){
		EncryptionConfigtDataSource.encTest("VMD");
		
		System.out.println(AESUtils.decrypt("http://www.ubqone.com", "BD1A99906A4FC7875CADFB54CD23D7DD"));
	}
}
