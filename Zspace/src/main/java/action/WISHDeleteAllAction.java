package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

public class WISHDeleteAllAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String mem_id = request.getParameter("mem_id"); // 회원아이디
		System.out.println("mem_id:" + mem_id);

		ITEMDAO itemdao = new ITEMDAO();

		// delete all
		int check = itemdao.WISHDeleteAll(mem_id);
		System.out.println("wish delete all check : " + check);

		//한 회원에 대한 관심상품 조회
				List<WISHDTO> wishlist =itemdao.searchWish(mem_id);
			
				for(int i = 0; i < wishlist.size();i++) {		
					WISHDTO itemdto = wishlist.get(i);
				}

				System.out.println("wishlist size =====: "+ wishlist.size());

				request.setAttribute("wishlist",wishlist);
				request.setAttribute("mem_id",mem_id);
			  
			  
			//3.공유해서 이동할 수 있도록 페이지를 지정
			  return "/WISH.jsp"; //관심상품 페이지로 보내기
	}

}