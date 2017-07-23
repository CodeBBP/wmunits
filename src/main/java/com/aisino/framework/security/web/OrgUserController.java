package com.aisino.framework.security.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aisino.framework.orm.Page;
import com.aisino.framework.security.entity.Org;
import com.aisino.framework.security.entity.Role;
import com.aisino.framework.security.entity.User;
import com.aisino.framework.security.service.OrgManager;
import com.aisino.framework.security.service.RoleManager;
import com.aisino.framework.security.service.UserManager;
import com.aisino.wmdw.lhkh.entity.Tree;

/**
 * 部门管理Controller
 * @author yuqs
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/security/orgtree")
public class OrgUserController {
	//注入部门管理对象
	@Autowired
	private OrgManager orgManager;
	@Autowired
	private UserManager userManager;
	//注入角色管理对象
	@Autowired
	private RoleManager roleManager;
	private List<Tree> trees;
	private List<Org> orgs;
	
	/**
	 * 机构树
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String orgTree(Model model) {
		List<Org> orgs = orgManager.getAll();
		for(Org org : orgs){
			if(org.getParentOrg() == null){
				model.addAttribute("orgid", org.getId());
				break;
			}
		}
		return "security/orgTree";
	}
	
	/**
	 * 机构树表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "orglist", method = RequestMethod.GET)
	public String orgTreeList(Model model) {
		return "security/orgTreeList";
	}
	
	/**
	 * 构建机构树
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "data")
	@ResponseBody
	public Object orgTreeData(Model model) {
		orgs = orgManager.getAll();
		trees = new ArrayList<Tree>();
		for (Org org : orgs) {
			buildOrgTag(trees, org);
		}
		return trees;
	}

	private void buildOrgTag(List<Tree> trees, Org org) {
		if (org != null) {
			Tree tree = new Tree();
			tree.setId(org.getId());
			tree.setpId(org.getParentOrg() == null ? -1 : org.getParentOrg().getId());
			tree.setName(org.getZzjgmc());
			if(org.getParentOrg() == null){
				tree.setUrl("userlist/" + org.getId());
			}else{
				tree.setUrl("userlist/" + org.getId());
			}
			tree.setTarget("userFrame");
			tree.setOpen(true);
//			if (org.getOrgs() != null && org.getOrgs().size() > 0) {
//				List<Tree> subTrees = new ArrayList<Tree>();
//				for (Org o : org.getOrgs()) {
//					buildOrgTag(subTrees, o);
//				}
//			}
			trees.add(tree);
		}
	}
	

	
	/**
	 * 机构人员管理
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "userlist/{id}")
	public String userList(@PathVariable("id") Long id, Model model, Page<User> page) {
		List<User> users = userManager.getUserByOrg(id);
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page.setTotalCount(users.size());
		page.setResult(users);
		model.addAttribute("roles", roleManager.getAll());
		model.addAttribute("page", page);
		model.addAttribute("orgid", id);
		model.addAttribute("orgname", orgManager.get(id).getZzjgmc());
		return "security/orguserList";
	}
	
	/**
	 * 机构人员管理
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "userlist")
	public String userList(Model model, Page<User> page) {
		List<User> users = userManager.getAll();
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page.setTotalCount(users.size());
		page.setResult(users);
		model.addAttribute("page", page);
		return "security/orguserList";
	}
	
	/**
	 * 新建用户时，返回用户编辑视图
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create/{orgid}", method = RequestMethod.GET)
	public String create(@PathVariable("orgid") Long id, Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("orgname", orgManager.get(id).getZzjgmc());
		model.addAttribute("orgid", id);
		model.addAttribute("roles", roleManager.getAll());
		return "security/orguserEdit";
	}
	
	/**
	 * 机构人员编辑
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "useredit/{id}")
	public String userEdit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", userManager.get(id));
		return "security/orguserEdit";
	}
	
	/**
	 * 机构人员更新
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "userupdate/{id}")
	public String userUpdate(@PathVariable("id") Long id, Model model) {
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
		model.addAttribute("entity", entity);
		model.addAttribute("roles", roles);
		return "security/orguserEdit";
	}
	
	/**
	 * 新增、编辑用户页面的提交处理。保存用户实体，并返回用户列表视图
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "userupdate", method = RequestMethod.POST)
	public String update(User user, Long[] orderIndexs, Long parentOrgId,String parentOrgName) {
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
		if(user.getZf() == null || user.getZf().equals("")){
			user.setZf("0.0");
		}
		userManager.save(user);
		return "redirect:/security/orgtree/userlist/"+parentOrgId;
	}
	
	/**
	 * 机构人员删除
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "userdelete/{id}")
	public String userDelete(@PathVariable("id") Long id) {
		Long orgId = userManager.get(id).getOrg().getId();
		userManager.delete(id);
		return "redirect:/security/orgtree/userlist/"+orgId;
	}
	
	/**
	 * 根据主键ID查询机构用户列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "userview/{id}")
	public String userView(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", userManager.get(id));
		return "security/orguserView";
	}
}
