package com.aisino.framework.security.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.PropertyFilter;
import com.aisino.framework.security.entity.Czrz;
import com.aisino.framework.security.entity.User;
import com.aisino.framework.security.service.CzrzManager;
import com.aisino.framework.security.service.UserManager;

/**
 * 操作日志管理Controller
 * @author yuqs
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/security/czrz")
public class CzrzController {
	//注入操作日志管理对象
	@Autowired
	private CzrzManager czrzManager;
	@Autowired
	private UserManager userManager;
	
	/**
	 * 分页查询操作日志，返回操作日志列表视图
	 * @param model
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, Page<Czrz> page, HttpServletRequest request) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		String username = (String)request.getSession().getAttribute("username");
		User user = null;
		if(Character.isDigit(username.charAt(0))){
			user = userManager.findUserByPhone(username);
		}else{
			user = userManager.findUserByUserName(username);
		}
		filters.add(new PropertyFilter("LIKES_czry", user.getDwmc()));
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = czrzManager.findPage(page, filters);
		model.addAttribute("page", page);
		return "security/czrzList";
	}
	
	/**
	 * 根据主键ID删除部门实体，并返回部门列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		czrzManager.delete(id);
		return "redirect:/security/czrz";
	}
}
