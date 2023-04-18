package action;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FAQboardDAO;
import model.FAQboardDTO;

public class FAQContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		 int post_num=Integer.parseInt(request.getParameter("post_num"));
		 //String  writer=request.getParameter("writer");
		 String pageNum=request.getParameter("pageNum");
		 
		 System.out.println("contentAction => "+post_num+","+pageNum);

		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		 FAQboardDAO dbPro=new FAQboardDAO();
		 FAQboardDTO article=dbPro.getArticle(post_num);
		 

		request.setAttribute("post_num", post_num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);
		
		FAQboardDTO beforeArticle = dbPro.getBeforeArticle(post_num);;
		FAQboardDTO afterArticle = dbPro.getAfterArticle(post_num);

		request.setAttribute("beforeArticle",beforeArticle);
		request.setAttribute("afterArticle",afterArticle);
	 	
			return "/FAQpostview.jsp";

	}

}
