package com.aisino.framework.security.dao;

import com.aisino.framework.orm.hibernate.HibernateDao;
import com.aisino.framework.security.entity.Org;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

/**
 * 部门持久化类
 * @author yuqs
 * @version 1.0
 */
@Component
public class OrgDao extends HibernateDao<Org, Long> {

	//根据名称查询机关
	public Org getOrgBySsxq(String ssxq) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from Org t ");
		sqlBuffer.append(" where t.zzjgmc=? ");
		Query query = this.createSQLQuery(sqlBuffer.toString(), ssxq);
		return (Org) query.uniqueResult();
	}

}
