package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.mem_updateDAO;
import model.mem_updateDTO;

public class Mem_updateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		
		
		String mem_id=(String)(request.getParameter("mem_id"));
	
		mem_updateDAO dbPro=new mem_updateDAO();
		mem_updateDTO article=dbPro.getArticle(mem_id);
		
		
		request.setAttribute("mem_id", mem_id);
		request.setAttribute("article", article);
		
		
		
		return "/mem_info.jsp";
	}
}
