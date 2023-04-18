package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.REVIEWboardDAO;
import model.REVIEWboardDTO;

public class REVIEWUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int post_num = Integer.parseInt(request.getParameter("post_num"));
		String pageNum = request.getParameter("pageNum");
	
		REVIEWboardDAO bdPro = new REVIEWboardDAO(); 
		REVIEWboardDTO article = bdPro.getArticle(post_num);
		
		

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);

		
		
		
		return "/REVIEWupdateform.jsp";
	}

}
