package action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.REVIEWboardDAO;
import action.*;

import java.util.*;//List


public class REVIEWListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
	     String pageNum=request.getParameter("pageNum");	
		 String search=request.getParameter("search");
		 String searchtext=request.getParameter("searchtext");

		 int count=0; 

	 
	  List articleList=null; 
	  
	  REVIEWboardDAO dbPro=new REVIEWboardDAO();
	  count=dbPro.getArticleSearchCount(search,searchtext); 
	  Hashtable<String,Integer> pgList=dbPro.pageList(pageNum, count);
		 
		
	  if (count > 0){
		  articleList=dbPro.getBoardArticles(pgList.get("startRow"),
				                                            pgList.get("endRow"),
				                                            search,searchtext);
	  }else {
		  articleList=Collections.EMPTY_LIST;
	  }


	  

		request.setAttribute("search", search);
		request.setAttribute("searchtext", searchtext);
		request.setAttribute("pgList", pgList);
		request.setAttribute("articleList", articleList);
		
		


		return "/REVIEWpost.jsp";
		
	}

}
