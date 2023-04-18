package action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.searchDAO;

public class SEARCHListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub

		String pageNum=request.getParameter("pageNum");
		String search=request.getParameter("search");
		String searchtext=request.getParameter("searchtext");
		System.out.println("itemlistAction의 매개변수 확인");
		System.out.println("search: "+search+",searchtext: "+searchtext);

		int count=0; //총 결과 수
		List itemList = new ArrayList(); //화면에 출력될 상품 저장

		searchDAO scPro=new searchDAO();
		count=scPro.ItemSearchCount(search, searchtext);
		Hashtable<String, Integer> pgList=scPro.pageList(pageNum, count);


		if(count>0) {
			System.out.println(pgList.get("startRow")+","+pgList.get("endRow"));
			itemList=scPro.ItemSearchList (pgList.get("startRow"), //첫번째 레코드 번호
						 				   pgList.get("endRow"),
						                   search, searchtext);
			System.out.println("SEARCHListAction의 itemList="+itemList);
		// }else { //count=0
		//	itemList=Collections.EMPTY_LIST;
		 }

		request.setAttribute("search",search);
		request.setAttribute("searchtext",searchtext);
		request.setAttribute("pgList", pgList);
		request.setAttribute("itemList",itemList);

		return "/Search.jsp";
	}

}
