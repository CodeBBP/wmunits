package com.aisino.wmdw.sxjs.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.aisino.framework.orm.IdEntity;

/**
 * 考核评估
 * @author xuzhe
 */
@Entity
@Table(name = "KHPG")
public class Khpg extends IdEntity {

	private static final long serialVersionUID = -3152600912024105162L;

	// 评估项目
	private String para;
	// 占分
	private String score;
	// 评估内容
	private List<Pgmx> pgmxs = new ArrayList<Pgmx>();
	
	public String getPara() {
		return para;
	}
	
	public void setPara(String para) {
		this.para = para;
	}
	
	public String getScore() {
		return score;
	}
	
	public void setScore(String score) {
		this.score = score;
	}

	@OneToMany(mappedBy = "khpg", cascade = CascadeType.ALL)
	public List<Pgmx> getPgmxs() {
		return pgmxs;
	}

	public void setPgmxs(List<Pgmx> pgmxs) {
		this.pgmxs = pgmxs;
	}
	
}
