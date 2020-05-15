package main;

import org.elasticsearch.action.search.*;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.sort.SortOrder;

import java.util.Arrays;

public class SearchAPI {
	
	public static void search(){
		QueryBuilder queryBuilder=QueryBuilders.boolQuery().
			filter(QueryBuilders.termQuery("last_name", "smith"))
			.must(QueryBuilders.existsQuery("first_name"))
			.must(QueryBuilders.existsQuery("age"))
//			.must(QueryBuilders.termQuery("first_name", "jane"))
		;
		SearchResponse response=SearchAction.INSTANCE
			.newRequestBuilder(ClientAPI.connect())
			.setQuery(queryBuilder)
			.addSort("age", SortOrder.DESC)
			.setTerminateAfter(10)
			.get();
		System.out.println(response.isTerminatedEarly());
		System.out.println(response.toString());
//		SearchResponse response=ClientAPI.connect().prepareSearch("megacorp")
//			.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//			.setQuery(QueryBuilders.termQuery("first_name", "john"))
//			.setPostFilter(QueryBuilders.rangeQuery("age").from("19").to("22"))
//			.setFrom(0).setSize(60).setExplain(false).get();
//		System.out.println(response.toString());
	}
	
	public static void scroll(){
		Client client=ClientAPI.connect();
		SearchResponse searchResponse=client
			.prepareSearch("megacorp")
			.setScroll(new TimeValue(6000))
			.setSize(1)
			.get();
		do{
			for (SearchHit hit:searchResponse.getHits().getHits()) {
				System.out.println(hit.getSourceAsString());
			}
			searchResponse = client.prepareSearchScroll(searchResponse.getScrollId()).setScroll(new TimeValue(6000)).execute().actionGet();
		}while (searchResponse.getHits().getHits().length!=0);
//		清除滚屏
		ClientAPI.connect().prepareClearScroll().addScrollId(searchResponse.getScrollId()).get();
		client.close();
	}
//	复杂搜索
	public static void multiSearch(){
		Client client=ClientAPI.connect();
		MultiSearchResponse response = client.prepareMultiSearch()
			.add(client.prepareSearch().setQuery(QueryBuilders.termQuery("first_name", "douglas")))
			.add(client.prepareSearch().setQuery(QueryBuilders.termQuery("last_name", "smith")))
		.get();
		for (MultiSearchResponse.Item i: response.getResponses()) {
			Arrays.stream(i.getResponse().getHits().getHits()).forEach(x-> System.out.println(x.getSourceAsString()));
		}
		
	}
//	聚合
	public static void aggregation(){
		SearchResponse response = ClientAPI.connect().prepareSearch()
			.setQuery(QueryBuilders.matchAllQuery())
			.addAggregation(AggregationBuilders
				.terms("myInterests")
				.field("interests")
				.order(BucketOrder.aggregation("avg_age", false))
//				子聚合
				.subAggregation(AggregationBuilders
					.avg("avg_age")
					.field("age")
				))
			.addAggregation(AggregationBuilders
				.avg("avg_age")
				.field("age")
			).get();
		SearchHit[] hits=response.getHits().getHits();
		for (SearchHit hit: hits) {
			System.out.println(hit.getSourceAsString());
		}
		System.out.println(response.getAggregations().asList().toString());
	}
	
	public static void main(String[] args) {
		search();
	}
}
