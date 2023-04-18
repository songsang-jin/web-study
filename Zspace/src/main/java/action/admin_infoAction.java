package action;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import model.memberDAO;

public class admin_infoAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		String admin_id = request.getParameter("admin_id");
		String pageNum = request.getParameter("pageNum");
		String search=request.getParameter("search");
		String searchtext=request.getParameter("searchtext");
		System.out.println("admin_info의 매개변수 확인");
		System.out.println("pageNum="+pageNum+", search="+search+", searchtext="+searchtext);
		
		 int count=0; //총 레코드 수
		  List articleList=null; //화면에 출력할 레코드 저장
		   
		  memberDAO dbPro=new memberDAO();
		  count=dbPro.getArticleSearchCount(search, searchtext);
		  System.out.println("memberAction의 현재 레코드 수(count)="+count);
		  
		  //                                      1) 화면에 출력할 페이지 번호, 2)출력할 레코드 개수 
		  Hashtable<String, Integer> pgList=dbPro.pageList(pageNum, count);
		  
		  if(count>0) { //레코드가 하나라도 있다면
			  System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
			  articleList=dbPro.getMemberArticles(pgList.get("startRow"), //첫번째 레코드 번호 
					  								pgList.get("endRow"), //불러올 갯수
					                             search, searchtext); //검색분야, 검색어
			 System.out.println("member 의 articleList="+articleList);
		  }else { //count=0
			  articleList=Collections.EMPTY_LIST; //비어있는 List객체 반환
		  }
		  HttpSession session = request.getSession();
			
		  System.out.println("pageNum="+pageNum+", search="+search+", searchtext="+searchtext);
		  
		//2. 처리한 결과를 공유(서버메모리에 저장)-> 이동할 페이지에 공유해서 사용  
		  request.setAttribute("search", search); //검색 분야
		  request.setAttribute("searchtext", searchtext); //검색어
		  request.setAttribute("pgList", pgList); //페이징 처리 10개가 들어있어용
		  request.setAttribute("articleList", articleList); //${articleList}
		  session.setAttribute("session_id", admin_id);
		
		return "/admin_info.jsp";
	}

}
