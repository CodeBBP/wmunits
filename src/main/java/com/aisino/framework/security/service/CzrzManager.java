package com.aisino.framework.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.PropertyFilter;
import com.aisino.framework.security.dao.CzrzDao;
import com.aisino.framework.security.entity.Czrz;

/**
 * 操作日志类
 * @author yuqs
 * @version 1.0
 */
@Component
public class CzrzManager {
	//注入操作日志持久化对象
	@Autowired
	private CzrzDao czrzDao;
	
	/**
	 * 保存操作日志实体
	 * @param entity
	 */
	public void save(Czrz entity) {
		czrzDao.save(entity);
	}
	
	/**
	 * 根据主键ID删除对应的操作日志
	 * @param id
	 */
	public void delete(Long id) {
		czrzDao.delete(id);
	}
	
	/**
	 * 根据主键ID获取操作日志实体
	 * @param id
	 * @return
	 */
	public Czrz get(Long id) {
		return czrzDao.get(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询操作日志列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Czrz> findPage(final Page<Czrz> page, final List<PropertyFilter> filters) {
		return czrzDao.findPage(page, filters);
	}
	
	/**
	 * 获取所有操作日志记录
	 * @return
	 */
	public List<Czrz> getAll() {
		return czrzDao.getAll();
	}
	
}
