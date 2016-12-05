/**
 * Profile.java 2012-6-18 上午10:34:40 author:huaxin
 *
 * Copyright(c) 2000-2012 HC360.COM, All Rights Reserved.
 */
package com.myes.nbadata.core;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 系统配置文件解析
 * @author liusz
 * @date 2013-2-04 上午11:22:10
 * @version 1.0
 * @since 1.0
 * */
public final class Profile extends PropertiesConfiguration{

	private static Profile profile = null;
	private static String webPath = null;
	
	private Profile(){}
	
	static
	{
		profile = new Profile();
		
		try 
		{
			profile.load(Profile.class.getResourceAsStream("/conf/sysprofile.properties"));
//			webPath = Profile.class.getClassLoader().getResource("/").getPath();
//			int index = webPath.indexOf("WEB-INF");
//			if(index!=-1){
//				webPath = webPath.substring(0, index);
//			}
		} 
		catch (ConfigurationException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static String getWebPath()
	{	
		return webPath;
	}
	
	public static Profile getInstance()
	{
		return profile;
	}
	

}
