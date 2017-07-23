package com.aisino.wmdw.gjgl.web;

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
import com.aisino.framework.security.entity.User;
import com.aisino.framework.security.service.UserManager;
import com.aisino.framework.security.shiro.ShiroUtils;
import com.aisino.wmdw.gjgl.entity.Dwcl;
import com.aisino.wmdw.gjgl.entity.Dwtj;
import com.aisino.wmdw.gjgl.service.DwclManager;
import com.aisino.wmdw.lhkh.service.KhcsManager;

/**
 * 稿件管理管理Controller
 * @author xuzhe
 */
@Controller
@RequestMapping(value = "/gjgl")
public class GjglController {
	//注入稿件管理管理对象
	@Autowired
	private DwclManager dwclManager;
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
	public String list(Model model, Page<Dwcl> page, HttpServletRequest request) {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		filters.add(new PropertyFilter("LIKES_wjlx", "2"));
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		String username = (String)request.getSession().getAttribute("username");
		User user = null;
		if(Character.isDigit(username.charAt(0))){
			user = userManager.findUserByPhone(username);
			filters.add(new PropertyFilter("LIKES_sbdw", user.getDwmc()));
		}else{
			user = userManager.findUserByUserName(username);
			filters.add(new PropertyFilter("LIKES_zzjgdm", trimr0(user.getOrg().getZzjgdm())));
		}
		page = dwclManager.findPage(page, filters);
		model.addAttribute("page", page);
		return "gjgl/gjglList";
	}
	
	/**
	 * 新建配置稿件时，返回配置稿件编辑视图
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model, HttpServletRequest request) {
		User user = ShiroUtils.getUser();
		String dwmc = user.getDwmc();
		model.addAttribute("dwmc", dwmc);
		model.addAttribute("gjgl", new Dwcl());
		return "gjgl/gjglEdit";
	}

	/**
	 * 编辑配置稿件时，返回配置稿件编辑视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		Dwcl entity = dwclManager.get(id);
		model.addAttribute("gjgl", entity);
		model.addAttribute("tsbz", "1");		// 特殊标识,区别出编辑的情况
		return "gjgl/gjglEdit";
	}
	
	/**
	 * 编辑配置稿件时，返回配置稿件查看视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("gjgl", dwclManager.get(id));
		return "gjgl/gjglView";
	}
	
	/**
	 * 新增、编辑配置稿件页面的提交处理。保存配置稿件实体，并返回配置稿件列表视图
	 * @param Authority
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Dwcl gjgl, HttpServletRequest request, String tsbz) {
		User user = ShiroUtils.getUser();
		//gjgl.setZzjgdm(user.getOrg().getZzjgdm());
		//gjgl.setZzjgmc(user.getOrg().getZzjgmc());
		if(gjgl.getGjzt().equals("")){
			gjgl.setGjzt("N");
		}else if(gjgl.getSpzt().equals("0")){	//不通过的重新编辑后提交，设置状态为未审核
			gjgl.setGjzt("N");
		}
		dwclManager.save(gjgl, tsbz, user);
		return "redirect:/gjgl";
	}
	
	/**
	 * 根据主键ID删除配置稿件实体，并返回配置稿件列表视图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		dwclManager.delete(id);
		return "redirect:/gjgl";
	}
	
	/**
	 * 返回视频上传视图
	 * @return
	 */
	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public String upload() {
		return "gjgl/upload";
	}
	
	/**
	 * 查询单位统计结果
	 * @return
	 */
	@RequestMapping(value = "dwtj", method = RequestMethod.GET)
	public String dwtj(Model model) {
		List<Dwcl> gjgls = dwclManager.getAll();
		List<Dwtj> dwtjs = new ArrayList<Dwtj>();
		Dwtj dwtj = null;
		int sbzs = 0;
		int ygds = 0;
		int btgs = 0;
		for(Dwcl gjgl : gjgls){
			dwtj = new Dwtj();
			sbzs = dwclManager.getSbzsByDwmc(gjgl.getSbdw().getId());	//统计上报总数
			ygds = dwclManager.getKhtgsByDwmc(gjgl.getSbdw().getId());	  //统计审核通过数
			btgs = dwclManager.getKhwtgsByDwmc(gjgl.getSbdw().getId());   //统计审核未通过数
//			dwtj.setSbdw(gjgl.getSbdw());
//			dwtj.setSbzs(sbzs);
//			dwtj.setYgds(ygds);
//			dwtj.setBtgs(btgs);
			dwtjs.add(dwtj);
			dwtj = null;
		}
		model.addAttribute("dwtjs", dwtjs);
		return "gjgl/dwtjList";
	}
	
	/**
	 * 考核稿件
	 * @return
	 */
	@RequestMapping(value = "khgj/{id}", method = RequestMethod.GET)
	public String khgj(@PathVariable("id") Long id, Model model) {
		Dwcl gjgl = dwclManager.get(id);
		model.addAttribute("bsgj", gjgl);
		return "gjgl/khgjEdit";
	}
	
	/**
	 * 保存考核结果
	 * @return
	 */
	@RequestMapping(value = "khgjsave", method = RequestMethod.POST)
	public String khgjsave(Dwcl gjgl, HttpServletRequest request) {
		User user = ShiroUtils.getUser();
		dwclManager.save(gjgl,"", user);		//加入操作日志
		return "redirect:/dwcl/dbrw";
	}
	
	@RequestMapping(value = "khgjview/{id}", method = RequestMethod.GET)
	public String khgjview(@PathVariable("id") Long id, Model model) {
		model.addAttribute("gjgl", dwclManager.get(id));
		return "lhkj/khgjView";
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
	
	// 去除字符串右边的全0字符
	public String trimr0(String zzjgdm){
		while(zzjgdm.charAt(zzjgdm.length()-1) == '0'){
			zzjgdm = zzjgdm.substring(0,zzjgdm.length()-2);
		}
		return zzjgdm+"%";
	}
}
