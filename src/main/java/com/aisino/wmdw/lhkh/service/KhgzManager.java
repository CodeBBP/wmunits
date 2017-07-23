package com.aisino.wmdw.lhkh.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.PropertyFilter;
import com.aisino.wmdw.lhkh.dao.KhgzDao;
import com.aisino.wmdw.lhkh.entity.Khcs;
import com.aisino.wmdw.lhkh.entity.Khgz;

/**
 * 考核参数管理类
 * @author xuzhe
 * @version 1.0
 */
@Component
public class KhgzManager {
	//注入变更项目持久化对象
	@Autowired
	private KhgzDao khgzDao;
	
	/**
	 * 保存配置考核参数实体
	 * @param entity
	 */
	public void save(Khgz entity) {
		khgzDao.save(entity);
	}
	
	/**
	 * 根据主键ID获取配置考核参数实体
	 * @param id
	 * @return
	 */
	public Khgz get(Long id) {
		return khgzDao.get(id);
	}
	
	/**
	 * 根据主键ID删除对应的配置考核参数实体
	 * @param id
	 */
	public void delete(Long id) {
		List<Khgz> khgzs = khgzDao.get(id).getKhgzs();
		if(khgzs.size() != 0){
			for(Khgz khgz : khgzs){
				delete(khgz.getId());
			}
		}
		//khgzDao.batchExecute("delete Khgz where pkhgz=" + id);
		khgzDao.delete(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询配置考核参数列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Khgz> findPage(final Page<Khgz> page, final List<PropertyFilter> filters) {
		return khgzDao.findPage(page, filters);
	}
	
	public List<Khgz> getAll() {
		List<Khgz> khgzs = khgzDao.getAll();
		return khgzs; 
	}
	
	public List<Khgz> getLeftAll() {
		List<Khgz> khgzs = khgzDao.getAll();
		List<Khgz> khgzs1 = new ArrayList<Khgz>();
		for(Khgz k : khgzs){
			if(k.getKhgzs() != null && k.getKhgzs().size()>0){
				khgzs1.add(k);
			}
		}
		return khgzs1; 
		//return khgzDao.findBy("menuFlag", 1);
	}
	
	public Page<Khgz> getYjPkhgz(Page<Khgz> page) {
		return  khgzDao.findPage(page, " from Khgz k where k.pkhgz is null");
	}
	
	/**
	 * 根据上级考核参数ID查询考核列表
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Khgz> getKhgzByPkhgz(Long khgzId) {
		String sql = "select * from khgz t " +                  
					" where t.pkhgz=? ";
		SQLQuery query = khgzDao.createSQLQuery(sql, khgzId);
		query.addEntity(Khgz.class);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Khgz> getKhgzByPkhcs(Long khgzId) {
		String sql = "select * from khgz t " +                  
		" where t.pkhgz=? ";
		SQLQuery query = khgzDao.createSQLQuery(sql, khgzId);
		query.addEntity(Khgz.class);
		return query.list();
	}
}
