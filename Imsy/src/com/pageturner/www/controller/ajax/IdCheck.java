package com.pageturner.www.controller.ajax;

/**
 * 아이디 중복확인 처리
 * @author	이명환
 * @since	2020.06.11
 */
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pageturner.www.dao.MemberDAO;
import com.pageturner.www.controller.PageController;

public class IdCheck implements PageController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String sid = req.getParameter("id");
		
		MemberDAO mDAO = new MemberDAO();
		System.out.println(sid);
		int cnt = mDAO.getIdCnt(sid);
		
		PrintWriter pw = null;
		try {
			pw = resp.getWriter();
			pw.println("{");
			pw.println("	\"cnt\": " + cnt);
			pw.println("}");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
