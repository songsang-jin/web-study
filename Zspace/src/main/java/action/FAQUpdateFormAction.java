package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FAQboardDAO;
import model.FAQboardDTO;

public class FAQUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int post_num=Integer.parseInt(request.getParameter("post_num"));
		   String pageNum=request.getParameter("pageNum");//페이지 번호
		   System.out.println("UpdateFormAction에서의 pageNum=>"+pageNum);//0
		   FAQboardDAO dbPro=new FAQboardDAO();//메서드 호출목적
		   FAQboardDTO article=dbPro.updateGetArticle(post_num);//조회수가 증가X
		  
		   request.setAttribute("post_num", post_num);
		  request.setAttribute("pageNum", pageNum);
		  request.setAttribute("article",article);
		
		return "/FAQupdateform.jsp";
	}
}
