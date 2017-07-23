package com.aisino.wmdw.sxjs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aisino.framework.orm.IdEntity;

/**
 * 项目材料
 * @author xuzhe
 */
@Entity
@Table(name = "XMCL")
public class Xmcl extends IdEntity {

	private static final long serialVersionUID = -3152600912024105162L;

	// 标题
	private String title;
	// 申报学校
	private String sbxs;
	// 作者
	private String author;
	// 审核领导
	private String shld;
	// 创建时间
	private String createdate;
	// 内容
	private String content;
	// 所属类别
	private String category;
	// 评估标准
	private String pgbz;
	// 审核状态
	private String shzt;
	// 总分
	private String zf;
	// 得分
	private String score;
	// 建议
	private String suggest;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getSbxs() {
		return sbxs;
	}

	public void setSbxs(String sbxs) {
		this.sbxs = sbxs;
	}

	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getShld() {
		return shld;
	}

	public void setShld(String shld) {
		this.shld = shld;
	}

	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPgbz() {
		return pgbz;
	}

	public void setPgbz(String pgbz) {
		this.pgbz = pgbz;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getZf() {
		return zf;
	}

	public void setZf(String zf) {
		this.zf = zf;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

}
