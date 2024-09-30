package com.amgreat.cache.vo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("List")
public class ListVO extends VO implements Serializable{
	@Indexed
	@Id
	private String id;
	private String cmdName;
	private ListVO next;
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public ListVO getNext() { return next; }
	public void setNext(ListVO next) { this.next = next; }
	public String getCmdName() { return cmdName; }
	public void setCmdName(String cmdName) { this.cmdName = cmdName; }
}
