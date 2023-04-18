package action;

//DB에 관련된 날짜, 시간
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CANCELboardDAO;
import model.CANCELboardDTO;

public class CANCELUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		//한글처리
	  request.setCharacterEncoding("utf-8");
	  String post_num=request.getParameter("post_num");
	  String pageNum=request.getParameter("pageNum");
	  //int post_type=Integer.parseInt(request.getParameter("post_type"));
	  System.out.println("UpdateProAction에서의 pageNum="+pageNum);
	  //------------------------------------------------------
	  CANCELboardDTO article=new CANCELboardDTO();
	  
	  article.setPost_num(Integer.parseInt(request.getParameter("post_num")));
	  article.setMem_id(request.getParameter("mem_id"));
	  article.setAdmin_id(request.getParameter("admin_id"));
	  article.setPost_title(request.getParameter("post_title"));
	  article.setPost_cnt(request.getParameter("post_cnt")); //글내용

	  
	  //DTO저장 끝
	  CANCELboardDAO dbPro=new CANCELboardDAO();
	  int check=dbPro.updateArticle(article); //1:성공 / 0: 실패
	  System.out.println("upProAction 확인 중 ");
	  
	  //2개의 공유값이 필요
	  request.setAttribute("pageNum", pageNum);
	  request.setAttribute("check", check);
	  request.setAttribute("post_num", post_num);
	  
	  return "/CANCELupdatepro.jsp";
	}

}