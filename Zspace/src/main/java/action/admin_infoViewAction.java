package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.memberDAO;
import model.memberDTO;

public class admin_infoViewAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String mem_id = request.getParameter("mem_id");

		 memberDTO article=null; //화면에 출력할 레코드 저장
		 int count = 0;
		 memberDAO dbPro=new memberDAO();
		
		 count = dbPro.getCount(mem_id);
		 System.out.println("admin_infoView() =>"+count);
		
		 article=dbPro.getArticle(mem_id);
		
		request.setAttribute("article", article); //${articleList}
		
		return "/admin_infoView.jsp";
	}

}
