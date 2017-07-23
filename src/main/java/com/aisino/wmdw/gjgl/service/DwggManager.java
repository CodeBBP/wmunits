package com.aisino.wmdw.gjgl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.PropertyFilter;
import com.aisino.framework.security.entity.User;
import com.aisino.wmdw.gjgl.dao.DwggDao;
import com.aisino.wmdw.gjgl.entity.Dwgg;

/**
 * 通知公告管理类
 * @author xuzhe
 * @version 1.0
 */
@Component
public class DwggManager {
	//注入变更项目持久化对象
	@Autowired
	private DwggDao dwggDao;
	
	/**
	 * 保存配置通知公告实体
	 * @param entity
	 */
	public void save(Dwgg entity) {
		dwggDao.save(entity);
	}
	
	/**
	 * 根据主键ID获取配置通知公告实体
	 * @param id
	 * @return
	 */
	public Dwgg get(Long id) {
		return dwggDao.get(id);
	}
	
	/**
	 * 根据主键ID删除对应的配置通知公告实体
	 * @param id
	 */
	public void delete(Long id) {
		dwggDao.delete(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询配置通知公告列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Dwgg> findPage(final Page<Dwgg> page, final List<PropertyFilter> filters) {
		return dwggDao.findPage(page, filters);
	}
	
	public List<Dwgg> getAll() {
		List<Dwgg> dwggs = dwggDao.getAll();
		return dwggs; 
	}
	
	//根据组织机构代码获取单位材料
	public List<Dwgg> getDwggByJsdwId(Long id) {
		List<Dwgg> dwggs = dwggDao.getDwggByJsdwId(id);
		return dwggs;
	}
	
	//根据组织机构代码获取单位材料
	public Dwgg getDwggByJsdwIdAndTzggId(Long jsdwid, Long tzggid) {
		Dwgg dwggs = dwggDao.getDwggByJsdwIdAndTzggId(jsdwid,tzggid);
		return dwggs;
	}
}
