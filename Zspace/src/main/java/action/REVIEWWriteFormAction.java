package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class REVIEWWriteFormAction implements CommandAction {

   @Override
   public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
 
     int post_num=0;
     if(request.getParameter("post_num")!=null) {
	      post_num=Integer.parseInt(request.getParameter("post_num"));
     }
     
  
     request.setAttribute("post_num",post_num);


		return "/REVIEWwriteform.jsp";
   }
   

}