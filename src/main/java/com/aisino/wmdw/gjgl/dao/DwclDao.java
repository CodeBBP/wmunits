package com.aisino.wmdw.gjgl.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.hibernate.HibernateDao;
import com.aisino.framework.security.entity.User;
import com.aisino.wmdw.gjgl.entity.Dwcl;
import com.aisino.wmdw.lhkh.entity.Khcs;

@Component
public class DwclDao extends HibernateDao<Dwcl, Long> {
	
	//统计申报总数
	public int getSbzsByDwmc(Long userId){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from Dwcl t ");
		sqlBuffer.append(" where t.sbdw=? ");
		Query query = this.createSQLQuery(sqlBuffer.toString(), userId);
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	//统计考核通过数
	public int getKhtgsByDwmc(Long userId){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from Dwcl t ");
		sqlBuffer.append(" where t.spzt='1' and t.sbdw=? ");
		Query query = this.createSQLQuery(sqlBuffer.toString(), userId);
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	//统计考核未通过数
	public int getKhwtgsByDwmc(Long userId){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from Dwcl t ");
		sqlBuffer.append(" where t.spzt='0' and t.sbdw=? ");
		Query query = this.createSQLQuery(sqlBuffer.toString(), userId);
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	// 查询需要审核的稿件
	@SuppressWarnings("unchecked")
	public List<Dwcl> getDwclByOrg(Long id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" from Dwcl t ");
		sqlBuffer.append(" where t.sbdw = ?");
		Query query = this.createQuery(sqlBuffer.toString(), id);
		return query.list();
	}
	
	public List<Dwcl> getActives() {
		Query query = this.createQuery(" from Dwcl t where t.spzt='N'");
		return query.list();
	}
	
	// 去除字符串右边的全0字符
	public String trimr0(String zzjgdm){
		int count = 0;
		while(zzjgdm.charAt(zzjgdm.length()-1) == '0'){
			count++;
			zzjgdm = zzjgdm.substring(0,zzjgdm.length()-2);
		}
		if(count == 0){
			return "";
		}
		return zzjgdm+"%";
	}

	//获取当前用户提交材料
	@SuppressWarnings("unchecked")
	public List<Dwcl> getDwclByUserAndKhcsId(Long uid, Long kid) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" from Dwcl t ");
		sqlBuffer.append(" where t.sbdw.id=? and t.khcsId=?");
		Query query = this.createQuery(sqlBuffer.toString(), uid, kid);
		return query.list();
	}

	public Long getSbzs() {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from Dwcl t ");
		Query query = this.createSQLQuery(sqlBuffer.toString());
		return Long.valueOf(query.uniqueResult().toString());
	}

	public Long getYgds() {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from Dwcl t ");
		sqlBuffer.append(" where t.spzt='1'");
		Query query = this.createSQLQuery(sqlBuffer.toString());
		return Long.valueOf(query.uniqueResult().toString());
	}

