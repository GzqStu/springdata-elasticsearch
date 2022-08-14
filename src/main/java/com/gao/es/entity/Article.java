package com.gao.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author ：gaozhiqi
 * @date ：2022/8/13 19:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(indexName = "index_study",type = "article")
public class Article {
    @Id
    @Field(type= FieldType.Long,store = true)
    private Long id;
    @Field(type= FieldType.text,store = true,analyzer = "ik_smart")
    private String title;
    @Field(type= FieldType.text,store = true,analyzer = "ik_smart")
    private String content;
}
