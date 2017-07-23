package com.aisino.framework.security.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 操作日志，记录登陆人员的操作
 * @author xuzhe
 * @version 1.0
 */
@Entity
@Table(name = "CZRZ")
public class Czrz extends SecurityEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3488406380107404492L;
	//操作人员
	public User czry;
	//操作时间
	private String czsj;
	//当前状态
	private String dqzt;
	//单位名称
	private User sbdw;
	//文件类型
	private String wjlx;
	//文件标题
	private String title;
	@ManyToOne
	@JoinColumn(name="czry")
	public User getCzry() {
		return czry;
	}
	
	public void setCzry(User czry) {
		this.czry = czry;
	}
	
	public String getCzsj() {
		return czsj;
	}
	
	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}
	
	
	public String getDqzt() {
		return dqzt;
	}

	public void setDqzt(String dqzt) {
		this.dqzt = dqzt;
	}
	
	public String getWjlx() {
		return wjlx;
	}
	
	public void setWjlx(String wjlx) {
		this.wjlx = wjlx;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@ManyToOne
	@JoinColumn(name="sbdw")
	public User getSbdw() {
		return sbdw;
	}

	public void setSbdw(User sbdw) {
		this.sbdw = sbdw;
	}

}
