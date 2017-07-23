package com.aisino.wmdw.sxjs.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aisino.framework.orm.Page;
import com.aisino.framework.orm.PropertyFilter;
import com.aisino.wmdw.gjgl.entity.Dwtj;
import com.aisino.wmdw.sxjs.entity.Xmcl;
import com.aisino.wmdw.sxjs.entity.Xstj;
import com.aisino.wmdw.sxjs.service.KhpgManager;
import com.aisino.wmdw.sxjs.service.XmclManager;

/**
 * 配置变更管理Controller
 * @author xuzhe
 */
@Controller
@RequestMapping(value = "/xmcl")
public class XmclController {
	//注入配置变更管理对象
	@Autowired
	private XmclManager xmclManager;
	@Autowired
	private KhpgManager khpgManager;
	
	/**
	 * 分页查询配置，返回配置稿件列表视图
	 * @param model
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, Page<Xmcl> page, HttpServletRequest request) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = xmclManager.findPage(page, filters);
		model.addAttribute("page", page);
		return "sxjs/xmclList";
	}
	
	/**
	 * 新建配置稿件时，返回配置稿件编辑视图
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("xmcl", new Xmcl());
		model.addAttribute("khpg", khpgManager.getAll());
		return "sxjs/xmclEdit";
	}

	/**
	 * 编辑配置稿件时，返回配置稿件编辑视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		Xmcl entity = xmclManager.get(id);
		model.addAttribute("xmcl", entity);
		model.addAttribute("khpg", khpgManager.getAll());
		return "sxjs/xmclEdit";
	}
	
	/**
	 * 编辑配置稿件时，返回配置稿件查看视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("xmcl", xmclManager.get(id));
		return "sxjs/xmclView";
	}
	
	/**
	 * 新增、编辑配置稿件页面的提交处理。保存配置稿件实体，并返回配置稿件列表视图
	 * @param Authority
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Xmcl xmcl) {
		if(xmcl.getShzt().equals("")){
			xmcl.setShzt("N");
		}else if(xmcl.getShzt().equals("0")){
			xmcl.setShzt("N");
		}
		xmclManager.save(xmcl);
		return "redirect:/xmcl";
	}
	
	/**
	 * 根据主键ID删除配置稿件实体，并返回配置稿件列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		xmclManager.delete(id);
		return "redirect:/xmcl";
	}
	
	/**
	 * 返回计分标准树形视图
	 * @return
	 */
	@RequestMapping(value = "pfbztree", method = RequestMethod.GET)
	public String jfbztree() {
		return "sxjs/pfbztree";
	}
	
	/**
	 * 返回视频上传视图
	 * @return
	 */
	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public String upload() {
		return "sxjs/upload";
	}
	
	/**
	 * 查询单位统计结果
	 * @return
	 */
	@RequestMapping(value = "xstj", method = RequestMethod.GET)
	public String xstj(Model model) {
		List<Xmcl> xmcls = xmclManager.getAll();
		List<Xstj> xstjs = new ArrayList<Xstj>();
		Xstj xstj = null;
		int sbzs = 0;
		int khtgs = 0;
		int khwtgs = 0;
		int wkhs = 0;
		for(Xmcl xmcl : xmcls){
			xstj = new Xstj();
			sbzs = xmclManager.getSbzsByXsmc(xmcl.getSbxs());		//统计上报总数
			khtgs = xmclManager.getKhtgsByXsmc(xmcl.getSbxs());	//统计审核通过数
			khwtgs = xmclManager.getKhwtgsByXsmc(xmcl.getSbxs());	//统计审核未通过数
			wkhs = xmclManager.getWkhsByXsmc(xmcl.getSbxs());    //未考核数
			xstj.setXsmc(xmcl.getSbxs());
			xstj.setSbzs(sbzs);
			xstj.setKhtgs(khtgs);
			xstj.setKhwtgs(khwtgs);
			xstj.setWkhs(wkhs);
			xstjs.add(xstj);
			xstj = null;
		}
		model.addAttribute("xstjs", xstjs);
		return "sxjs/xstjList";
	}
	
	/**
	 * 返回登录用户代办任务
	 * @return
	 */
	@RequestMapping(value = "dbrw", method = RequestMethod.GET)
	public String dbrw(Model model, HttpServletRequest request) {
		String username = (String)request.getSession().getAttribute("username");
		List<Xmcl> xmcls = xmclManager.getXmclByUsername(username);
		model.addAttribute("xmcls", xmcls);
		return "sxjs/dbrwList";
	}
	
	/**
	 * 考核稿件
	 * @return
	 */
	@RequestMapping(value = "khcl/{id}", method = RequestMethod.GET)
	public String khgj(@PathVariable("id") Long id, Model model) {
		Xmcl xmcl = xmclManager.get(id);
		model.addAttribute("xmcl", xmcl);
		return "sxjs/khclEdit";
	}
	
	/**
	 * 保存考核结果
	 * @return
	 */
	@RequestMapping(value = "khclsave", method = RequestMethod.POST)
	public String khgjsave(Xmcl xmcl) {
		xmclManager.save(xmcl);
		return "redirect:/xmcl/dbrw";
	}
	
	@RequestMapping(value = "khgjview/{id}", method = RequestMethod.GET)
	public String khgjview(@PathVariable("id") Long id, Model model) {
		model.addAttribute("xmcl", xmclManager.get(id));
		return "sxjs/khclView";
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
