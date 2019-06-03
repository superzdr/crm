package cn.wolfcode.crm.query;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageResult<T> {
	private List<T> data;
	private int currentPage;
	private int pageSize;
	private int totalCount;
	
	private int totalPage;
	private int prevPage;
	private int nextPage;
	
	public PageResult(List<T> data, int currentPage, int pageSize, int totalCount) {
		this.data = data;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		
		this.totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize :  this.totalCount / this.pageSize + 1;
		this.prevPage = this.currentPage - 1 >= 1 ? this.currentPage - 1 : 1;
		this.nextPage = this.currentPage + 1 <= this.totalPage ? this.currentPage + 1 : this.totalPage;
	}

	public PageResult(int currentPage,int pageSize){
		this(Collections.EMPTY_LIST,currentPage,pageSize,0);
	}
}
