package com.trace.trace.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trace.trace.member.Member;
import com.trace.trace.member.MemberDAO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ReportController {

	@Autowired
	private MemberDAO mDAO;
	
	@Autowired
	private ReportDAO rDAO;
	
	@RequestMapping("/dataroom.report")
	public String dataroomReport(HttpServletRequest request, @RequestParam(name = "cate2", defaultValue = "dataroom") String cate2,@RequestParam("list_no") String listNo) {
		mDAO.islogined(request);
		request.setAttribute("cate2", cate2);
		request.setAttribute("no", listNo);
		return"report";
	}
	@RequestMapping("/dataroom.write.report")
	public String writeDataroomReport(HttpServletRequest request,Report r) {
		
		mDAO.islogined(request);
		
		rDAO.doReport(request, r);
		request.setAttribute("cp", "home");
		request.setAttribute("cpSub", "homeboard");
		request.setAttribute("loginPage", "/member/logined");
		request.setAttribute("loginSub", "loginedM");
		return"index";
	}
	
	
	
}
