package com.strange.collaboration.article;

import com.strange.collaboration.sample.Sample;
import com.strange.collaboration.sample.SampleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    SampleService sampleService;

    @GetMapping({"/index"})
    @Operation(summary = "Get All Articles")
    public String getArticles(Model model) throws Exception {

        // 현재 가정한 샘플의 구체적인 속성은 글로서 articles이라는 속성명을 부여함
        model.addAttribute("articles",sampleService.findAllSample());

        return "article/articles";
    }

    @GetMapping({"/articles/{id}"})
    @Operation(summary = "Get ArticleDetail")
    public String getArticleDetail(Model model, @PathVariable Long id) throws Exception {
        sampleService.findSampleDetailById(id).ifPresent(sample -> model.addAttribute("article", sample));
        return "article/articleDetail";
    }

    @GetMapping("/article")
    @Operation(summary = "Get ArticleInsert for Posting Article")
    public String findArticleForm(Model model, Sample article) throws Exception {
        model.addAttribute("article", article);

        return "article/articleInsert";
    }

    @PostMapping("/article")
    @Operation(summary = "Post Article")
    public String addArticle(@ModelAttribute Sample article) throws Exception {
        log.info(article.toString());
        sampleService.addArticle(article);

        return "redirect:/articles";
    }

    @GetMapping("/article/{id}")
    @Operation(summary = "Get ArticleUpdate for Updating Article")
    public String findArticleUpdateForm(Model model, @PathVariable Long id) throws Exception {
        log.info(sampleService.findSampleDetailById(id).toString());
        sampleService.findSampleDetailById(id).ifPresent(sample -> model.addAttribute("article", sample));
        return "article/articleUpdate";
    }

    @PatchMapping("/article")
    @Operation(summary = "Patch ArticleDetail")
    public String updateArticleDetail(@ModelAttribute Sample article) throws Exception {
        log.info(article.toString());
        sampleService.updateArticle(article);

        return "redirect:/articles";
    }


    @DeleteMapping("/article/{id}")
    @Operation(summary = "Delete Article")
    public void deleteArticle(@PathVariable Long id) throws Exception {
        log.info(id.toString());
        sampleService.deleteArticleById(id);

    }
}
