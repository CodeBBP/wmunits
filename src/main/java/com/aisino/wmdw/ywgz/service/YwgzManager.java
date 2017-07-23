package com.aisino.wmdw.ywgz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.PropertyFilter;
import com.aisino.wmdw.ywgz.dao.YwgzDao;
import com.aisino.wmdw.ywgz.entity.Ywgz;

@Component
public class YwgzManager {
	//注入变更项目持久化对象
	@Autowired
	private YwgzDao ywgzDao;
	
	/**
	 * 保存配置通知公告实体
	 * @param entity
	 */
	public void save(Ywgz entity) {
		ywgzDao.save(entity);
	}
	
	/**
	 * 根据主键ID获取配置通知公告实体
	 * @param id
	 * @return
	 */
	public Ywgz get(Long id) {
		return ywgzDao.get(id);
	}
	
	/**
	 * 根据主键ID删除对应的配置通知公告实体
	 * @param id
	 */
	public void delete(Long id) {
		ywgzDao.delete(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询配置通知公告列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Ywgz> findPage(final Page<Ywgz> page, String sblx, Long sbdw) {
		String hql = "from Ywgz t where t.sblx=? and t.sbdw.id=?";
		return ywgzDao.findPage(page, hql, sblx, sbdw);
	}
	
	public List<Ywgz> getAll() {
		return ywgzDao.getAll();
	}
}
