package action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.REVIEWboardDAO;
import model.REVIEWboardDTO;

public class REVIEWUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
			
		request.setCharacterEncoding("utf-8");

		String pageNum = "1";
        REVIEWboardDTO article=new REVIEWboardDTO();
	
		article.setPost_num(Integer.parseInt(request.getParameter("post_num")));
		article.setMem_id(request.getParameter("mem_id"));
		article.setPost_title(request.getParameter("post_title"));
		article.setPost_cnt(request.getParameter("post_cnt"));
		article.setRated(request.getParameter("rated"));
		
	    

		REVIEWboardDAO dbPro=new REVIEWboardDAO();
		int check=dbPro.updateArticle(article);
		
	
	
		request.setAttribute("post_num",request.getParameter("post_num"));
		request.setAttribute("pageNum",pageNum);
		request.setAttribute("check", check);
	
	     return "/REVIEWupdatepro.jsp";
	}

}
