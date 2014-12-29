package test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<T extends Serializable> {
	private int pageIndex; // 页面索引号

	private int pageCount; // 页面总数

	private int pageSize = 10; // 页面内容条数大小默认10条内容

	private int allSize = 0; // 取出的总条数

	private int pageArraySize = 6; // 页面list的集合的大小，在页面中显示页面的表签数

	private int prePage = 0; // 默认上一页0

	private int nextPage = 0; // 默认下一页0

	private int currentPage = 1; // 默认当前页面0

	private List<T> pageList; // 页面中存放的取出对象list集合

	// 每个页面中存放的页号list集合
	private List<String> pageArray = new ArrayList<String>();

	// 默认构造函数
	public Page() {
	}

	public Page(int pageIndex, int pageSize, int allSize, List<T> pageList) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.allSize = allSize;
		this.pageList = pageList;
		this.currentPage = pageIndex + 1;
		init();
	}

	public void init() {
		// pageAarry 中开始页和中间页,结束页
		int startPageNum = 1;
		int endPageNum = 1;
		int temp = pageArraySize / 2;
		if (allSize > 0) {
			
			// 计算页面总数
			if (allSize % pageSize == 0) {
				pageCount = allSize / pageSize;
			} else {
				pageCount = allSize / pageSize + 1;
			}

			if (pageCount < pageArraySize) {
				endPageNum = pageCount;
			} else {
				if (currentPage <= pageArraySize - temp) {
					startPageNum = 1;
					endPageNum = pageArraySize;
				} else if ((pageArraySize - temp < currentPage)
						&& (currentPage <= pageCount - temp)) {
					if(pageArraySize % 2 == 0){
						startPageNum = currentPage - temp + 1;
					}else{
						startPageNum = currentPage - temp;
					}
					endPageNum = currentPage + temp;
				} else if (currentPage > pageCount - temp) {
					startPageNum = pageCount - (pageArraySize - 1);
					endPageNum = pageCount;
				}
			}
			//判断是否页面索引超出页面总数
			if(currentPage > pageCount){
				currentPage = pageCount;
				pageIndex = currentPage - 1;
			}
			if(pageIndex < 0){
				currentPage = 1;
				pageIndex = 0;
			}
			
			if (startPageNum <= 0) {
				startPageNum = 1;
			}
			if (endPageNum > pageCount) {
				endPageNum = pageCount;
			}

			// 设置上一页，下一页
			nextPage = currentPage + 1;
			prePage = currentPage - 1;
			if (prePage < 0) {
				prePage = 0;
			}
			if (nextPage >= pageCount) {
				nextPage = 0;
			}

			for (int i = startPageNum; i <= endPageNum; i++) {
				Integer num = new Integer(i);
				pageArray.add(num.toString());
			}
		}
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getAllSize() {
		return allSize;
	}

	public void setAllSize(int allSize) {
		this.allSize = allSize;
	}

	public int getPageArraySize() {
		return pageArraySize;
	}

	public void setPageArraySize(int pageArraySize) {
		this.pageArraySize = pageArraySize;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getPageList() {
		return pageList;
	}

	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}

	public List<String> getPageArray() {
		return pageArray;
	}

	public void setPageArray(List<String> pageArray) {
		this.pageArray = pageArray;
	}

}
