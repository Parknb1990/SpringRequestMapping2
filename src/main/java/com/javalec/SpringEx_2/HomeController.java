package com.javalec.SpringEx_2;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
//	url���� Spring_Ex2/index�� �Է��ϰ� �Ǹ� index RequestMapping�� ���� index.jsp�� �̵�
	@RequestMapping("index")
	public String goIndex() {
		return "index";
	}
	
//	Get, Post ��� ���� student ���� ����
	@RequestMapping(method=RequestMethod.GET, value="/student")
	
//	HttpServletRequest ��ü�� �̿��� �� �޾ƿ���
	public String goStudent(HttpServletRequest httpServletRequest, Model model) {
		
		System.out.println("RequestMethod.GET");
		
		String id = httpServletRequest.getParameter("id");
		String pw = httpServletRequest.getParameter("pw");
		
		System.out.println("id : "+id);
		System.out.println("pw : "+pw);
		
//		Model��ü�� �̿��� �� ����
		model.addAttribute("studentId", id);
		model.addAttribute("studentPw", pw);
		
		return "student/studentId";
	}
	
	
//	POST ������� ���۹��� �����͸� ModelAndView ��ü�� �̿��� ���
	@RequestMapping(method=RequestMethod.POST, value="/student")
//	ModelAndView �޼ҵ� ����
	public ModelAndView goStudent(HttpServletRequest httpServletRequest) {
		
		System.out.println("RequestMethod.POST");
		
		String id = httpServletRequest.getParameter("id");
		String pw = httpServletRequest.getParameter("pw");
		
		System.out.println("id : "+id);
		System.out.println("pw : "+pw);
		
//		ModelAndView ��ü ����
		ModelAndView mv = new ModelAndView();
//		student������ �ִ� studentId�� �� ����
		mv.setViewName("student/studentId");
		
//		id�� pw �� �Է�
		mv.addObject("studentId", id);
		mv.addObject("studentPw", pw);
		
		return mv;
	}
	
}
