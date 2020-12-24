package com.mhtech.platform.msrv.auth.elasticsearch;

import java.util.List;

import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.mhtech.platform.msrv.auth.elasticsearch.domain.ESEntity;

@Component
public class ElasticSearchClient {
	
	/**
	 * 新增或修改文档，根据esId判断
	 * @param entities
	 * @return
	 */
	public <T extends ESEntity> String addOrUpdateDocuments(List<? extends ESEntity> entities) {
		if(CollectionUtils.isEmpty(entities)) {
			return null;
		}
		int defaultIdx = 0;
		ESEntity bean = entities.get(defaultIdx);
		return addOrUpdateDocuments(bean.getIndexName(), bean.getIndexType(), entities);
	}
	
	private String addOrUpdateDocuments(String indexName, String indexType, List<? extends ESEntity> entities) {
		ClientInterface client = ElasticSearchHelper.getRestClientUtil();
		return client.addDocuments(indexName, indexType, entities);
	}
	
	/**
	 * 删除文档
	 * @param indexName
	 * @param indexType
	 * @param esIds
	 * @return
	 */
	public String deleteDocuments(String indexName, String indexType, String... esIds) {
		if(esIds == null || esIds.length < 1) return null;
		ClientInterface client = ElasticSearchHelper.getRestClientUtil();
		return client.deleteDocuments(indexName, indexType, esIds);
	}
}
