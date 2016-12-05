/**
 * CoreConstants.java 2013-1-29 下午4:17:03 author:liusz
 *
 * Copyright(c) 2000-2012 HC360.COM, All Rights Reserved.
 */
package com.myes.nbadata.core;

/**
 *
 * @author liusz
 * @date 2013-1-29 下午4:17:03
 * @version 1.0
 * @since 1.0
 * */
public class CoreConstants {
	
	/**
     * 项目名称
     */
    public static final String PROJECT_NAME="project.name";

    /**
     * ES集群ip
     */
    public static final String ES_HOST=Profile.getInstance().getString("es.host");
	
	/**项目名称**/
	public static final String SYS_PROJECT_NAME = Profile.getInstance().getString("project.name");
	
	public static final String  ES_CLUSTER=Profile.getInstance().getString("es.cluster.name");
	public static final int 	ES_PORT=Integer.valueOf(Profile.getInstance().getString("es.port"));
	public static final String	ES_INDEX=Profile.getInstance().getString("es.index");
	public static final String	ES_PRODUCT_TYPE=Profile.getInstance().getString("es.product.type");
	public static final String	ES_ORDER_DESC=Profile.getInstance().getString("es.order.desc");
	public static final String	ES_ORDER_ASC=Profile.getInstance().getString("es.order.asc");
	
	public static final String MEDIAADMIN_MONITOR_CONF = Profile.getInstance().getString("project.monitorFormat");
	
	public static final String MEDIAADMIN_MONITOR_LOGFILE = "logfunction.xml";
	
}
