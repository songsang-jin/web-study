package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.memberDAO;

public class admin_infoDelAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String mem_id = request.getParameter("mem_id");
		String mem_pwd = request.getParameter("mem_pwd");
		
		memberDAO dbPro = new memberDAO();
		
		int check = dbPro.memberDelete(mem_id,mem_pwd);
		
		request.setAttribute("mem_id", mem_id);
	    request.setAttribute("mem_pwd", mem_pwd);//수정할 페이지로 이동
	    request.setAttribute("check",check);
		
		return "/admin_info.do";
	}

}
