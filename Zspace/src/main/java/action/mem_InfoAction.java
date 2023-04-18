package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.memberDAO;
import model.memberDTO;

public class mem_InfoAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String mem_id = request.getParameter("mem_id");
		
		memberDAO dbPro =new memberDAO();
		memberDTO article = dbPro.getArticle(mem_id);
		
		request.setAttribute("article", article);
		
		return "/mem_info.jsp";
	}

}
