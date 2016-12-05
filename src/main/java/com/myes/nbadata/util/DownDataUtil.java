package com.myes.nbadata.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * 网页大数据抓取工具类
 * 
 * @author mengke
 * @version 1.0 2016年12月1日
 * @since 1.0
 */
public class DownDataUtil {

	/**
	 * 
	 * 根据网页url和字符集获取网页源代码
	 * @author mengke
	 * @param url 网页url
	 * @param encoding 网页字符集
	 * @return String 网页源代码
	 */
	public static String getHTMLSourceCodeByUrl(String url, String encoding){
		
		StringBuffer buffer = new StringBuffer();
		URL urlObject = null;
		URLConnection uc = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			//建立网络连接
			urlObject = new URL(url);
			//打开网络链接
			uc = urlObject.openConnection();
			//建立文件写入流
			isr = new InputStreamReader(uc.getInputStream(), encoding);
			//建立文件缓存流
			br = new BufferedReader(isr);
			
			//创建临时文件
			String temp = null;
			while((temp = br.readLine()) != null){
				buffer.append(temp+"\n");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("网络连接不给力，请检查网络设置。");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("网络链接打开失败，请稍后重试^_^");
		} finally {
			try {
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 
	 * 解析网页源代码,组装数据到集合
	 * @author mengke
	 * @param htmlStr 网页源代码
	 * @return List<HashMap<String, String>> 数据集合
	 */
	public static List<HashMap<String, String>> getDataFromHTML(String htmlStr){
		
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		//解析网页源代码
		Document document = Jsoup.parse(htmlStr);
		
		//获取酒店信息列表
		Elements elements = document.getElementsByClass("searchresult_list");
		
		for(Element element : elements){
			HashMap<String, String> map = new HashMap<String, String>();
			//获取酒店图片地址
			String imgSrc = element.getElementsByTag("img").attr("src");
			//获取酒店title
			String title = element.getElementsByTag("img").attr("alt");
			//获取酒店描述信息
			String desc = element.getElementsByClass("searchresult_htladdress").text();
			//获取酒店价格
			String price = element.getElementsByClass("J_price_lowList").text();
			map.put("imgSrc", imgSrc);
			map.put("title", title);
			map.put("desc", desc);
			map.put("price", price);
			System.out.println(map);
			list.add(map);
		}
		return list;
	}
	
	public static void main(String[] args) {
		// 1. 根据网页url和字符集获取网页源代码
		String url = "http://hotels.ctrip.com/hotel/beijing1#ctm_ref=ctr_hp_sb_lst";
		String encoding = "utf-8";
		String html = getHTMLSourceCodeByUrl(url, encoding);
		// 2. 解析网页源代码，组装数据到集合
		getDataFromHTML(html);
		// 3. 存入数据库
		
	}

}
