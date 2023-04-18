package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ITEMDAO;
import model.WISHDTO;

public class WISHDeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//String id = request.getParameter("id");  //마이페이지의 장바구니용 번호
		String mem_id = request.getParameter("mem_id"); //회원아이디
		
		System.out.println("mem_id : "+mem_id);
		
		int item_num = Integer.parseInt(request.getParameter("item_num")); //상품번호
		System.out.println("item_num : "+item_num);
		
		ITEMDAO itemdao = new ITEMDAO();
		
		// delete 
		int check = itemdao.WishDelete(item_num, mem_id);
		System.out.println("관심상품테이블데이터 delete 성공유무 : " + check);
		
		//한 회원에 대한 관심상품 조회
				List<WISHDTO> wishlist =itemdao.searchWish(mem_id);
			
				for(int i = 0; i < wishlist.size();i++) {		
					WISHDTO itemdto = wishlist.get(i);
				}
				
				System.out.println("wishlist size =====: "+ wishlist.size());

				request.setAttribute("wishlist",wishlist);
				request.setAttribute("mem_id",mem_id);
			  
		
		return "/WISH.jsp";
	}

}
