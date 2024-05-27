package com.mycom.enjoy.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.mycom.enjoy.member.dto.MemberDto;

// 클라이언트에 대한 응답을 성공/실패 <= json 문자열 "result":"login"
@Component
public class LoginInterceptor implements HandlerInterceptor {
	private final String jsonStr = "{\"result\":\"login\"}";

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 안써놓으면 까먹으니까 써놔
		String requestURI = request.getRequestURI();
		System.out.println("LoginInterceptor >> " + requestURI);
		// JSP는 비동기+페이지 이동 둘 다 처리
		// SPC는 비동기 요청만 처리하면 된다.
		// 비동기 처리만 해준다.
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		
        // CORS 에서  put, delete 등 오류 해결 코드
		if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
		if (memberDto == null) {
			// 로그인 필요하다는 응답.
			
			if (requestURI.equals("/notices") && request.getMethod().equals("GET")) {
				return true;
			} else if (requestURI.contentEquals("/boards") && request.getMethod().equals("GET")) {
				return true;
			} else if (requestURI.equals("/trip") || requestURI.equals("/trip/search/")
					|| requestURI.equals("/trip/searchPopular")
					|| requestURI.equals("/trip/searchPopularAge")
					|| requestURI.equals("/trip/searchPopularDay")
					|| requestURI.equals("/trip/searchRandom")) {
				return true;
			} else if (requestURI.equals("/trip/replys") && request.getMethod().equals("GET")) { //replys get만 true리턴.
				return true;
			}else if(requestURI.startsWith("/trip/getRegion/")||
					requestURI.startsWith("/trip/searchDetail/")) {
				return true;
			}else if(requestURI.startsWith("/bookmarks/")&&request.getMethod().equals("GET")) {
				return true;
			}
			response.getWriter().write(jsonStr);
			return false;
		}
		return true;

	}

}
