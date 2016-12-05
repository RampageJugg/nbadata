/**
 * WriteMlog.java 2012-8-27 下午2:27:43 author:huaxin
 *
 * Copyright(c) 2000-2012 HC360.COM, All Rights Reserved.
 */
package com.myes.nbadata.monitor;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.myes.nbadata.core.CoreConstants;
/**
 * 
 * @author huaxin
 * @date 2012-8-27 下午2:27:43
 * @version 1.0
 * @since 1.0
 * */
public class WriteMlog {

//	private  WriteMlog mlog = new WriteMlog();
	/** 输出日志类 */
	private Log logger = LogFactory.getLog("monitorLog");

	private long starTime;    //开始时间
	private String state="0"; //默认成功
	
	/** 设置开始时间 */
	public  void setStarTime(long starTime) {
		this.starTime = starTime;
	}

	/** 设置状态 0:成功 1：失败 */
	public  void setState(String state) {
		this.state = state;
	}


	/**
	 * Description ： 在目标文件中输出日志
	 * @param funNum 功能点编号
	 * @param industry 所在当前行业
	 * @param starTime 监控的开始时间
	 */
	public  void Wlog(String funNum) {
		try {			
			/** 计算运行功能点所用时间 为能提高计算时间的精准度，该语句应在首行执行 **/
			String useTime = String.valueOf(System.currentTimeMillis() - this.starTime);
			MonitorUtils.FunctionElement fe = MonitorUtils.getFunctionElementByItemId(funNum);
			String endTime = "[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") .format(new Date()) + "]";
			String monitorFormat = CoreConstants.MEDIAADMIN_MONITOR_CONF;

			// 获取应用节点的IP地址
			String serviceIP=InetAddress.getLocalHost().getHostAddress();
			/** 替换日志串中的变量值 不用区分顺序 **/
			String fmtMon = monitorFormat
					.replace("[ServerIp]", serviceIP)
					.replace("[FunNum]", fe.getFid().toString())
					.replace("[EndTime]", endTime)
					.replace("[UseTime]", useTime)
					.replace("[Industry]", "-")
					.replace("[state]", this.state);
			logger.info(fmtMon);
		} catch (Exception e) {
			System.out.println("写入Monitor监控日志出错... publish/src/com/hc360/cms/monitor/WriteMlog.java");
			e.printStackTrace();
		}
	}
}
