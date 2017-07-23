package com.aisino.wmdw.sxjs.web;

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
import com.aisino.wmdw.lhkh.entity.Tree;
import com.aisino.wmdw.sxjs.entity.Khpg;
import com.aisino.wmdw.sxjs.entity.Pgmx;
import com.aisino.wmdw.sxjs.service.KhpgManager;
import com.aisino.wmdw.sxjs.service.PgmxManager;

/**
 * 配置参数管理Controller
 * @author xuzhe
 */
@Controller
@RequestMapping(value = "/khpg")
public class KhpgController {
	//注入配置参数管理对象
	@Autowired
	private KhpgManager khpgManager;
	@Autowired
	private PgmxManager pgmxManager;
	private List<Khpg> khpgs;
	private List<Tree> trees;
	private List<Pgmx> pgmxs;
	
	/**
	 * 分页查询配置，返回配置参数列表视图
	 * @param model
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, Page<Khpg> page, HttpServletRequest request) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = khpgManager.findPage(page, filters);
		model.addAttribute("page", page);
		return "sxjs/khpgList";
	}
	
	/**
	 * 新建配置参数时，返回配置参数编辑视图
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("khpg", new Khpg());
		return "sxjs/khpgEdit";
	}

	/**
	 * 编辑配置参数时，返回配置参数编辑视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		Khpg entity = khpgManager.get(id);
		model.addAttribute("khpg", entity);
		model.addAttribute("pgmxs", entity.getPgmxs());
		return "sxjs/khpgEdit";
	}
	
	/**
	 * 编辑配置参数时，返回配置参数查看视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Khpg entity = khpgManager.get(id);
		model.addAttribute("khpg", entity);
		model.addAttribute("pgmxs", entity.getPgmxs());
		return "sxjs/khpgView";
	}
	
	/**
	 * 新增、编辑配置参数页面的提交处理。保存配置参数实体，并返回配置参数列表视图
	 * @param Authority
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Khpg khpg, String[] pgnrs, String[] khfss, String[] shyds, String[] bzfzs) {
		Pgmx pgmx = null;
		List<Pgmx> pgmxs = new ArrayList<Pgmx>();
		for(int i = 0; i<pgnrs.length; i++){
			pgmx = new Pgmx();
			pgmx.setKhpg(khpg);
			pgmx.setPgnr(pgnrs[i]);
			pgmx.setKhfs(khfss[i]);
			pgmx.setShyd(shyds[i]);
			pgmx.setBzfz(bzfzs[i]);
			pgmxs.add(pgmx);
			}
		khpgManager.save(khpg, pgmxs);
		return "redirect:/khpg";
	}
	
	/**
	 * 根据主键ID删除配置参数实体，并返回配置参数列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		khpgManager.delete(id);
		return "redirect:/khpg";
	}
	
	/**
	 * 构建计分标准树
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "pgbztree")
	@ResponseBody
	public Object jfbzTreeData(Model model) {
		khpgs = khpgManager.getAll();
		trees = new ArrayList<Tree>();
		for (Khpg khpg : khpgs) {
			buildJfbzTag(trees, khpg);
		}
		return trees;
	}

	private void buildJfbzTag(List<Tree> trees, Khpg khpg) {
		if (khpg != null) {
			Tree tree = new Tree();
			tree.setId(khpg.getId());
			tree.setpId(Long.valueOf("-1"));
			tree.setName(khpg.getPara());			
			tree.setUrl("");//回调
			tree.setTarget("");
			tree.setOpen(false);
			if (khpg.getPgmxs() != null && khpg.getPgmxs().size() > 0) {
				//List<Tree> subTrees = new ArrayList<Tree>();
				for (Pgmx k : khpg.getPgmxs()) {
					buildJfbzTag(trees, k);
				}
			}
			trees.add(tree);
		}
	}
	
	private void buildJfbzTag(List<Tree> trees, Pgmx pgmx) {
		if (pgmx != null) {
			Tree tree = new Tree();
			tree.setId(pgmx.getId());
			tree.setpId(pgmx.getKhpg().getId());
			tree.setName(pgmx.getPgnr());
			tree.setUrl("");//回调
			tree.setTarget("");
			tree.setPname(pgmx.getKhpg().getPara());
			tree.setFs(pgmx.getBzfz());
			tree.setOpen(false);
			trees.add(tree);
		}
	}
}
