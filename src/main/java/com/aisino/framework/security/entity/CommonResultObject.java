package com.aisino.framework.security.entity;

/**
 * 
 * @author xuzhe Created on 2013-06-15 上午11:43:36
 * @version v1.0
 * 
 */
public class CommonResultObject {
	private String retCode;
	private String retMsg;
	private String content;
	private String spareField;

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSpareField() {
		return spareField;
	}

	public void setSpareField(String spareField) {
		this.spareField = spareField;
	}

}
