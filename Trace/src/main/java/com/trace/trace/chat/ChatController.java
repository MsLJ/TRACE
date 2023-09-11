package com.trace.trace.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trace.trace.member.MemberDAO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ChatController {

	@Autowired
	private MemberDAO mDAO;
	
	
	
	@RequestMapping("/go.chat")
	public String gochat(HttpServletRequest request){
		if (mDAO.islogined(request)) {
			request.setAttribute("cp", "chat/chat");
			request.setAttribute("cpSub", "chatItem");
			request.setAttribute("loginPage", "member/logined");
			request.setAttribute("loginSub", "loginedM");
		}else {
			mDAO.islogined(request);
			request.setAttribute("cp", "home");
			request.setAttribute("cpSub", "homeboard");
			request.setAttribute("loginPage", "member/logined");
			request.setAttribute("loginSub", "loginedM");
			
		}
		
		
		
		return "index";
		
	}
	
	
	
	
}
