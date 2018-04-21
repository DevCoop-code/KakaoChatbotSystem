package com.kakaochatbot.domain;

public class PageMaker 
{
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int pageNum;
	
	private int displayPageNum = 10;
	
	public void setTotalCount(int totalCount, int pageNum)
	{
		this.totalCount = totalCount;
		this.pageNum = pageNum;
		
		calcData(pageNum);
	}
	
	private void calcData(int pageNum)
	{
		endPage = (int)(Math.ceil(pageNum / (double)displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		int tempEndPage = (int)(Math.ceil(totalCount / (double)10.0));
		
		if(endPage > tempEndPage)
		{
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		next = endPage * 10 >= totalCount ? false : true;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}
