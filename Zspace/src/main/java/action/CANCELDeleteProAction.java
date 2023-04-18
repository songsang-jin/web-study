package action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CANCELboardDAO;

public class CANCELDeleteProAction implements CommandAction {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
      // TODO Auto-generated method stub
      
      //추가(삭제된 게시물로 페이지를 이동시키기 위해
      String pageNum=request.getParameter("pageNum");
   
      int post_num=Integer.parseInt(request.getParameter("post_num"));
      System.out.println("DeleteProAction에서의 pageNum="+pageNum+",num="+post_num);
       
      CANCELboardDAO dbPro=new CANCELboardDAO();
      int check=dbPro.deleteArticle(post_num);//1:성공 / 0: 실패
   

      //2개의 공유값이 필요
      request.setAttribute("pageNum",pageNum);
      request.setAttribute("check", check);
      

 
      
      return "/CANCELdeletePro.jsp";
   }

}