package action;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

//list.jsp에서 작업한 자바코드 : ListAction처리 => 컨트롤러를거쳐 /list.jsp에 전해줌
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.QNAboardDAO;

public class QNAListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub

	  String pageNum=request.getParameter("pageNum");
	  String search=request.getParameter("search");
	  String searchtext=request.getParameter("searchtext");
	  System.out.println("ListAction의 매개변수 확인");
	  System.out.println("pageNum="+pageNum+", search="+search+", searchtext="+searchtext);

	  int count=0; //총 레코드 수
	  List articleList=null; //화면에 출력할 레코드 저장
	   
	  QNAboardDAO dbPro=new QNAboardDAO();
	  count=dbPro.getArticleSearchCount(search, searchtext);
	  System.out.println("ListAction의 현재 레코드 수(count)="+count);
	  
	  //                                      1) 화면에 출력할 페이지 번호, 2)출력할 레코드 개수 
	  Hashtable<String, Integer> pgList=dbPro.pageList(pageNum, count);
	  
	  if(count>0) { //레코드가 하나라도 있다면
		  System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
		  articleList=dbPro.getBoardArticles(pgList.get("startRow"), //첫번째 레코드 번호 
				  							 pgList.get("endRow"), //불러올 갯수
				                             search, searchtext); //검색분야, 검색어
		 System.out.println("ListAction의 articleList="+articleList);
	  }else { //count=0
		  articleList=Collections.EMPTY_LIST; //비어있는 List객체 반환
	  }
	//2. 처리한 결과를 공유(서버메모리에 저장)-> 이동할 페이지에 공유해서 사용  
	  request.setAttribute("search", search); //검색 분야
	  request.setAttribute("searchtext", searchtext); //검색어
	  request.setAttribute("pgList", pgList); //페이징 처리 10개가 들어있어용
	  request.setAttribute("articleList", articleList); //${articleList}
	
	//3.공유해서 이동할 수 있도록 페이지를 지정
	 return "/QNApost.jsp"; //컨트롤러가 view를 받아서 이동시키면서 공유O
	}
}
