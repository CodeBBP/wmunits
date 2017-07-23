package com.aisino.framework.orm;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * id抽象类
 * @author yuqs
 * @version 1.0
 */
@MappedSuperclass
public abstract class IdEntity implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//主键标识ID
	protected Long id;

	/**
	 * 安全实体的主键生成策略为序列，序列名称为SEC_SEQUENCE
	 * @return
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
