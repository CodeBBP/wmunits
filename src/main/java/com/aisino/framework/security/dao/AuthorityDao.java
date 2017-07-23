package com.aisino.framework.security.dao;

import com.aisino.framework.orm.hibernate.HibernateDao;
import com.aisino.framework.security.entity.Authority;
import org.springframework.stereotype.Component;

/**
 * 权限持久化类
 * @author yuqs
 * @version 1.0
 */
@Component
public class AuthorityDao extends HibernateDao<Authority, Long> {

}
