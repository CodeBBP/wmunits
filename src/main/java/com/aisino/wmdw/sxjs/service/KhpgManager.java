package com.aisino.wmdw.sxjs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.PropertyFilter;
import com.aisino.wmdw.sxjs.dao.KhpgDao;
import com.aisino.wmdw.sxjs.entity.Khpg;
import com.aisino.wmdw.sxjs.entity.Pgmx;

/**
 * 考核评估管理类
 * @author xuzhe
 * @version 1.0
 */
@Component
public class KhpgManager {
	//注入变更项目持久化对象
	@Autowired
	private KhpgDao khpgDao;
	
	/**
	 * 保存配置考核评估实体
	 * @param entity
	 */
	public void save(Khpg entity, List<Pgmx> khmxs) {
		if(entity.getId() != null) {
			khpgDao.batchExecute("delete Khmx where khpg=" + entity.getId());
		}
		if(khmxs != null && khmxs.size() > 0 ) {
			entity.setPgmxs(khmxs);
		}
		khpgDao.save(entity);
	}
	
	/**
	 * 根据主键ID获取配置考核评估实体
	 * @param id
	 * @return
	 */
	public Khpg get(Long id) {
		return khpgDao.get(id);
	}
	
	/**
	 * 根据主键ID删除对应的配置考核评估实体
	 * @param id
	 */
	public void delete(Long id) {
		khpgDao.delete(id);
	}
	
	/**
	 * 根据分页对象、过滤集合评估，分页查询配置考核评估列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Khpg> findPage(final Page<Khpg> page, final List<PropertyFilter> filters) {
		return khpgDao.findPage(page, filters);
	}
	
	public List<Khpg> getAll() {
		List<Khpg> bgxms = khpgDao.getAll();
		return bgxms; 
	}
}
