package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrderPayDAO;
import model.OrderPayDTO;

public class OrderPayAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		//int item_num=0;
		int pay_num=0; 
		int btn= Integer.parseInt(request.getParameter("btn"));
		System.out.println("OrderPayAction의 btn "+btn);//1
		
		//System.out.println("OrderPayListAction의 전달된 pay_num=>"+pay_num);
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		String mem_id = request.getParameter("mem_id"); //회원아이디
		System.out.println("OrderPayAction의 mem_id : "+mem_id);//1
		

		// insert
		OrderPayDAO orderpay = new OrderPayDAO();
		
	//	if(item_num==20) {
			
	//	}else {
			List<OrderPayDTO> orderList = orderpay.getOrderMemList( mem_id);
		//}
//		for (int i=0;i<orderList.size(); i++) {
//			//OrderPayDTO order=orderList.get(i);
//				pay_num = order_date + i;
//		}
		System.out.println("orderlist size =====: "+ orderList.size());

		request.setAttribute("pay_num",pay_num);
		request.setAttribute("orderList",orderList);
		request.setAttribute("orderpay",orderpay);
		request.setAttribute("mem_id",mem_id);

		request.setAttribute("btn",btn);

		//3.공유해서 이동할 수 있도록 페이지를 지정
		  return "/OrderPay.jsp";

	}

}
