package main;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DocumentAPI {

//	创建文档
	public static void create() {
		Map<String,String> map=new HashMap<>();
		map.put("first_name", "海涛");
		map.put("last_name", "欧");
		map.put("age", "21");
		Client client=ClientAPI.connect();
		System.out.println(client.prepareIndex("megacorp", "employee").setSource(map).get().getResult());
		client.close();
	}
//	查询
	public static void get(){
		Client client=ClientAPI.connect();
		System.out.println(client.prepareGet("megacorp", "employee", "1")
			.get());
		client.close();
	}
	
	public static void multiGet() {
		MultiGetResponse responses = ClientAPI.connect().prepareMultiGet()
			.add("megacorp","employee","1","2","3")
			.get();
		for (MultiGetItemResponse ItemResponse : responses.getResponses()) {
			GetResponse response=ItemResponse.getResponse();
			if (response.isExists()){
				System.out.println(response.getSourceAsString());
			}
		}
	}
//	删除
	public static void delete(){
		System.out.println(ClientAPI.connect().prepareDelete("megacorp", "employee", "0UDE3moBugXN2YBNOI9h").get());
	}
//	通过查询删除
	public static void deleteByQuery(){
		System.out.println(DeleteByQueryAction.INSTANCE.newRequestBuilder(ClientAPI.connect())
			.filter(QueryBuilders.matchQuery("first_name", "海涛"))
			.source("megacorp")
			.get().toString());
	}
//	通过查询删除，使用回调
	public static void deleteByQueryWithListener(){
		DeleteByQueryAction.INSTANCE.newRequestBuilder(ClientAPI.connect())
			.filter(QueryBuilders.matchQuery("first_name", "海涛"))
			.source("megacorp")
			.execute(new ActionListener<BulkByScrollResponse>() {
				@Override
				public void onResponse(BulkByScrollResponse bulkByScrollResponse) {
					System.out.println(bulkByScrollResponse.toString());
				}
				
				@Override
				public void onFailure(Exception e) {
					e.printStackTrace();
				}
			});
	}
	
	public static void updateByUpdateRequest(){
		UpdateRequest updateRequest=new UpdateRequest();
		updateRequest.index("megacorp");
		updateRequest.type("employee");
		updateRequest.id("1");
		updateRequest.doc(new HashMap<String,String>(){
			{
				put("age", "20");
			}
		});
		try {
			System.out.println(ClientAPI.connect().update(updateRequest).get().toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateByPrepareUpdate(){
		System.out.println(ClientAPI.connect().prepareUpdate("megacorp", "employee", "1")
			.setDoc(new HashMap<String,String>(){
				{
					put("age", "21");
				}
			}).get().toString());
	}
	
	public static void updateByQuery(){
//		使用方法参考deleteByQuery
	}
	
//	更新，如果不存在则创建
	public static void upsert(){
		UpdateRequest updateRequest=new UpdateRequest();
		updateRequest.index("megacorp");
		updateRequest.type("employee");
		updateRequest.id("1");
		updateRequest.doc(new HashMap<String,String>(){
			{
				put("age", "20");
			}
		}).upsert(new HashMap<String,String>(){
			{
				put("age", "20");
			}
		});
		try {
			System.out.println(ClientAPI.connect().update(updateRequest).get().toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
//	bulk
	public static void bulk(){
		Client client=ClientAPI.connect();
		BulkRequestBuilder bulkRequestBuilder=client.prepareBulk();
		bulkRequestBuilder
			.add(client.prepareIndex("megacorp", "employee","123")
				.setSource(new HashMap<String,String>(){
					{
						put("age", "123");
					}
				}))
			.add(client.prepareIndex("megacorp", "employee","1234")
				.setSource(new HashMap<String,String>(){
					{
						put("age", "1234");
					}
				}));
		BulkResponse responses=bulkRequestBuilder.get();
		if (responses.hasFailures()){
//			失败
		}
	}
	
	public static void main(String[] args) {
		multiGet();
	}
}
