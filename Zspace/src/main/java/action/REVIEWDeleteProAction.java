package action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.REVIEWboardDAO;

public class REVIEWDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String pageNum=request.getParameter("pageNum");
		int post_num=Integer.parseInt(request.getParameter("post_num"));
       
		REVIEWboardDAO dbPro=new REVIEWboardDAO();
		int check=dbPro.deleteArticle(post_num);
	

		request.setAttribute("pageNum",pageNum);
		request.setAttribute("check", check);
		

	    int pageSize=10;
	    int blockSize=5;

	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");

	 if(pageNum==null){
		 pageNum="1";
	 }
	 int currentPage=Integer.parseInt(pageNum);              
	 int startRow=(currentPage-1)*pageSize+1;
	 int endRow=currentPage*pageSize;
	 int count=0; 
	 int number=0;
	  
	  List articleList=null; 
	  
	  dbPro=new REVIEWboardDAO();
	  count=dbPro.getArticleCount(); 
	  
	  if(count >0) {
		  articleList=dbPro.getArticles(startRow, pageSize);
	  }
	  number=count-(currentPage-1)*pageSize;
	  request.setCharacterEncoding("utf-8");
	 
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("number", number);
		request.setAttribute("articleList", articleList);
		
		
		return "/REVIEWdeletepro.jsp";
	}

}
