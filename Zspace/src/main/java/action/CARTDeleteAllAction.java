package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CARTDAO;
import model.CARTDTO;

public class CARTDeleteAllAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String mem_id = request.getParameter("mem_id"); // 회원아이디
		System.out.println("mem_id:" + mem_id);

		CARTDAO itemdao = new CARTDAO();

		// delete all
		int check = itemdao.CARTDeleteAll(mem_id);
		System.out.println("cart delete all check : " + check);

		// 한 회원에 대한 장바구니 조회
		List<CARTDTO> cartlist = itemdao.searchCART(mem_id);

		for (int i = 0; i < cartlist.size(); i++) {
			CARTDTO itemdto = cartlist.get(i);
		}

		System.out.println("cartlist size =====: " + cartlist.size());

		request.setAttribute("cartlist", cartlist);
		request.setAttribute("mem_id", mem_id);

		return "/cart_num.jsp";
	}

}