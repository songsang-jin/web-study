package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.mem_updateDAO;

public class Mem_deleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
	String mem_id=(String)(request.getParameter("mem_id"));
	
	mem_updateDAO dbPro=new mem_updateDAO();
	int check=dbPro.deleteMemState(mem_id);
	
	request.setAttribute("mem_id", mem_id);
	request.setAttribute("check", check);
	
	session.invalidate();
	PrintWriter out = response.getWriter();
	
	return "/mem_delpro.jsp";
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
