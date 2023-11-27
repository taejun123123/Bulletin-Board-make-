package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        //1. 예상 데이터
        Article a =new Article(1L,"안녕하세요","김밥아주머니");
        Article b =new Article(2L,"안녕하세요","초밥아저씨");
        Article c =new Article(3L,"안녕하세요","경찰아저씨");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        //2. 실제 데이터
        List<Article> articles = articleService.index();
        //3. 비교 및 검증
    assertEquals(expected.toString(),articles.toString());
    }

    @Test
    void show_성공() {
        //1.예상 데이터
        Long id = 1L;
        Article expected = new Article(id,"안녕하세요","김밥아주머니");
        //2.실제 데이터
        Article article = articleService.show(id);
        //3.비교 및 검증
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    void show_실패() {
        //1.예상 데이터
        Long id = -1L;
        Article expected = null;
        //2.실제 데이터
        Article article = articleService.show(id);
        //3.비교 및 검증
        assertEquals(expected,article);
    }
    @Transactional
    @Test
    void create_성공() {
        //1.예상 데이터
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null,title,content);
        Article expected = new Article(4L,title,content);
        //2.실제 데이터
        Article article = articleService.create(dto);
        //3.비교 및 검증
        assertEquals(expected.toString(),article.toString());
    }
    @Transactional
    @Test
    void create_실패() {
        //1.예상 데이터
        Long id =4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = null;
        //2.실제 데이터
        Article article = articleService.create(dto);
        //3.비교 및 검증
        assertEquals(expected,article);
    }
}