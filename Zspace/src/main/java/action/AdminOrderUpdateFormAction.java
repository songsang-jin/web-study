package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.order_checkDAO;

public class AdminOrderUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		
		
		String order_sta = request.getParameter("order_sta");
		String order_prcs = request.getParameter("order_prcs");

		
		order_checkDAO dbPro = new order_checkDAO();
		
		
		request.setAttribute("order_sta",order_sta);
		request.setAttribute("order_prcs",order_prcs);
		
		
		return "/admin_page.jsp";
	}

}
