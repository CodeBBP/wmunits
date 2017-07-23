package com.aisino.wmdw.gjgl.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aisino.framework.orm.Page;
import com.aisino.framework.security.entity.Org;
import com.aisino.framework.security.entity.User;
import com.aisino.framework.security.service.OrgManager;
import com.aisino.framework.security.service.UserManager;
import com.aisino.framework.security.shiro.ShiroUtils;
import com.aisino.framework.utils.DateUtils;
import com.aisino.wmdw.gjgl.entity.Dwcl;
import com.aisino.wmdw.gjgl.entity.Dwtj;
import com.aisino.wmdw.gjgl.service.DwclManager;
import com.aisino.wmdw.lhkh.entity.Khcs;
import com.aisino.wmdw.lhkh.service.KhcsManager;

/**
 * 配置变更管理Controller
 * @author xuzhe
 */
@Controller
@RequestMapping(value = "/dwcl")
public class DwclController{
	//注入配置变更管理对象
	@Autowired
	private DwclManager dwclManager;
	@Autowired
	private KhcsManager khcsManager;
	@Autowired
	private UserManager userManager;
	@Autowired
	private OrgManager orgManager;
	
	/**
	 * 分页查询配置，返回配置稿件列表视图
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, HttpServletResponse response, Page<Dwcl> page,Long khcsid, String export, String title, String cjsjq, String cjsjz, String dwlx, String spzt) {
		User user = ShiroUtils.getUser();
		if(export != null && export.equals("1")){
			page.setPageNo(1);
			page.setPageSize(99999);
			page = dwclManager.getDwclByParameter(user, khcsid, null, null, title, cjsjq, cjsjz, dwlx, spzt, page);
			DwExport(page.getResult(), response);
			return null;
		}
		page = dwclManager.getDwclByParameter(user, khcsid, null, null, title, cjsjq, cjsjz, dwlx, spzt, page);
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		model.addAttribute("page", page);
		model.addAttribute("title", title);
		model.addAttribute("cjsjq", cjsjq);
		model.addAttribute("cjsjz", cjsjz);
		model.addAttribute("spzt", spzt);
		model.addAttribute("khcsid", khcsid);
		model.addAttribute("dwlx", dwlx);
		if(khcsid != null && khcsid > 0){
			return "dwcl/dwclkhcsList";
		}
		return "dwcl/dwclList";
	}
	
	/**
	 * 材料上报树
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String khcsTree(Model model) {
		return "dwcl/khcsTree";
	}
	
	/**
	 * 分页查询配置，返回综合查询视图
	 */
	@RequestMapping(value = "zhcx", method = RequestMethod.GET)
	public String zhcx(Model model, HttpServletResponse response, String export, Page<Dwcl> page,Long khcsid, Long sbdw, Long qxwmb, String title, String cjsjq, String cjsjz, String dwlx, String spzt) {
		Org org = ShiroUtils.getUser().getOrg();
		if(org.getId() != 221) {
			qxwmb = org.getId();
		}
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		if(export != null && export.equals("1")){
			page.setPageNo(1);
			page.setPageSize(99999);
			page = dwclManager.getDwclByParameter(null, khcsid, sbdw, qxwmb, title, cjsjq, cjsjz, dwlx, spzt, page);
			DwExport(page.getResult(), response);
			return null;
		}
		page = dwclManager.getDwclByParameter(null, khcsid, sbdw, qxwmb, title, cjsjq, cjsjz, dwlx, spzt, page);
		model.addAttribute("page", page);
		model.addAttribute("org", org);
		model.addAttribute("title", title);
		model.addAttribute("cjsjq", cjsjq);
		model.addAttribute("cjsjz", cjsjz);
		model.addAttribute("spzt", spzt);
		model.addAttribute("khcsid", khcsid);
		model.addAttribute("dwlx", dwlx);
		model.addAttribute("sbdw", sbdw);
		if(qxwmb != null && qxwmb>0){
			model.addAttribute("sbdws", userManager.getUserByOrg(qxwmb));
		}
		model.addAttribute("qxwmb", qxwmb);
		model.addAttribute("orgs", orgManager.getQxorg());
		return "dwcl/zhcxList";
	}
	
