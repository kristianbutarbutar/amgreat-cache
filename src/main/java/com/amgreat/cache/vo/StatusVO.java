package com.amgreat.cache.vo;

import java.io.Serializable;

public class StatusVO implements Serializable {
	private String description;
	private String code;

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	public String getCode() { return code; }
	public void setCode(String code) { this.code = code; }
}
