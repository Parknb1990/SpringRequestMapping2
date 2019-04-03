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
	
//	url에서 Spring_Ex2/index를 입력하게 되면 index RequestMapping을 만나 index.jsp로 이동
	@RequestMapping("index")
	public String goIndex() {
		return "index";
	}
	
//	Get, Post 방식 선언 student 폴더 맵핑
	@RequestMapping(method=RequestMethod.GET, value="/student")
	
//	HttpServletRequest 객체를 이용해 값 받아오기
	public String goStudent(HttpServletRequest httpServletRequest, Model model) {
		
		System.out.println("RequestMethod.GET");
		
		String id = httpServletRequest.getParameter("id");
		String pw = httpServletRequest.getParameter("pw");
		
		System.out.println("id : "+id);
		System.out.println("pw : "+pw);
		
//		Model객체를 이용해 값 전달
		model.addAttribute("studentId", id);
		model.addAttribute("studentPw", pw);
		
		return "student/studentId";
	}
	
	
//	POST 방식으로 전송받은 데이터를 ModelAndView 객체를 이용해 출력
	@RequestMapping(method=RequestMethod.POST, value="/student")
//	ModelAndView 메소드 생성
	public ModelAndView goStudent(HttpServletRequest httpServletRequest) {
		
		System.out.println("RequestMethod.POST");
		
		String id = httpServletRequest.getParameter("id");
		String pw = httpServletRequest.getParameter("pw");
		
		System.out.println("id : "+id);
		System.out.println("pw : "+pw);
		
//		ModelAndView 객체 생성
		ModelAndView mv = new ModelAndView();
//		student폴더에 있는 studentId로 값 전달
		mv.setViewName("student/studentId");
		
//		id와 pw 값 입력
		mv.addObject("studentId", id);
		mv.addObject("studentPw", pw);
		
		return mv;
	}
	
}
