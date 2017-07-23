package com.aisino.framework.security.service;

import java.util.ArrayList;
import java.util.List;

import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.PropertyFilter;
import com.aisino.framework.security.dao.OrgDao;
import com.aisino.framework.security.entity.Org;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 部门管理类
 * @author yuqs
 * @version 1.0
 */
@Component
public class OrgManager {
	//注入部门持久化对象
	@Autowired
	private OrgDao orgDao;
	
	/**
	 * 保存部门实体
	 * @param entity
	 */
	public void save(Org entity) {
		orgDao.save(entity);
	}
	
	/**
	 * 根据主键ID删除对应的部门
	 * @param id
	 */
	public void delete(Long id) {
		orgDao.delete(id);
	}
	
	/**
	 * 根据主键ID获取部门实体
	 * @param id
	 * @return
	 */
	public Org get(Long id) {
		return orgDao.get(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询部门列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Org> findPage(final Page<Org> page, final List<PropertyFilter> filters) {
		return orgDao.findPage(page, filters);
	}
	
	/**
	 * 获取所有部门记录
	 * @return
	 */
	public List<Org> getAll() {
		return orgDao.getAll();
	}
	
	/**
	 * 根据名称查询机关
	 * @return
	 */
	public Org getOrgBySsxq(String ssxq) {
		return orgDao.getOrgBySsxq(ssxq);
	}

	/**
	 * 获取县区文明办
	 * @return
	 */
	public Object getQxorg() {
		List<Org> orgs = new ArrayList<Org>();
		for(Org o : orgDao.getAll()){
			if(o.getParentOrg()!=null){
				orgs.add(o);
			}
		}
		return orgs;
	}
}
