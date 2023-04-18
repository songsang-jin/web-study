package action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrderPayDAO;
import model.OrderPayDTO;

public class OrderPayProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		//int pay_num = 0;
        OrderPayDTO order=new OrderPayDTO();
        List<OrderPayDTO> orderList = new ArrayList<OrderPayDTO>();

        System.out.println("pro가 시작됩니다.111"+order);
        //System.out.println("pro가 시작됩니다.222"+orderList);

	    order.setMem_id(request.getParameter("mem_id"));
        System.out.println("pro가 시작됩니다.");

	   order.setItem_su(Integer.parseInt(request.getParameter("item_su")));    
	   System.out.println(request.getParameter("item_su"));
	   order.setItem_num(Integer.parseInt(request.getParameter("item_num")));
        System.out.println(request.getParameter("item_num"));
	    order.setPay_amt(Integer.parseInt(request.getParameter("pay_amt")));

        System.out.println("====amt====: "+request.getParameter("pay_amt"));
	    order.setPay_meth(request.getParameter("pay_meth"));
	    order.setItem_img(request.getParameter("item_img"));
	    order.setItem_name(request.getParameter("item_name"));

	    System.out.println("pro야 잘 받았니?");

	   // order.setPay_num(Integer.parseInt(request.getParameter("pay_num")));
	    order.setPay_total(Integer.parseInt(request.getParameter("pay_total")));
	   // order.setOrder_date(String.valueOf(request.getParameter("order_date")));

	    System.out.println("pro야 잘 받았을까?222");

	    OrderPayDAO opPro=new OrderPayDAO();
		int check=opPro.insertOrder(order);
		int x=opPro.getOrderInsert(order);

		System.out.println("insertOrder=>"+check);
		System.out.println("getOrderInsert=>"+x);

		System.out.println("pro, 여기까지 왔니?");

		request.setAttribute("order",order);
	//	request.setAttribute("pay_num", pay_num);
		request.setAttribute("orderList",orderList);
		request.setAttribute("mem_id",request.getParameter("mem_id"));


		//3.공유해서 이동할 수 있도록 페이지를 지정
		  return "/OrderPaypro.jsp";

	}

}
