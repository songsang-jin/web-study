package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.memberDAO;

public class PwdRestProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String mem_id = request.getParameter("mem_id");
		String mem_pwd = "1234567";
		
		memberDAO dbPro = new memberDAO();
		
		int check = dbPro.pwdReset(mem_id,mem_pwd);
		
		request.setAttribute("check", check);
		
		return "/pwdReset_Pro.jsp";
	}

}
