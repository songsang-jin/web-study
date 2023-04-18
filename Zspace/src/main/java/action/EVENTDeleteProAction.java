package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EVENTboardDAO;

public class EVENTDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub

	  String pageNum=request.getParameter("pageNum");
	  int post_num=Integer.parseInt(request.getParameter("post_num"));
	  System.out.println("DeleteProAction에서의 pageNum="+pageNum+", post_num="+post_num);
	
	  EVENTboardDAO dbPro=new EVENTboardDAO();
	  int check=dbPro.deleteArticle(post_num); //1:성공 / 0: 실패
	  
	  //2개의 공유값이 필요
	  request.setAttribute("pageNum", pageNum);//삭제 페이지로 이동
	  request.setAttribute("check", check); //데이터 삭제 성공 유무
	  
	  return "/EVENTdeletepro.jsp";
	}

}