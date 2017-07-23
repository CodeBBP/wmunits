package com.aisino.wmdw.sxsb.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.aisino.framework.security.entity.User;
import com.aisino.framework.security.service.UserManager;
import com.aisino.framework.security.shiro.ShiroUtils;
import com.aisino.wmdw.lhkh.entity.Khgz;
import com.aisino.wmdw.lhkh.service.KhgzManager;
import com.aisino.wmdw.sxsb.entity.Cxhj;
import com.aisino.wmdw.sxsb.service.CxhjManager;

/**
 * 地图查询管理Controller
 * @author xuzhe
 */
@Controller
@RequestMapping(value = "/cxhj")
public class CxhjController {
	//注入配置参数管理对象
	@Autowired
	private CxhjManager cxhjManager;
	@Autowired
	private KhgzManager khgzManager;
	@Autowired
	private UserManager userManager;
	private List<Cxhj> cxhjs;
	
	/**
	 * 分页查询配置，返回配置参数列表视图
	 * @param model
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, Page<Cxhj> page, HttpServletRequest request) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		User user = ShiroUtils.getUser();
		//filters.add(new PropertyFilter("LIKES_scdw", user.getId().toString()));	//查询上传单位下的
		page = cxhjManager.findPage(page, filters);
		model.addAttribute("page", page);
		return "cxhj/cxhjList";
	}
	
	/**
	 * 材料上报树
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String khgzTree(Model model) {
		return "cxhj/khgzTree";
	}
	
	/**
	 * 新建配置稿件时，返回配置稿件编辑视图
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create/{khgzId}", method = RequestMethod.GET)
	public String create(@PathVariable("khgzId") Long khgzId, Model model) {
		model.addAttribute("cxhj", new Cxhj());
		//List<Khgz> khgzs = khgzManager.getKhgzByPkhcs(khgzId);
		Khgz khgz = khgzManager.get(khgzId);
		model.addAttribute("khgz", khgz);
		model.addAttribute("scdw", ShiroUtils.getUser());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.addAttribute("cjsj",sf.format(new Date()));
		model.addAttribute("tsbz", "1");
		return "cxhj/cxhjEdit";
	}

	/**
	 * 编辑配置稿件时，返回配置稿件编辑视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		Cxhj entity = cxhjManager.get(id);
		model.addAttribute("cxhj", entity);
		model.addAttribute("tsbz", "1");
		return "cxhj/cxhjEdit";
	}
	
	/**
	 * 编辑配置稿件时，返回配置稿件查看视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cxhj", cxhjManager.get(id));
		return "cxhj/cxhjView";
	}
	
	/**
	 * 新增、编辑配置稿件页面的提交处理。保存配置稿件实体，并返回配置稿件列表视图
	 * @param Authority
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Cxhj cxhj, HttpServletRequest request, String tsbz) {
		User user = ShiroUtils.getUser();
		cxhj.setScdw(user);
		cxhjManager.save(cxhj,tsbz);
		return "redirect:/cxhj";
	}
	
	/**
	 * 根据主键ID删除配置稿件实体，并返回配置稿件列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		cxhjManager.delete(id);
		return "redirect:/cxhj";
	}
	
	/**
	 * 返回地图视图
	 * @return
	 */
	@RequestMapping(value = "dtview", method = RequestMethod.GET)
	public String view() {
		return "cxhj/dtView";
	}
	
	/**
	 * 返回地图视图
	 * @return
	 */
	@RequestMapping(value = "hjdtview", method = RequestMethod.GET)
	public String hjdtview() {
		return "cxhj/dtallView";
	}
	
