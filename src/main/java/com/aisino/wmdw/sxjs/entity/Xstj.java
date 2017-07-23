package com.aisino.wmdw.sxjs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aisino.framework.orm.IdEntity;

/**
 * 单位统计
 * @author xuzhe
 */
public class Xstj extends IdEntity {

	private static final long serialVersionUID = -3152600912024105162L;

	// 单位名称
	private String xsmc;
	// 上报总数
	private int sbzs;
	// 考核通过数
	private int khtgs;
	// 考核未通过数
	private int khwtgs;
	// 未考核数
	private int wkhs;
	
	public String getXsmc() {
		return xsmc;
	}
	
	public void setXsmc(String xsmc) {
		this.xsmc = xsmc;
	}
	
	public int getSbzs() {
		return sbzs;
	}
	
	public void setSbzs(int sbzs) {
		this.sbzs = sbzs;
	}
	
	public int getKhtgs() {
		return khtgs;
	}
	
	public void setKhtgs(int khtgs) {
		this.khtgs = khtgs;
	}
	
	public int getKhwtgs() {
		return khwtgs;
	}
	
	public void setKhwtgs(int khwtgs) {
		this.khwtgs = khwtgs;
	}

	public int getWkhs() {
		return wkhs;
	}

	public void setWkhs(int wkhs) {
		this.wkhs = wkhs;
	}

}
