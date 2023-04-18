package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QNAboardDAO;
import model.QNAboardDTO;

public class QNAUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		//1. qnapost3.jsp에서 수정할 때 
		int post_num=Integer.parseInt(request.getParameter("post_num"));
		String pageNum=request.getParameter("pageNum");
		System.out.println("updateFormAction의 post_num="+post_num+", pageNum="+pageNum);
		QNAboardDAO dbPro=new QNAboardDAO(); //메서드 호출 목적
		QNAboardDTO article=dbPro.updateGetArticle(post_num);
		//2. 서버의 메모리에 저장(공유)
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);
		
		return "/QNAupdateform.jsp";
	}

}
