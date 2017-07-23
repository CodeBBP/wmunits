package com.aisino.wmdw.gjgl.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aisino.framework.orm.IdEntity;
import com.aisino.framework.security.entity.User;

/**
 * 通知公告
 * @author xuzhe
 */
@Entity
@Table(name = "TZGG")
public class Tzgg extends IdEntity {

	private static final long serialVersionUID = -3152600912024105162L;

	// 标题
	private String title;
	// 发布单位
	private User fbdw;
	// 作者
	private String author;
	// 创建时间
	private String cjsj;
	// 有效日期
	private String yxrq;
	// 内容
	private String content;
    // 阅读状态
    private String ydzt;
    // 单位ID
    private Long dwid;
    
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
	public String getYxrq() {
		return yxrq;
	}
	public void setYxrq(String yxrq) {
		this.yxrq = yxrq;
	}
	@Transient
	public String getYdzt() {
		return ydzt;
	}
	public void setYdzt(String ydzt) {
		this.ydzt = ydzt;
	}
	
	@Transient
	public Long getDwid() {
		return dwid;
	}
	public void setDwid(Long dwid) {
		this.dwid = dwid;
	}
	@ManyToOne
	@JoinColumn(name="fbdw")
	public User getFbdw() {
		return fbdw;
	}

	public void setFbdw(User fbdw) {
		this.fbdw = fbdw;
	}

}
