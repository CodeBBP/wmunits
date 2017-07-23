package com.aisino.wmdw.sxsb.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.aisino.framework.orm.hibernate.HibernateDao;
import com.aisino.wmdw.sxsb.entity.Cxhj;

@Component
public class CxhjDao extends HibernateDao<Cxhj, Long> {

	// 查询需要审核的稿件
	@SuppressWarnings("unchecked")
	public List<Cxhj> getCxhjByZzjgdm(Long id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" from Cxhj t ");
		sqlBuffer.append(" where t.operator = ?");
		Query query = this.createQuery(sqlBuffer.toString(), id);
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
			return zzjgdm;
		}
		return zzjgdm+"%";
	}

	@SuppressWarnings("unchecked")
	public List<Cxhj> getDbrwByZzjgdm(String zzjgdm) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" from Cxhj t ");
		sqlBuffer.append(" where t.zzjgdm like ?");
		Query query = this.createQuery(sqlBuffer.toString(), trimr1(zzjgdm));
		return query.list();
	}
	
	// 去除字符串右边的全0字符
	public String trimr1(String zzjgdm){
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
