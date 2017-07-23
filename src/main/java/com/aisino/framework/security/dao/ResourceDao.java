package com.aisino.framework.security.dao;

import com.aisino.framework.orm.hibernate.HibernateDao;
import com.aisino.framework.security.entity.Resource;
import org.springframework.stereotype.Component;

/**
 * 资源持久化类
 * @author yuqs
 * @version 1.0
 */
@Component
public class ResourceDao extends HibernateDao<Resource, Long> {

}