	/**
	 * 返回地图视图
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "dtallview", method = RequestMethod.POST)
	public Object dtallview(HttpServletRequest request) {
		String username = (String)request.getSession().getAttribute("username");
		User user = null;
		if(Character.isDigit(username.charAt(0))){
			user = userManager.findUserByPhone(username);
		}else{
			user = userManager.findUserByUserName(username);
		}
		List<Cxhj> cxhjs = cxhjManager.getCxhjByZzjgdm(user.getOrg().getZzjgdm());
		for(Cxhj cxhj : cxhjs){
			if(cxhj.getContent().length()>30){
				cxhj.setContent(cxhj.getContent().substring(0, 30));
			}
		}
		return cxhjs;
	}
	
	/**
	 * 返回上传照片视图
	 * @return
	 */
	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public String upload() {
		return "cxhj/upload";
	}
	
	/**
	 * 返回地图标记视图
	 * @return
	 */
	@RequestMapping(value = "mapmark", method = RequestMethod.GET)
	public String mapmarkpoint(Model model) {
		return "cxhj/dtEdit";
	}
	
	/**
	 * 返回地图标记视图
	 * @return
	 */
	@RequestMapping(value = "mapmark/{xpoint}/{ypoint}", method = RequestMethod.GET)
	public String mapmarkpoint(@PathVariable("xpoint") String xpoint, @PathVariable("ypoint") String ypoint, Model model) {
		if(xpoint.length()>1){
			model.addAttribute("xpoint", xpoint);
			ypoint = ypoint.substring(0,2)+"."+ypoint.substring(2);
			model.addAttribute("ypoint", ypoint);
		}
		return "cxhj/dtEdit";
	}
	
	/**
	 * 返回地图标记视图
	 * @return
	 */
	@RequestMapping(value = "mapview/{id}", method = RequestMethod.GET)
	public String mapview(@PathVariable("id") Long id, Model model) {
		Cxhj cxhj = cxhjManager.get(id);
		if(cxhj.getContent().length()>30){
			model.addAttribute("content", cxhj.getContent().substring(0, 30));
		}else{
			model.addAttribute("content", cxhj.getContent());
		}
		cxhj.setContent(null);
		model.addAttribute("cxhj", cxhj);
		return "cxhj/dtView";
	}
	
	/**
	 * 返回登录用户代办任务
	 * @return
	 */
	@RequestMapping(value = "dbrw", method = RequestMethod.GET)
	public String dbrw(Model model, HttpServletRequest request) {
		User user = ShiroUtils.getUser();
		List<Cxhj> cxhjs = cxhjManager.getDbrwByZzjgdm(user.getOrg().getZzjgdm());
		model.addAttribute("cxhjs", cxhjs);
		return "cxhj/dbrwList";
	}
	
	/**
	 * 审核环境
	 * @return
	 */
	@RequestMapping(value = "shhj/{id}", method = RequestMethod.GET)
	public String khcl(@PathVariable("id") Long id, Model model) {
		Cxhj cxhj = cxhjManager.get(id);
		model.addAttribute("cxhj", cxhj);
		return "cxhj/shhjEdit";
	}
	
	/**
	 * 保存考核结果
	 * @return
	 */
	@RequestMapping(value = "shhjsave", method = RequestMethod.POST)
	public String shhjsave(Cxhj cxhj, HttpServletRequest request) {
		String username = (String)request.getSession().getAttribute("username");
		User user = null;
		if(Character.isDigit(username.charAt(0))){
			user = userManager.findUserByPhone(username);
		}else{
			user = userManager.findUserByUserName(username);
		}
		cxhjManager.save(cxhj,"");
		return "redirect:/cxhj/dbrw";
	}
	
	@RequestMapping(value = "shhjview/{id}", method = RequestMethod.GET)
	public String shhjview(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cxhj", cxhjManager.get(id));
		return "cxhj/shhjView";
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
        String ctxPath = request.getSession().getServletContext().getRealPath("/")+ "uploads\\"+(String)request.getSession().getAttribute("username")+"/";
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
            String newFileName = fileName;// 构成新文件名。
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
	
	// 去除字符串右边的全0字符
	public String trimr0(String zzjgdm){
		while(zzjgdm.charAt(zzjgdm.length()-1) == '0'){
			zzjgdm = zzjgdm.substring(0,zzjgdm.length()-2);
		}
		return zzjgdm+"%";
	}
}
