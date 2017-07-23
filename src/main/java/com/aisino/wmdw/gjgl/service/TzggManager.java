package com.aisino.wmdw.gjgl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.PropertyFilter;
import com.aisino.framework.security.entity.User;
import com.aisino.wmdw.gjgl.dao.DwggDao;
import com.aisino.wmdw.gjgl.dao.TzggDao;
import com.aisino.wmdw.gjgl.entity.Dwgg;
import com.aisino.wmdw.gjgl.entity.Tzgg;

/**
 * 通知公告管理类
 * @author xuzhe
 * @version 1.0
 */
@Component
public class TzggManager {
	//注入变更项目持久化对象
	@Autowired
	private TzggDao tzggDao;
	@Autowired
	private DwggDao dwggDao;
	
	/**
	 * 保存配置通知公告实体
	 * @param entity
	 */
	public void save(Tzgg entity, List<User> users) {
		if(entity.getId() == null){
			Dwgg dwgg = null;
			tzggDao.save(entity);
			for(User user : users){
				dwgg = new Dwgg();   //防止保存的对象残留ID
				dwgg.setJsdwid(user.getId());
				dwgg.setTzggid(entity.getId());
				dwgg.setYdzt("0");
				dwggDao.save(dwgg);
			}
		}else{
			tzggDao.save(entity);
		}
		
	}
	
	/**
	 * 根据主键ID获取配置通知公告实体
	 * @param id
	 * @return
	 */
	public Tzgg get(Long id) {
		return tzggDao.get(id);
	}
	
	/**
	 * 根据主键ID删除对应的配置通知公告实体
	 * @param id
	 */
	public void delete(Long id) {
		List<Dwgg> dwggs = dwggDao.getDwggByTzggId(id);
		for(Dwgg dwgg : dwggs){
			dwggDao.delete(dwgg);
		}
		tzggDao.delete(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询配置通知公告列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Tzgg> findPage(final Page<Tzgg> page, final List<PropertyFilter> filters) {
		return tzggDao.findPage(page, filters);
	}
	
	public List<Tzgg> getAll() {
		List<Tzgg> tzggs = tzggDao.getAll();
		return tzggs; 
	}
	
	//根据组织机构代码获取单位材料
	public List<Tzgg> getTzggByZzjgdm(String zzjgdm) {
		List<Tzgg> tzggs = tzggDao.getTzggByZzjgdm(zzjgdm);
		return tzggs;
	}
}
