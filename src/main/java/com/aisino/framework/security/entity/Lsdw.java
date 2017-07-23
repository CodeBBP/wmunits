package com.aisino.framework.security.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonValue;

/**
 * 用户实体类，继承抽象安全实体类
 * @author yuqs
 * @version 1.0
 */
public class Lsdw
{
	//单位名称
	private String dwmc;
	//用户名
	private Long id;
	
	public String getDwmc() {
		return dwmc;
	}
	
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
}
