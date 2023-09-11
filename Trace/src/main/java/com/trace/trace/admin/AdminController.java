package com.trace.trace.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trace.trace.member.Member;
import com.trace.trace.member.MemberDAO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

	@Autowired
	private MemberDAO mDAO;
	@Autowired
	private AdminDAO aDAO;
	@RequestMapping("/admin.go")
	public String adminGo(HttpServletRequest request,Member m) {
		if (mDAO.islogined(request)) {
			aDAO.getAllMember(request);
			mDAO.getLog(request);
			return "admin/adminview";
			
  		}else {
  			mDAO.login(request, m);
  			request.setAttribute("cp", "home");
  			request.setAttribute("cpSub", "homeboard");
  			request.setAttribute("loginPage", "member/logined");
  			request.setAttribute("loginSub", "loginedM");
  			return "index";
		}
		
		}
	
	@RequestMapping("/adimin.delete.writeandmember")
	public String adminDeleteWriteMember(HttpServletRequest request) {
		if (mDAO.islogined(request)) {
			aDAO.deleteWriterandMember(request);
			aDAO.getAllMember(request);
			mDAO.getLog(request);
			return "admin/adminview";
			
		} 
		return"index";
	}
	@RequestMapping("/adimin.delete.member")
	public String adminDeleteMember(HttpServletRequest request) {
		if (mDAO.islogined(request)) {
			aDAO.deleteMember(request);
			aDAO.getAllMember(request);
			mDAO.getLog(request);
			return "admin/adminview";
			
		} 
		return"index";
	}
	
	
	
}
	

