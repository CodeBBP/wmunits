package com.aisino.wmdw.sxsb.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.PropertyFilter;
import com.aisino.framework.security.dao.CzrzDao;
import com.aisino.framework.security.entity.Czrz;
import com.aisino.framework.security.shiro.ShiroUtils;
import com.aisino.wmdw.gjgl.entity.Dwcl;
import com.aisino.wmdw.lhkh.dao.KhgzDao;
import com.aisino.wmdw.lhkh.entity.Khcs;
import com.aisino.wmdw.lhkh.entity.Khgz;
import com.aisino.wmdw.sxsb.dao.CxhjDao;
import com.aisino.wmdw.sxsb.entity.Cxhj;

/**
 * 城乡环境管理类
 * @author xuzhe
 * @version 1.0
 */
@Component
public class CxhjManager {
	//注入变更项目持久化对象
	@Autowired
	private CxhjDao cxhjDao;
	@Autowired
	private CzrzDao czrzDao;
	@Autowired
	private KhgzDao khgzDao;
	
	/**
	 * 保存配置城乡环境实体
	 * @param entity
	 */
	public void save(Cxhj entity, String tsbz) {
		if(!tsbz.equals("1")){
			Czrz czrz = new Czrz();
			czrz.setCzry(ShiroUtils.getUser());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String czsj = sdf.format(new Date());
			czrz.setCzsj(czsj);
			czrz.setSbdw(entity.getScdw());
			czrz.setTitle(entity.getTitle());
			czrzDao.save(czrz);
		}
		cxhjDao.save(entity);
	}
	
	/**
	 * 根据主键ID获取配置城乡环境实体
	 * @param id
	 * @return
	 */
	public Cxhj get(Long id) {
		return cxhjDao.get(id);
	}
	
	/**
	 * 根据主键ID删除对应的配置城乡环境实体
	 * @param id
	 */
	public void delete(Long id) {
		cxhjDao.delete(id);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询配置城乡环境列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Cxhj> findPage(final Page<Cxhj> page, final List<PropertyFilter> filters) {
		return cxhjDao.findPage(page, filters);
	}
	
	public List<Cxhj> getAll() {
		List<Cxhj> cxhjs = cxhjDao.getAll();
		return cxhjs; 
	}

	public List<Cxhj> getCxhjByZzjgdm(String zzjgdm) {
		List<Cxhj> cxhjs = cxhjDao.getCxhjByZzjgdm(Long.valueOf("1"));
		return cxhjs;
	}

	public List<Cxhj> getDbrwByZzjgdm(String zzjgdm) {
		List<Cxhj> cxhjs = cxhjDao.getDbrwByZzjgdm(zzjgdm);
		return cxhjs;
	}

	public Page<Cxhj> getCxhjByUserAndKhgzId(Page<Cxhj> page, Long uid, Long kid) {
		List<Dwcl> dwcls = new ArrayList<Dwcl>();
		Khgz khgz = khgzDao.get(kid);
//		List<Khgz> khgzs = khgz.getKhgzs();
		StringBuffer sb = new StringBuffer();
		sb.append(" from Cxhj t where t.scdw.id="+uid);
		sb.append(" and t.khgz.id="+kid);
//		if(khgzs.size()>0){
//			sb.append(" and (");
//			for(Khgz k : khgzs){
//				sb.append("t.khgzId="+k.getId()+" or ");
//			}
//			sb.replace(sb.length()-3, sb.length(), "");
//			sb.append(" ) ");
//		}
		return cxhjDao.findPage(page, sb.toString());
	}
	
}
