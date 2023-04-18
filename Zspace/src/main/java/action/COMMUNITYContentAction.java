package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.COMMUNITYDAO;
import model.COMMUNITYDTO;
import model.COMMUNITYreplyDTO;

//요청을 받아서 처리해주는 클래스(액션 클래스) -> 실행결과 -> 컨트롤러 -> jsp
public class COMMUNITYContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub

		//1.content.jsp에서 처리한 자바코드
		//글 상세보기-> 게시판(상품의 정보를 자세히(SangDetail.jsp)
		//list.jsp에서 링크 : num, PageNum
		  int post_num=Integer.parseInt(request.getParameter("post_num"));
		  String pageNum=request.getParameter("pageNum");
		  System.out.println("ContentAction의 pageNum="+pageNum+", post_num="+post_num);
		  
		  COMMUNITYDAO dbPro=new COMMUNITYDAO();
		  COMMUNITYDTO article=dbPro.getArticle(post_num); //조회수 증가
		  
		  
		  
		  //링크 문자열의 길이를줄이기 위해서
		  System.out.println("content.do의 매개변수 확인");
	
		  //2. 처리한 결과를 서버의 메모리에 저장(request)-> jsp에서${키명}
		  request.setAttribute("post_num", post_num); //${num}을 주기 위해 key, value 똑같이 
		  request.setAttribute("pageNum", pageNum); // ${pageNum}
		  request.setAttribute("article",article);
		  
		  //댓글출력하기
		  List replyList=null;
		  
		  COMMUNITYDAO reply = new COMMUNITYDAO();
	      replyList = reply.replyDetail(post_num);
	      System.out.println("replyList 출력확인 : "+replyList);
	        
	      request.setAttribute("reply", replyList);
		 
			  return "/COMMUNITYview.jsp";
		  
	}
}
