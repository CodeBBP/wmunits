package com.aisino.wmdw.sxsb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aisino.framework.orm.IdEntity;
import com.aisino.framework.security.entity.User;
import com.aisino.wmdw.lhkh.entity.Khgz;

/**
 * 城乡环境
 * @author xuzhe
 */
@Entity
@Table(name = "Cxhj")
public class Cxhj extends IdEntity {

	private static final long serialVersionUID = -3152600912024105162L;

	// 标题
	private String title;
	// 创建时间
	private String cjsj;
	// 内容
	private String content;
	// 所属类别
	private String sslb;
	// 牵头部门
	private String qtbm;
	// 责任单位
	private String zrdw;
	// 解决时间
	private String jjsj;
	// 实景照片
	private String sjzp;
    // 图片X坐标点
    private String xpoint;
    // 图片Y坐标点
    private String ypoint;
    // 状态标注(0未处理，1处理中，2已解决)
    private String ztbz;
    // 上传单位
    private User scdw;
    // 考核gz
    private Khgz khgz;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getSslb() {
		return sslb;
	}
	public void setSslb(String sslb) {
		this.sslb = sslb;
	}
	public String getQtbm() {
		return qtbm;
	}
	public void setQtbm(String qtbm) {
		this.qtbm = qtbm;
	}
	public String getZrdw() {
		return zrdw;
	}
	public void setZrdw(String zrdw) {
		this.zrdw = zrdw;
	}
	public String getJjsj() {
		return jjsj;
	}
	public void setJjsj(String jjsj) {
		this.jjsj = jjsj;
	}
	public String getSjzp() {
		return sjzp;
	}
	public void setSjzp(String sjzp) {
		this.sjzp = sjzp;
	}
	public String getXpoint() {
		return xpoint;
	}
	public void setXpoint(String xpoint) {
		this.xpoint = xpoint;
	}
	public String getYpoint() {
		return ypoint;
	}
	public void setYpoint(String ypoint) {
		this.ypoint = ypoint;
	}
	public String getZtbz() {
		return ztbz;
	}
	public void setZtbz(String ztbz) {
		this.ztbz = ztbz;
	}
	@ManyToOne
	@JoinColumn(name="scdw")
	public User getScdw() {
		return scdw;
	}

	public void setScdw(User scdw) {
		this.scdw = scdw;
	}
	@ManyToOne
	@JoinColumn(name="khgz")
	public Khgz getKhgz() {
		return khgz;
	}
	public void setKhgz(Khgz khgz) {
		this.khgz = khgz;
	}
}
