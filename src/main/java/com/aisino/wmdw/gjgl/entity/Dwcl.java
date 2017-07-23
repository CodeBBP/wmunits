package com.aisino.wmdw.gjgl.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aisino.framework.orm.IdEntity;
import com.aisino.framework.security.entity.User;

/**
 * 单位材料
 * @author xuzhe
 */
@Entity
@Table(name = "DWCL")
public class Dwcl extends IdEntity {

	private static final long serialVersionUID = -3152600912024105162L;

	// 标题
	private String title;
	// 上报单位
	private User sbdw;
	// 作者
	private String author;
	// 创建时间
	private String cjsj;
	// 内容
	private String content;
	// 上报类型
	private String sblx;
	// 稿件类别
	private String gjlb;
	// 计分标准
	private String jfbz;
	// 考核参数id
	private Long khcsId;
	// 审批状态(null:未审批，0：新稿，1：已归档，2：退回)
	private String spzt;
	// 总分
	private String zf;
	// 建议
	private String suggest;
    // 文件类型
    private String wjlx;
    // 稿件状态
    private String gjzt;
    
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCjsj() {
		return cjsj;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public String getGjlb() {
		return gjlb;
	}

	public void setGjlb(String gjlb) {
		this.gjlb = gjlb;
	}

	public String getJfbz() {
		return jfbz;
	}

	public void setJfbz(String jfbz) {
		this.jfbz = jfbz;
	}

	public String getSpzt() {
		return spzt;
	}

	public void setSpzt(String spzt) {
		this.spzt = spzt;
	}

	public String getZf() {
		return zf;
	}

	public void setZf(String zf) {
		this.zf = zf;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	public String getWjlx() {
		return wjlx;
	}

	public void setWjlx(String wjlx) {
		this.wjlx = wjlx;
	}

	public String getGjzt() {
		return gjzt;
	}

	public void setGjzt(String gjzt) {
		this.gjzt = gjzt;
	}

	public Long getKhcsId() {
		return khcsId;
	}

	public void setKhcsId(Long khcsId) {
		this.khcsId = khcsId;
	}
	
	public String getSblx() {
		return sblx;
	}

	public void setSblx(String sblx) {
		this.sblx = sblx;
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
