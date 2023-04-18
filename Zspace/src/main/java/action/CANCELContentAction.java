package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CANCELboardDAO;
import model.CANCELboardDTO;

//요청을 받아서 처리해주는 클래스(액션 클래스) -> 실행결과 -> 컨트롤러 -> jsp
public class CANCELContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		/*
		String postNumParam = request.getParameter("post_num");	
		if (postNumParam == null || postNumParam.isEmpty()) {
		    // post_num이 null이거나 빈 문자열("")인 경우 처리
		//1.content.jsp에서 처리한 자바코드
		//글 상세보기-> 게시판(상품의 정보를 자세히(SangDetail.jsp)
		//list.jsp에서 링크 : num, PageNum
		  } else {
			    int post_num = Integer.parseInt(postNumParam);
		 
			*/     
		
		  int post_num=Integer.parseInt(request.getParameter("post_num"));
		  String pageNum = request.getParameter("pageNum").trim();

		  System.out.println("ContentAction의 pageNum="+pageNum+", post_num="+post_num);
		  
		  CANCELboardDAO dbPro=new CANCELboardDAO();
		  CANCELboardDTO article=dbPro.getArticle(post_num); //조회수 증가

		  //링크 문자열의 길이를줄이기 위해서
		  int ref=article.getRef();
		  int re_step=article.getRe_step();
		  //System.out.println("content.do의 매개변수 확인");
		  System.out.println("ref="+ref+", re_step="+re_step);
		 
	
		  //2. 처리한 결과를 서버의 메모리에 저장(request)-> jsp에서 키명
		  request.setAttribute("post_num", post_num); //${num}을 주기 위해 key, value 똑같이 		  
		  request.setAttribute("pageNum", pageNum); // ${pageNum}
		  request.setAttribute("article",article);
		  //ref,re_step, re_level값은 article에서 분리가 가능해서 생략
		  
		  return "/CANCELpostview.jsp";
		  
	}
}