	/**
	 * 分页查询配置，返回积分排行视图
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "jfph", method = RequestMethod.GET)
	public String jfph(Model model, HttpServletResponse response, String export, Page<Dwtj> page, String pxzl, String pxlb, String ndsj, String jfsort) {
		//User user = ShiroUtils.getUser();
		if(pxlb != null){	//直接点击菜单不查询
		
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		Page<User> upage = new Page();
		Page<List> opage = new Page();
		List<Dwtj> dwtjs = new ArrayList<Dwtj>();
		List<User> users = null;
		List olist = null;
		if(pxlb != null && pxlb.equals("2")){
			if(export != null && export.equals("1")){
				upage.setPageNo(1);
				upage.setPageSize(99999);
				upage = userManager.getUserByPx(upage, ndsj, pxzl, jfsort);
				users = upage.getResult();
				for(User u : users){
					Dwtj dwtj = new Dwtj();
					dwtj.setSbdw(u.getDwmc());
					dwtj.setSbzs(dwclManager.getDwsbzs(u.getId(), ndsj, null));
					dwtj.setYgds(dwclManager.getDwsbzs(u.getId(), ndsj, "1"));
					dwtj.setZf(dwclManager.getDwzf(u.getId(), ndsj));
					dwtjs.add(dwtj);
				}
				JfphExport(dwtjs, response, pxlb);
				return null;
			}
			upage.setPageNo(page.getPageNo());
			upage = userManager.getUserByPx(upage, ndsj, pxzl, jfsort);
			page.setTotalCount(upage.getTotalCount());
			users = upage.getResult();
			for(User u : users){
				Dwtj dwtj = new Dwtj();
				dwtj.setSbdw(u.getDwmc());
				dwtj.setSbzs(dwclManager.getDwsbzs(u.getId(), ndsj, null));
				dwtj.setYgds(dwclManager.getDwsbzs(u.getId(), ndsj, "1"));
				dwtj.setZf(dwclManager.getDwzf(u.getId(), ndsj));
				dwtjs.add(dwtj);
			}
		}else{
			List fslist = userManager.getOrgByPx(opage, ndsj, "jf", jfsort).getResult();
			if(export != null && export.equals("1")){
				opage.setPageNo(1);
				opage.setPageSize(99999);
				opage = userManager.getOrgByPx(opage, ndsj, pxzl, jfsort);
				olist = opage.getResult();
				for(int i=0; i<olist.size(); i++){
					Object[] o = (Object[]) olist.get(i);
					Object[] fs = (Object[]) fslist.get(i);
					Dwtj dwtj = new Dwtj();
					Org org = orgManager.get(Long.valueOf(o[0].toString()));
					dwtj.setSbdw(org.getZzjgmc());
					dwtj.setSbzs(dwclManager.getWmbsbzs(org.getId(), ndsj, null));
					dwtj.setYgds(dwclManager.getWmbsbzs(org.getId(), ndsj, "1"));
					dwtj.setZf(fs[1].toString());
					dwtjs.add(dwtj);
				}
				JfphExport(dwtjs, response, pxlb);
				return null;
			}
			opage.setPageNo(page.getPageNo());
			opage = userManager.getOrgByPx(opage, ndsj, pxzl, jfsort);
			page.setTotalCount(opage.getTotalCount());
			olist = opage.getResult();
			for(int i=0; i<olist.size(); i++){
				Object[] o = (Object[]) olist.get(i);
				Object[] fs = (Object[]) fslist.get(i);
				Dwtj dwtj = new Dwtj();
				Org org = orgManager.get(Long.valueOf(o[0].toString()));
				dwtj.setSbdw(org.getZzjgmc());
				dwtj.setSbzs(dwclManager.getWmbsbzs(org.getId(), ndsj, null));
				dwtj.setYgds(dwclManager.getWmbsbzs(org.getId(), ndsj, "1"));
				dwtj.setZf(fs[1].toString());
				dwtjs.add(dwtj);
			}
		}
//		final String ssss = jfsort;
//		Collections.sort(dwtjs, new Comparator() {		//ArrayList排序
//	        public int compare(Object o1, Object o2) {
//	        	Dwtj d1 = (Dwtj) o1;
//	        	Dwtj d2 = (Dwtj) o2;
//	        	Float f1 = Float.valueOf(d1.getZf());
//	        	Float f2 = Float.valueOf(d2.getZf());
//	        	if(ssss != null && ssss.equals("desc")){
//	        		return f2.compareTo(f1);
//	        	}else{
//	        		return f1.compareTo(f2);
//	        	}
//	        }
//		});
		page.setResult(dwtjs);
//		if(qxwmb != null && qxwmb>0){
//			model.addAttribute("sbdws", userManager.getUserByOrg(qxwmb));
//		}
		model.addAttribute("page", page);
		model.addAttribute("pxlb", pxlb);
//		model.addAttribute("qxwmb", qxwmb);
		model.addAttribute("ndsj", ndsj);
//		model.addAttribute("sbdw", sbdw);
		model.addAttribute("jfsort", jfsort);
		model.addAttribute("orgs", orgManager.getQxorg());
		}
		return "dwcl/jfphList";
	}
	
	/**
	 * 分页查询配置，返回单位栏目视图
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "dwlm", method = RequestMethod.GET)
	public String dwlm(Model model, Page<Dwtj> page, HttpServletResponse response, Long sbdw, String export, Long qxwmb, String ndsj, String ydsj) {
		User user = ShiroUtils.getUser();
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		if(sbdw == null || sbdw <= 0){
			sbdw = user.getId();
		}
		
		Page<Khcs> kpage = new Page();
		if(export != null && export.equals("1")){
			kpage.setPageNo(1);
			kpage.setPageSize(99999);
			kpage = khcsManager.getYjPkhcs(kpage);	//获取一级考核参数节点
			List<Khcs> khcss = kpage.getResult();
			List<Dwtj> dwtjs = new ArrayList<Dwtj>();
			for(Khcs k : khcss){
				Dwtj dwtj = new Dwtj();
				dwtj.setSbdw(k.getKhxm());
				dwtj.setSbzs(dwclManager.getDwsbzsByLm(sbdw, k.getId(), ndsj, ydsj, null));
				dwtj.setYgds(dwclManager.getDwsbzsByLm(sbdw, k.getId(), ndsj, ydsj, "1"));
				dwtj.setZf(dwclManager.getDwzfByLm(sbdw, k.getId(), ndsj, ydsj));
				dwtjs.add(dwtj);
			}
			DwlmExport(dwtjs, response);
			return null;
		}
		kpage.setPageNo(page.getPageNo());
		kpage = khcsManager.getYjPkhcs(kpage);	//获取一级考核参数节点
		page.setTotalCount(kpage.getTotalCount());
		List<Khcs> khcss = kpage.getResult();
		List<Dwtj> dwtjs = new ArrayList<Dwtj>();
		for(Khcs k : khcss){
			Dwtj dwtj = new Dwtj();
			dwtj.setSbdw(k.getKhxm());
			dwtj.setSbzs(dwclManager.getDwsbzsByLm(sbdw, k.getId(), ndsj, ydsj, null));
			dwtj.setYgds(dwclManager.getDwsbzsByLm(sbdw, k.getId(), ndsj, ydsj, "1"));
			dwtj.setZf(dwclManager.getDwzfByLm(sbdw, k.getId(), ndsj, ydsj));
			dwtjs.add(dwtj);
		}
		page.setResult(dwtjs);
		if(qxwmb != null && qxwmb>0){
			model.addAttribute("sbdws", userManager.getUserByOrg(qxwmb));
		}
		model.addAttribute("page", page);
		model.addAttribute("qxwmb", qxwmb);
		model.addAttribute("ndsj", ndsj);
		model.addAttribute("sbdw", sbdw);
		model.addAttribute("enable", user.getEnabled());
		model.addAttribute("orgs", orgManager.getQxorg());
		return "dwcl/dwlmList";
	}
	
	/**
	 * 新建配置稿件时，返回配置稿件编辑视图
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create/{khcsId}", method = RequestMethod.GET)
	public String create(@PathVariable("khcsId") Long khcsId, Model model, HttpServletRequest request) {
		List<Khcs> khcss = khcsManager.getKhcsByPkhcs(khcsId);
		model.addAttribute("dwcl", new Dwcl());
		model.addAttribute("khcss", khcss);
		model.addAttribute("sbdw", ShiroUtils.getUser());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.addAttribute("cjsj",sf.format(new Date()));
		model.addAttribute("orgs", orgManager.getQxorg());
		return "dwcl/dwclEdit";
	}

	/**
	 * 编辑配置稿件时，返回配置稿件编辑视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		Dwcl dwcl = dwclManager.get(id);
		Khcs khcs = khcsManager.get(dwcl.getKhcsId());
		List<Khcs> khcss = khcsManager.getKhcsByPkhcs(khcs.getPkhcs().getId());
		model.addAttribute("khcss", khcss);
		model.addAttribute("dwcl", dwcl);
		//model.addAttribute("tsbz", "1");		// 特殊标识
		//model.addAttribute("khcs", khcsManager.getAll());
		return "dwcl/dwclEdit";
	}
	
	/**
	 * 编辑配置稿件时，返回配置稿件查看视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		Dwcl dwcl = dwclManager.get(id);
		Khcs khcs = khcsManager.get(dwcl.getKhcsId());
		List<Khcs> khcss = khcsManager.getKhcsByPkhcs(khcs.getPkhcs().getId());
		model.addAttribute("khcss", khcss);
		model.addAttribute("dwcl", dwcl);
		return "dwcl/dwclView";
	}
	
	/**
	 * 新增、编辑配置稿件页面的提交处理。保存配置稿件实体，并返回配置稿件列表视图
	 * @param Authority
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	//@ResponseBody
	public String update(Model model, Long id, @RequestParam String cjsj, Dwcl dwcl, Long sbdwid, String savecl, Long kid,HttpServletRequest request, String tsbz) {
		User czry = ShiroUtils.getUser();
		User sbdw = userManager.get(sbdwid);
		dwcl.setSbdw(sbdw);
		if(dwcl.getZf().equals("")){
			dwcl.setZf("0.0");
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dwcl.setCjsj(sf.format(new Date()));
		Khcs khcs = khcsManager.get(dwcl.getKhcsId());
		dwcl.setContent(request.getParameter("content_"));
		if(dwcl.getSpzt()!=null && dwcl.getSpzt().equals("1")){
			Float zf = Float.valueOf(khcs.getScore())+Float.valueOf(dwcl.getZf());		//材料总分
			//Float zf1 = Float.valueOf(sbdw.getZf())+zf;		//单位总分
			dwcl.setZf(zf.toString());
			//sbdw.setZf(zf1.toString());
			//userManager.save(sbdw);
			dwclManager.save(dwcl, tsbz, czry);
			return "redirect:/dwcl/dbrw";
		}else if(dwcl.getSpzt()!=null && dwcl.getSpzt().equals("0")){
			dwclManager.save(dwcl, tsbz, czry);
			return "redirect:/dwcl/dbrw";
		}
		String succ = "1";
		if(savecl != null && !savecl.equals("")){
			dwcl.setSpzt("0");
			succ = "2";
		}
		dwclManager.save(dwcl, tsbz, czry);
		model.addAttribute("succ", succ);
		Long pId = khcs.getPkhcs() == null ? 0L : khcs.getPkhcs().getId();
		//return "redirect:/khcs/khcsitem/" + pId;		//返回父考核参数下的材料
		return "dwcl/dwclkhcsList";
	}
	
//	/**
//	 * 新增、编辑配置稿件页面的提交处理。保存配置稿件实体，并返回配置稿件列表视图
//	 * @param Authority
//	 * @return
//	 * @throws ParseException 
//	 */
//	@RequestMapping(value = "update", method = RequestMethod.POST)
//	public String updatekhcl(Model model, Dwcl dwcl, Long sbdwid, String savecl, Long kid,HttpServletRequest request, String tsbz) {
//		User czry = ShiroUtils.getUser();
//		User sbdw = userManager.get(sbdwid);
//		dwcl.setSbdw(sbdw);
//		if(dwcl.getZf().equals("")){
//			dwcl.setZf("0.0");
//		}
//		Khcs khcs = khcsManager.get(dwcl.getKhcsId());
//		if(dwcl.getSpzt()!=null && dwcl.getSpzt().equals("1")){ //通过
//			Float zf = Float.valueOf(khcs.getScore())+Float.valueOf(dwcl.getZf());		//材料总分
//			//Float zf1 = Float.valueOf(sbdw.getZf())+zf;		//单位总分
//			dwcl.setZf(zf.toString());
//			//sbdw.setZf(zf1.toString());
//			//userManager.save(sbdw);
//			dwclManager.save(dwcl, tsbz, czry);
//		}else if(dwcl.getSpzt()!=null && dwcl.getSpzt().equals("2")){ //退回
//			dwclManager.save(dwcl, tsbz, czry);
//		}
//		return "redirect:/dwcl/dbrw";
//	}
	
