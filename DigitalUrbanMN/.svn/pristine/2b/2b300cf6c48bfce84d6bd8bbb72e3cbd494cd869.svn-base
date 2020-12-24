package com.mobile.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Page
 * @Description TODO 分页类
 * @Author admini
 * @Date 2019/8/2 15:46
 * @Version 1.0
 */
public class Page implements Serializable{
    private int pageSize =10; //每页显示条数
    private int totalCount; //总条数
    private int start; //开始条数
    private int pageNo;//当前页
    private int totalPages; //总页数
    private List data;//数据1
    private List data1;//数据1
    private List data2;//数据1
    public Page(int totalCount){
        this.totalCount = totalCount;
    }

    /**
     * 获取中页数��
     * @return
     */
    public int getTotalPages() {
        totalPages = totalCount / pageSize;

        if(totalCount % pageSize != 0){
            totalPages++;
        }

        return totalPages;
    }
    /**
     * 设置当前页的开始条数
     * @param pageNo 页数��
     * @return
     */
    public int getStart(int pageNo){

        if(pageNo < 1){
            pageNo = 1;
        }
        else if(getTotalPages()>0&&pageNo > getTotalPages()){
            pageNo = getTotalPages();
        }

        start = (pageNo-1) * pageSize;
        return start;
    }

    //get and set
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public void setStart(int start) {
        this.start = start;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getStart() {
        return start;
    }

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public List getData1() {
		return data1;
	}

	public void setData1(List data1) {
		this.data1 = data1;
	}

	public List getData2() {
		return data2;
	}

	public void setData2(List data2) {
		this.data2 = data2;
	}


   
    
}
