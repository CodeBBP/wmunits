package com.aisino.framework.web;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;

public class BaseController extends SimpleControllerHandlerAdapter {

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
	    binder.registerCustomEditor(Date.class, new DateEditor());
	    //binder.registerCustomEditor(Integer.class, new IntConvertEditor());
	}
}
