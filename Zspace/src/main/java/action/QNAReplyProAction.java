package action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QNAboardDAO;
import model.QNAreplyDTO;

public class QNAReplyProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub

        request.setCharacterEncoding("utf-8");
		int reply_num=Integer.parseInt(request.getParameter("reply_num"));
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		String reply_cnt = request.getParameter("reply_cnt");
		String admin_id = request.getParameter("admin_id");
		System.out.println("오류 확인");
		
		//CommentForm commentForm=(CommentForm)form;
		QNAreplyDTO redata = new QNAreplyDTO();
		redata.setReply_num(Integer.parseInt(request.getParameter("reply_num")));
		redata.setAdmin_id(request.getParameter("admin_id"));
		redata.setReply_cnt(request.getParameter( "reply_cnt"));
		redata.setReply_date(new Timestamp(System.currentTimeMillis()));				
		QNAboardDAO dbPro= new QNAboardDAO();
		dbPro.addReply(redata);
	System.out.println("넘어오는지 값 확인");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();  
		out.println("<script language='javascript'>");  
		out.println("location.href = \"/Zspace/content.do?reply_num="+reply_num+"&pageNum="+pageNum+"\";");  
		out.println("</script>");  

		return "/QNApostview.jsp";
	}
}