	public Long getBtgs() {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from Dwcl t ");
		sqlBuffer.append(" where t.spzt='0'");
		Query query = this.createSQLQuery(sqlBuffer.toString());
		return Long.valueOf(query.uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	public Page<Dwcl> getDwclByParameter(User user, Long[] khcsids, Long[] sbdwid, String title, String cjsjq, String cjsjz,
			String dwlx, String spzt, Page<Dwcl> page) {
		StringBuffer sqlBuffer = new StringBuffer();
		//StringBuffer countBuffer = new StringBuffer();
		//countBuffer.append("select count(1) from Dwcl t where t.wjlx='1'");
//		sqlBuffer.append("select * from (select t.*,rownum rn from Dwcl t ");sqlBuffer.append(" where t.wjlx='1' and rownum <="+page.getPageNo()*page.getPageSize());
		sqlBuffer.append("select * from Dwcl t where t.wjlx='1' ");
		if(user != null && user.getEnabled().equals("0")){
			sqlBuffer.append(" and t.sbdw='"+user.getId()+"'");
			//countBuffer.append(" and t.sbdw='"+user.getId()+"'");
		}
		if(khcsids != null && khcsids.length > 0){
			sqlBuffer.append(" and (");
			//countBuffer.append(" and");
			for(int i=0; i<khcsids.length; i++){
				sqlBuffer.append(" t.khcs_id='"+khcsids[i]+"' or");
				//countBuffer.append(" t.khcs_id='"+khcsids[i]+"' or");
			}
			sqlBuffer.replace(sqlBuffer.length()-3, sqlBuffer.length(), "");
			//countBuffer.replace(countBuffer.length()-3, countBuffer.length(), "");
			sqlBuffer.append(" )");
		}
		if(sbdwid != null && sbdwid.length > 0){
			sqlBuffer.append(" and (");
			for(int i=0; i<sbdwid.length; i++){
				sqlBuffer.append(" t.sbdw='"+sbdwid[i]+"' or");
			}
			sqlBuffer.replace(sqlBuffer.length()-3, sqlBuffer.length(), "");
			sqlBuffer.append(" )");
		}
		if(title != null && !title.equals("")){
			sqlBuffer.append(" and t.title='"+title+"'");
			//countBuffer.append(" and t.title='"+title+"'");
		}
		if(cjsjq != null && !cjsjq.equals("")){
			sqlBuffer.append(" and t.cjsj>='"+cjsjq+"'");
			//countBuffer.append(" and t.cjsj>='"+cjsjq+"'");
		}
		if(cjsjz != null && !cjsjz.equals("")){
			sqlBuffer.append(" and t.cjsj<='"+cjsjz+"'");
			//countBuffer.append(" and t.cjsj<='"+cjsjz+"'");
		}
		if(dwlx != null && !dwlx.equals("")){
			sqlBuffer.append(" and t.dwlx='"+dwlx+"'");
			//countBuffer.append(" and t.dwlx='"+dwlx+"'");
		}
		if(spzt != null && !spzt.equals("")){
			if(spzt.equals("n")){
				sqlBuffer.append(" and t.spzt is null");
			}else{
				sqlBuffer.append(" and t.spzt='"+spzt+"'");
			}
			//countBuffer.append(" and t.spzt='"+spzt+"'");
		}
		sqlBuffer.append(" order by t.cjsj desc");
		//sqlBuffer.append("  ) where rn >"+(page.getPageNo()-1)*page.getPageSize());
		//Query querycount = this.createSQLQuery(countBuffer.toString());
		//page.setTotalCount(Long.valueOf(querycount.list().get(0).toString()));
		Query query = this.createSQLQuery(sqlBuffer.toString()).addEntity(Dwcl.class);
		page.setTotalCount(query.list().size());
		query.setFirstResult((page.getPageNo()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		page.setResult(query.list());
		return page;
	}
	
	//统计所有的单位上报稿件
	@SuppressWarnings("unchecked")
	public Page<List> getDwtjByParameter(Page<List> page, List<User> users, String cjsjq, String cjsjz, String[] dwlx, String spzt) {
		StringBuffer sqlBuffer = new StringBuffer();
		StringBuffer countBuffer = new StringBuffer();
//		Query query1 = this.createSQLQuery(sqlBuffer.toString());
//		query1.setMaxResults(10);
//		query1.setFirstResult(0);
//		page.setResult(query1.list());
		countBuffer.append("select count(1) from sec_user a,(select t.sbdw ,count(1) as total from Dwcl t where t.wjlx='1'");	  //统计总数
		if(spzt != null && !spzt.equals("")){
			sqlBuffer.append("select count(1) from (select a.*,rownum rn from (select count(1) from Dwcl t");	  //统计有审批状态的单位
		}else{
			sqlBuffer.append("select * from (select c.*,rownum rn from (" +
					"select a.id,decode(b.total,null, 0, b.total) as count from  sec_user a ," +
					"(select t.sbdw, count(1) as total from Dwcl t ");		//统计所有单位的
		}
		sqlBuffer.append(" where t.wjlx='1' ");
		if(dwlx != null){
			sqlBuffer.append(" and ");
			countBuffer.append(" and ");
			if(users != null && users.size()>0){
				for(User user : users){
					sqlBuffer.append(" t.sbdw='"+user.getId().toString()+"' or");
					countBuffer.append(" t.sbdw='"+user.getId().toString()+"' or");
				}
				sqlBuffer.replace(sqlBuffer.length()-3, sqlBuffer.length(), "");
				countBuffer.replace(countBuffer.length()-3, countBuffer.length(), "");
			}else{
				return null;
			}
		}
		
		if(cjsjq != null && !cjsjq.equals("")){
			sqlBuffer.append(" and t.cjsj>='"+cjsjq+"'");
			countBuffer.append(" and t.cjsj>='"+cjsjq+"'");
		}
		if(cjsjz != null && !cjsjz.equals("")){
			sqlBuffer.append(" and t.cjsj<='"+cjsjz+"'");
			countBuffer.append(" and t.cjsj<='"+cjsjz+"'");
		}
		if(spzt != null && !spzt.equals("")){
			sqlBuffer.append(" and t.spzt='"+spzt+"'");
			countBuffer.append(" and t.spzt='"+spzt+"'");
		}
		sqlBuffer.append(" group by t.sbdw )b where a.enabled='0' and a.id=b.sbdw(+))c where rownum <="+page.getPageNo()*page.getPageSize()+") where rn>"+(page.getPageNo()-1)*page.getPageSize());
		countBuffer.append(" group by t.sbdw )b where a.enabled='0' and a.id=b.sbdw(+) ");
		Query querycount = this.createSQLQuery(countBuffer.toString());
		if(querycount.list()!=null && querycount.list().size()>0){
			page.setTotalCount(Long.valueOf(querycount.list().get(0).toString()));
		}else{
			page.setTotalCount(Long.valueOf(0));
		}
		Query query = this.createSQLQuery(sqlBuffer.toString());
		page.setResult(query.list());
		return page;
	}

	public int getCountByOrg() {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from Dwcl t where t.spzt is null ");
		Query query = this.createSQLQuery(sqlBuffer.toString());
		return Integer.valueOf((query.uniqueResult().toString()));
	}

	public String getYgdsByParameter(Long sbdwid, String cjsjq, String cjsjz,
			String[] dwlx, String spzt) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from Dwcl t ");	  //统计有审批状态的单位
		sqlBuffer.append(" where t.wjlx='1' ");
		if(sbdwid != null && sbdwid.longValue()>0){
			sqlBuffer.append(" and t.sbdw='"+sbdwid+"'");
		}
		if(cjsjq != null && !cjsjq.equals("")){
			sqlBuffer.append(" and t.cjsj>='"+cjsjq+"'");
		}
		if(cjsjz != null && !cjsjz.equals("")){
			sqlBuffer.append(" and t.cjsj<='"+cjsjz+"'");
		}
		if(spzt != null && !spzt.equals("")){
			sqlBuffer.append(" and t.spzt='"+spzt+"'");
		}
		Query query = this.createSQLQuery(sqlBuffer.toString());
		return query.uniqueResult().toString();
	}

	//
	@SuppressWarnings("unchecked")
	public String getDwsbzs(Long sbdw, String ndsj, String spzt) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from Dwcl t where t.wjlx='1' ");
		if(sbdw != null && sbdw > 0){
			sqlBuffer.append(" and t.sbdw='"+sbdw+"'");
		}
		if(ndsj != null && !ndsj.equals("")){
			sqlBuffer.append(" and t.cjsj>='"+ndsj+"-01-01'");
			sqlBuffer.append(" and t.cjsj<='"+ndsj+"-12-31'");
		}
		if(spzt != null && !spzt.equals("")){
			sqlBuffer.append(" and t.spzt='"+spzt+"'");
		}
		Query query = this.createSQLQuery(sqlBuffer.toString());
		return query.uniqueResult().toString();
	}

	public String getDwzf(Long id, String ndsj) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select decode(sum(c.coun),null,0, sum(c.coun)) from (select (case when b.coun>k.sccs then k.score*k.sccs else k.score*b.coun end) " +
				"as coun from khcs k,(select a.khcs_id,count(1) as coun from Dwcl a where a.spzt='1' ");
		if(id != null && id > 0){
			sqlBuffer.append(" and a.sbdw='"+id+"'");
		}
		if(ndsj != null && !ndsj.equals("")){
			sqlBuffer.append(" and a.cjsj>='"+ndsj+"-01-01'");
			sqlBuffer.append(" and a.cjsj<='"+ndsj+"-12-31'");
		}
		sqlBuffer.append(" group by a.khcs_id)b where k.id =b.khcs_id)c");
		Query query = this.createSQLQuery(sqlBuffer.toString());
		return query.uniqueResult().toString();
	}

	public String getDwsbzsByLm(Long sbdw, List<Long> khcsid, String ndsj, String ydsj, String spzt) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from Dwcl t where t.wjlx='1' ");
		if(sbdw != null && sbdw > 0){
			sqlBuffer.append(" and t.sbdw='"+sbdw+"'");
		}
		if(khcsid != null && khcsid.size() > 0){
			sqlBuffer.append(" and (");
			for(Long kid : khcsid){
				sqlBuffer.append(" t.khcs_id='"+kid+"' or");
			}
			sqlBuffer.replace(sqlBuffer.length()-3, sqlBuffer.length(), "");
			sqlBuffer.append(" ) ");
		}
		if(ndsj != null && !ndsj.equals("")){
			if(ydsj != null && !ydsj.equals("")){
				sqlBuffer.append(" and t.cjsj>='"+ndsj+"-"+ydsj+"-01'");
				sqlBuffer.append(" and t.cjsj<='"+ndsj+"-"+ydsj+"-31'");
			}else{
				sqlBuffer.append(" and t.cjsj>='"+ndsj+"-01-01'");
				sqlBuffer.append(" and t.cjsj<='"+ndsj+"-12-31'");
			}
		}
		if(spzt != null && !spzt.equals("")){
			sqlBuffer.append(" and t.spzt='"+spzt+"'");
		}
		Query query = this.createSQLQuery(sqlBuffer.toString());
		return query.uniqueResult().toString();
	}

