package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.mem_updateDAO;
import model.mem_updateDTO;

public class Mem_resetProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		
	request.setCharacterEncoding("utf-8");
	mem_updateDTO article=new mem_updateDTO();
	
	article.setMem_id(request.getParameter("mem_id"));
	article.setMem_pwd("1234");	
	
	
	mem_updateDAO dbPro=new mem_updateDAO();
	int check=dbPro.resetMemState(article);
	article = dbPro.getArticle(request.getParameter("mem_id"));
	
	request.setAttribute("mem_id",request.getParameter("mem_id"));
	request.setAttribute("check",check);
	request.setAttribute("article",article);
	
	
	
	return "/mem_resetPro.jsp";
	
	
	
	}
	
}
