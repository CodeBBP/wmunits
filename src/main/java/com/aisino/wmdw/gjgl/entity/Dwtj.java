package com.aisino.wmdw.gjgl.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.aisino.framework.orm.IdEntity;
import com.aisino.framework.security.entity.User;

/**
 * 单位统计
 * @author xuzhe
 */
public class Dwtj extends IdEntity {

	private static final long serialVersionUID = -3152600912024105162L;

	// 单位名称
	private String sbdw;
	// 创建时间
	private String cjsj;
	// 单位类型
	private String dwlx;
	// 上报总数
	private String sbzs;
	// 已归档数
	private String ygds;
	// 不通过数
	private String btgs;
	// 总分
	private String zf;
	
	public String getSbzs() {
		return sbzs;
	}
	
	public void setSbzs(String sbzs) {
		this.sbzs = sbzs;
	}

	public String getSbdw() {
		return sbdw;
	}

	public void setSbdw(String sbdw) {
		this.sbdw = sbdw;
	}

	public String getYgds() {
		return ygds;
	}

	public void setYgds(String ygds) {
		this.ygds = ygds;
	}

	public String getBtgs() {
		return btgs;
	}

	public void setBtgs(String btgs) {
		this.btgs = btgs;
	}

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

	public String getZf() {
		return zf;
	}

	public void setZf(String zf) {
		this.zf = zf;
	}

	public String getDwlx() {
		return dwlx;
	}

	public void setDwlx(String dwlx) {
		this.dwlx = dwlx;
	}
	
}
