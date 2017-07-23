package com.aisino.framework.security.dao;

import com.aisino.framework.orm.hibernate.HibernateDao;
import com.aisino.framework.security.entity.Menu;
import org.springframework.stereotype.Component;

/**
 * 菜单持久化类
 * @author yuqs
 * @version 1.0
 */
@Component
public class MenuDao extends HibernateDao<Menu, Long> {

}
