package com.aisino.framework.dictionary.dao;

import com.aisino.framework.dictionary.entity.Dictionary;
import com.aisino.framework.orm.hibernate.HibernateDao;
import org.springframework.stereotype.Component;

/**
 * 配置字典持久化类
 * @author yuqs
 */
@Component
public class DictionaryDao extends HibernateDao<Dictionary, Long> {

}
