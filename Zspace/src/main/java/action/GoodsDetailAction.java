package action;

import java.text.SimpleDateFormat;
//-----------------------------------------
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import action.*;

//요청을 받아서 처리해주는 클래스(액션 클래스) -> 실행결과 -> 컨트롤러 -> jsp
public class GoodsDetailAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub

		//1.상품의 정보를 자세히(goods_detail_page.jsp)
		//메인 페이지나 카테고리 페이지에서 링크 : item_num
		  int item_num=Integer.parseInt(request.getParameter("item_num"));
		  System.out.println("GoodsDetailAction에 전달된 item_num="+item_num);
		  
		  ITEMDAO dbPro=new ITEMDAO();
		  ITEMDTO goods=dbPro.getGoodsInfo(item_num); //썸네일 이미지 출력
		  System.out.println("goods=>"+goods);
		  
		  String item_img=""; //상품 이미지
		  //링크 문자열의 길이를줄이기 위해서
		  System.out.println("item.do의 매개변수 확인");  
	
		  //2. 처리한 결과를 서버의 메모리에 저장(request)-> jsp에서${키명}
		  request.setAttribute("item_num", item_num); //${item_num}을 주기 위해 key, value 똑같이 
		  request.setAttribute("item_img",item_img);
		  request.setAttribute("goods", goods);
		  
			  return "/goods_detail_page.jsp";
		  
	}
}
