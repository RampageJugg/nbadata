package com.myes.nbadata.util;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import com.myes.nbadata.core.CoreConstants;

/**
 * 
 * 关系数据库     ⇒ 数据库 ⇒ 表    ⇒ 行    ⇒ 列(Columns)
 * Elasticsearch  ⇒ 索引   ⇒ 类型  ⇒ 文档  ⇒ 字段(Fields)
 * 
 * @author mengke
 * @version 1.0 2016年11月29日
 * @since 1.0
 */
public class ElasticSearchHandler {

	static Map<String, String> map = new HashMap<String, String>();
	
	static Settings settings = Settings.settingsBuilder().put(map)
		.put("cluster.name", CoreConstants.ES_CLUSTER).build();
	
	//创建私有对象
	private static TransportClient client;
	
	static{
		try{
			client = TransportClient.builder().settings(settings).build();
			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(CoreConstants.ES_HOST), CoreConstants.ES_PORT));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//取得实例
	public static synchronized TransportClient getTransportClient(){
		return client;
	}
	
	/**
	 * 
	 * 建立索引
	 * 
	 * @param indexname 为索引库名，一个es集群中可以有多个索引库。 名称必须为小写
	 * @param type      为索引类型，是用来区分同索引库下不同类型的数据的，一个索引库下可以有多个索引类型。
	 * @param builder   索引结构
	 */
	public static void createIndexResponse(String indexname, String type){
		//创建索引
		client.admin().indices().prepareCreate(indexname).execute().actionGet();
		//创建索引结构
		XContentBuilder builder = getProductMapping();
		PutMappingRequest mapping = Requests.putMappingRequest(indexname).type(type).source(builder);
		client.admin().indices().putMapping(mapping).actionGet();
    }
	//索引结构
	private static XContentBuilder getProductMapping() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * 删除单个索引
	 * 
	 * @param indexname 索引名称
	 */
	public static void deleteIndexResponse(String indexname){
		IndicesExistsRequest iExistsRequest = new IndicesExistsRequest(indexname);
		IndicesExistsResponse inExistsResponse = client.admin().indices().exists(iExistsRequest).actionGet();
		if(inExistsResponse.isExists()){
			client.admin().indices().prepareDelete(indexname).execute().actionGet();
		}
	}
	
	public static void insertDocument(Object object){
		
		String json = generateJson(object);
	}

	private static String generateJson(Object object) {
		String json = "";
		try{
			XContentBuilder contentBuilder = XContentFactory.jsonBuilder().startObject();
			//得到类对象
			Class<?> clazz = object.getClass();
			//得到类中的所有属性的集合
			Field[] fields = clazz.getFields();
			for(int i=0; i<fields.length; i++){
				Field field = fields[i];
				//设置此属性可以访问
				field.setAccessible(true);
				//得到此属性的值
				Object fieldValue = field.get(object);
				//得到此属性的名称
				String fieldName = field.getName();
				//得到此属性的类型
				String fieldType = field.getType().getSimpleName();
				if("Date".equals(fieldType)){
					contentBuilder.field(fieldName, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fieldValue));
				}else {
					contentBuilder.field(fieldName, fieldValue);
				}
			}
			contentBuilder.endObject();
			json = contentBuilder.string();
		}catch(Exception e){
			e.printStackTrace();
		}
		return json;
	}
}
