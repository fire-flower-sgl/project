package com.mobile.service;
import java.util.List;
import java.util.Map;

import com.mobile.model.CallRecords;
import com.mobile.model.Page;

public interface CallRecordsService {
    int add(CallRecords callRecords);//新增
    int delete(String id);//删除
    int update(CallRecords callRecords);//更新
    CallRecords findCallRecords(String id);//根据id查询
    Map<String,Object> findCallRecord(String id);
    List<Map<String, Object>> findCallRecordsList(Map<String,String> map);//条件查询
    Page findCallRecordsListPage(Map<String,String> map);//条件查询 分页
    int findCallRecordstPageCount(String sql);//分页 总条数
}
