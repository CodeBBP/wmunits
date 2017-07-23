package com.aisino.app.web;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Rotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aisino.framework.orm.Page;
import com.aisino.framework.security.entity.Org;
import com.aisino.framework.security.entity.User;
import com.aisino.framework.security.service.OrgManager;
import com.aisino.framework.security.service.UserManager;
import com.aisino.framework.security.shiro.ShiroUtils;
import com.aisino.framework.utils.ValidateUtils;
import com.aisino.wmdw.gjgl.entity.Dwcl;
import com.aisino.wmdw.gjgl.entity.Dwtj;
import com.aisino.wmdw.gjgl.service.DwclManager;



/**
 * 平台级别Controller
 * @author yuqs
 */
@Controller
public class PlatformContoller {
	//注入单位材料管理对象
	@Autowired
	private DwclManager dwclManager;
	@Autowired
	private OrgManager orgManager;
	@Autowired
	private UserManager userManager;
	
	/**
	 * 登录成功后系统首页（一般存在top、left、right三大区域通过frameset包含）
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index" ,method=RequestMethod.GET)
	public String main(Model model, HttpSession session) {
		String adminFlag = (String)session.getAttribute("admin");
		User user = ShiroUtils.getUser();
		model.addAttribute("user", user);
		if(adminFlag != null && adminFlag.equalsIgnoreCase("y")) {
			//return "system/wmdwleft_bak";
			return "system/adminindex";
		} else {
			return "system/wmdwindex";
		}
		//return "system/index";
	}
	
	/**
	 * 如果首页为frameset布局，则存在top
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/top" ,method=RequestMethod.GET)
	public String top(Model model) {
		User user = ShiroUtils.getUser();
		model.addAttribute("uid", user.getId());
		return "system/top";
	}
	
	/**
	 * 系统left
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/left" ,method=RequestMethod.GET)
	public String left(Model model, HttpSession session) {
		String adminFlag = (String)session.getAttribute("admin");
		if(adminFlag != null && adminFlag.equalsIgnoreCase("y")) {
			//return "system/wmdwleft_bak";
			return "system/adminleft";
		} else {
			return "system/wmdwleft";
		}
	}
	
	/**
	 * 文明单位left
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wmdwleft" ,method=RequestMethod.GET)
	public String wmdwleft(Model model) {
		return "system/wmdwleft";
	}
	
	/**
	 * 系统管里left
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/systemleft" ,method=RequestMethod.GET)
	public String systemleft(Model model) {
		return "system/systemleft";
	}
	
	/**
	 * 城乡环境left
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cxhjleft" ,method=RequestMethod.GET)
	public String wmdwglleft(Model model) {
		return "system/cxhjleft";
	}
	
	/**
	 * 思想建设left
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sxjsleft" ,method=RequestMethod.GET)
	public String sxjsleft(Model model) {
		return "system/sxjsleft";
	}
	
	/**
	 * 如果首页为frameset布局，则存在middle时
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/middle" ,method=RequestMethod.GET)
	public String middle(Model model) {
		return "system/middle";
	}
	
	/**
	 * 如果首页为frameset布局，则存在right
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/right" ,method=RequestMethod.GET)
	public String right(Page<Dwcl> page, Model model, HttpServletRequest request) throws IOException {
		User user = ShiroUtils.getUser();
		String adminFlag = (String)request.getSession().getAttribute("admin");
		if(adminFlag != null && adminFlag.equalsIgnoreCase("y")) {
		page.setPageSize(5);
		dwclManager.getDwclByOrg(page, user.getOrg().getId());
		int count = dwclManager.getCountByOrg(user.getOrg().getId());   //待办任务数
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date()); 
		List<Dwtj> dwtjs = new ArrayList<Dwtj>();
		//文明办统计
		Page<List> opage = new Page();
		List olist = null;
		opage.setPageNo(1);
		opage.setPageSize(99);//统计所有文明办
		opage = userManager.getOrgByPx(opage, year, "jf", "desc");
		olist = opage.getResult();
		for(int i=0; i<olist.size(); i++){
			Object[] o = (Object[]) olist.get(i);
			Dwtj dwtj = new Dwtj();
			Org org = orgManager.get(Long.valueOf(o[0].toString()));
			dwtj.setSbdw(org.getZzjgmc());
			dwtj.setZf(o[1].toString());
			dwtjs.add(dwtj);
		}
		JFreeChart bingchart = getJFreeChart("饼状统计图", dwtjs);	//饼状图
		
		//文明单位统计
		Page<User> upage = new Page();
		List<User> users = null;
		upage.setPageNo(1);
		upage.setPageSize(7);//统计前7名
		upage = userManager.getUserByPx(upage, year, "jf", "desc");
		users = upage.getResult();
		dwtjs.clear();
		for(User u : users){
			Dwtj dwtj = new Dwtj();
			dwtj.setSbdw(u.getDwmc());
			dwtj.setZf(dwclManager.getDwzf(u.getId(), year));
			dwtjs.add(dwtj);
		}
		JFreeChart zhuchart = getJFreeChartZ("柱状统计图", users);		//柱状图
		
		String filename = ServletUtilities.saveChartAsJPEG(bingchart, 500, 300, null, request.getSession());
		String filename1 = ServletUtilities.saveChartAsJPEG(zhuchart, 580, 300, null, request.getSession());
	    String bchartUrl = request.getContextPath() + "/chart?filename=" + filename;
	    String zchartUrl = request.getContextPath() + "/chart?filename=" + filename1;
		model.addAttribute("bchartUrl", bchartUrl);
		model.addAttribute("zchartUrl", zchartUrl);
		model.addAttribute("page", page);
		model.addAttribute("count", count);
		return "system/right";
		} else {
			return "redirect:/dwcl";
		}
	}
	
	/**
	 * 验证码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/validate" ,method=RequestMethod.GET)
	public void validate(HttpServletResponse response, HttpSession session) {
		response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "No-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
		String verifyCode = ValidateUtils.generateTextCode(ValidateUtils.TYPE_NUM_ONLY, 4, null);
		session.setAttribute("validateCode", verifyCode);
		BufferedImage bim = ValidateUtils.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
		try {
			ImageIO.write(bim, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public JFreeChart getJFreeChart(String title, List<Dwtj> dwtjs) throws IOException{
		DefaultPieDataset dpd = new DefaultPieDataset();		// 创建饼图数据对象
	    for(Dwtj d : dwtjs){
	    	dpd.setValue(d.getSbdw(), Float.valueOf(d.getZf()));
	    }
	    JFreeChart chart = ChartFactory.createPieChart3D(title, dpd, true, true, true);// createpieChart3D创建3D饼图  
	    PiePlot3D plot = (PiePlot3D) chart.getPlot(); // 取得3D饼图对象
	    plot.setBaseSectionOutlinePaint(Color.RED);// 图形边框颜色
	    plot.setBaseSectionOutlineStroke(new BasicStroke(0.5f));  // 图形边框粗细 
	    plot.setSectionOutlinesVisible(false);
	    plot.setForegroundAlpha(0.50f);  // 指定图片的透明度(0.0-1.0) 
	    plot.setToolTipGenerator(new StandardPieToolTipGenerator());  // 设置鼠标悬停提示  
	    plot.setCircular(true);// 指定显示的饼图上圆形(false)还椭圆形(true)
	    plot.setDirection(Rotation.CLOCKWISE);    //设置旋转方向，Rotation.CLOCKWISE)为顺时针
	    return chart;
	}
	
	public JFreeChart getJFreeChartZ(String title,  List<User> users) throws IOException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(User u : users){
			dataset.addValue(Float.valueOf(u.getZf()), "总分", u.getDwmc());
		}
		JFreeChart chart = ChartFactory.createBarChart(title, "文明单位", "总分", dataset, PlotOrientation.VERTICAL, false, false, false);
		CategoryPlot plot = chart.getCategoryPlot();
		// 设置网格背景颜色
		plot.setBackgroundPaint(Color.white);
		// 设置网格竖线颜色
		plot.setDomainGridlinePaint(Color.blue);
		// 设置网格横线颜色
		plot.setRangeGridlinePaint(Color.blue);

		DecimalFormat df = new DecimalFormat("0.0");
		NumberAxis vn = (NumberAxis) plot.getRangeAxis();
		vn.setNumberFormatOverride(df);
		// 显示每个柱的数值，并修改该数值的字体属性
 		CategoryAxis domainAxis = plot.getDomainAxis(); 
 		domainAxis.setMaximumCategoryLabelWidthRatio(1.6f);
 		StackedBarRenderer3D renderer = new StackedBarRenderer3D();
 		renderer.setSeriesPaint(1, new Color(0, 255, 0));//实报柱子的颜色为绿色
		renderer.setSeriesOutlinePaint(1,Color.red);//边框为红色
		renderer.setItemMargin(0.5);//组内柱子间隔为组宽的10%
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",new DecimalFormat("0.0")));       
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE10, TextAnchor.BASELINE_LEFT));
		renderer.setItemLabelAnchorOffset(10D);
		plot.setRenderer(renderer);
		return chart;
	}
}
