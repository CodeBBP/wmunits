package com.aisino.wmdw.gjgl.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.aisino.framework.orm.hibernate.HibernateDao;
import com.aisino.wmdw.gjgl.entity.Tzgg;

@Component
public class TzggDao extends HibernateDao<Tzgg, Long> {
	
	// 查询需要审核的稿件
	@SuppressWarnings("unchecked")
	public List<Tzgg> getTzggByZzjgdm(String zzjgdm) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" from Tzgg t ");
		sqlBuffer.append(" where t.zzjgdm like ?");
		Query query = this.createQuery(sqlBuffer.toString(), trimr0(zzjgdm));
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
}
