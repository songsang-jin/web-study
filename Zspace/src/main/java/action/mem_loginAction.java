package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import model.memberDAO;
import model.memberDTO;

public class mem_loginAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String mem_id = request.getParameter("mem_id");
		String mem_pwd = request.getParameter("mem_pwd");
		String selecet = request.getParameter("selecet");
		
		memberDAO dbPro =new memberDAO();
		memberDTO article =null;
		
		if(selecet.equals("admin_login") ) {
			
			String admin_id = mem_id;
			String admin_pwd =mem_pwd;
			int check=dbPro.getAdminLogin(admin_id, admin_pwd);
			System.out.println("check =>"+check);
			if(check==1) {
				
				 article = dbPro.getArticle(admin_id);
				 request.setAttribute("article", article);
				 HttpSession session = request.getSession();
				 session.setAttribute("check", check);
				 session.setAttribute("mem_id", mem_id);
				 
				 return "/loginPro.jsp";
			}else {
				
				request.setAttribute("check",check);
			return "/loginPro.jsp";
			}
		}else {
		
			int check=dbPro.getLogin(mem_id, mem_pwd);
			System.out.println("check =>"+check);
			
			if(check==1) {
				
				 article = dbPro.getArticle(mem_id);
				 request.setAttribute("article", article);
				 HttpSession session = request.getSession();
				 session.setAttribute("check", check);
				 session.setAttribute("mem_id", mem_id);
				 
				 return "/loginPro.jsp";
			}else {
				request.setAttribute("check",check);
			return "/loginPro.jsp";
			}
		}
	}
}
