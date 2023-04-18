package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CARTDAO;
import model.CARTDTO;

public class CARTListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		// int id = Integer.parseInt(request.getParameter("id")); //마이페이지의 장바구니용 번호
		String mem_id = request.getParameter("mem_id"); //회원아이디
		//String mem_id = "test1"; // 로그인할때 나중에 교체하기

		// System.out.println("CARTlistAction의 id=>"+id);
		System.out.println("mem_id=>" + mem_id);

		// insert
		int item_num = 0;
		CARTDAO itemdao = new CARTDAO(); 
		HttpSession session = request.getSession();
		
		if (request.getParameter("item_num") != null) {
			item_num = Integer.parseInt(request.getParameter("item_num")); // 상품번호
			System.out.println("CartListAction의 전달된 item_num=>" + item_num);
			// 장바구니테이블에 넣기
			int check = itemdao.getCartInsert(mem_id, item_num);
			System.out.println("관심상품테이블데이터 insert 성공유무 : " + check);// 1
		}

		// 한 회원에 대한 목록 조회
		List<CARTDTO> cartlist = itemdao.searchCART(mem_id);

		for (int i = 0; i < cartlist.size(); i++) {
			CARTDTO itemdto = cartlist.get(i);
			
			System.out.println("cart size =====: " + cartlist.size());
		}
		
		session.setAttribute("total",cartlist);
		request.setAttribute("cartlist", cartlist);
		request.setAttribute("mem_id", mem_id);
		request.setAttribute("item_num", item_num);

		// 3.공유해서 이동할 수 있도록 페이지를 지정
		return "/cart_num.jsp"; // 장바구니
	}
}
