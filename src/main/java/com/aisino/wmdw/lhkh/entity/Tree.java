package com.aisino.wmdw.lhkh.entity;

import java.io.Serializable;
import java.util.List;

public class Tree implements Serializable 
{
	private static final long serialVersionUID = 5192890919020070739L;

	private Long id;
	
	private Long pId;
	// 节点名称
	private String name;
	
	private String url;
	
	private String target;
	// 详细内容
	private String pname;
	
	private boolean open;
	
	private String fs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getFs() {
		return fs;
	}

	public void setFs(String fs) {
		this.fs = fs;
	}
	
}
