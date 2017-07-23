package com.aisino.wmdw.lhkh.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.aisino.framework.orm.IdEntity;

/**
 * 考核参数
 * @author xuzhe
 */
@Entity
@Table(name = "KHCS")
public class Khcs extends IdEntity {

	private static final long serialVersionUID = -3152601912024105162L;

	// 考核项目
	private String khxm;
	// 详细内容
	private String xxnr;
	// 上级项目
	private Khcs pkhcs;
	// 占分
	private String score;
	// 上传次数
	private int sccs;
	//菜单标识
	private Integer menuFlag;
	//部门管辖的所有下级部门列表（一对多关联）
    private List<Khcs> khcss = new ArrayList<Khcs>();
	
	public String getKhxm() {
		return khxm;
	}
	
	public void setKhxm(String khxm) {
		this.khxm = khxm;
	}
	
	public String getXxnr() {
		return xxnr;
	}
	
	public void setXxnr(String xxnr) {
		this.xxnr = xxnr;
	}
	
	@ManyToOne
	@JoinColumn(name="pkhcs", nullable=true)
	public Khcs getPkhcs() {
		return pkhcs;
	}

	public void setPkhcs(Khcs pkhcs) {
		this.pkhcs = pkhcs;
	}

	@OneToMany(mappedBy = "pkhcs",cascade = CascadeType.ALL)
	public List<Khcs> getKhcss() {
		return khcss;
	}

	public void setKhcss(List<Khcs> khcss) {
		this.khcss = khcss;
	}

	public String getScore() {
		return score;
	}
	
	public void setScore(String score) {
		this.score = score;
	}

	public Integer getMenuFlag() {
		return menuFlag;
	}

	public void setMenuFlag(Integer menuFlag) {
		this.menuFlag = menuFlag;
	}

	public int getSccs() {
		return sccs;
	}

	public void setSccs(int sccs) {
		this.sccs = sccs;
	}
	
}
