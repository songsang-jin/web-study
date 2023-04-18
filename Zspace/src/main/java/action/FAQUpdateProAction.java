package action;

import java.sql.Timestamp;//추가할 부분(시간)

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.FAQboardDAO;
import model.FAQboardDTO;

// /writePro.do
public class FAQUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		     request.setCharacterEncoding("utf-8");
		     HttpSession session = request.getSession();
		     int post_num=Integer.parseInt(request.getParameter("post_num"));
		     String pageNum=request.getParameter("pageNum");
		     System.out.println("UpdateProAction에서의 pageNum=>"+pageNum);//0
		     System.out.println("UpdateProAction에서의 post_num=>"+post_num);
		     
		     FAQboardDTO article = new FAQboardDTO();
		     article.setPost_num(Integer.parseInt(request.getParameter("post_num")));
		     article.setPost_view(Integer.parseInt(request.getParameter("post_view")));
		     article.setAdmin_id((String)session.getAttribute("mem_id"));
		     article.setPost_title(request.getParameter("post_title"));
		     article.setPost_cnt(request.getParameter("post_cnt"));
		     article.setPost_date(Timestamp.valueOf(request.getParameter("post_date"))); 
		     
		    FAQboardDAO dbPro=new FAQboardDAO();
		    int check=dbPro.updateArticle(article);
		   
		    System.out.println("=====================pageNum"+pageNum);
		    request.setAttribute("post_num", post_num);
		    request.setAttribute("pageNum", pageNum);//수정할 페이지로 이동
		    request.setAttribute("check", check);//데이터 수정성공유무
		    
		return "/FAQupdatepro.jsp";
	}
}
