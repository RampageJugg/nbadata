/**
 * LogConfig.java 2012-8-27 下午2:27:25 author:huaxin
 *
 * Copyright(c) 2000-2012 HC360.COM, All Rights Reserved.
 */
package com.myes.nbadata.monitor;
/**
 *
 * @author huaxin
 * @date 2012-8-27 下午2:27:25
 * @version 1.0
 * @since 1.0
 * */
public enum LogConfig {
    
	MEDIA_SERVICE_ORDER("160001"),
    MEDIA_USER_SSO("160002"),
    MEDIA_CONTRACT_LIST("160003"),
    MEDIA_CONTRACT_ADDORDER("160004"),
    MEDIA_CONTRACT_INVALID("160005"),
    MEDIA_CONTRACT_SEND("160006"),
    MEDIA_CONTRACT_BACKIMG("160007"),
    MEDIA_CONTRACT_SAVE("160008"),
    MEDIA_CONTRACT_UPDATE("160009"),
    MEDIA_CONTRACTS_SUBJECT_SAVE("160010"),
    MEDIA_CONTRACTS_SUBJECT_UPDATE("160011"),
	MEDIA_CONTRACTS_AUDIT("160012"),
	MEDIA_DXCRM_LIST("160013"),
	MEDIA_FAX_LIST("160014"),
	MEDIA_CONTRACT_UPDATEORDER("160015"),
	MEDIA_DIC_ADDCOMPANY("160016"),
	MEDIA_DIC_UPDATECOMPANY("160017");
	private LogConfig(String code){
		this.code = code;
	}
	
	
	private String code;
	
	public String getCode() {
		return code;
	}
}
