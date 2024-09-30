package com.amgreat.cache.vo;

import java.io.Serializable;

public class ResponseVO extends VO implements Serializable {
	private ResponseVO next = null;
	public ResponseVO getNext() { return next; }
	public void setNext(ResponseVO next) { this.next = next; }
}
