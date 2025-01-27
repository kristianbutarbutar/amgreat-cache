package com.amgreat.cache.vo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;

public class RequestVO extends VO implements Serializable {
	@Indexed
	@Id
	private String pageId;
	private RequestVO next = null;
	private RequestVO filter = null;
	private String cmdString;
	public RequestVO getNext() { return next; }
	public void setNext(RequestVO next) { this.next = next; }
	public String getCmdString() { return cmdString; }
	public void setCmdString(String cmdString) { this.cmdString = cmdString; }
	
	
	public String getPageId() { return pageId; }
	public void setPageId(String pageId) { this.pageId = pageId; }
	public RequestVO getFilter() { return filter; }
	public RequestVO setFilter(RequestVO filter) { this.filter = filter; return this.filter;}
	public RequestVO addNext() {
		if(this.getNext()!=null) return this.getNext().addNext();
		else {
			this.setNext(new RequestVO());
			return this.getNext();
		}
	}
	
}
