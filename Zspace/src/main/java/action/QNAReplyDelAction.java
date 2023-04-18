package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QNAboardDAO;

public class QNAReplyDelAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int post_num=Integer.parseInt(request.getParameter("post_num"));
		int reply_num=Integer.parseInt(request.getParameter("reply_num"));
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		
		QNAboardDAO dbPro= new QNAboardDAO();
		dbPro.delReply(reply_num);
	
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();  
		out.println("<script language='javascript'>");  
		out.println("alert('댓글이 삭제 되었습니다.');");  
		out.println("location.href = \"/Zspace/content.do?post_num="+post_num+"&pageNum="+pageNum+"\";");  
		out.println("</script>");  
		
		return "/QNApostview.jsp";
	}
}
