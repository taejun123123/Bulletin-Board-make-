package com.example.firstproject.controller;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.entity.Article;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService; // 서비스 객체 주입
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){


        log.info(form.toString());
        //1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        log.info(article.toString());
        //2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }
    @GetMapping("/articles/{id}")
        public String show(@PathVariable Long id, Model model){
        log.info("id=" +id);
        //1.id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);
        //2.모델에 데이터 등록하기
        model.addAttribute("article",articleEntity);
        model.addAttribute("commentDtos", commentDtos);
        //3.뷰 페이지 반환하기
        return "articles/show";
        }
        @GetMapping("/articles")
        public String index(Model model){
        //1.모든 데이터 가져오기
            ArrayList<Article> articleEntityList =articleRepository.findAll();
            model.addAttribute("articleList",articleEntityList);
        return "articles/index";
        }
        @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article",articleEntity);
        return "articles/edit";
        }
        @PostMapping("articles/update")
    public String update(ArticleForm form){

        log.info(form.toString());
        //dto 엔티티로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        // 엔티티를 db에 저장
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if(target != null){
            articleRepository.save(articleEntity);
        }
        return "redirect:/articles/" + articleEntity.getId();
        }
        @GetMapping("articles/{id}/delete")
        public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니당");
        //1.삭제할 대상 가져오기
            Article target = articleRepository.findById(id).orElse(null);
            //2.대상 엔티티 삭제하기
            if(target != null){
                articleRepository.delete(target);
                rttr.addFlashAttribute("msg","삭제되었다.");
            }
            //3.결과 페이지로 리다이렉트하기
        return "redirect:/articles";
        }

}
