package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;//삭제할 메서드를 호출
import action.*;
import java.io.*;
import java.util.Collections;
import java.util.List; 
import java.sql.Timestamp;

//실제로 삭제를 해주면서 deletePro.jsp
public class COMMUNITYReplyProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		//String mem_id="gest";
		

        request.setCharacterEncoding("utf-8");
		int post_num=Integer.parseInt(request.getParameter("post_num"));
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		String reply_cnt = request.getParameter("reply_cnt");
		String mem_id = request.getParameter("mem_id");

		//CommentForm commentForm=(CommentForm)form;
		COMMUNITYreplyDTO reply = new COMMUNITYreplyDTO();
		//PropertyUtils.copyProperties(comment,commentForm);
		//메서드 호출
		reply.setPost_num(Integer.parseInt(request.getParameter("post_num"))); //게시물 번호
		reply.setMem_id(request.getParameter("mem_id")); //작성자
		reply.setReply_cnt(request.getParameter("reply_cnt")); //댓글 내용
		reply.setReply_date(new Timestamp(System.currentTimeMillis())); //작성 날짜		
		COMMUNITYDAO manager= new COMMUNITYDAO();
		manager.addReply(reply);
	
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();  
		out.println("<script language='javascript'>");  
		out.println("location.href = \"/Zspace/content.do?post_num="+post_num+"&pageNum="+pageNum+"\";");  
		out.println("</script>");  

		return "/content.do";
	}
}
