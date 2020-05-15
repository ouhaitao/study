package main;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetSocketAddress;
import java.util.List;

public class ClientAPI {
	
//	手动设置IP、端口
	private static void getClient(){
		TransportClient transportClient=new PreBuiltTransportClient(Settings.EMPTY)
			.addTransportAddress(new TransportAddress(new InetSocketAddress("127.0.0.1", 9300)));
		
		List<DiscoveryNode> list=transportClient.connectedNodes();
		list.forEach(x-> System.out.println(x.getAddress().toString()));
		transportClient.close();
	}
	
//  自动发现集群中的所有节点（至少设置一个IP，不然无法知道连接哪个集群）
	private static void getClient2(){
		Settings settings=Settings.builder().put("client.transport.sniff","true").build();
		TransportClient transportClient=new PreBuiltTransportClient(settings)
			.addTransportAddress(new TransportAddress(new InetSocketAddress("127.0.0.1", 9300)));
		List<DiscoveryNode> list=transportClient.connectedNodes();
		list.forEach(x-> System.out.println(x.getAddress().toString()));
		transportClient.close();
	}
	
	public static void main(String[] args){
		getClient2();
	}
	
	public static Client connect(){
		TransportClient transportClient=new PreBuiltTransportClient(Settings.EMPTY)
			.addTransportAddress(new TransportAddress(new InetSocketAddress("127.0.0.1", 9300)));
		return transportClient;
	}
	
}
