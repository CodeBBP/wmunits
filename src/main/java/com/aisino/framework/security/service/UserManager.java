package com.aisino.framework.security.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.PropertyFilter;
import com.aisino.framework.security.dao.UserDao;
import com.aisino.framework.security.entity.Org;
import com.aisino.framework.security.entity.User;
import com.aisino.framework.utils.Digests;
import com.aisino.framework.utils.EncodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户管理类
 * @author yuqs
 * @version 1.0
 */
@Component
public class UserManager {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	//注入用户持久化对象
	@Autowired
	private UserDao userDao;
	
	/**
	 * 保存、更新用户实体
	 * @param entity
	 */
	public void save(User entity) {
//		if (StringUtils.isNotBlank(entity.getPlainPassword())) {
//			entryptPassword(entity);
//		}
		userDao.save(entity);
	}
	
	/**
	 * 根据主键ID删除对应的用户实体
	 * @param id
	 */
	public void delete(Long id) {
		userDao.delete(id);
	}
	
	/**
	 * 根据主键ID获取用户实体
	 * @param id
	 * @return
	 */
	public User get(Long id) {
		return userDao.get(id);
	}
	
	/**
	 * 根据用户名称，获取用户实体
	 * @param username
	 * @return
	 */
	public User findUserByPhone(String username) {
		return userDao.findUniqueBy("sjhm", username);
	}
	
	/**
	 * 根据用户名称，获取用户实体
	 * @param username
	 * @return
	 */
	public User findUserByUserName(String username) {
		return userDao.findUniqueBy("username", username);
	}
	
	/**
	 * 根据单位名称，获取用户实体
	 * @param username
	 * @return
	 */
	public User findUserByDwmc(String dwmc) {
		return userDao.findUniqueBy("dwmc", dwmc);
	}
	
	/**
	 * 根据用户名判断是否唯一
	 * @param newUserName
	 * @param oldUserName
	 * @return
	 */
	public boolean isUserNameUnique(String newUserName, String oldUserName) {
		return userDao.isPropertyUnique("username", newUserName, oldUserName);
	}
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询用户列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<User> findPage(final Page<User> page, final List<PropertyFilter> filters) {
		return userDao.findPage(page, filters);
	}
	
	/**
	 * 根据分页对象、所属部门ID号，分页查询用户列表
	 * @param page
	 * @param orgId
	 * @return
	 */
	public Page<User> searchUser(final Page<User> page, Long orgId) {
		Org org = new Org(orgId);
		
		String hql = "from User user where user.org=?";
		return userDao.findPage(page, hql, org);
	}
	
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<User> getAll() {
		return userDao.getAll();
	}
	
	/**
	 * 根据用户ID查询该用户所拥有的权限列表
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAuthoritiesName(Long userId) {
		String sql = "select a.name from sec_user u " + 
					" left outer join sec_role_user ru on u.id=ru.user_id " + 
					" left outer join sec_role r on ru.role_id=r.id " + 
					" left outer join sec_role_authority ra on r.id = ra.role_id " + 
					" left outer join sec_authority a on ra.authority_id = a.id " +                     
					" where u.id=? ";
		SQLQuery query = userDao.createSQLQuery(sql, userId);
		return query.list();
	}
	
	/**
	 * 根据用户ID查询该用户所拥有的角色列表
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getRolesName(Long userId) {
		String sql = "select r.name from sec_user u " + 
					" left outer join sec_role_user ru on u.id=ru.user_id " + 
					" left outer join sec_role r on ru.role_id=r.id " + 
					" where u.id=? ";
		SQLQuery query = userDao.createSQLQuery(sql, userId);
		return query.list();
	}
	
	/**
	 * 根据机构ID查询用户列表
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUserByOrg(Long orgId) {
		String sql = "select * from sec_user u " +                  
					" where u.org=? ";
		SQLQuery query = userDao.createSQLQuery(sql, orgId);
		query.addEntity(User.class);
		return query.list();
	}

	/**
	 * 根据机构ID查询用户列表
	 * @param orgId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<User> getUserByPx(Page<User> page, String ndsj, String pxzl, String jfsort) {
//		if(sbdwid != null && sbdwid > 0){
//			return userDao.findPage(page, " from User u where u.id = "+sbdwid);
//		}
//		return userDao.findPage(page, " from User u where u.org.id = "+orgId);
		return userDao.getUserByPx(page, ndsj, pxzl, jfsort);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findUserByParameter(String dwmc, String[] dwlx) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from sec_user t ");
		if(dwlx != null && dwlx.length>0){
			sqlBuffer.append(" where 1=1 and");
			for(int i=0; i<dwlx.length; i++){
				sqlBuffer.append(" t.dwlx='"+dwlx[i]+"' or");
			}
			sqlBuffer.replace(sqlBuffer.length()-3, sqlBuffer.length(), "");
		}
		if(dwmc != null && !dwmc.equals("")){
			sqlBuffer.append(" and t.dwmc='"+dwmc+"'");
		}
		Query query = userDao.createSQLQuery(sqlBuffer.toString()).addEntity(User.class);
		return query.list();
	}

	//根据所属文明办获取总分
	@SuppressWarnings("unchecked")
	public String getZfByOrgid(Long id) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from sec_user t where t.org = '" + id +"'"); 
		Query query = userDao.createSQLQuery(sqlBuffer.toString()).addEntity(User.class);
		List<User> users = query.list();
		Float f = 0.0f;
		for(User u : users){
			if(u.getZf() != null && !u.getZf().equals("")){
				f += Float.valueOf(u.getZf());
			}
		}
		return f.toString();
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserByZf() {
		String sql = "select * from sec_user u where u.enabled='0'and rownum < 7 order by TO_NUMBER(u.zf, '999.9')  desc ";
		SQLQuery query = userDao.createSQLQuery(sql);
		query.addEntity(User.class);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public Page<List> getOrgByPx(Page<List> page, String ndsj, String pxzl, String jsort) {
		return userDao.getOrgByPx(page, ndsj, pxzl, jsort);
	}
	
//	/**
//	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
//	 */
//	private void entryptPassword(User user) {
//		byte[] salt = Digests.generateSalt(SALT_SIZE);
//		user.setSalt(EncodeUtils.hexEncode(salt));
//
//		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
//		user.setPassword(EncodeUtils.hexEncode(hashPassword));
//	}

}
