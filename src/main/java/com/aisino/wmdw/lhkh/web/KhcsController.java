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
import com.aisino.wmdw.gjgl.entity.Dwcl;
import com.aisino.wmdw.gjgl.service.DwclManager;
import com.aisino.wmdw.lhkh.entity.Khcs;
import com.aisino.wmdw.lhkh.entity.Tree;
import com.aisino.wmdw.lhkh.service.KhcsManager;

/**
 * 配置参数管理Controller
 * @author xuzhe
 */
@Controller
@RequestMapping(value = "/khcs")
public class KhcsController {
	//注入配置参数管理对象
	@Autowired
	private KhcsManager khcsManager;
	@Autowired
	private DwclManager dwclManager;
	private List<Khcs> khcss;
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
	public String list(Model model, Page<Khcs> page, HttpServletRequest request) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = khcsManager.findPage(page, filters);
		model.addAttribute("page", page);
		model.addAttribute("lookup", request.getParameter("lookup"));
		return "lhkh/khcsList";
	}
	
	/**
	 * 机构树
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String khcsTree(Model model) {
		return "lhkh/khcsTree";
	}
	
	/**
	 * 考核参数树表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "treelist", method = RequestMethod.GET)
	public String khcsTreeList(Model model) {
		return "lhkh/khcsTreeList";
	}
	
	/**
	 * 考核参数树表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "dwcltreelist", method = RequestMethod.GET)
	public String dwcltreelist(Model model) {
		return "dwcl/khcsTreeList";
	}
	
	/**
	 * 考核参数树表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "khcstree", method = RequestMethod.GET)
	public String khcstree(Model model) {
		return "dwcl/jfbztree";
	}
	
	/**
	 * 构建考核菜单
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "khcsmenu")
	@ResponseBody
	public Object khcsmenu() {
		StringBuffer buffer = new StringBuffer();
		List<Khcs> khcss = khcsManager.getLeftAll();
		buffer.append("<div class='dtree' style='width:200px;overflow:scroll'><ul><script>d = new dTree('d');d.add(0,-1,'document tree');");
		if(khcss != null && khcss.size()>0){
			for(Khcs k : khcss){
				if(k.getPkhcs() == null){
					buffer.append("d.add("+k.getId()+",0,'"+k.getKhxm()+"','khcs/khcsitem/" + k.getId()+"','','mainFrame');");
				}else{
					buffer.append("d.add("+k.getId()+","+k.getPkhcs().getId()+",'"+k.getKhxm()+"','khcs/khcsitem/" + k.getId()+"','','mainFrame');");
				}
			}
		}
		buffer.append("document.write(d);</script></ul></div>");
		return buffer;
	}
	
	/**
	 * 构建机构树
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "treedata")
	@ResponseBody
	public Object khcsTreeData() {
		khcss = khcsManager.getAll();
		trees = new ArrayList<Tree>();
		for (Khcs khcs : khcss) {
			buildOrgTag(trees, khcs);
		}
		return trees;
	}
	
	/**
	 * 构建机构树
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "dwcltreedata")
	@ResponseBody
	public Object dwcltreedata() {
		khcss = khcsManager.getLeftAll();
		trees = new ArrayList<Tree>();
		for (Khcs khcs : khcss) {
			buildOrgTag(trees, khcs);
		}
		return trees;
	}
	
	@RequestMapping(value = "leftdata")
	@ResponseBody
	public Object khcsLeftMenu() {
		khcss = khcsManager.getLeftAll();
		trees = new ArrayList<Tree>();
		for (Khcs khcs : khcss) {
			if (khcs != null) {
				Tree tree = new Tree();
				tree.setId(khcs.getId());
				tree.setpId(khcs.getPkhcs() == null ? -1 : khcs.getPkhcs().getId());
				tree.setName(khcs.getKhxm());
				tree.setUrl("khcsitem/" + khcs.getId());
				tree.setTarget("khcsFrame");
				tree.setOpen(false);
				trees.add(tree);
			}
		}
		return trees;
	}
	
	private void buildOrgTag(List<Tree> trees, Khcs khcs) {
		if (khcs != null) {
			Tree tree = new Tree();
			tree.setId(khcs.getId());
			tree.setpId(khcs.getPkhcs() == null ? -1 : khcs.getPkhcs().getId());
			tree.setName(khcs.getKhxm());
			if(khcs.getPkhcs() == null){
				tree.setUrl("khcslist/" + khcs.getId());
			}else{
				tree.setUrl("khcslist/" + khcs.getId());
			}
			tree.setTarget("khcsFrame");
			tree.setOpen(false);
			trees.add(tree);
		}
	}
	
	@RequestMapping(value = "khcsitem/{id}")
	public String khcsitem(@PathVariable("id") Long id, Page<Dwcl> page, Model model) {
		//List<Khcs> khcss = khcsManager.getKhcsByPkhcs(id);
		User user = ShiroUtils.getUser();
		page = dwclManager.getDwclByUserAndKhcsId(page, user.getId(),id);
		model.addAttribute("khcsid", id);
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		model.addAttribute("page", page);
		return "dwcl/dwclkhcsList";
	}
	
	/**
	 * 考核参数管理
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "khcslist/{id}")
	public String khcsList(@PathVariable("id") Long id, Model model, Page<Khcs> page, HttpServletRequest request) {
		if(id != null){
			List<Khcs> khcss = khcsManager.getKhcsByPkhcs(id);	//数量少不用分组
			//设置默认排序方式
			if (!page.isOrderBySetted()) {
				page.setOrderBy("id");
				page.setOrder(Page.ASC);
			}
			page.setTotalCount(khcss.size());
			page.setResult(khcss);
			model.addAttribute("page", page);
			model.addAttribute("pid", id);
			model.addAttribute("tsbz", tsbz);
			tsbz = "";
		}
		return "lhkh/khcsList";
	}
	
	/**
	 * 考核参数管理
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "khcslist")
	public String khcsList(Model model, Page<Khcs> page, HttpServletRequest request) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		//List<Khcs> khcss = khcsManager.getAll();
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		//page.setTotalCount(khcss.size());
		//page.setResult(khcss.subList(0, 10));
		page = khcsManager.findPage(page, filters);
		model.addAttribute("page", page);
		model.addAttribute("pid", "0");
		model.addAttribute("tsbz", tsbz);	//刷新左边树结构
		tsbz = "";
		return "lhkh/khcsList";
	}
	
	/**
	 * 新建配置参数时，返回配置参数编辑视图
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create/{pid}", method = RequestMethod.GET)
	public String create(@PathVariable("pid") Long id, Model model) {
		if(id != null && id >0){
			model.addAttribute("pkhcs", khcsManager.get(id).getKhxm());
			model.addAttribute("pid", id);
		}
		model.addAttribute("khcs", new Khcs());
		return "lhkh/khcsEdit";
	}

	/**
	 * 编辑配置参数时，返回配置参数编辑视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		Khcs entity = khcsManager.get(id);
		model.addAttribute("khcs", entity);
		return "lhkh/khcsEdit";
	}
	
	/**
	 * 编辑配置参数时，返回配置参数查看视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Khcs entity = khcsManager.get(id);
		model.addAttribute("khcs", entity);
		return "lhkh/khcsView";
	}
	
	/**
	 * 新增、编辑配置参数页面的提交处理。保存配置参数实体，并返回配置参数列表视图
	 * @param Authority
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Khcs khcs, Long parentKhcsId, HttpServletRequest request) {
		if(parentKhcsId != null && parentKhcsId > 0){
			khcs.setPkhcs(khcsManager.get(parentKhcsId));
		}
		khcsManager.save(khcs);
		tsbz = "1";   //特殊标志（刷新左边树形结构）
		if(parentKhcsId != null && parentKhcsId > 0){
			return "redirect:/khcs/khcslist/"+parentKhcsId;
		}
		return "redirect:/khcs/khcslist";
	}
	
	/**
	 * 根据主键ID删除配置参数实体，并返回配置参数列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}/{pid}")
	public String delete(@PathVariable("id") Long id, @PathVariable("pid") Long parentKhcsId) {
		khcsManager.delete(id);
		tsbz = "1";
		return "redirect:/khcs/khcslist/"+parentKhcsId;
	}
	
	/**
	 * 根据主键ID删除配置参数实体，并返回配置参数列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		khcsManager.delete(id);
		tsbz = "1";
		return "redirect:/khcs/khcslist/";
	}
	
	/**
	 * 构建计分标准树
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "jfbztree")
	@ResponseBody
	public Object jfbzTreeData(Model model) {
		khcss = khcsManager.getAll();
		trees = new ArrayList<Tree>();
		for (Khcs khcs : khcss) {
			buildJfbzTag(trees, khcs);
		}
		return trees;
	}

	private void buildJfbzTag(List<Tree> trees, Khcs khcs) {
		if (khcs != null) {
			Tree tree = new Tree();
			tree.setId(khcs.getId());
			
			tree.setName(khcs.getKhxm());			
			tree.setUrl("");//回调
			tree.setTarget(khcs.getXxnr());
			tree.setFs(khcs.getScore());
			tree.setOpen(false);
			if (khcs.getPkhcs() != null) {
				tree.setpId(khcs.getPkhcs().getId());
				//buildJfbzTag(trees, khcs.getPkhcs());
			}
			if(khcs.getKhcss().size() == 0){
				tree.setPname("1");
			}
			trees.add(tree);
		}
	}
	
}
