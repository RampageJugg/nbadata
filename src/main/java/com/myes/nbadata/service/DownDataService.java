package com.myes.nbadata.service;

import java.util.HashMap;
import java.util.List;

import com.myes.nbadata.po.DownData;

/**
 * 
 * 网页数据抓取接口
 * 
 * @author mengke
 * @version 1.0 2016年12月2日
 * @since 1.0
 */
public interface DownDataService {

	List<HashMap<String, String>> downHotelsByHTML(DownData downData) throws Exception;
}
