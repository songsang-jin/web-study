package action;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.REVIEWboardDAO;
import model.REVIEWboardDTO;

public class REVIEWContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		

		int post_num=Integer.parseInt(request.getParameter("post_num"));
		String pageNum=request.getParameter("pageNum");
		
		
		REVIEWboardDAO dbPro=new REVIEWboardDAO();
		REVIEWboardDTO article=dbPro.getArticle(post_num);
		
		
		
		request.setAttribute("post_num",post_num);
		request.setAttribute("pageNum",pageNum);
		request.setAttribute("article",article);
		
		
		

		REVIEWboardDTO beforeArticle = dbPro.getBeforeArticle(post_num);;
		REVIEWboardDTO afterArticle = dbPro.getAfterArticle(post_num);

		request.setAttribute("beforeArticle",beforeArticle);
		request.setAttribute("afterArticle",afterArticle);
		
		
		return "/REVIEWpostview.jsp" ;
	}
	

}
