package com.amgreat.cache;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amgreat.cache.ClusterConfigurationProperties;

import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;

@Component
public class RedisConnectLettuce {
	
	@Autowired
	private ClusterConfigurationProperties conf;
	
	private StatefulRedisClusterConnection<String, String> connectionCluster;
	private RedisClusterClient standaloneClient;
	
	public void openClusterConnection() {
		
		if (connectionCluster == null) {
			
			List<RedisURI> list = new ArrayList();
			list.add(RedisURI.Builder.redis("masterNodeIp1").withPort(6379).withPassword("redisClusterMayaxxx129".toCharArray()).build());
			list.add(RedisURI.Builder.redis("masterNodeIp2").withPort(6379).withPassword("redisClusterMayaxxx129".toCharArray()).build());
			list.add(RedisURI.Builder.redis("masterNodeIp3").withPort(6379).withPassword("redisClusterMayaxxx129".toCharArray()).build());
			Duration oneHours = Duration.ofHours(1);
			
			final ClusterTopologyRefreshOptions refreshOptions = ClusterTopologyRefreshOptions.builder()
					.refreshPeriod(oneHours) // this one breaks it
					.enableAllAdaptiveRefreshTriggers().build();
			final ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder().autoReconnect(true)
					.maxRedirects(4).topologyRefreshOptions(refreshOptions).build();
			
			RedisClusterClient clusterClient = RedisClusterClient.create(list);
			
			clusterClient.setOptions(clusterClientOptions);
			
			connectionCluster = clusterClient.connect();
			
			RedisAdvancedClusterCommands<String, String> syncCommands = connectionCluster.sync();

		}
	}

	public void closeClusterConnection() {
		if(connectionCluster==null) {
			connectionCluster.close();
		}
	}
	
	public void openConnection() {
		System.out.println("check redis loaded ? :" + conf.getStandaloneIP());
		
		RedisClusterClient standaloneClient = RedisClusterClient.create( RedisURI.create( conf.getStandaloneIP() , conf.getStandalonePort()) );
	
		ClusterTopologyRefreshOptions topologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
		                                .enablePeriodicRefresh(10, TimeUnit.MINUTES)
		                                .build();
	
		standaloneClient.setOptions(ClusterClientOptions.builder()
		                                .topologyRefreshOptions(topologyRefreshOptions)
		                                .build());
	}
	
	public void closeStandaloneConnection() {
		if(standaloneClient==null) {
			standaloneClient.close();
			standaloneClient.shutdown();
		}
	}
}
