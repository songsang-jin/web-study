package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.order_checkDAO;
import model.order_checkDTO;

public class AdminOrderUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		
		request.setCharacterEncoding("utf-8");
		
		
		order_checkDTO order= new order_checkDTO();
		
			
		order.setOrder_sta(request.getParameter("order_sta"));
		order.setOrder_prcs(request.getParameter("order_prcs"));
		order.setOrder_num(request.getParameter("order_num"));
		
		
		order_checkDAO dbPro = new order_checkDAO();
		int check=dbPro.updateStatus(order);
		request.setAttribute("check",check);

		
		return "/adminorderupdatePro.jsp";
	}

}
