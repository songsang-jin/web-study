package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QNAboardDAO;
import model.QNAreplyDTO;

//요청을 받아서 처리해주는 클래스(액션 클래스) -> 실행결과 -> 컨트롤러 -> jsp
public class QNAReplyCntAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub

		//1.content.jsp에서 처리한 자바코드
		  int post_num=Integer.parseInt(request.getParameter("post_num"));
		  String pageNum=request.getParameter("pageNum");
		  //String post_title=request.getParameter("post_title");
		  System.out.println("ReplyCntAction의 pageNum="+pageNum+", post_num="+post_num);
		  
		  QNAboardDAO dbPro=new QNAboardDAO();
		 // QNAreplyDTO replyList=dbPro.replyDetail(post_num);
		  
		  //2. 처리한 결과를 서버의 메모리에 저장(request)-> jsp에서${키명}
		  request.setAttribute("post_num", post_num); //${num}을 주기 위해 key, value 똑같이 
		  request.setAttribute("pageNum", pageNum); // ${pageNum}
		  //request.setAttribute("post_title",post_title);
		//  request.setAttribute("replyList",replyList);
		  //ref,re_step, re_level값은 article에서 분리가 가능해서 생략
		  
		  return "/QNApostview.jsp";   
  	}
}
