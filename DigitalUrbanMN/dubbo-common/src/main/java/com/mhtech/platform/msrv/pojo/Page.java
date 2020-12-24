package com.mhtech.platform.msrv.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Page
 * @Description TODO 分页类
 * @Author admini
 * @Date 2019/8/2 15:46
 * @Version 1.0
 */
@ApiModel(value = "页码信息")
public class Page<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "每页条数", example = "10")
	private int pageSize =10; //每页显示条数
	@ApiModelProperty(value = "总条数", example = "10000")
    private int totalCount; //总条数
	@ApiModelProperty(value = "起始位置", example = "15")
    private int start; //开始条数
	@ApiModelProperty(value = "当前页码", example = "10")
    private int pageNo;//当前页
	@ApiModelProperty(value = "总页数", example = "2000")
    private int totalPages; //总页数
	@ApiModelProperty(value = "数据集")
    private List<T> data;//数据


    @Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", totalCount=" + totalCount + ", start=" + start + ", pageNo=" + pageNo
				+ ", totalPages=" + totalPages + ", data=" + data + "]";
	}

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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
	
    public Page(int pageSize, int totalCount, int start, int pageNo, int totalPages, List<T> data) {
		super();
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.start = start;
		this.pageNo = pageNo;
		this.totalPages = totalPages;
		this.data = data;
	}

	public Page() {
		super();
	}

}
