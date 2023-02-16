package page;

import java.util.ArrayList;
import java.util.List;

public class Paging {
	
	private Object page; // 방명록 (가장 상위인 Object로 만들어서 VisitDTO도 사용할 수 있게끔 한듯?
	private List<Integer> pageList; //페이징번호
	private int currentPage; // 현재페이지
	private int lastPage; // 마지막페이지
	private int pageLimit = 10;// 페이지보여지는 갯수(초기화 10)
	private int listLimit = 5; //페이징번호 보여지는 갯수(초기화 5)
	
	public Paging(Object page, int lastPage) {
		this.page = page;
		this.lastPage = lastPage;
		setPageList();
	}
	
	public Paging(Object page, int currentPage, int lastPage) {
		this(page, lastPage);
		this.currentPage = currentPage;
	}
	
	public Paging(Object page, int currentPage, int lastPage, int pageLimit, int listLimit) {
		this(page, lastPage);
		this.currentPage = currentPage;
		this.pageLimit = pageLimit;
		this.listLimit = listLimit;
	}
	
	public Object getPage() {
		return page;
	}
	
	public List<Integer> getPageList() {
		int start = 0;
		int end = listLimit;
		if(currentPage > (listLimit / 2) + 1) {
			start = currentPage - (listLimit / 2 + 1);
			end = currentPage + (listLimit / 2) - (listLimit % 2 == 0 ? 1 : 0);
		}
		if(end > lastPage) {
			end = lastPage;
		}
		return pageList.subList(start, end);
	}
	
	private void setPageList() {
		pageList = new ArrayList<Integer>();
		for(int i = 1; i <= lastPage; i++) {
			pageList.add(i);
		}
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public int getPrevPage() {
		return currentPage == 1 ? -1 : currentPage - 1;
	}
	
	public int getNextPage() {
		return currentPage == lastPage ? -1 : currentPage + 1;
	}
	
	public int getLastPage() {
		return lastPage;
	}
	
	public int getPageLimit() {
		return pageLimit;
	}
	
	public int getListLimit() {
		return listLimit;
	}
}
