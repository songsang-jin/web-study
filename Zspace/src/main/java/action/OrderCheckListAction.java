package action;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import model.exchangeDAO;
import model.OrderCheckDAO;
import model.OrderCheckDTO;
//요청명령어에 해당되는 명령어 처리클래스=액션클래스=컨트롤러클래스
public class OrderCheckListAction implements CommandAction {
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {	 
		request.setCharacterEncoding("utf-8");//언어설정
	  //HttpSession session = request.getSession();
		String mem_id=request.getParameter("mem_id");
		String pageNum=request.getParameter("pageNum");	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = request.getParameter("startDate");
		String endDate= request.getParameter("endDate");
		//String mem_id = "test1";
	 System.out.println("OrderCheckListAction의 매개변수 확인");
	 System.out.println(",startDate=>"+startDate+", endDate=>"+endDate);
	 
	 OrderCheckDAO ocdao=new OrderCheckDAO();
	 List<OrderCheckDTO> orderList = new ArrayList<OrderCheckDTO>();	 	 

	 orderList = ocdao.orderCheck(mem_id);

//2.처리한 결과를 공유(서버메모리에 저장)->이동할 페이지에 공유해서 사용(request)
	 	request.setAttribute("orderList", orderList);//추가할데이터 들어간곳
		request.setAttribute("startDate",startDate);		
		request.setAttribute("endDate",endDate); 
		request.setAttribute("mem_id",mem_id);
		System.out.println("값나오게 하고싶어서"+mem_id);
		System.out.println("값나오게 하고싶어서"+orderList);

		return "/orderListAll.jsp";//컨트롤러가 이동시키면서 공유시켜준다.
	}
}
