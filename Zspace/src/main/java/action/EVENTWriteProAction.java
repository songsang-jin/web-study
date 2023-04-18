package action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EVENTboardDAO;
import model.EVENTboardDTO;

public class EVENTWriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		/*
		 * int post_type=Integer.parseInt(request.getParameter("post_type"));
		 * System.out.println("WriteProAction의 post_type: "+post_type);
		 */
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		EVENTboardDTO article = new EVENTboardDTO();

		article.setPost_num(Integer.parseInt(request.getParameter("post_num")));
		/*
		 * article.setItem_num(Integer.parseInt(request.getParameter("item_num")));
		 * article.setPost_view(Integer.parseInt(request.getParameter("post_view")));
		 * article.setCount(Integer.parseInt(request.getParameter("count")));
		 */
		 article.setAdmin_id((String)session.getAttribute("mem_id"));
		article.setPost_title(request.getParameter("post_title"));
		article.setPost_cnt(request.getParameter("post_cnt")); // 글내용
		article.setPost_date(new Timestamp(System.currentTimeMillis()));

		EVENTboardDAO dbPro = new EVENTboardDAO();
		dbPro.insertArticle(article);
		/*
		 * if(post_type==1) { return "/WRITE/write"+post_type+"pro.jsp"; }else
		 * if(post_type==2) { return "/WRITE/write"+post_type+"pro.jsp"; }else
		 * if(post_type==3) { return "/WRITE/write"+post_type+"pro.jsp"; }else
		 * if(post_type==4) { return "/WRITE/write"+post_type+"pro.jsp"; }else
		 * if(post_type==5) { return "/WRITE/write"+post_type+"pro.jsp"; }else{ return
		 * "/WRITE/write"+post_type+"pro.jsp"; } }
		 */
		return "/EVENTwritepro.jsp";
	}
}