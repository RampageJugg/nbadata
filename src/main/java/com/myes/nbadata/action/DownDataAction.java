package com.myes.nbadata.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myes.nbadata.po.DownData;
import com.myes.nbadata.service.DownDataService;
import com.myes.nbadata.util.ResponseUtil;

/**
 * 
 * 网页数据抓取控制类
 * 
 * @author mengke
 * @version 1.0 2016年12月2日
 * @since 1.0
 */
@Controller
public class DownDataAction {
	
	@Autowired
	private DownDataService downDataService;
	
	/**
	 * 
	 * 根据网页url和字符集获取网页源代码
	 * 解析网页源代码，组装数据到集合
	 * 存入数据库
	 * 重定向到查询方法
	 * 
	 * @param downData
	 * @param attr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("downdata/downdataByHTML.shtml")
	public void downdataByHTML(HttpServletResponse response, DownData downData, RedirectAttributes attr) throws Exception{
		//设置读取网页源代码文件流编码
		downData.setEncoding("utf-8");
		
		List<HashMap<String, String>> dataList = downDataService.downHotelsByHTML(downData);
		
		//转为json数据
		JSONArray jarr = JSONArray.fromObject(dataList);
		
		ResponseUtil.renderJson(response, jarr.toString());
	}
	
}
