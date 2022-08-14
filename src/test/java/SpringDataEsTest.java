import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gao.es.entity.Article;
import com.gao.es.repository.ArticleRepository;
import com.gao.es.util.ServiceHelper;

/**
 * @author ：gaozhiqis
 * @date ：2022/8/13 19:45
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringDataEsTest {
    @Autowired
    private ArticleRepository     articleRepository;
    @Autowired
    private ElasticsearchTemplate template;

    @Test
    public void createIndex() throws Exception {
        //创建索引，并配置映射关系
        template.createIndex(Article.class);
    }

    /**
     * 添加文档
     */
    @Test
    public void addArticle() {
        Article article = new Article(1l, "资料的伙伴，Java资料免费领取！ 还可领取一份Java学习", "频、代码、PPT以及深度学习书籍 ！你想要的里面都");
        articleRepository.save(article);
        Article article2 = new Article(2l, "资料的伙伴，Java资料免费领取！ 还可领取一份Java学习2222", "频、代码、PPT以及深度学习书籍 ！你想要的里面都222");
        articleRepository.save(article2);
        Article article3 = new Article(3l, "资料的伙伴，Java资料免费领取！ 还可领取一份Java学习333", "频、代码、PPT以及深度学习书籍 ！你想要的里面都333");
        articleRepository.save(article3);
    }

    /**
     * 删除文档
     */
    @Test
    public void deleteArticleById() {
        articleRepository.deleteById(1l);
    }

    /**
     * 删除所有文档
     */
    @Test
    public void deleteAllArticle() {
        articleRepository.deleteAll();
    }

    /**
     * 批量添加文档
     */
    @Test
    public void batchAddArticle() {
        List<Article> list = new ArrayList<Article>();
        Article article = new Article(1l, "资料的伙伴，Java资料免费领取！ 还可领取一份Java学习", "频、代码、PPT以及深度学习书籍 ！你想要的里面都");
        Article article2 = new Article(2l, "资料的伙伴，Java资料免费领取！ 还可领取一份Java学习2222", "频、代码、PPT以及深度学习书籍 ！你想要的里面都222");
        Article article3 = new Article(3l, "资料的伙伴，Java资料免费领取！ 还可领取一份Java学习333", "频、代码、PPT以及深度学习书籍 ！你想要的里面都333");
        list.add(article);
        list.add(article2);
        list.add(article3);
        articleRepository.saveAll(ServiceHelper.listConvertIterable(list));
    }

    /**
     * 根据id查询
     */
    @Test
    public void selectById() {
        Optional<Article> optional = articleRepository.findById(1l);
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
    }

    /**
     * 查询所有
     */
    @Test
    public void selectAll() {
        Iterator<Article> iterator = articleRepository.findAll().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 原生查询
     */
    @Test
    public void nativeSearch() {
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("免费领取").defaultField("title"))
                .withPageable(PageRequest.of(0, 10)).build();
        List<Article> list = template.queryForList(query, Article.class);
        list.forEach(v -> System.out.println(v));
    }
}
