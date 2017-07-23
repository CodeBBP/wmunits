package com.aisino.framework.security.dao;

import org.springframework.stereotype.Component;

import com.aisino.framework.orm.hibernate.HibernateDao;
import com.aisino.framework.security.entity.Czrz;

/**
 * 操作日志持久化类
 * @author yuqs
 * @version 1.0
 */
@Component
public class CzrzDao extends HibernateDao<Czrz, Long> {

}
