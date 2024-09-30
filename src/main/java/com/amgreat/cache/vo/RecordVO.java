package com.amgreat.cache.vo;

import java.io.Serializable;

public class RecordVO implements Serializable{
	private ResponseVO response;
	private RecordVO next;
	private StatusVO status;
	
	
	public StatusVO getStatus() {
		return status;
	}
	public void setStatus(StatusVO status) {
		this.status = status;
	}
	public RecordVO getNext() { return next; }
	public void setNext(RecordVO next) { this.next = next; }
	public ResponseVO getResponse() { return response; }
	public void setResponse(ResponseVO response) { this.response = response; }
}
