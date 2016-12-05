package com.myes.nbadata.po;

/**
 * 
 * 抓取网页url和字符集参数实体类
 * 
 * @author mengke
 * @version 1.0 2016年12月2日
 * @since 1.0
 */
public class DownData {

	//抓取网页url
	private String url;
	
	//网页字符集
	private String encoding;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
}
