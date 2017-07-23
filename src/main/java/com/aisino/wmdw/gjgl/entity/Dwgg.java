package com.aisino.wmdw.gjgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aisino.framework.orm.IdEntity;

/**
 * 单位公告
 * @author xuzhe
 */
@Entity
@Table(name = "DWGG")
public class Dwgg extends IdEntity {

	private static final long serialVersionUID = -3152600912024105162L;

	// 公告ID
	private Long tzggid;
	// 接受单位ID
	private Long jsdwid;
	// 阅读状态(0:未读，1：已读)
	private String ydzt;
	
	public Long getTzggid() {
		return tzggid;
	}
	
	public void setTzggid(Long tzggid) {
		this.tzggid = tzggid;
	}
	
	public Long getJsdwid() {
		return jsdwid;
	}

	public void setJsdwid(Long jsdwid) {
		this.jsdwid = jsdwid;
	}

	public String getYdzt() {
		return ydzt;
	}
	
	public void setYdzt(String ydzt) {
		this.ydzt = ydzt;
	}
    
}
