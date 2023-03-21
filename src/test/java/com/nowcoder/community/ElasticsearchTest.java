package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.elasticsearch.DiscussPostRepository;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.service.ElasticsearchService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ElasticsearchTest {

   @Autowired
    private DiscussPostMapper discussPostMapper;
   @Autowired
    private DiscussPostRepository discussPostRepository;
   @Autowired
   private ElasticsearchRestTemplate  elasticsearchRestTemplate;

   @Autowired
   private ElasticsearchService elasticsearchService;

   @Test
   public void test(){
       System.out.println(elasticsearchService.getTotal("互联网"));
   }

   @Test
    public void testInsert(){
       discussPostRepository.save(discussPostMapper.selectDiscussPostById(241));
       discussPostRepository.save(discussPostMapper.selectDiscussPostById(242));
       discussPostRepository.save(discussPostMapper.selectDiscussPostById(243));
   }
    //增加
    @Test
    public void testInsertList(){
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(101,0,100,0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(102,0,100,0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(103,0,100,0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(111,0,100,0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(131,0,100,0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(132,0,100,0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(133,0,100,0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(134,0,100,0));
        discussPostRepository.saveAll(discussPostMapper.selectDiscussPost(138,0,100,0));
    }


    //修改
    @Test
    public void testUpdate(){
        DiscussPost post = discussPostMapper.selectDiscussPostById(231);
        post.setContent("我是新人，使劲灌水");
        discussPostRepository.save(post);
    }

    @Test
    public void testDelete(){
//       discussPostRepository.deleteById(231);
        discussPostRepository.deleteAll();
    }


    @Test
    public void testSearchByRepository(){
         NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery("互联网寒冬","title","content"))
                .withSorts(SortBuilders.fieldSort("type").order(SortOrder.DESC))
                .withSorts(SortBuilders.fieldSort("score").order(SortOrder.DESC))
                .withSorts(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                .withPageable(PageRequest.of(0,10))
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>"),
                        new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")
                ).build();
        SearchHits<DiscussPost> hits = elasticsearchRestTemplate.search(searchQuery, DiscussPost.class);
        List<DiscussPost> list = new ArrayList<>();
        for (SearchHit hit : hits) {
            DiscussPost post =(DiscussPost) hit.getContent();
            List titleField = hit.getHighlightField("title");
            if (!titleField.isEmpty()){
                post.setTitle((String) titleField.get(0));
//                System.out.println(titleField.get(0));
            }
            List contentField = hit.getHighlightField("content");
            if (!contentField.isEmpty()){
                post.setContent((String) contentField.get(0));
            }
            System.out.println(post);
            list.add(post);
        }
        System.out.println(hits.getTotalHits());


    }
}
