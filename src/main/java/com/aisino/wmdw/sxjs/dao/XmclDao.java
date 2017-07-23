package com.aisino.wmdw.sxjs.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.aisino.framework.orm.hibernate.HibernateDao;
import com.aisino.wmdw.sxjs.entity.Xmcl;

@Component
public class XmclDao extends HibernateDao<Xmcl, Long> {
	
	@SuppressWarnings("unchecked")
	public int getSbzsByXsmc(String dwmc){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from Xmcl t ");
		sqlBuffer.append(" where t.sbxs=? ");
		Query query = this.createSQLQuery(sqlBuffer.toString(), dwmc);
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	@SuppressWarnings("unchecked")
	public int getKhtgsByXsmc(String dwmc){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from Xmcl t ");
		sqlBuffer.append(" where t.shzt='1' and t.sbxs=? ");
		Query query = this.createSQLQuery(sqlBuffer.toString(), dwmc);
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	@SuppressWarnings("unchecked")
	public int getKhwtgsByXsmc(String dwmc){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from Xmcl t ");
		sqlBuffer.append(" where t.shzt='0' and t.sbxs=? ");
		Query query = this.createSQLQuery(sqlBuffer.toString(), dwmc);
		return Integer.valueOf(query.uniqueResult().toString());
	}
	
	@SuppressWarnings("unchecked")
	public int getWkhsByXsmc(String dwmc){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select count(1) from Xmcl t ");
		sqlBuffer.append(" where t.shzt='N' and t.sbxs=? ");
		Query query = this.createSQLQuery(sqlBuffer.toString(), dwmc);
		return Integer.valueOf(query.uniqueResult().toString());
	}

	// 查询需要审核的稿件
	@SuppressWarnings("unchecked")
	public List<Xmcl> getXmclByUsername(String username) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" from Xmcl t ");
		sqlBuffer.append(" where t.shzt='N' and t.shld=? ");
		Query query = this.createQuery(sqlBuffer.toString(), username);
		return query.list();
	}
}
