package com.myes.nbadata.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import com.myes.nbadata.monitor.LogConfig;
import com.myes.nbadata.monitor.WriteMlog;
import com.myes.nbadata.util.StringUtil;

/**
 * 
 * @author liusz
 * @date 2013-1-21 下午1:13:00
 * @version 1.0
 * @since 1.0
 * */
public class LogAspect {
	/**
	 * 对所有Controller类，方法含有@OperationDescription，进行@Around拦截
	 */
	public Object advice(ProceedingJoinPoint joinPoint,OperationDescription annotation) {
		Object result=null;
		String statu="0";
		long starTime = System.currentTimeMillis();
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			statu="1";
			e.printStackTrace();
		}finally{
			String[] parem = annotation.getOperFun();
			LogConfig[] types = annotation.type();
			Object [] objs = joinPoint.getArgs();
			int index = StringUtil.getArrayIndex(objs[0], parem);
			if(parem.length==types.length && ("ALL".equals(parem[0]) || index>-1)){
				index="ALL".equals(parem[0])?0:index;
				WriteMlog mlog = new WriteMlog();
				mlog.setStarTime(starTime);// 监控日志设置开始时间		
				mlog.setState(statu);				
				mlog.Wlog(types[index].getCode());// 写监控日志
			}
		}
		return result;
	}
}
