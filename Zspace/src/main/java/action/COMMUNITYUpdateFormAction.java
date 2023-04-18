package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.COMMUNITYDAO;
import model.COMMUNITYDTO;

public class COMMUNITYUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		//1. content.jsp : 글 수정 버튼 클릭하면 updateForm.do?num=3&page=1
		int post_num=Integer.parseInt(request.getParameter("post_num"));
		String pageNum=request.getParameter("pageNum");
		System.out.println("updateFormAction의 post_num="+pageNum);
		COMMUNITYDAO dbPro=new COMMUNITYDAO(); //메서드 호출 목적
		COMMUNITYDTO article=dbPro.updateGetArticle(post_num); //조회수 증가x
		
		//2. 서버의 메모리에 저장(공유)
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);
		
		
		return "/COMMUNITYupdateForm.jsp";
	}

}