	/**
	 * 根据主键ID删除配置稿件实体，并返回配置稿件列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long[] id) {
		Khcs khcs = khcsManager.get(dwclManager.get(id[0]).getKhcsId());
		dwclManager.delete(id);
		Long pId = khcs.getPkhcs() == null ? 0L : khcs.getPkhcs().getId();
		return "redirect:/khcs/khcsitem/" + pId;
	}
	
	/**
	 * 返回计分标准树形视图
	 * @return
	 */
	@RequestMapping(value = "jfbztree", method = RequestMethod.GET)
	public String jfbztree() {
		return "dwcl/jfbztree";
	}
	
	/**
	 * 返回视频上传视图
	 * @return
	 */
	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public String upload() {
		return "dwcl/upload";
	}
	
	/**
	 * 查询单位统计结果
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "dwtj", method = RequestMethod.GET)
	public String dwtj(Model model, Page<Dwtj> page, String dwmc, String cjsjq, String cjsjz, String[] dwlx) {
		List<User> user = null;
		Page<List> pagelist = new Page();
		pagelist.setPageNo(page.getPageNo());
		user = userManager.findUserByParameter(dwmc, dwlx);  //根据单位类型和名称
		pagelist = dwclManager.getDwtjByParameter(pagelist, user, cjsjq, cjsjz, dwlx, null);
		if(pagelist != null && pagelist.getTotalCount()>0){
			page.setTotalCount(pagelist.getTotalCount());
			List sbzslist = pagelist.getResult();   //上报总数
			List<Dwtj> dwtjs = new ArrayList<Dwtj>();
			Dwtj dwtj = null;
			String sbzs = "0";
			String ygds = "0";
			String btgs = "0";
			List<User> sbdw = new ArrayList<User>();
			for(int i=0; i<sbzslist.size(); i++){
				Object[] s = (Object[]) sbzslist.get(i);
				sbdw.add(userManager.get(Long.valueOf(s[0].toString())));
				String ygdslist = dwclManager.getYgdsByParameter(sbdw.get(i).getId(), cjsjq, cjsjz, null, "1");
				String btgslist = dwclManager.getYgdsByParameter(sbdw.get(i).getId(), cjsjq, cjsjz, null, "0");
				ygds = ygdslist;
				btgs = btgslist;
				dwtj = new Dwtj();
				sbzs = s[1].toString();	//统计上报总数
				dwtj.setSbdw(sbdw.get(i).getDwmc());
				dwtj.setSbzs(sbzs);
				dwtj.setYgds(ygds);
				dwtj.setBtgs(btgs);
				dwtj.setZf(sbdw.get(i).getZf());
				dwtjs.add(dwtj);
				dwtj = null;
			}
			page.setResult(dwtjs);
		}else{
			page.setResult(null);
		}
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		model.addAttribute("page", page);
		model.addAttribute("dwmc", dwmc);
		model.addAttribute("cjsjq", cjsjq);
		model.addAttribute("cjsjz", cjsjz);
		if(dwlx != null){
			String str = "";
			for(int i=0; i<dwlx.length; i++){
				str += dwlx[i]+",";
			}
			str = str.substring(0, str.length()-1);
			model.addAttribute("dwlx", str);
		}
		
		return "dwcl/dwtjList";
	}
	
	/**
	 * 返回登录用户代办任务
	 * @return
	 */
	@RequestMapping(value = "dbrw", method = RequestMethod.GET)
	public String dbrw(Page<Dwcl> page, Model model, HttpServletRequest request) {
		User user = ShiroUtils.getUser();
		dwclManager.getDwclByOrg(page, user.getOrg().getId());
		model.addAttribute("page", page);
		return "lhkh/dbrwList";
	}
	
