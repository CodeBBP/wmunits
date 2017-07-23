package com.aisino.app.web.taglibs.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.aisino.framework.security.entity.Menu;
import com.aisino.framework.security.service.MenuManager;
import com.aisino.framework.security.shiro.ShiroUtils;
import com.aisino.framework.web.TagBuilder;
import com.aisino.framework.web.TagDTO;
import com.aisino.wmdw.lhkh.entity.Khcs;
import com.aisino.wmdw.lhkh.service.KhcsManager;

/**
 * 自定义菜单标签处理类。
 * 根据当前认证实体获取允许访问的所有菜单，并输出特定导航菜单的html
 * @author yuqs
 */
@Component
public class KhcsMenuTagBuilder implements TagBuilder {
	
	@Autowired
	private KhcsManager khcsManager;
	//Spring的上下文
	private WebApplicationContext springContext;
	//Servlet的上下文
	private ServletContext servletContext = null;
	@Override
	public String build(TagDTO dto) {
		this.servletContext = dto.getServletContext();
		this.springContext = dto.getSpringContext();
		StringBuffer buffer = new StringBuffer();
		//获取所有可允许访问的菜单列表
		List<Khcs> ks = khcsManager.getLeftAll();
		//循环迭代菜单列表，构成ID、List结构的Map
		Map<Long, List<Khcs>> menuMaps = buildMenuTreeMap(ks);
		//根据Map构造符合左栏菜单显示的html
		buildMenuTreeFolder(buffer, menuMaps, Menu.ROOT_MENU);
		return buffer.toString();
	}
	
	/**
	 * 循环迭代菜单列表，构成ID、List结构的Map
	 * @param menus
	 * @return
	 */
	private Map<Long, List<Khcs>> buildMenuTreeMap(List<Khcs> menus) {
		Map<Long, List<Khcs>> menuMap = new TreeMap<Long, List<Khcs>>();
		for (Khcs menu : menus) {
			/**
			 * 判断是否有上一级菜单，如果有，则添加到上一级菜单的Map中去 如果没有上一级菜单，把该菜单作为根节点
			 */
			Long parentMenuId = menu.getPkhcs() == null ? Menu.ROOT_MENU
					: menu.getPkhcs().getId();
			if (!menuMap.containsKey(parentMenuId)) {
				List<Khcs> subMenus = new ArrayList<Khcs>();
				subMenus.add(menu);
				menuMap.put(parentMenuId, subMenus);
			} else {
				List<Khcs> subMenus = menuMap.get(parentMenuId);
				subMenus.add(menu);
				menuMap.put(parentMenuId, subMenus);
			}
		}
		return menuMap;
	}
	
	/**
	 * 获取当前登录账号所有允许访问的菜单列表
	 * @return
	 */
	private List<Menu> getAllowedAccessMenu() {
		MenuManager menuManager = springContext.getBean(MenuManager.class);
		return menuManager.getAllowedAccessMenu(ShiroUtils.getUserId());
	}
	
	/**
	 * 构建菜单目录
	 * @param buffer html信息
	 * @param menuMap
	 * @param menuId
	 */
	private void buildMenuTreeFolder(StringBuffer buffer, Map<Long, List<Khcs>> menuMap, Long menuId)
	{
		List<Khcs> treeFolders = menuMap.get(menuId);
		if(treeFolders == null)
		{
			return;
		}
		for(Khcs menu : treeFolders)
		{
			buffer.append("<div class='node'>");
			buffer.append("<a id='node_" + menu.getId() + "' class='nodeLink' href='#' onclick=\"switchLeaf('" + menu.getId() + "')\">" + menu.getKhxm() + "</a>");
			buffer.append("<div class='leaf' id='leaf_" + menu.getId() + "' style='display: none;'>");
			List<Khcs> treeNodes = menuMap.get(menu.getId());
			/**
			 * 有子菜单时，将子菜单添加到当前节点上
			 */
			buildMenuTreeNode(buffer, treeNodes);
			buffer.append("</div>");
			buffer.append("</div>");
		}
	}
	
	/**
	 * 循环子菜单资源，并构造tree型html语句
	 * @param buffer
	 * @param treeNodes
	 */
	private void buildMenuTreeNode(StringBuffer buffer, List<Khcs> treeNodes)
	{
		if(treeNodes == null)
		{
			return;
		}
		for(Khcs menu : treeNodes)
		{
			buffer.append("<a class='leafLink' href='");
				buffer.append(servletContext.getContextPath());
				buffer.append("/khcs/khcsitem/"+menu.getId());
			buffer.append("' target='mainFrame' >");
			buffer.append(menu.getKhxm());
			buffer.append("</a>");
		}
	}
}
