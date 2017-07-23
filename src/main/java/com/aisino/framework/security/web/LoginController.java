package com.aisino.framework.security.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aisino.framework.security.entity.CommonResultObject;
import com.aisino.framework.security.entity.User;
import com.aisino.framework.utils.DateUtils;
import com.aisino.framework.utils.ValidateUtils;

/**
 * 系统登录Controller
 * @author yuqs
 * @version 1.0
 */
@Controller
public class LoginController {
	private static Log log = LogFactory.getLog(LoginController.class);
	
	@RequestMapping(value = "/login" ,method = RequestMethod.POST)
	public String login(User user, Model model, HttpServletRequest request) {
		String vcode = request.getParameter("vcode");
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		String svcode = (String)session.getAttribute("validateCode");
		session.setAttribute("username", user.getUsername());
		if(StringUtils.isEmpty(vcode) || StringUtils.isEmpty(svcode) || !vcode.equalsIgnoreCase(svcode)) {
			model.addAttribute("username", user.getUsername());
			model.addAttribute("error", "验证码不正确，请重新输入");
			return "security/login";
		}
		log.info("Login user=====" + user.getUsername());
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try {
			subject.login(token);
			return "redirect:/index";
		} catch(Exception re) {
			token.clear();
			model.addAttribute("username", user.getUsername());
			model.addAttribute("error", "登录失败");
			return "security/login";
		}
	}
	
	@RequestMapping(value = "/login" ,method = RequestMethod.GET)
	public String loginForm() {
		return "security/login";
	}
	
	@RequestMapping(value = "/adminlogin" ,method = RequestMethod.GET)
	public String adminlogin() {
		return "security/adminlogin";
	}
	
	@RequestMapping(value = "/adminlogin" ,method = RequestMethod.POST)
	public String adminloginForm(User user, Model model, HttpServletRequest request) {
		String vcode = request.getParameter("vcode");
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		String svcode = (String)session.getAttribute("validateCode");
		session.setAttribute("username", user.getUsername());
		if(StringUtils.isEmpty(vcode) || StringUtils.isEmpty(svcode) || !vcode.equalsIgnoreCase(svcode)) {
			model.addAttribute("username", user.getUsername());
			model.addAttribute("error", "验证码不正确，请重新输入");
			return "security/adminlogin";
		}
		log.info("Login user =>" + user);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		String remember = WebUtils.getCleanParam(request, "remember");
		log.info("remember=" + remember);
		try {
		    if(remember != null && remember.equalsIgnoreCase("on")) {
		    	   token.setRememberMe(true);
		    }
			subject.login(token);
			if(!Character.isDigit(user.getUsername().charAt(0))){
				session.setAttribute("admin", "y");
			}
			return "redirect:/index";
		} catch(UnknownAccountException ue) {
			token.clear();
			model.addAttribute("error", "登录失败，您输入的账号不存在");
			return "security/adminlogin";
		} catch(IncorrectCredentialsException ie) {
			token.clear();
			model.addAttribute("username", user.getUsername());
			model.addAttribute("error", "登录失败，密码不匹配");
			return "security/adminlogin";
		} catch(RuntimeException re) {
			token.clear();
			model.addAttribute("username", user.getUsername());
			model.addAttribute("error", "登录失败");
			return "security/adminlogin";
		}
	}
	
	@RequestMapping(value = "/sendmsg" ,method = RequestMethod.POST)
	@ResponseBody
	public Object sendmsg(HttpSession session, String mobile) {
		String lines = ""; //短信返回结果
		CommonResultObject commonResultObject = new CommonResultObject();
		String verifyCode = ValidateUtils.generateTextCode(ValidateUtils.TYPE_NUM_ONLY, 4, null);
		session.setAttribute("validateCode", verifyCode);
		session.setAttribute("usermobile", mobile);
		commonResultObject.setRetCode(verifyCode);
		return commonResultObject;
	}
}