	/**
	 * 考核稿件
	 * @return
	 */
	@RequestMapping(value = "khcl/{id}", method = RequestMethod.GET)
	public String khcl(@PathVariable("id") Long id, Model model) {
		Dwcl dwcl = dwclManager.get(id);
		Khcs khcs = khcsManager.get(dwcl.getKhcsId());
		List<Khcs> khcss = khcsManager.getKhcsByPkhcs(khcs.getPkhcs().getId());
		model.addAttribute("khcss", khcss);
		model.addAttribute("dwcl", dwcl);
		model.addAttribute("khcl", "1");
		return "dwcl/dwclEdit";
	}
	
	/**
	 * 保存考核结果
	 * @return
	 */
//	@RequestMapping(value = "khclsave", method = RequestMethod.POST)
//	public String khclsave(Dwcl dwcl, Long sbdwid, HttpServletRequest request) {
//		User czry = ShiroUtils.getUser();	//操作人员
//		User sbdw = userManager.get(sbdwid);
//		dwcl.setSbdw(sbdw);
//		dwclManager.save(dwcl,"",czry);
//		return "redirect:/dwcl/dbrw";
//	}
	
	@RequestMapping(value = "khclview/{id}", method = RequestMethod.GET)
	public String khclview(@PathVariable("id") Long id, Model model) {
		Dwcl dwcl = dwclManager.get(id);
		Khcs khcs = khcsManager.get(dwcl.getKhcsId());
		List<Khcs> khcss = khcsManager.getKhcsByPkhcs(khcs.getPkhcs().getId());
		model.addAttribute("khcss", khcss);
		model.addAttribute("dwcl", dwcl);
		model.addAttribute("khcl", "1");
		return "lhkh/khclView";
	}
	
