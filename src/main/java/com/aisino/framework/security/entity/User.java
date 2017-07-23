package com.aisino.framework.security.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonValue;

/**
 * 用户实体类，继承抽象安全实体类
 * @author yuqs
 * @version 1.0
 */
@Entity
@Table(name = "SEC_USER")
public class User extends SecurityEntity
{
	private static final long serialVersionUID = 7446802057673100315L;
	//系统管理员账号类型
	public static final Integer TYPE_ADMIN = 0;
	//普通用户账号类型
	public static final Integer TYPE_GENERAL = 1;
	//单位名称
	private String dwmc;
	//用户名
	private String username;
	//密码
	private String password;
	//单位类型
	private String dwlx;
	//单位性质
	private String dwxz;
	//单位等级
	private String dwdj;
	//单位地址
	private String dwdz;
	//分管领导
	private String fgld;
	//分管领导号码
	private String fgldhm;
	//单位联系人
	private String lxr;
	//联系电话
	private String lxdh;
	//手机号码
	private String sjhm;
	//QQ号码
	private String qqhm;
	//邮箱
	private String email;
	//性别
	private String sex;
	// 总分
	private String zf;
	//所属部门
	private Org org;
	//是否可用
	private String enabled;
	//角色列表（多对多关联）
	private List<Role> roles = new ArrayList<Role>();
	//权限列表（多对多关联）
	private List<Authority> authorities = new ArrayList<Authority>();
	
	public User() {
		
	}

	@Column(name = "dwmc", unique = true, nullable = false, length = 50)
	public String getDwmc() {
		return dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "enabled")
	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="SEC_ROLE_USER",joinColumns={ @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	public List<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="SEC_USER_AUTHORITY",joinColumns={ @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	public List<Authority> getAuthorities() 
	{
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	@ManyToOne
	@JoinColumn(name="org", nullable=true)
	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public String getDwlx() {
		return dwlx;
	}

	public void setDwlx(String dwlx) {
		this.dwlx = dwlx;
	}

	public String getDwxz() {
		return dwxz;
	}

	public void setDwxz(String dwxz) {
		this.dwxz = dwxz;
	}

	public String getDwdj() {
		return dwdj;
	}

	public void setDwdj(String dwdj) {
		this.dwdj = dwdj;
	}

	public String getDwdz() {
		return dwdz;
	}

	public void setDwdz(String dwdz) {
		this.dwdz = dwdz;
	}

	public String getFgld() {
		return fgld;
	}

	public void setFgld(String fgld) {
		this.fgld = fgld;
	}

	public String getFgldhm() {
		return fgldhm;
	}

	public void setFgldhm(String fgldhm) {
		this.fgldhm = fgldhm;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getSjhm() {
		return sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getQqhm() {
		return qqhm;
	}

	public void setQqhm(String qqhm) {
		this.qqhm = qqhm;
	}

	public String getZf() {
		return zf;
	}

	public void setZf(String zf) {
		this.zf = zf;
	}
	
}
