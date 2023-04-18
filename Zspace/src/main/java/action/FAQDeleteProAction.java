package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FAQboardDAO;

// /writePro.do
public class FAQDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		     int post_num= Integer.parseInt(request.getParameter("post_num"));
		     String pageNum=request.getParameter("pageNum");
		     System.out.println("DeleteProAction에서의 mem_id=>"+post_num+",mem_pwd=>"+pageNum);
		     //----------------------------------------------------
		     FAQboardDAO dbPro=new FAQboardDAO();
		    int check=dbPro.deleteArticle(post_num);
		   
		    request.setAttribute("post_num", post_num);
		    request.setAttribute("pageNum", pageNum);
		    request.setAttribute("check",check);
		    
		    return "/FAQdeletepro.jsp";
	}
}
