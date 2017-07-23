package com.aisino.wmdw.gjgl.web;

import java.util.ArrayList;
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
import com.aisino.framework.security.entity.User;
import com.aisino.framework.security.service.UserManager;
import com.aisino.framework.security.shiro.ShiroUtils;
import com.aisino.wmdw.gjgl.entity.Dwgg;
import com.aisino.wmdw.gjgl.entity.Tzgg;
import com.aisino.wmdw.gjgl.service.DwggManager;
import com.aisino.wmdw.gjgl.service.TzggManager;

/**
 * 配置变更管理Controller
 * @author xuzhe
 */
@Controller
@RequestMapping(value = "/tzgg")
public class TzggController {
	//注入配置变更管理对象
	@Autowired
	private TzggManager tzggManager;
	@Autowired
	private DwggManager dwggManager;
	@Autowired
	private UserManager userManager;
	
	/**
	 * 分页查询配置，返回配置稿件列表视图
	 * @param model
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, Page<Tzgg> page, HttpServletRequest request) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = tzggManager.findPage(page, filters);
		model.addAttribute("page", page);
		return "tzgg/tzggList";
	}
	
	/**
	 * 新建配置稿件时，返回配置稿件编辑视图
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		User user = ShiroUtils.getUser();
		model.addAttribute("fbdw", user);
		model.addAttribute("tzgg", new Tzgg());
		return "tzgg/tzggEdit";
	}

	/**
	 * 编辑配置稿件时，返回配置稿件编辑视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		Tzgg entity = tzggManager.get(id);
		model.addAttribute("tzgg", entity);
		return "tzgg/tzggEdit";
	}
	
	/**
	 * 编辑配置稿件时，返回配置稿件查看视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		User user = ShiroUtils.getUser();
		Dwgg dwgg = dwggManager.getDwggByJsdwIdAndTzggId(user.getId(),id);
		dwgg.setYdzt("1");	//查看过后标记状态为已读
		dwggManager.save(dwgg);
		model.addAttribute("tzgg", tzggManager.get(id));
		return "tzgg/tzggView";
	}
	
	/**
	 * 新增、编辑配置稿件页面的提交处理。保存配置稿件实体，并返回配置稿件列表视图
	 * @param Authority
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Tzgg tzgg, Long fbdwid, HttpServletRequest request) {
		User user = userManager.get(fbdwid);
		List<User> users = userManager.getAll();
		tzgg.setFbdw(user);
		tzggManager.save(tzgg, users);
		return "redirect:/tzgg";
	}
	
	/**
	 * 根据主键ID删除配置稿件实体，并返回配置稿件列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		tzggManager.delete(id);
		return "redirect:/tzgg";
	}
	
	/**
	 * 根据主键ID删除配置稿件实体，并返回配置稿件列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "dwggdelete/{id}")
	public String dwggdelete(@PathVariable("id") Long id) {
		dwggManager.delete(id);
		return "redirect:/tzgg/dwgg";
	}
	
	/**
	 * 编辑配置稿件时，返回配置稿件编辑视图
	 * @return
	 */
	@RequestMapping(value = "dwgg", method = RequestMethod.GET)
	public String dwgg(Model model, HttpServletRequest request) {
		User user = ShiroUtils.getUser();
		List<Dwgg> dwggs = dwggManager.getDwggByJsdwId(user.getId());
		Tzgg entity = null;
		List<Tzgg> tzggs = new ArrayList<Tzgg>();
		for(Dwgg dwgg : dwggs){
			entity = tzggManager.get(dwgg.getTzggid());
			entity.setYdzt(dwgg.getYdzt());
			entity.setDwid(dwgg.getId());
			tzggs.add(entity);
		}
		model.addAttribute("tzggs", tzggs);
		return "tzgg/dwggList";
	}
	
	// 去除字符串右边的全0字符
	public String trimr0(String zzjgdm){
		while(zzjgdm.charAt(zzjgdm.length()-1) == '0'){
			zzjgdm = zzjgdm.substring(0,zzjgdm.length()-2);
		}
		return zzjgdm+"%";
	}
}
