package com.aisino.wmdw.gjgl.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.PropertyFilter;
import com.aisino.framework.security.dao.CzrzDao;
import com.aisino.framework.security.dao.OrgDao;
import com.aisino.framework.security.dao.UserDao;
import com.aisino.framework.security.entity.Czrz;
import com.aisino.framework.security.entity.Org;
import com.aisino.framework.security.entity.User;
import com.aisino.framework.security.service.UserManager;
import com.aisino.wmdw.gjgl.dao.DwclDao;
import com.aisino.wmdw.gjgl.entity.Dwcl;
import com.aisino.wmdw.lhkh.dao.KhcsDao;
import com.aisino.wmdw.lhkh.entity.Khcs;

/**
 * 报送稿件管理类
 * @author xuzhe
 * @version 1.0
 */
@Component
public class DwclManager {
	//注入变更项目持久化对象
	@Autowired
	private DwclDao dwclDao;
//	@Autowired
//	private CzrzDao czrzDao;
	@Autowired
	private OrgDao orgDao;
	@Autowired
	private KhcsDao khcsDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserManager userManager;
	
	/**
	 * 保存配置报送稿件实体
	 * @param entity
	 */
	public void save(Dwcl entity, String tsbz, User user) {
		if(tsbz != "1"){
			Czrz czrz = new Czrz();
			czrz.setCzry(user);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String czsj = sdf.format(new Date());
			czrz.setCzsj(czsj);
			if(entity.getWjlx().equals("1")){
				czrz.setDqzt(entity.getSpzt());
				czrz.setWjlx("单位材料");
			}
			if(entity.getWjlx().equals("2")){
				czrz.setDqzt(entity.getGjzt());
				czrz.setWjlx("宣传稿件");
			}
			czrz.setSbdw(entity.getSbdw());
			czrz.setTitle(entity.getTitle());
			//czrzDao.save(czrz);
		}
		dwclDao.save(entity);
	}
	
	/**
	 * 根据主键ID获取配置报送稿件实体
	 * @param id
	 * @return
	 */
	public Dwcl get(Long id) {
		return dwclDao.get(id);
	}
	
	/**
	 * 根据主键ID删除对应的配置报送稿件实体
	 * @param id
	 */
	public void delete(Long id) {
		dwclDao.delete(id);
	}
	
