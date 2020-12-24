package com.mhtech.common.result.entity;

//import lombok.Data;

import java.util.List;

//import io.swagger.annotations.ApiModelProperty;

/**
 * @author deng
 * @date 2018/12/13
 */
//@Data
public class PageVO<T> {
	//@ApiModelProperty(value = "当前页数,从0开始计数",example="0", position=1)
    private Integer currentPage;
	//@ApiModelProperty(value = "总共页数",example="10", position=2)
    private Integer totalPage;
	/*@ApiModelProperty(value = "数据列表",position=0)*/
    private List<T> data;

    public PageVO(){
        super();
    }

    public PageVO(int currentPage, int totalPage, List<T> data) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.data = data;
    }
}

