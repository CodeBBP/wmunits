package com.aisino.wmdw.lhkh.web;

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
import com.aisino.framework.security.entity.User;
import com.aisino.framework.security.shiro.ShiroUtils;
import com.aisino.wmdw.lhkh.entity.Khgz;
import com.aisino.wmdw.lhkh.entity.Tree;
import com.aisino.wmdw.lhkh.service.KhgzManager;
import com.aisino.wmdw.sxsb.entity.Cxhj;
import com.aisino.wmdw.sxsb.service.CxhjManager;

/**
 * 配置参数管理Controller
 * @author xuzhe
 */
@Controller
@RequestMapping(value = "/khgz")
public class KhgzController {
	//注入配置参数管理对象
	@Autowired
	private KhgzManager khgzManager;
	@Autowired
	private CxhjManager cxhjManager;
	private List<Khgz> khgzs;
	private List<Tree> trees;
	String tsbz = ""; 	//特殊标志（刷新左边树形结构）
	
	/**
	 * 分页查询配置，返回配置参数列表视图
	 * @param model
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, Page<Khgz> page, HttpServletRequest request) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = khgzManager.findPage(page, filters);
		model.addAttribute("page", page);
		model.addAttribute("lookup", request.getParameter("lookup"));
		return "lhkh/khgzList";
	}
	
	/**
	 * 机构树
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String khgzTree(Model model) {
		return "cxhj/khgzTree";
	}
	
	/**
	 * 考核参数树表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "treelist", method = RequestMethod.GET)
	public String khgzTreeList(Model model) {
		return "lhkh/khgzTreeList";
	}
	
	/**
	 * 考核参数树表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "cxhjtreelist", method = RequestMethod.GET)
	public String cxhjtreelist(Model model) {
		return "cxhj/khgzTreeList";
	}
	
	/**
	 * 考核参数树表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "khgztree", method = RequestMethod.GET)
	public String khgztree(Model model) {
		return "dwcl/jfbztree";
	}
	
	/**
	 * 构建机构树
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "treedata")
	@ResponseBody
	public Object khgzTreeData() {
		khgzs = khgzManager.getAll();
		trees = new ArrayList<Tree>();
		for (Khgz khgz : khgzs) {
			buildOrgTag(trees, khgz);
		}
		return trees;
	}
	
	@RequestMapping(value = "leftdata")
	@ResponseBody
	public Object khgzLeftMenu() {
		khgzs = khgzManager.getAll();
		trees = new ArrayList<Tree>();
		for (Khgz khgz : khgzs) {
			if (khgz != null) {
				Tree tree = new Tree();
				tree.setId(khgz.getId());
				tree.setpId(khgz.getPkhgz() == null ? -1 : khgz.getPkhgz().getId());
				tree.setName(khgz.getKhxm());
				tree.setUrl("khgzitem/" + khgz.getId());
				tree.setTarget("khgzFrame");
				tree.setOpen(false);
				trees.add(tree);
			}
		}
		return trees;
	}
	
	private void buildOrgTag(List<Tree> trees, Khgz khgz) {
		if (khgz != null) {
			Tree tree = new Tree();
			tree.setId(khgz.getId());
			tree.setpId(khgz.getPkhgz() == null ? -1 : khgz.getPkhgz().getId());
			tree.setName(khgz.getKhxm());
			if(khgz.getPkhgz() == null){
				tree.setUrl("khgzlist/" + khgz.getId());
			}else{
				tree.setUrl("khgzlist/" + khgz.getId());
			}
			tree.setTarget("khgzFrame");
			tree.setOpen(false);
			trees.add(tree);
		}
	}
	
	@RequestMapping(value = "khgzitem/{id}")
	public String khgzitem(@PathVariable("id") Long id, Page<Cxhj> page, Model model) {
		//List<Khgz> khgzs = khgzManager.getKhgzByPkhgz(id);
		System.out.println("id====>" + id);
		User user = ShiroUtils.getUser();
		page = cxhjManager.getCxhjByUserAndKhgzId(page, user.getId(), id);
		model.addAttribute("khgzid", id);
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		model.addAttribute("page", page);
		return "cxhj/cxhjkhgzList";
	}
	
	/**
	 * 考核参数管理
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "khgzlist/{id}")
	public String khgzList(@PathVariable("id") Long id, Model model, Page<Khgz> page, HttpServletRequest request) {
		if(id != null){
			List<Khgz> khgzs = khgzManager.getKhgzByPkhgz(id);	//数量少不用分组
			//设置默认排序方式
			if (!page.isOrderBySetted()) {
				page.setOrderBy("id");
				page.setOrder(Page.ASC);
			}
			page.setTotalCount(khgzs.size());
			page.setResult(khgzs);
			model.addAttribute("page", page);
			model.addAttribute("pid", id);
			model.addAttribute("tsbz", tsbz);
			tsbz = "";
		}
		return "lhkh/khgzList";
	}
	
	/**
	 * 考核参数管理
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "khgzlist")
	public String khgzList(Model model, Page<Khgz> page, HttpServletRequest request) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		//List<Khgz> khgzs = khgzManager.getAll();
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		//page.setTotalCount(khgzs.size());
		//page.setResult(khgzs.subList(0, 10));
		page = khgzManager.findPage(page, filters);
		model.addAttribute("page", page);
		model.addAttribute("pid", "0");
		model.addAttribute("tsbz", tsbz);	//刷新左边树结构
		tsbz = "";
		return "lhkh/khgzList";
	}
	
	/**
	 * 新建配置参数时，返回配置参数编辑视图
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create/{pid}", method = RequestMethod.GET)
	public String create(@PathVariable("pid") Long id, Model model) {
		if(id != null && id >0){
			model.addAttribute("pkhgz", khgzManager.get(id).getKhxm());
			model.addAttribute("pid", id);
		}
		model.addAttribute("khgz", new Khgz());
		return "lhkh/khgzEdit";
	}

	/**
	 * 编辑配置参数时，返回配置参数编辑视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		Khgz entity = khgzManager.get(id);
		model.addAttribute("khgz", entity);
		return "lhkh/khgzEdit";
	}
	
	/**
	 * 编辑配置参数时，返回配置参数查看视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Khgz entity = khgzManager.get(id);
		model.addAttribute("khgz", entity);
		return "lhkh/khgzView";
	}
	
	/**
	 * 新增、编辑配置参数页面的提交处理。保存配置参数实体，并返回配置参数列表视图
	 * @param Authority
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Khgz khgz, Long parentKhgzId, HttpServletRequest request) {
		if(parentKhgzId != null && parentKhgzId > 0){
			khgz.setPkhgz(khgzManager.get(parentKhgzId));
		}
		khgzManager.save(khgz);
		tsbz = "1";   //特殊标志（刷新左边树形结构）
		if(parentKhgzId != null && parentKhgzId > 0){
			return "redirect:/khgz/khgzlist/"+parentKhgzId;
		}
		return "redirect:/khgz/khgzlist";
	}
	
	/**
	 * 根据主键ID删除配置参数实体，并返回配置参数列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}/{pid}")
	public String delete(@PathVariable("id") Long id, @PathVariable("pid") Long parentKhgzId) {
		khgzManager.delete(id);
		tsbz = "1";
		return "redirect:/khgz/khgzlist/"+parentKhgzId;
	}
	
	/**
	 * 根据主键ID删除配置参数实体，并返回配置参数列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		khgzManager.delete(id);
		tsbz = "1";
		return "redirect:/khgz/khgzlist/";
	}
	
	/**
	 * 构建计分标准树
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "jfbztree")
	@ResponseBody
	public Object jfbzTreeData(Model model) {
		khgzs = khgzManager.getAll();
		trees = new ArrayList<Tree>();
		for (Khgz khgz : khgzs) {
			buildJfbzTag(trees, khgz);
		}
		return trees;
	}

	private void buildJfbzTag(List<Tree> trees, Khgz khgz) {
		if (khgz != null) {
			Tree tree = new Tree();
			tree.setId(khgz.getId());
			
			tree.setName(khgz.getKhxm());			
			tree.setUrl("");//回调
			tree.setTarget(khgz.getXxnr());
			tree.setFs(khgz.getScore());
			tree.setOpen(false);
			if (khgz.getPkhgz() != null) {
				tree.setpId(khgz.getPkhgz().getId());
				//buildJfbzTag(trees, khgz.getPkhgz());
			}
			if(khgz.getKhgzs().size() == 0){
				tree.setPname("1");
			}
			trees.add(tree);
		}
	}
	
}
