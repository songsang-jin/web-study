     package action;

//DB에 관련된 날짜, 시간
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QNAboardDAO;
import model.QNAboardDTO;

public class QNAWriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
	  request.setCharacterEncoding("utf-8");
	  QNAboardDTO article=new QNAboardDTO();
	  System.out.println("디버깅중");	
	  
	  System.out.println("request.getParameter(\"post_num\")=>"+request.getParameter("post_num"));
	  System.out.println("request.getParameter(\"mem_id\")=>"+request.getParameter("mem_id"));
	  System.out.println("request.getParameter(\"post_title\")=>"+request.getParameter("post_title"));
	  System.out.println("request.getParameter(\"ref\")=>"+request.getParameter("ref"));
	  System.out.println("request.getParameter(\"re_step\")=>"+request.getParameter("re_step"));
	  System.out.println("request.getParameter(\"re_level\")=>"+request.getParameter("re_level"));
	  
	  article.setPost_num(Integer.parseInt(request.getParameter("post_num")));
	  article.setMem_id(request.getParameter("mem_id"));
	  article.setAdmin_id(request.getParameter("admin_id"));
	  article.setPost_title(request.getParameter("post_title"));	  
	  article.setPost_cnt(request.getParameter("post_cnt")); //글내용
	  article.setPost_date(new Timestamp(System.currentTimeMillis()));
	  //article.setPost_view(Integer.parseInt(request.getParameter("post_view")));
	  article.setRef(Integer.parseInt(request.getParameter("ref")));
	  article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
	  article.setRe_level(Integer.parseInt(request.getParameter("re_level")));
	  article.setReply_cnt(request.getParameter("reply_cnt"));
	  article.setReply_date(new Timestamp(System.currentTimeMillis()));
	    
	  
	  //DTO저장 끝
	  QNAboardDAO dbPro=new QNAboardDAO();
	  dbPro.insertArticle(article);
	  System.out.println("dbpro 호출");
	  
	return "/QNAwritepro.jsp";   
	  
	}

}