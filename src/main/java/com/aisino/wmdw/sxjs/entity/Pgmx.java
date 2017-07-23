package com.aisino.wmdw.sxjs.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aisino.framework.orm.IdEntity;

/**
 * 评估明细
 * @author xuzhe
 */
@Entity
@Table(name = "PGMX")
public class Pgmx extends IdEntity {

	private static final long serialVersionUID = -3152600212024105162L;

	// 考核内容
	private String pgnr;
	// 考核方式
	private String khfs;
	// 标准分值
	private String bzfz;
	// 审核要点
	private String shyd;
	// 考核评估
	private Khpg khpg;
	
	public String getPgnr() {
		return pgnr;
	}
	
	public void setPgnr(String pgnr) {
		this.pgnr = pgnr;
	}
	
	public String getKhfs() {
		return khfs;
	}
	
	public void setKhfs(String khfs) {
		this.khfs = khfs;
	}
	
	public String getBzfz() {
		return bzfz;
	}
	
	public void setBzfz(String bzfz) {
		this.bzfz = bzfz;
	}
	
	public String getShyd() {
		return shyd;
	}
	
	public void setShyd(String shyd) {
		this.shyd = shyd;
	}
	
	@ManyToOne
	@JoinColumn(name = "khpg")
	public Khpg getKhpg() {
		return khpg;
	}
	
	public void setKhpg(Khpg khpg) {
		this.khpg = khpg;
	}
	
}
