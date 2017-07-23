package com.aisino.framework.security.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.PropertyFilter;
import com.aisino.framework.security.entity.Lsdw;
import com.aisino.framework.security.entity.Org;
import com.aisino.framework.security.entity.Role;
import com.aisino.framework.security.entity.User;
import com.aisino.framework.security.service.RoleManager;
import com.aisino.framework.security.service.UserManager;

/**
 * 用户管理Controller
 * @author yuqs
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/security/user")
public class UserController {
	//注入用户管理对象
	@Autowired
	private UserManager userManager;
	//注入角色管理对象
	@Autowired
	private RoleManager roleManager;
	
	/**
	 * 分页查询用户，返回用户列表视图
	 * @param model
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, Page<User> page, HttpServletRequest request) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = userManager.findPage(page, filters);
		model.addAttribute("page", page);
		return "security/userList";
	}
	
	/**
	 * 新建用户时，返回用户编辑视图
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleManager.getAll());
		return "security/userEdit";
	}

	/**
	 * 编辑用户时，返回用户编辑视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		User entity = userManager.get(id);
		List<Role> roles = roleManager.getAll();
		List<Role> roless = entity.getRoles();
		for(Role role : roles) {
			for(Role selRole : roless) {
				if(role.getId().longValue() == selRole.getId().longValue())
				{
					role.setSelected(1);
				}
				if(role.getSelected() == null)
				{
					role.setSelected(0);
				}
			}
		}
		model.addAttribute("user", entity);
		model.addAttribute("roles", roles);
		return "security/userEdit";
	}
	
	/**
	 * 编辑用户时，返回用户编辑视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "userupdate/{id}", method = RequestMethod.GET)
	public String userupdate(@PathVariable("id") Long id, Model model) {
		User entity = userManager.get(id);
		List<Role> roles = roleManager.getAll();
		List<Role> roless = entity.getRoles();
		for(Role role : roles) {
			for(Role selRole : roless) {
				if(role.getId().longValue() == selRole.getId().longValue())
				{
					role.setSelected(1);
				}
				if(role.getSelected() == null)
				{
					role.setSelected(0);
				}
			}
		}
		model.addAttribute("user", entity);
		model.addAttribute("roles", roles);
		model.addAttribute("tsbz", "1");	//特殊标志，普通用户与管理保存后返回的界面不同
		return "security/userSetting";
	}
	
	/**
	 * 编辑用户时，返回用户查看视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", userManager.get(id));
		return "security/userView";
	}
	
	@RequestMapping(value = "setting", method = RequestMethod.POST)
	public String setting(User user) {
		User pUser = userManager.get(user.getId());
		pUser.setFgld(user.getFgld());
		pUser.setFgldhm(user.getFgldhm());
		pUser.setDwlx(user.getDwlx());
		pUser.setSex(user.getSex());
		pUser.setEmail(user.getEmail());
		pUser.setLxdh(user.getLxdh());
		pUser.setQqhm(user.getQqhm());
		pUser.setPassword(user.getPassword());
		userManager.save(pUser);
		return "redirect:/dwcl";
	}
	
	/**
	 * 新增、编辑用户页面的提交处理。保存用户实体，并返回用户列表视图
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(User user, Long[] orderIndexs, Long parentOrgId, String tsbz) {
		if(orderIndexs != null) {
			for(Long order : orderIndexs) {
				Role role = new Role();
				role.setId(order);
				user.getRoles().add(role);
			}
		}
		if(parentOrgId != null && parentOrgId.longValue() > 0) {
			Org parent = new Org(parentOrgId);
			user.setOrg(parent);
		}
		userManager.save(user);
		if(tsbz.equals("1")){
			return "redirect:/dwcl";
		}
		return "redirect:/security/user";
	}
	
	/**
	 * 根据主键ID删除用户实体，并返回用户列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		userManager.delete(id);
		return "redirect:/security/user";
	}
	
	/**
	 * 根据主键ID删除用户实体，并返回用户列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "orguser/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object orguser(@PathVariable("id") Long id) {
		List<User> users = userManager.getUserByOrg(id);
		List<Lsdw> lsdws = new ArrayList<Lsdw>();
		for(User u : users){
			Lsdw l = new Lsdw();
			l.setDwmc(u.getDwmc());
			l.setId(u.getId());
			lsdws.add(l);
		}
		return lsdws;
	}
}
