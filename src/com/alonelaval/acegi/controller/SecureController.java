package com.alonelaval.acegi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.alonelaval.acegi.test.TestInterface;
import com.alonelaval.acegi.user.User;

/**
 * 
 * @author huawei
 * @detail MyAcegi.com.lavakn.acegi.controller.SecureController.java
 * @createDate 2009-6-15 下午04:40:42	
 */
@Controller
public class SecureController {
	
	@Autowired
	private TestInterface contactManager;
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * 
	 */
	@RequestMapping("/secure.action")
	public ModelAndView toSecure(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView("/secure/index");
	//	System.out.println("11111111111111111111111111111111111111111111111");	
		response.sendRedirect(request.getContextPath()+"/secure/index.jsp");

		return mv;
	}
	
	@RequestMapping("/add.action")
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView mv = new ModelAndView("/secure/index");
	//	System.out.println("11111111111111111111111111111111111111111111111");	
		//throw new  Exception("老子是异常!");
		//response.
	//	WebApplicationContext wac =WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
		//TestInterface testInterface = (TestInterface)wac.getBean("contactManager");
		
		//contactManager.Add("huawei","huawei");
		//if(testInterface!= null){
		//	testInterface.Add("huawei", "hhua");
		//}
	//	contactManager.Add("aaa", "bbbb");
		//response.sendRedirect(request.getContextPath()+"/secure/index.jsp");

		return mv;
	}
	@RequestMapping("/get.action")
	public ModelAndView get(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView("pdfView");
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			User user= new User(); 
			
			user.setUsername("huawei   "+i);
			user.setPassword("huawei   "+i);
			user.setEnabled(i%2 == 0 ? true :false );
			users.add(user);
		}
		

		return mv.addObject("users",users);
	}
	@RequestMapping("/remove.action")
	public ModelAndView remove(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView("/secure/index");
	//	System.out.println("11111111111111111111111111111111111111111111111");	
		contactManager.remove("aaaaaaaaaaaaaaaaaaa");
	//	response.sendRedirect(request.getContextPath()+"/secure/index.jsp");

		return mv;
	}
	@RequestMapping("/getAll.action")
	public ModelAndView getAll(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView("/secure/index");
	//	System.out.println("11111111111111111111111111111111111111111111111");
		contactManager.getAll();
		//response.sendRedirect(request.getContextPath()+"/secure/index.jsp");

		return mv;
	}
	
	@RequestMapping("/update.action")
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView mv = new ModelAndView("/secure/index");
	//	System.out.println("11111111111111111111111111111111111111111111111");	
		//response.sendRedirect(request.getContextPath()+"/secure/index.jsp");

		return mv;
	}
}