	/**
	 * 单位材料列表导出Excel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void DwExport(List<Dwcl> dwcls, HttpServletResponse response) {
		try {
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			String filename = "报表信息" + DateUtils.convertDateToString("yyyy-MM-dd", DateUtils.getSystemTime());
			response.setHeader("Content-disposition", "attachment; filename="+ new String(filename.getBytes("GB2312"), "8859_1")+ ".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook book = Workbook.createWorkbook(os);
			WritableSheet sheet = book.createSheet("sheet1", 0);
			// 添加表头, 第一个参数是列，第二个是行
			Label label1 = new Label(0, 0, "材料标题"); // 第一列第一行
			Label label2 = new Label(1, 0, "上报时间"); // 第二列第一行
			Label label3 = new Label(2, 0, "上报类型");
			Label label4 = new Label(3, 0, "考核参数");
			Label label5 = new Label(4, 0, "审批状态");
			Label label6 = new Label(5, 0, "上报单位");
			// 添加到sheet1中
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			sheet.addCell(label5);
			sheet.addCell(label6);
			int length = 0;
			for (int i = 0; i < sheet.getColumns(); i++) {    //动态变换首列的长度
				length = sheet.getCell(i, 0).getContents().length() * 2 + 5;
				sheet.setColumnView(i, length);
			}
			if (dwcls.size() != 0) {
				Iterator it = dwcls.iterator();
				int i = 1; // 表示行
				while (it != null && it.hasNext()) {
					Dwcl dwcl = (Dwcl) it.next(); // list里存放学生
					Label l1 = new Label(0, i, dwcl.getTitle());
					Label l2 = new Label(1, i, dwcl.getCjsj());
					Label l3 = null;
					if(dwcl.getSblx().equals("1")){
						l3 = new Label(2, i, "新闻");
					}else if(dwcl.getSblx().equals("2")){
						l3 = new Label(2, i, "活动");
					}else if(dwcl.getSblx().equals("3")){
						l3 = new Label(2, i, "文件");
					}else if(dwcl.getSblx().equals("4")){
						l3 = new Label(2, i, "简报");
					}
					Label l4 = new Label(3, i, khcsManager.get(dwcl.getKhcsId()).getKhxm());
					Label l5 = null;
					if(dwcl.getSpzt() == null){
						l5 = new Label(4, i, "未审核");
					}else if(dwcl.getSpzt().equals("0")){
						l5 = new Label(4, i, "新稿");
					}else if(dwcl.getSpzt().equals("1")){
						l5 = new Label(4, i, "通过");
					}else if(dwcl.getSpzt().equals("2")){
						l5 = new Label(4, i, "退回");
					}
					Label l6 = new Label(5, i, dwcl.getSbdw().getDwmc());
//					Label l22 = new Label(21, i, StringUtils.isNotEmpty(dwcl.getKssl()) ? NumberUtils.formatMoney(dwcl.getKssl()): null);
//					Label l27 = new Label(26, i, DateUtils.convertDateToString("yyyy-MM-dd", dwcl.getTfrq()));
					sheet.addCell(l1);
					sheet.addCell(l2);
					sheet.addCell(l3);
					sheet.addCell(l4);
					sheet.addCell(l5);
					sheet.addCell(l6);
					i++;
				}
				book.write();
				book.close();
			} else {
				book.write();
				book.close();
			}
		} catch (Exception e) {
			LogFactory.getLog(this.getClass()).error(e.getMessage());
		}
	}
	
	/**
	 * 单位栏目列表导出Excel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void DwlmExport(List<Dwtj> dwtjs, HttpServletResponse response) {
		try {
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			String filename = "报表信息" + DateUtils.convertDateToString("yyyy-MM-dd", DateUtils.getSystemTime());
			response.setHeader("Content-disposition", "attachment; filename="+ new String(filename.getBytes("GB2312"), "8859_1")+ ".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook book = Workbook.createWorkbook(os);
			WritableSheet sheet = book.createSheet("sheet1", 0);
			// 添加表头, 第一个参数是列，第二个是行
			Label label1 = new Label(0, 0, "栏目"); // 第一列第一行
			Label label2 = new Label(1, 0, "上报数"); // 第二列第一行
			Label label3 = new Label(2, 0, "通过数");
			Label label4 = new Label(3, 0, "积分");
			// 添加到sheet1中
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			int length = 0;
			for (int i = 0; i < sheet.getColumns(); i++) {    //动态变换首列的长度
				length = sheet.getCell(i, 0).getContents().length() * 2 + 5;
				sheet.setColumnView(i, length);
			}
			if (dwtjs.size() != 0) {
				Iterator it = dwtjs.iterator();
				int i = 1; // 表示行
				while (it != null && it.hasNext()) {
					Dwtj dwtj = (Dwtj) it.next(); // list里存放学生
					Label l1 = new Label(0, i, dwtj.getSbdw());
					Label l2 = new Label(1, i, dwtj.getSbzs());
					Label l3 = new Label(2, i, dwtj.getYgds());
					Label l4 = new Label(3, i, dwtj.getZf());
					sheet.addCell(l1);
					sheet.addCell(l2);
					sheet.addCell(l3);
					sheet.addCell(l4);
					i++;
				}
				book.write();
				book.close();
			} else {
				book.write();
				book.close();
			}
		} catch (Exception e) {
			LogFactory.getLog(this.getClass()).error(e.getMessage());
		}
	}
	
	/**
	 * 积分排行列表导出Excel
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void JfphExport(List<Dwtj> dwtjs, HttpServletResponse response, String pxlb) {
		try {
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			String filename = "报表信息" + DateUtils.convertDateToString("yyyy-MM-dd", DateUtils.getSystemTime());
			response.setHeader("Content-disposition", "attachment; filename="+ new String(filename.getBytes("GB2312"), "8859_1")+ ".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook book = Workbook.createWorkbook(os);
			WritableSheet sheet = book.createSheet("sheet1", 0);
			// 添加表头, 第一个参数是列，第二个是行
			Label label1 = null;
			if(pxlb!=null&&pxlb.equals("2")){
				label1 = new Label(0, 0, "上报单位"); // 第一列第一行
			}else{
				label1 = new Label(0, 0, "文明办"); // 第一列第一行
			}
			Label label2 = new Label(1, 0, "上报数"); // 第二列第一行
			Label label3 = new Label(2, 0, "通过数");
			Label label4 = new Label(3, 0, "积分");
			// 添加到sheet1中
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			int length = 0;
			for (int i = 0; i < sheet.getColumns(); i++) {    //动态变换首列的长度
				length = sheet.getCell(i, 0).getContents().length() * 2 + 5;
				sheet.setColumnView(i, length);
			}
			if (dwtjs.size() != 0) {
				Iterator it = dwtjs.iterator();
				int i = 1; // 表示行
				while (it != null && it.hasNext()) {
					Dwtj dwtj = (Dwtj) it.next(); // list里存放学生
					Label l1 = new Label(0, i, dwtj.getSbdw());
					Label l2 = new Label(1, i, dwtj.getSbzs());
					Label l3 = new Label(2, i, dwtj.getYgds());
					Label l4 = new Label(3, i, dwtj.getZf());
					sheet.addCell(l1);
					sheet.addCell(l2);
					sheet.addCell(l3);
					sheet.addCell(l4);
					i++;
				}
				book.write();
				book.close();
			} else {
				book.write();
				book.close();
			}
		} catch (Exception e) {
			LogFactory.getLog(this.getClass()).error(e.getMessage());
		}
	}
	
	/**
	 * 上传文件
	 * @return
	 */
	@RequestMapping(value = "fileupload", method = RequestMethod.POST)
	@ResponseBody
	public String fileupload(HttpServletRequest request) {
        String responseStr = "";  
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();    
        String ctxPath = request.getSession().getServletContext().getRealPath("/")+ "uploads\\"+(String)request.getSession().getAttribute("usermobile")+"/";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());  
        ctxPath += ymd + "/";  
        //创建文件夹  
        File file = new File(ctxPath);    
        if (!file.exists()) {    
            file.mkdirs();    
        }    
        String fileName = null;
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {    
            // 上传文件名    
            MultipartFile mf = entity.getValue();    
            fileName = mf.getOriginalFilename();  
            String newFileName = ymd + "-" + fileName;// 构成新文件名。
            File uploadFile = new File(ctxPath + newFileName);    
            try {  
                FileCopyUtils.copy(mf.getBytes(), uploadFile); 
                responseStr="上传成功";  
            } catch (IOException e) {  
                responseStr="上传失败";  
                e.printStackTrace();  
            }    
        }   
            return null;
    }
	
}
