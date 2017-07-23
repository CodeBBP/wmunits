package com.aisino.wmdw.sxjs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.PropertyFilter;
import com.aisino.wmdw.sxjs.dao.PgmxDao;
import com.aisino.wmdw.sxjs.entity.Pgmx;

/**
 * 考核明细管理类
 * @author xuzhe
 * @version 1.0
 */
@Component
public class PgmxManager {
	//注入变更项目持久化对象
	@Autowired
	private PgmxDao pgxmDao;
	
	/**
	 * 保存配置考核明细实体
	 * @param entity
	 */
	public void save(Pgmx entity) {
		pgxmDao.save(entity);
	}
	
	/**
	 * 根据主键ID获取配置考核明细实体
	 * @param id
	 * @return
	 */
	public Pgmx get(Long id) {
		return pgxmDao.get(id);
	}
	
	/**
	 * 根据主键ID删除对应的配置考核明细实体
	 * @param id
	 */
	public void delete(Long id) {
		pgxmDao.delete(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询配置考核明细列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Pgmx> findPage(final Page<Pgmx> page, final List<PropertyFilter> filters) {
		return pgxmDao.findPage(page, filters);
	}
	
	public List<Pgmx> getAll() {
		List<Pgmx> bgxms = pgxmDao.getAll();
		return bgxms; 
	}
}
