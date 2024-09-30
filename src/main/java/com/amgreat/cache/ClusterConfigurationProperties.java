package com.amgreat.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class ClusterConfigurationProperties {

    /*
     * spring.redis.cluster.nodes[0] = 127.0.0.1:7379
     * spring.redis.cluster.nodes[1] = 127.0.0.1:7380
     * ...
     */

    private List<String> nodes;
    
    @Value("${standalone.password}")
    private String password;
    
    @Value("${standalone.maxredirect}")
    private int maxRedirects;
    
    @Value("${standalone.refreshtime}")
    private long refreshTime;
    
    @Value("${standalone.ip}")
    private String standaloneIP; //127.0.0.1
    
    @Value("${standalone.port}")
    private int standalonePort;  //6379

    /**
     * Get initial collection of known cluster nodes in format {@code host:port}.
     *
     * @return
     */
    public List<String> getNodes() { return nodes; }
    public void setNodes(List<String> nodes) { this.nodes = nodes; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public int getMaxRedirects() { return maxRedirects; }
	public void setMaxRedirects(int maxRedirects) { this.maxRedirects = maxRedirects; }
	public long getRefreshTime() { return refreshTime; }
	public void setRefreshTime(long refreshTime) { this.refreshTime = refreshTime; }
	public String getStandaloneIP() { return standaloneIP; }
	public void setStandaloneIP(String standaloneIP) { this.standaloneIP = standaloneIP; }
	public int getStandalonePort() { return standalonePort; }
	public void setStandalonePort(int standalonePort) { this.standalonePort = standalonePort; }
}
