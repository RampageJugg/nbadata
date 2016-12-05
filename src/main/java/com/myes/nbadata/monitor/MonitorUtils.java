/**
 * MonitorUtils.java 2012-8-27 下午6:35:38 author:huaxin
 *
 * Copyright(c) 2000-2012 HC360.COM, All Rights Reserved.
 */
package com.myes.nbadata.monitor;

import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.myes.nbadata.core.CoreConstants;

/**
 *方法描述：获取配置文件参数帮助类
 * @author huaxin
 * @date 2012-8-27 下午6:35:38
 * @version 1.0
 * @since 1.0
 * */
public class MonitorUtils {
private MonitorUtils(){}
	
	private static XMLConfiguration xmlConfig = new XMLConfiguration();
	
	static
	{
		try 
		{			
			xmlConfig.setFileName(new ClassPathResource(CoreConstants.MEDIAADMIN_MONITOR_LOGFILE).getFile().getAbsolutePath());				
			xmlConfig.load();
		} 
		catch (ConfigurationException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * 方法描述：获取配置文件信息辅助方法
	 * 创建人：panly
	 * @param：itemId
	 * @return：fe
	 */
	public static FunctionElement getFunctionElementByItemId(String itemId)
	{
		FunctionElement fe = new FunctionElement();
		String fv = null;
		Integer fid = null;
		Integer level = null;
		Document doc = xmlConfig.getDocument();
		NodeList nodeList = doc.getElementsByTagName("item");
		for (int i = 0; i < nodeList.getLength(); i++) 
		{
			String id = xmlConfig.getString("item("+i+")[@id]");
			if(itemId.equals(id))
			{
				fid = xmlConfig.getInt("item("+i+")[@fid]");
				fv = xmlConfig.getString("item("+i+")[@value]");
				level = xmlConfig.getInt("item("+i+")[@level]");
				fe.setFid(fid);
				fe.setFv(fv);
				fe.setLevel(level);
				break;
			}
		}
		return fe;
	}
	/**
	 * 方法描述：静态方法，给相应参数赋值
	 * 创建人：panly	 
	 */
	public static class FunctionElement
	{
		private Integer fid = null;
		private String fv = null;
		private Integer level = 0; 
		FunctionElement(){}
		
		FunctionElement(Integer fid,String fv,Integer level)
		{
			this.fid = fid;
			this.fv = fv;
			this.level = level;
		}
		/**
		 * 获取level的值
		 * @return level
		 */
		public Integer getLevel() {
			return level;
		}
		/**
		 * 设置level的值
		 * @param level
		 */
		public void setLevel(Integer level) {
			this.level = level;
		}
		/**
		 * 获取fid的值
		 * @return fid
		 */
		public Integer getFid() {
			return fid;
		}
		/**
		 * 设置fid的值
		 * @param fid
		 */
		public void setFid(Integer fid) {
			this.fid = fid;
		}
		/**
		 * 获取fv的值
		 * @return fv
		 */
		public String getFv() {
			return fv;
		}
		/**
		 * 设置fv的值
		 * @param fv
		 */
		public void setFv(String fv) {
			this.fv = fv;
		}
		
		public String toString()
		{
			return "fid:"+this.getFid()+",fv:"+this.getFv()+",level:"+this.level;
		}
	}
}
