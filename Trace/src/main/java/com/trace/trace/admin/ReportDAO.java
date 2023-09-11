package com.trace.trace.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ReportDAO {

	@Autowired
	private ReportRepo rr;
	
	public void doReport(HttpServletRequest request, Report rp) {
		try {
			rp.setCate(request.getParameter("cate"));
			rp.setCate2(request.getParameter("cate2"));
			
			rr.save(rp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
