package com.pageturner.www.controller.ajax;
/**
 * 이 클래스는 게시글 내 좋아요 버튼을 눌렀을 때 좋아요처리를 비동기로 진행시켜줄 클래스입니다.
 * @author leeseulkim
 * @since 28th May 2020
 * 
 */
import javax.servlet.*;
import javax.servlet.http.*;

import com.pageturner.www.controller.*;
import com.pageturner.www.dao.*;

public class ClickLikeBtn implements PageController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("###### like처리");
		//ajax 명시
		req.setAttribute("isRedirect", null);
		
		//세션에 저장되어있는 아이디 불러오기
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("SID");
		//파라미터 불러오기
		String spno = req.getParameter("pno");
		int pno = 0;
		
		try {
			pno = Integer.parseInt(spno);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//DB 작업 
		PostsDAO dao = new PostsDAO();
		int sel_cnt = dao.selCnt(pno, id);
		StringBuffer buff = new StringBuffer();
		if(sel_cnt == 0) {
			int cnt = dao.addLike(pno, id);
			
			buff.append("{");
			buff.append("\"cnt\": " + cnt + "");
			buff.append("}");
			
		} else {
			sel_cnt = dao.selCheck(pno, id);
			if(sel_cnt == 1) {
				// 좋아요 체크상태일 때
				int cnt = dao.cancelLike(pno, id);
				buff.append("{");
				buff.append("\"cnt\": " + cnt + "");
				buff.append("}");
			} else {
				// 좋아요 체크상태 아닐 때
				int cnt = dao.reLike(pno, id);
				buff.append("{");
				buff.append("\"cnt\": " + cnt + "");
				buff.append("}");
			}
		}
		
		System.out.println(buff.toString());
		
		return buff.toString();
	}

}