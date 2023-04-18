package action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CANCELboardDAO;
import model.CANCELboardDTO;


public class CANCELWriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
	  request.setCharacterEncoding("utf-8");
	  
	  CANCELboardDTO article=new CANCELboardDTO();	  
	     //
	     System.out.println("request.getParameter(\"post_num\")=>"+request.getParameter("post_num"));
	     System.out.println("request.getParameter(\"ref\")=>"+request.getParameter("ref"));
	     System.out.println("request.getParameter(\"re_step\")=>"+request.getParameter("re_step"));
	     System.out.println("request.getParameter(\"re_level\")=>"+request.getParameter("re_level"));
	     System.out.println("request.getParameter(\"mem_id\")=>"+request.getParameter("mem_id"));
	     System.out.println("request.getParameter(\"admin_id\")=>"+request.getParameter("admin_id"));
	     System.out.println("request.getParameter(\"post_title\")=>"+request.getParameter("post_title"));
	     System.out.println("request.getParameter(\"post_cnt\")=>"+request.getParameter("post_cnt"));
	     //System.out.println("request.getParameter(\"post_view\")=>"+request.getParameter("post_view"));
	     //
	     article.setPost_num(Integer.parseInt(request.getParameter("post_num")));
	     article.setRef(Integer.parseInt(request.getParameter("ref")));
	     article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		 article.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		 
		 //article.setPost_type(Integer.parseInt(request.getParameter("post_type")));
		 /* article.setItem_num(Integer.parseInt(request.getParameter("item_num")));
		 * 
		 * article.setCount(Integer.parseInt(request.getParameter("count")));*/
	     article.setPost_date(new Timestamp(System.currentTimeMillis()));//오늘의 날짜저장
 		 article.setMem_id(request.getParameter("mem_id"));//abc
		 article.setAdmin_id(request.getParameter("admin_id"));//admin (신규글)
		 article.setPost_title(request.getParameter("post_title"));		 
		 article.setPost_cnt(request.getParameter("post_cnt")); //글내용
		 
		 /* article.setItem_img(request.getParameter("item_img"));
		 * article.setRated(request.getParameter("rated"));
		 * article.setPost_view(Integer.parseInt(request.getParameter("post_view")));
		 * article.setPost_date(Timestamp.valueOf(request.getParameter("post_date")));
		 * //
		 */
		/* //문제나오는 부분 답글을 달면 에러
		article.setRef(Integer.parseInt(request.getParameter("ref")));
		article.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		article.setRe_level(Integer.parseInt(request.getParameter("re_level")));*/
	  //DTO저장 끝
	  CANCELboardDAO dbPro=new CANCELboardDAO();
	  dbPro.insertArticle(article);
	  
	  return "/CANCELwritepro.jsp";
	}

}