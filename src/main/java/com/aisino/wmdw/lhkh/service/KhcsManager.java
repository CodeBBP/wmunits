package com.aisino.wmdw.lhkh.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.PropertyFilter;
import com.aisino.wmdw.lhkh.dao.KhcsDao;
import com.aisino.wmdw.lhkh.entity.Khcs;

/**
 * 考核参数管理类
 * @author xuzhe
 * @version 1.0
 */
@Component
public class KhcsManager {
	//注入变更项目持久化对象
	@Autowired
	private KhcsDao khcsDao;
	
	/**
	 * 保存配置考核参数实体
	 * @param entity
	 */
	public void save(Khcs entity) {
		khcsDao.save(entity);
	}
	
	/**
	 * 根据主键ID获取配置考核参数实体
	 * @param id
	 * @return
	 */
	public Khcs get(Long id) {
		return khcsDao.get(id);
	}
	
	/**
	 * 根据主键ID删除对应的配置考核参数实体
	 * @param id
	 */
	public void delete(Long id) {
		List<Khcs> khcss = khcsDao.get(id).getKhcss();
		if(khcss.size() != 0){
			for(Khcs khcs : khcss){
				delete(khcs.getId());
			}
		}
		//khcsDao.batchExecute("delete Khcs where pkhcs=" + id);
		khcsDao.delete(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询配置考核参数列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Khcs> findPage(final Page<Khcs> page, final List<PropertyFilter> filters) {
		return khcsDao.findPage(page, filters);
	}
	
	public List<Khcs> getAll() {
		List<Khcs> khcss = khcsDao.getAll();
		return khcss; 
	}
	
	public List<Khcs> getLeftAll() {
		List<Khcs> khcss = khcsDao.getAll();
		List<Khcs> khcss1 = new ArrayList<Khcs>();
		for(Khcs k : khcss){
			if(k.getKhcss() != null && k.getKhcss().size()>0){
				khcss1.add(k);
			}
		}
		return khcss1; 
		//return khcsDao.findBy("menuFlag", 1);
	}
	
	public Page<Khcs> getYjPkhcs(Page<Khcs> page) {
		return  khcsDao.findPage(page, " from Khcs k where k.pkhcs is null");
	}
	
	/**
	 * 根据上级考核参数ID查询考核列表
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Khcs> getKhcsByPkhcs(Long khcsId) {
		String sql = "select * from khcs t " +                  
					" where t.pkhcs=? ";
		SQLQuery query = khcsDao.createSQLQuery(sql, khcsId);
		query.addEntity(Khcs.class);
		return query.list();
	}
}
