package com.aisino.wmdw.sxjs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.PropertyFilter;
import com.aisino.wmdw.sxjs.dao.XmclDao;
import com.aisino.wmdw.sxjs.entity.Xmcl;

/**
 * 项目材料管理类
 * @author xuzhe
 * @version 1.0
 */
@Component
public class XmclManager {
	//注入变更项目持久化对象
	@Autowired
	private XmclDao xmclDao;
	
	/**
	 * 保存配置项目材料实体
	 * @param entity
	 */
	public void save(Xmcl entity) {
		xmclDao.save(entity);
	}
	
	/**
	 * 根据主键ID获取配置项目材料实体
	 * @param id
	 * @return
	 */
	public Xmcl get(Long id) {
		return xmclDao.get(id);
	}
	
	/**
	 * 根据主键ID删除对应的配置项目材料实体
	 * @param id
	 */
	public void delete(Long id) {
		xmclDao.delete(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询配置项目材料列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Xmcl> findPage(final Page<Xmcl> page, final List<PropertyFilter> filters) {
		return xmclDao.findPage(page, filters);
	}
	
	public List<Xmcl> getAll() {
		List<Xmcl> bgxms = xmclDao.getAll();
		return bgxms; 
	}
	
	//上报总数
	public int getSbzsByXsmc(String dwmc){
		return xmclDao.getSbzsByXsmc(dwmc);
	}
	
	//未考核数
	public int getWkhsByXsmc(String dwmc){
		return xmclDao.getWkhsByXsmc(dwmc);
	}
	
	//审核通过数
	public int getKhtgsByXsmc(String dwmc){
		return xmclDao.getKhtgsByXsmc(dwmc);
	}
	
	//审核未通过数
	public int getKhwtgsByXsmc(String dwmc){
		return xmclDao.getKhwtgsByXsmc(dwmc);
	}

	public List<Xmcl> getXmclByUsername(String username) {
		List<Xmcl> bgxms = xmclDao.getXmclByUsername(username);
		return bgxms;
	}
}
