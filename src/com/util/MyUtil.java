package com.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

public class MyUtil {

	public int getPageCount(int numPerBlock, int dataCount) {
		
		int pageCount = 0;
		pageCount = dataCount/numPerBlock;
		
		if(dataCount % numPerBlock != 0){
			pageCount++;
		}
		
		return pageCount;
		
	}
	
	public String getPagingText(String searchText, int currentPage, int totalPage) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
	
		int numPerBlock = 3; //리스트밑에 나오는 페이지 번호 출력 갯수
		int currentPageSetup; //표시할 첫 페이지의 -1 해준값
		int page;
		
		StringBuffer sb = new StringBuffer();
		if(currentPage==0 || totalPage==0){ //데이터 없을경우
			return "";
		}
		
		//표시할 첫페이지에서 -1 한 값 
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		
		if(currentPage % numPerBlock == 0)
			currentPageSetup = currentPageSetup - numPerBlock;
		
		/*if(!searchValue.equals("")) {
				searchValue = URLEncoder.encode(searchValue,"UTF-8");
		}
		
		String searchText = "&searchKey=" + searchKey +"&searchValue=" + searchValue;*/
		
		
		
		//이전
		if(totalPage > numPerBlock && currentPageSetup > 0) {
			sb.append("<a href='/bbs?pageNum=" + currentPageSetup + searchText + "'>[이전]</a>&nbsp;");
		}
		
		page = currentPageSetup + 1;
		
		while(page <= totalPage && page <= (currentPageSetup + numPerBlock)) {
			
			if(page == currentPage) {
				sb.append("<font color='red'>" + page + "</font>&nbsp;");
			}else {
				sb.append("<a href='/bbs?pageNum=" + page + searchText + "'>" + page + "</a>&nbsp;");
			}
			
			page++;
			
		}
		
		//다음
		if(totalPage - currentPageSetup > numPerBlock) {
			sb.append("<a href='/bbs?pageNum=" + page + searchText + "'>[다음]</a>&nbsp;");
		}
		
		
		
		return sb.toString();
	}
	
	public static String getClientIP(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");
	    //System.out.println("> X-FORWARDED-FOR : " + ip);

	    if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	        //System.out.println("> Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	        //System.out.println(">  WL-Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	        //System.out.println("> HTTP_CLIENT_IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	        //System.out.println("> HTTP_X_FORWARDED_FOR : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getRemoteAddr();
	        //System.out.println("> getRemoteAddr : "+ip);
	    }
	    System.out.println("> Result : IP Address : "+ip);

	    return ip;
	}
	
	/*<a href="#" class="pg_icon first"><span>처음</span></a> 
	<a href="#" class="pg_icon before"><span>이전</span></a> 
	<a href="#" class="ac">1</a>
	<a href="#">2</a> 
	<a href="#">3</a> 
	<a href="#"	class="pg_icon next"><span>다음</span></a> 
	<a href="#" class="pg_icon last"><span>마지막</span></a>*/

	public String getPatientPagingText(int currentPage, int totalPage) throws UnsupportedEncodingException {
		
		int numPerBlock = 3; //리스트밑에 나오는 페이지 번호 출력 갯수
		int currentPageSetup; //표시할 첫 페이지의 -1 해준값
		int page;
		
		StringBuffer sb = new StringBuffer();
		if(currentPage==0 || totalPage==0){ //데이터 없을경우
			return "";
		}
		
		//표시할 첫페이지에서 -1 한 값 
		currentPageSetup = (currentPage/numPerBlock)*numPerBlock;
		
		if(currentPage % numPerBlock == 0)
			currentPageSetup = currentPageSetup - numPerBlock;
		
		/*//이전
		if(totalPage > numPerBlock && currentPageSetup > 0) {
			sb.append("<a href='javascript:search(1)' class='pg_icon first'><span>처음</span></a>&nbsp;");
		} else {
			sb.append("<a class='pg_icon first'><span>처음</span></a>&nbsp;");
		}*/
		
		sb.append("<a href='javascript:search(1)' class='pg_icon first'><span>처음</span></a>&nbsp;");
		
		//이전
		if(totalPage > numPerBlock && currentPageSetup > 0) {
			sb.append("<a href='javascript:search(" + currentPageSetup + ")' class='pg_icon before'><span>이전</span></a>&nbsp;");
		} else {
			sb.append("<a class='pg_icon before'><span>이전</span></a>&nbsp;");
		}
		
		page = currentPageSetup + 1;
		
		while(page <= totalPage && page <= (currentPageSetup + numPerBlock)) {
			
			if(page == currentPage) {
				sb.append("<a href='javascript:search(" + page + ")' class='ac'>" + page + "</a>&nbsp;");
			}else {
				sb.append("<a href='javascript:search(" + page + ")'>" + page + "</a>&nbsp;");
			}
			
			page++;
			
		}
		
		//다음
		if(totalPage - currentPageSetup > numPerBlock) {
			sb.append("<a href='javascript:search(" + page + ")' class='pg_icon next'><span>다음</span></a>&nbsp;");
		} else {
			sb.append("<a class='pg_icon next'><span>다음</span></a>&nbsp;");
		}
		
		/*if(totalPage > numPerBlock && currentPageSetup > 0) {
			sb.append("<a href='javascript:search(" + totalPage + ")' class='pg_icon last'><span>마지막</span></a>&nbsp;");
		} else {
			sb.append("<a class='pg_icon last'><span>마지막</span></a>&nbsp;");
		}*/
		
		sb.append("<a href='javascript:search(" + totalPage + ")' class='pg_icon last'><span>마지막</span></a>&nbsp;");
		
		return sb.toString();
	}
	
}
