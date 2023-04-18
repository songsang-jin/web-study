package action;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

public class WISHListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
	  
		request.setCharacterEncoding("utf-8");
		String mem_id = request.getParameter("mem_id"); //회원아이디
		
		//System.out.println("WISHListAction의 id=>"+id);
		System.out.println("mem_id : "+mem_id);
		
		// insert
		int item_num = 0;
		ITEMDAO itemdao = new ITEMDAO();
		if(request.getParameter("item_num") != null) {
			item_num = Integer.parseInt(request.getParameter("item_num")); //상품번호
			System.out.println("WISHListAction의 전달된 item_num=>"+item_num);
			//관심상품테이블에 넣기
			int check = itemdao.getWishInsert(mem_id, item_num);
			System.out.println("관심상품테이블데이터 insert 성공유무 : " + check);//1
		}
		
	
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
