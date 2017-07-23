package com.aisino.wmdw.ywgz.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aisino.framework.orm.IdEntity;
import com.aisino.framework.security.entity.User;

@Entity
@Table(name = "YWGZ")
public class Ywgz extends IdEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6709843489331936797L;
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

	@ManyToOne
	@JoinColumn(name="sbdw")
	public User getSbdw() {
		return sbdw;
	}

	public void setSbdw(User sbdw) {
		this.sbdw = sbdw;
	}

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

	@Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name="content", columnDefinition="CLOB", nullable=true) 
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSblx() {
		return sblx;
	}

	public void setSblx(String sblx) {
		this.sblx = sblx;
	}
}
