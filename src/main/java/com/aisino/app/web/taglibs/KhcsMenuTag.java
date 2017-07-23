package com.aisino.app.web.taglibs;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.aisino.app.web.taglibs.builder.KhcsMenuTagBuilder;
import com.aisino.framework.web.TagDTO;

/**
 * 系统首界面左栏导航菜单自定义标签
 * 该类继承RequestContextAwareTag，主要用于获取WebApplicationContext
 * @author yuqs
 */
public class KhcsMenuTag extends RequestContextAwareTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3041636263647268721L;
	//Spring的上下文
	private WebApplicationContext springContext;
	//Servlet的上下文
	private ServletContext servletContext = null;

	/**
	 * 继承RequestContextAwareTag的doStartTagInternal方法，实际上是doStartTag的模板方法
	 */
	@Override
	protected int doStartTagInternal() throws Exception {
		//获取ServletContext
		servletContext = pageContext.getServletContext();
		//获取spring上下文
		springContext = getRequestContext().getWebApplicationContext();
		JspWriter writer = pageContext.getOut();
		if (springContext == null) {
			writer.write("获取菜单项失败");
		} else {
			TagDTO dto = new TagDTO(servletContext);
			dto.setSpringContext(springContext);
			KhcsMenuTagBuilder builder = springContext.getBean(KhcsMenuTagBuilder.class);
			writer.write(builder.build(dto));
		}
		return 0;
	}
}