	/**
	 * 根据主键ID删除对应的配置报送稿件实体
	 * @param id
	 */
	public void delete(Long[] id) {
		for(int i = 0; i<id.length; i++){
			dwclDao.delete(id[i]);
		}
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询配置报送稿件列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<Dwcl> findPage(final Page<Dwcl> page, final List<PropertyFilter> filters) {
		return dwclDao.findPage(page, filters);
	}
	
	public List<Dwcl> getAll() {
		List<Dwcl> dwcls = dwclDao.getAll();
		return dwcls; 
	}
	
	//上报总数
	public int getSbzsByDwmc(Long userId){
		return dwclDao.getSbzsByDwmc(userId);
	}
	
	//审核通过数
	public int getKhtgsByDwmc(Long userId){
		return dwclDao.getKhtgsByDwmc(userId);
	}
	
	//审核未通过数
	public int getKhwtgsByDwmc(Long userId){
		return dwclDao.getKhwtgsByDwmc(userId);
	}
	
	//根据组织机构代码获取单位材料
	public Page<Dwcl> getDwclByOrg(Page<Dwcl> page, Long orgId) {
		Org org = orgDao.get(orgId);
		Org parent = org.getParentOrg();
		if(parent == null){
			return dwclDao.findPage(page, " from Dwcl t where t.spzt is null order by t.cjsj desc");
		} else {
			return page;
		}
	}
	
	//根据组织机构代码获取待审核总数
	public int getCountByOrg(Long orgId) {
		Org org = orgDao.get(orgId);
		Org parent = org.getParentOrg();
		if(parent == null){
			return dwclDao.getCountByOrg();
		} else {
			return 0;
		}
	}

	public Page<Dwcl> getDwclByUserAndKhcsId(Page<Dwcl> page, Long uid, Long kid) {
		List<Dwcl> dwcls = new ArrayList<Dwcl>();
		Khcs khcs = khcsDao.get(kid);
		List<Khcs> khcss = khcs.getKhcss();
		StringBuffer sb = new StringBuffer();
		sb.append(" from Dwcl t where t.sbdw.id="+uid);
		if(khcss.size()>0){
			sb.append(" and (");
			for(Khcs k : khcss){
				sb.append("t.khcsId="+k.getId()+" or ");
			}
			sb.replace(sb.length()-3, sb.length(), "");
			sb.append(" ) order by t.cjsj desc");
		}
		return dwclDao.findPage(page, sb.toString());
	}

	//统计上报总数
	public Long getSbzs() {
		return dwclDao.getSbzs();
	}

	//统计已归档数
	public Long getYgds() {
		return dwclDao.getYgds();
	}

	//统计未通过数
	public Long getBtgs() {
		return dwclDao.getBtgs();
	}

	public Page<Dwcl> getDwclByParameter(User user, Long khcsid, Long sbdwid, Long qxwmb, String tile, String cjsjq,
			String cjsjz, String dwlx, String spzt, Page<Dwcl> page) {
		Long[] khcsids = null;	//如果包含下属参数则要包含进
		Long[] sbdw = null;
		if(khcsid != null && khcsid.longValue()>0){
			Khcs khcs = khcsDao.get(khcsid);
			List<Khcs> khcss = khcs.getKhcss();
			if(khcss !=null && khcss.size()>0){
				khcsids = new Long[khcss.size()];
				int i = 0;
				for(Khcs k : khcss){
					khcsids[i] = k.getId();
					i++;
				}
			}else{
				khcsids = new Long[1];
				khcsids[0] = khcsid;
			}
		}
		if(qxwmb != null && qxwmb > 0 && sbdwid == null){
			List<User> users = userManager.getUserByOrg(qxwmb);
			if(users !=null && users.size()>0){
				sbdw = new Long[users.size()];
				int i = 0;
				for(User u : users){
					sbdw[i] = u.getId();
					i++;
				}
			}
		}else if(sbdwid != null){
			sbdw = new Long[1];
			sbdw[0] = sbdwid;
		}
		return dwclDao.getDwclByParameter(user, khcsids, sbdw, tile, cjsjq, cjsjz, dwlx, spzt, page);
	}

	//根据参数查询总数
	@SuppressWarnings("unchecked")
	public Page<List> getDwtjByParameter(Page<List> page, List<User> user, String cjsjq, String cjsjz, String[] dwlx, String spzt) {
		return dwclDao.getDwtjByParameter(page, user, cjsjq, cjsjz, dwlx, spzt);
	}
	
	//根据参数查询已归档数
	@SuppressWarnings("unchecked")
	public String getYgdsByParameter(Long sbdwid, String cjsjq, String cjsjz, String[] dwlx, String spzt) {
		return dwclDao.getYgdsByParameter(sbdwid, cjsjq, cjsjz, dwlx, spzt);
	}

	//获取单位上报数
	public String getDwsbzs(Long sbdw, String ndsj, String spzt) {
		return dwclDao.getDwsbzs(sbdw, ndsj, spzt);
	}

	public String getDwzf(Long id, String ndsj) {
		return dwclDao.getDwzf(id, ndsj);
	}

	public String getDwsbzsByLm(Long sbdw, Long khcsid, String ndsj, String ydsj, String spzt) {
		List<Long> khcslist = new ArrayList<Long>();
		for(Khcs khcs : khcsDao.get(khcsid).getKhcss()){
			if(khcs.getKhcss() != null && khcs.getKhcss().size() > 0){
				for(Khcs k : khcs.getKhcss()){
					khcslist.add(k.getId());
				}
			}
		}
		return dwclDao.getDwsbzsByLm(sbdw, khcslist, ndsj, ydsj, spzt);
	}

	public String getDwzfByLm(Long sbdw, Long khcsid, String ndsj, String ydsj) {
		List<Long> khcslist = new ArrayList<Long>();
		for(Khcs khcs : khcsDao.get(khcsid).getKhcss()){
			if(khcs.getKhcss() != null && khcs.getKhcss().size() > 0){
				for(Khcs k : khcs.getKhcss()){
					khcslist.add(k.getId());
				}
			}
		}
		return dwclDao.getDwzfByLm(sbdw, khcslist, ndsj, ydsj);
	}

	public String getWmbsbzs(Long id, String ndsj, String spzt) {
		List<User> users = userManager.getUserByOrg(id);
		Integer count = 0;
		String zs = null;
		for(User u : users){
			zs = dwclDao.getDwsbzs(u.getId(), ndsj, spzt);
			count += Integer.valueOf(zs);
		}
		return count.toString();
	}
}
