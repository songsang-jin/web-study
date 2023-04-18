package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.*;
import model.*;
import java.io.*;

//실제로 삭제를 해주면서 deletePro.jsp
public class COMMUNITYReplyDelAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int post_num=Integer.parseInt(request.getParameter("post_num"));
		int reply_num=Integer.parseInt(request.getParameter("reply_num"));
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		
		COMMUNITYDAO manager= new COMMUNITYDAO();
		manager.deleteReply(reply_num);
	
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();  
		out.println("<script language='javascript'>");  
		out.println("alert('덧글이 삭제 되었습니다.');");  
		out.println("location.href = \"/Zspace/content.do?post_num="+post_num+"&pageNum="+pageNum+"\";");  
		out.println("</script>");  
		
		return "/content.do";
	}
}
