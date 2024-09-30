package com.amgreat.cache;

import java.time.Duration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.lettuce.core.ReadFrom;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;

@Configuration
public class RedishConnect {
	 	
		@Autowired 
		private ClusterConfigurationProperties redisProperties;
	
		@Bean
		public JedisConnectionFactory jedisConnectionFactory() {
		    JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		    jedisConFactory.setHostName(redisProperties.getStandaloneIP()); 
		    jedisConFactory.setPort(redisProperties.getStandalonePort());
		    jedisConFactory.setPassword(redisProperties.getPassword() );
		    return jedisConFactory;
		}
		
		//@Bean
	    public LettuceConnectionFactory redisStandAloneConnectionFactory() {
	    	 return new LettuceConnectionFactory(new RedisStandaloneConfiguration( redisProperties.getStandaloneIP(), redisProperties.getStandalonePort()));
	    }
		
	    //@Bean
	    public RedisTemplate<String, Map<String, Object>> redisTemplateStandAlone(@Qualifier("redisStandAloneConnectionFactory")LettuceConnectionFactory redisConnectionFactory) {    	      
	        RedisTemplate<String, Map<String, Object>> redisTemplate = new RedisTemplate<>();
	        redisTemplate.setConnectionFactory(redisConnectionFactory);
	        redisTemplate.setKeySerializer(new StringRedisSerializer());
	        redisTemplate.setValueSerializer(new StringRedisSerializer());         
	        redisTemplate.afterPropertiesSet();
	        return redisTemplate;
	    }
	    
	    //@Bean
	    public LettuceConnectionFactory redisClusterConnectionFactory() {
	        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration( redisProperties.getNodes() );
	        redisClusterConfiguration.setMaxRedirects( redisProperties.getMaxRedirects() );
	        redisClusterConfiguration.setPassword( redisProperties.getPassword() );
	        // Support adaptive cluster topology refresh and static refresh source
	        //In case a master node failure this configuration manage the whole topology for otherher nodes.
	        ClusterTopologyRefreshOptions clusterTopologyRefreshOptions =  ClusterTopologyRefreshOptions.builder()
	                .enablePeriodicRefresh()
	                .enableAllAdaptiveRefreshTriggers()
	                .refreshPeriod(Duration.ofSeconds(redisProperties.getRefreshTime()))
	                .build();
	 
	        ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
	                .topologyRefreshOptions(clusterTopologyRefreshOptions).build();
	 
	               
	        LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
	                .readFrom(ReadFrom.ANY)
	                .clientOptions(clusterClientOptions).build();
	 
	        return new LettuceConnectionFactory(redisClusterConfiguration, lettuceClientConfiguration);
	    }
	    
	    //@Bean
	    public RedisTemplate<String, Map<String, Object>> redisClusterTemplate(@Qualifier("redisClusterConnectionFactory") LettuceConnectionFactory redisConnectionFactory) {
	    	Jackson2JsonRedisSerializer<Map<String, Object>> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Map.class);
	    	
	    	ObjectMapper om = new ObjectMapper();
	    	
	    	om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	    	
	        jackson2JsonRedisSerializer.setObjectMapper(om);        
	        RedisTemplate<String, Map<String, Object>> redisTemplate = new RedisTemplate<>();
	        redisTemplate.setConnectionFactory(redisConnectionFactory);
	        redisTemplate.setKeySerializer(new StringRedisSerializer());
	        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
	        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
	        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);       
	        redisTemplate.afterPropertiesSet();
	        
	        return redisTemplate;
	    }
}
