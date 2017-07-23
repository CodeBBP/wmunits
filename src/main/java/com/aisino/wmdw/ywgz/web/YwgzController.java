package com.aisino.wmdw.ywgz.web;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.aisino.framework.security.shiro.ShiroUtils;
import com.aisino.wmdw.ywgz.entity.Ywgz;
import com.aisino.wmdw.ywgz.service.YwgzManager;

@Controller
@RequestMapping(value = "/ywgz")
public class YwgzController {
	@Autowired
	private YwgzManager manager;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model, Page<Ywgz> page, String sblx, HttpServletRequest request) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		filters.add(new PropertyFilter("EQS_sblx", sblx));
		page = manager.findPage(page, sblx, ShiroUtils.getUserId());
		model.addAttribute("page", page);
		model.addAttribute("sblx", sblx);
		return "ywgz/ywgzList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model, String sblx) {
		model.addAttribute("ywgz", new Ywgz());
		model.addAttribute("sblx", sblx);
		model.addAttribute("sbdw", ShiroUtils.getUser());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.addAttribute("cjsj",sf.format(new Date()));
		return "ywgz/ywgzEdit";
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, String sblx, Model model) {
		Ywgz ywgz = manager.get(id);
		model.addAttribute("ywgz", ywgz);
		model.addAttribute("sblx", sblx);
		return "ywgz/ywgzEdit";
	}
	
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Ywgz ywgz = manager.get(id);
		model.addAttribute("ywgz", ywgz);
		return "ywgz/ywgzView";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Model model, String sblx, Ywgz ywgz) {
		ywgz.setSbdw(ShiroUtils.getUser());
		ywgz.setSblx(sblx);
		manager.save(ywgz);
		model.addAttribute("sblx", sblx);
		return "redirect:/ywgz/list";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, String sblx, Model model) {
		manager.delete(id);
		model.addAttribute("sblx", sblx);
		return "redirect:/ywgz/list";
	}
}
