package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.memberDAO;

public class idCheckAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String mem_id = request.getParameter("mem_id");
		System.out.println("idCheck_mem_id =>"+mem_id);
		
		memberDAO dbPro = new memberDAO();
		
		int chcek = dbPro.checkId(mem_id);
		System.out.println("idCheck_chcek =>"+chcek);
		
		request.setAttribute("check", chcek);
		request.setAttribute("mem_id", mem_id);
		
		return "/idCheck.jsp";
	}

}
