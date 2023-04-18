package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.mem_updateDAO;
import model.mem_updateDTO;

public class Mem_updateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
	request.setCharacterEncoding("utf-8");
	mem_updateDTO article=new mem_updateDTO();
	
	article.setMem_id(request.getParameter("mem_id"));
	article.setMem_pwd(request.getParameter("mem_pwd"));
	article.setMem_phone(request.getParameter("mem_phone"));
	article.setMem_email(request.getParameter("mem_email"));
	article.setMem_zipcode(request.getParameter("mem_zipcode"));
	article.setMem_addr1(request.getParameter("mem_addr1"));
	article.setMem_addr2(request.getParameter("mem_addr2"));
	
	
	mem_updateDAO dbPro=new mem_updateDAO();
	int check=dbPro.updateMemState(article);
	
	request.setAttribute("mem_id",request.getParameter("mem_id"));
	request.setAttribute("check",check);
	request.setAttribute("article",article);
	
	
	
	
	return "/mem_updatePro.jsp";
	
	
	
	
	}
	
}