	public String getDwzfByLm(Long sbdw, List<Long> khcsid, String ndsj, String ydsj) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select decode(sum(c.coun),null,0, sum(c.coun)) from (select (case when b.coun>k.sccs then k.score*k.sccs else k.score*b.coun end) " +
				"as coun from khcs k,(select a.khcs_id,count(1) as coun from Dwcl a where a.spzt='1' ");
		if(sbdw != null && sbdw > 0){
			sqlBuffer.append(" and a.sbdw='"+sbdw+"'");
		}
		if(khcsid != null && khcsid.size() > 0){
			sqlBuffer.append(" and (");
			for(Long kid : khcsid){
				sqlBuffer.append(" a.khcs_id='"+kid+"' or");
			}
			sqlBuffer.replace(sqlBuffer.length()-3, sqlBuffer.length(), "");
			sqlBuffer.append(" ) ");
		}
		if(ndsj != null && !ndsj.equals("")){
			if(ydsj != null && !ydsj.equals("")){
				sqlBuffer.append(" and a.cjsj>='"+ndsj+"-"+ydsj+"-01'");
				sqlBuffer.append(" and a.cjsj<='"+ndsj+"-"+ydsj+"-31'");
			}else{
				sqlBuffer.append(" and a.cjsj>='"+ndsj+"-01-01'");
				sqlBuffer.append(" and a.cjsj<='"+ndsj+"-12-31'");
			}
		}
		sqlBuffer.append(" group by a.khcs_id)b where k.id =b.khcs_id)c");
		Query query = this.createSQLQuery(sqlBuffer.toString());
		return query.uniqueResult().toString();
	}
}
