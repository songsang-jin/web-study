package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.memberDAO;
import model.memberDTO;

public class mem_joinAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		memberDTO article = new memberDTO();
		
		article.setMem_id(request.getParameter("mem_id"));
		article.setMem_pwd(request.getParameter("mem_pwd"));
		article.setMem_name(request.getParameter("mem_name"));
		article.setMem_email(request.getParameter("mem_email"));
		article.setMem_phone(request.getParameter("mem_phone"));
		article.setMem_zipcode(request.getParameter("mem_zipcode"));
		article.setMem_addr1(request.getParameter("mem_addr1"));
		article.setMem_addr2(request.getParameter("mem_addr2"));
		
		
		memberDAO dbPro = new memberDAO();
		
		int check = dbPro.insert(article);
		System.out.println("join check=>"+check);
		
		request.setAttribute("check", check);
		return "/joinPro.jsp";
	}

}
