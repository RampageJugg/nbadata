package com.myes.nbadata.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myes.nbadata.dao.HotelMapper;
import com.myes.nbadata.po.DownData;
import com.myes.nbadata.po.Hotel;
import com.myes.nbadata.service.DownDataService;
import com.myes.nbadata.util.DownDataUtil;

/**
 * 
 * 网页数据抓取实现类
 * 
 * @author mengke
 * @version 1.0 2016年12月2日
 * @since 1.0
 */
@Service
public class DownDataServiceImpl implements DownDataService {

	@Autowired
	private HotelMapper hotelMapper;
	
	@Override
	public List<HashMap<String, String>> downHotelsByHTML(DownData downData) throws Exception {
		List<HashMap<String, String>> dataList = null;
		
		if(downData != null){
			// 1. 根据网页url和字符集获取网页源代码
			String html = DownDataUtil.getHTMLSourceCodeByUrl(downData.getUrl(), downData.getEncoding());
			// 2. 解析网页源代码，组装数据到集合
			dataList = DownDataUtil.getDataFromHTML(html);
			// 3. 存入数据库
			for(HashMap<String, String> map : dataList){
				Hotel hotel = new Hotel();
				hotel.setImgsrc(map.get("imgSrc")==null?"":map.get("imgSrc"));
				hotel.setTitle(map.get("title")==null?"":map.get("title"));
				hotel.setDescription(map.get("desc")==null?"":map.get("desc"));
				hotel.setPrice(map.get("price")==null?"":map.get("price"));
				hotel.setUrl(downData.getUrl());
				hotelMapper.insertSelective(hotel);
			}
		}
		
		return dataList;
	}

}
