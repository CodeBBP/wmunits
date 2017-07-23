package com.aisino.app.web.taglibs.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import com.aisino.framework.security.entity.Menu;
import com.aisino.framework.security.entity.User;
import com.aisino.framework.security.service.MenuManager;
import com.aisino.framework.security.shiro.ShiroUtils;
import com.aisino.framework.web.TagDTO;
import com.aisino.framework.web.TagBuilder;
import com.aisino.wmdw.lhkh.entity.Khcs;
import com.aisino.wmdw.lhkh.service.KhcsManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * 自定义菜单标签处理类。
 * 根据当前认证实体获取允许访问的所有菜单，并输出特定导航菜单的html
 * @author yuqs
 */
@Component
public class MenuTagBuilder implements TagBuilder {
	
	//注入配置参数管理对象
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
		List<Menu> menus = getAllowedAccessMenu();
		//循环迭代菜单列表，构成ID、List结构的Map
		Map<Long, List<Menu>> menuMaps = buildMenuTreeMap(menus);
		//根据Map构造符合左栏菜单显示的html
		buildMenuTreeFolder(buffer, menuMaps, Menu.ROOT_MENU);
		return buffer.toString();
	}
	
	/**
	 * 循环迭代菜单列表，构成ID、List结构的Map
	 * @param menus
	 * @return
	 */
	private Map<Long, List<Menu>> buildMenuTreeMap(List<Menu> menus) {
		Map<Long, List<Menu>> menuMap = new TreeMap<Long, List<Menu>>();
		for (Menu menu : menus) {
			/**
			 * 判断是否有上一级菜单，如果有，则添加到上一级菜单的Map中去 如果没有上一级菜单，把该菜单作为根节点
			 */
			Long parentMenuId = menu.getParentMenu() == null ? Menu.ROOT_MENU
					: menu.getParentMenu().getId();
			if (!menuMap.containsKey(parentMenuId)) {
				List<Menu> subMenus = new ArrayList<Menu>();
				subMenus.add(menu);
				menuMap.put(parentMenuId, subMenus);
			} else {
				List<Menu> subMenus = menuMap.get(parentMenuId);
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
	private void buildMenuTreeFolder(StringBuffer buffer, Map<Long, List<Menu>> menuMap, Long menuId)
	{
		User user = ShiroUtils.getUser();
		List<Menu> treeFolders = menuMap.get(menuId);
		if(treeFolders == null)
		{
			return;
		}
		buffer.append("<div id='idSlideView3' class='sv3'>");
		for(Menu menu : treeFolders)
		{
			
			if(menu.getName().equals("文明创建") && user.getEnabled().equals("1")){
				continue;
			}
			buffer.append("<dl>");
			buffer.append("<dt style='cursor:hand'>" + menu.getName() + "</dt> ");
			buffer.append("<dd>");
			//buffer.append("<div class='leaf' id='leaf_" + menu.getId() + "' style='display: none;'>");
			if(menu.getName().equals("文明创建")){
				List<Khcs> khcss = khcsManager.getLeftAll();
				buffer.append("<div class='dtree' style=''><ul><script>d = new dTree('d');d.add(0,-1,'document tree');");
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
			}else{
				List<Menu> treeNodes = menuMap.get(menu.getId());
				/**
				 * 有子菜单时，将子菜单添加到当前节点上
				 */
				buildMenuTreeNode(buffer, treeNodes);
			}
			buffer.append("</dd>");
			buffer.append("</dl>");
		}
		buffer.append("</div>");
	}
	
	/**
	 * 循环子菜单资源，并构造tree型html语句
	 * @param buffer
	 * @param treeNodes
	 */
	private void buildMenuTreeNode(StringBuffer buffer, List<Menu> treeNodes)
	{
		if(treeNodes == null)
		{
			return;
		}
		buffer.append("<ul>");
		for(Menu menu : treeNodes)
		{
			buffer.append("<li><a href='");
			if(StringUtils.isEmpty(menu.getDescription())) {
				buffer.append("javascript:void(0)");
			} else {
				buffer.append(servletContext.getContextPath());
				buffer.append(menu.getDescription());
			}

			buffer.append("' target='mainFrame' >");
			buffer.append(menu.getName());
			buffer.append("</a></li>");
		}
		buffer.append("</ul>");
	}
}
