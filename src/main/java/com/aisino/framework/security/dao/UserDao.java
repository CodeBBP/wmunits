package com.aisino.framework.security.dao;

import java.util.List;

import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.hibernate.HibernateDao;
import com.aisino.framework.security.entity.User;
import com.aisino.wmdw.gjgl.entity.Dwcl;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

/**
 * 用户持久化类
 * @author yuqs
 * @version 1.0
 */
@Component
public class UserDao extends HibernateDao<User, Long> {

	//获取排名后的单位信息
	@SuppressWarnings("unchecked")
	public Page<User> getUserByPx(Page<User> page, String ndsj, String pxzl, String jsort) {
		StringBuffer sqlBuffer = new StringBuffer();
		if(pxzl.equals("jf")){
			sqlBuffer.append("select * from(select a.*,decode(b.pxzf,null, 0, b.pxzf) as sjzf from sec_user a," +
					"( select c.sbdw, decode(sum(c.coun),null,0,sum(c.coun)) as pxzf from " +
					"(select b.sbdw,(case when b.coun>k.sccs then k.score*k.sccs else k.score*b.coun end) as coun " +
					"from khcs k, (select a.khcs_id,a.sbdw, count(1) as coun from Dwcl a " +
					"where a.spzt = '1' ");
		}else if(pxzl.equals("sbs")){
			sqlBuffer.append("select * from(select a.*,decode(b.coun,null, 0, b.coun) as coun " +
					"from sec_user a,(select a.sbdw,count(1) as coun from Dwcl a where 1=1 ");
		}else{
			sqlBuffer.append("select * from(select a.*,decode(b.coun,null, 0, b.coun) as coun " +
			"from sec_user a,(select a.sbdw,count(1) as coun from Dwcl a where a.spzt = '1' ");
		}
		
		if(ndsj != null && !ndsj.equals("")){
			sqlBuffer.append(" and a.cjsj>='"+ndsj+"-01-01'");
			sqlBuffer.append(" and a.cjsj<='"+ndsj+"-12-31'");
		}
		if(pxzl.equals("jf")){
			sqlBuffer.append(" group by a.khcs_id,a.sbdw)b  where k.id =b.khcs_id )c group by c.sbdw) b where a.id = b.sbdw(+) and a.enabled='0')c order by TO_NUMBER(c.sjzf, '999.9') ");
		}else{
			sqlBuffer.append(" group by a.sbdw) b where a.id = b.sbdw(+) and a.enabled='0')c order by TO_NUMBER(c.coun, '999.9') ");
		}
		if(jsort != null && jsort.equals("desc")){
			sqlBuffer.append("desc");
		}else{
			sqlBuffer.append("asc");
		}
		Query query = this.createSQLQuery(sqlBuffer.toString()).addEntity(User.class);
		page.setTotalCount(query.list().size());
		query.setFirstResult((page.getPageNo()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		page.setResult(query.list());
		return page;
	}

	//获取排名后的文明办信息
	@SuppressWarnings("unchecked")
	public Page<List> getOrgByPx(Page<List> page, String ndsj, String pxzl, String jsort) {
		StringBuffer sqlBuffer = new StringBuffer();
		if(pxzl.equals("jf")){
			sqlBuffer.append("select * from(select c.org,sum(c.sjzf) as sjzf from(select a.*,decode(b.pxzf,null, 0, b.pxzf) as sjzf from sec_user a," +
					"( select c.sbdw, decode(sum(c.coun),null,0,sum(c.coun)) as pxzf from " +
					"(select b.sbdw,(case when b.coun>k.sccs then k.score*k.sccs else k.score*b.coun end) as coun " +
					"from khcs k, (select a.khcs_id,a.sbdw, count(1) as coun from Dwcl a " +
					"where a.spzt = '1' ");
		}else if(pxzl.equals("sbs")){
			sqlBuffer.append("select * from(select c.org,sum(c.coun) as coun from( " +
					"select a.*,decode(b.coun,null, 0, b.coun) as coun " +
					" from sec_user a,(select a.sbdw,count(1) as coun from Dwcl a where 1=1 ");
		}else{
			sqlBuffer.append("select * from(select c.org,sum(c.coun) as coun from( " +
					"select a.*,decode(b.coun,null, 0, b.coun) as coun " +
					" from sec_user a,(select a.sbdw,count(1) as coun from Dwcl a where a.spzt = '1' ");
		}
		
		if(ndsj != null && !ndsj.equals("")){
			sqlBuffer.append(" and a.cjsj>='"+ndsj+"-01-01'");
			sqlBuffer.append(" and a.cjsj<='"+ndsj+"-12-31'");
		}
		if(pxzl.equals("jf")){
			sqlBuffer.append(" group by a.khcs_id,a.sbdw)b  where k.id =b.khcs_id )c group by c.sbdw) b where a.id = b.sbdw(+) and a.enabled='0')c group by c.org)d order by TO_NUMBER(d.sjzf, '999.9')");
		}else{
			sqlBuffer.append(" group by a.sbdw) b where a.id = b.sbdw(+) and a.enabled='0')c group by c.org)d " +
					"order by TO_NUMBER(d.coun, '999.9') ");
		}
		if(jsort != null && jsort.equals("desc")){
			sqlBuffer.append("desc");
		}else{
			sqlBuffer.append("asc");
		}
		System.out.println(sqlBuffer.toString());
		Query query = this.createSQLQuery(sqlBuffer.toString());
		page.setTotalCount(query.list().size());
		query.setFirstResult((page.getPageNo()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		page.setResult(query.list());
		return page;
	}

}
