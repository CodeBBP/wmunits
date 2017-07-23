package com.aisino.framework.security.dao;

import com.aisino.framework.orm.hibernate.HibernateDao;
import com.aisino.framework.security.entity.Role;
import org.springframework.stereotype.Component;

/**
 * 角色持久化类
 * @author yuqs
 * @version 1.0
 */
@Component
public class RoleDao extends HibernateDao<Role, Long> {

}
