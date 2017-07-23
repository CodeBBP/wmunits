package com.aisino.wmdw.gjgl.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.aisino.framework.orm.hibernate.HibernateDao;
import com.aisino.wmdw.gjgl.entity.Dwgg;

@Component
public class DwggDao extends HibernateDao<Dwgg, Long> {

	@SuppressWarnings("unchecked")
	public List<Dwgg> getDwggByJsdwId(Long id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" from Dwgg t ");
		sqlBuffer.append(" where t.jsdwid = ?");
		Query query = this.createQuery(sqlBuffer.toString(), id);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Dwgg> getDwggByTzggId(Long id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" from Dwgg t ");
		sqlBuffer.append(" where t.tzggid = ?");
		Query query = this.createQuery(sqlBuffer.toString(), id);
		return query.list();
	}

	public Dwgg getDwggByJsdwIdAndTzggId(Long jsdwid, Long tzggid) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" from Dwgg t ");
		sqlBuffer.append(" where t.tzggid = ? and t.jsdwid = ?");
		Query query = this.createQuery(sqlBuffer.toString(), tzggid, jsdwid);
		return (Dwgg) query.uniqueResult();
	}
	
}
