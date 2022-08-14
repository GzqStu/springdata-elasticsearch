package com.gao.es.repository;

import com.gao.es.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface ArticleRepository extends ElasticsearchCrudRepository<Article, Long> {
}
